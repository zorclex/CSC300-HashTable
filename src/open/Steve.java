package open;

enum NodeState{
	empty_since_start, //0
	empty_after_delete, //1
	regular; //2
}

public class Steve {
	public HashNode[] buckets;
	public int Size;
	public int Count;
	private final float Threashold = 0.7f;
	private final int initialSize = 10;
	
	public Steve() {
		this.buckets = new HashNode[initialSize];
		this.Size = initialSize;					//don't divide by zero, doesn't work too well
		this.Count = 0;
		for(int i = 0; i < buckets.length; i++) {
			buckets[i] = HashNode.emptySinceStartNode(i);
		}
	}
	
	private int GetHash(int key) {
		int hashValue = Integer.hashCode(key);
		if (hashValue < 0) {
			hashValue += 2147483648L;
		}
		return hashValue % this.Size;
	}
	
	//add
/*	public void HashInsert(int key, int value) throws Exception 
//	{
//		int hashCode = this.GetHash(key);
//		HashNode newNode = new HashNode(key, value, hashCode);
//		HashNode bucket = this.buckets[hashCode];
//		
//		if (bucket.State == NodeState.empty_since_start ||
//			bucket.State == NodeState.empty_after_delete) //if open slot found
//		{
//			this.buckets[hashCode] = newNode;
//			this.Count++;
//			this.Resize(); //resize if needed
//		} 
//		else 
//		{ //if not ess or ead then State must be regular, meaning occupied, and a new slot mush be found
//			int nextkey = hashCode; // search key?
//			do 
//			{
//				System.out.print("-"); //for testing
//				nextkey = (hashCode + 1) % this.Size; //throws exceptions and continues for ages
//				bucket = this.buckets[nextkey];
//			} while (bucket.State == NodeState.regular && nextkey != hashCode); //while occupied and not looped - seems a strangely-written condition
//			
//			
//			if (nextkey != hashCode) 
//			{
//				this.buckets[nextkey] = newNode;
//				this.Count++;
//				this.Resize(); //resize if needed
//			} 
//			else 
//			{
//				throw new Exception("The hash table is full.");
//			}
//		}
//	}
*/
	
	public void HashInsert(int key, int value) throws Exception 
	{ //but written by me this time because things were weird.
		
		//hash and locate position
		int hashCode = this.GetHash(key); 
		HashNode newNode = new HashNode(key, value, hashCode);
		HashNode bucket = this.buckets[hashCode];
		
		int nextKey = hashCode;
//		System.out.println("+++++++++bucket is currently (" + bucket + ", " + bucket.State + ", " + "" + "");
		
		for(int i = 0; i < this.Size; i++)
		{
//			System.out.println(this.Size + "< HashInsert loop #" + i  + " -nextKey of " + nextKey + " -hashCode of " + hashCode + " -value of " + value);
//			if(slot == hashCode)
//			if (bucket.State == NodeState.empty_after_delete ||
//				bucket.State == NodeState.empty_since_start) //if open slot found
			if (bucket.State != NodeState.regular)
			{
				this.buckets[nextKey] = newNode; //potentially wrong?
				this.Count++;
				this.Resize(); //resize if needed
				return;
			}
			else if (bucket.State == NodeState.regular)
			{
				nextKey = (nextKey + 1) % this.Size;
				bucket = this.buckets[nextKey];
			}
			else
			{
				throw new Exception("The hash table is full.");
			}
				
		}
		
	}
	
	//get
	public HashNode Get(int key) {
		//get hash code
		int hashCode = this.GetHash(key);
		//look up on the array
		HashNode curr = this.buckets[hashCode];
		int nextKey = hashCode;
		if (curr.State == NodeState.empty_since_start) {
			return null;
		} else if (curr.State == NodeState.empty_after_delete || 
				curr.Key !=key) {
			//we need to check the next opening address
			nextKey = (nextKey + 1) % this.Size;
			curr = this.buckets[nextKey];
			while (curr.State != NodeState.empty_since_start && nextKey != hashCode) {
				if (curr.State == NodeState.empty_after_delete || curr.Key != key) {
					nextKey = (nextKey + 1) % this.Size;
					curr = this.buckets[nextKey];
				} else {
					return curr;
				}
			}
			return null;
		} else {
			return curr;
		}
		//check the key and the key on the bucket
		//if not, look at the next position
		//keep on searching until we searched all the positions or
		//until we meet empty-since-start
	}
	
	//remove
	public void Remove(int key) {
		int hashCode = this.GetHash(key);
		int nextkey = hashCode;
		HashNode curr = this.buckets[nextkey];
		if (curr.State == NodeState.empty_since_start) {
			return;
		} else if (curr.State == NodeState.regular && curr.Key ==key ) {
			this.buckets[nextkey] = HashNode.emptyAfterDeleteNode(nextkey);
			return;
		} else {
			nextkey = (nextkey + 1) % this.Size;
			curr = this.buckets[nextkey];
			while (curr.State == NodeState.empty_after_delete ||
					(curr.State == NodeState.regular && curr.Key
					!= key) || nextkey == hashCode){
				nextkey = (nextkey + 1) % this.Size;
				curr = this.buckets[nextkey];
			}
			if (nextkey == hashCode) {
				return;
			} else if (curr.State == NodeState.empty_since_start) {
				return;
			} else {
				this.buckets[nextkey] = HashNode.emptyAfterDeleteNode(nextkey);
				return;
			}
		}
	}
	
	//resize
//	void Resize() throws Exception {
//		if (!this.IsChunky()) {
////			System.out.println("Staying slim today? ");
//			return;
//		}
//		HashNode[] originalBuckets = this.buckets;
//		this.Count = 0;
//		this.Size = this.Size * 2;
//		this.buckets = new HashNode[this.Size];
//		for(int i = 0; i < this.Size; i++) {
//			this.buckets[i] = HashNode.emptySinceStartNode(i);
//		}
//		for(int i = 0; i < originalBuckets.length; i++) {
//			HashNode curr = originalBuckets[i];
//			if (curr.State == NodeState.regular) {
//				this.HashInsert(curr.Key, curr.Value);
//			}
//		}
////		System.out.print("Resize!");
//	}
	
	private void Resize()  throws Exception {
		if (this.IsChunky()) 
		{
			System.out.print("\nResizing!\n");
//			Main.printTable(this);
			this.Size = this.Size * 2;
			this.Count = 0;
			HashNode[] original = this.buckets;
			this.buckets = new HashNode[this.Size]; //bucket states?
			for(int i = 0; i < this.buckets.length; i++) {
				this.buckets[i] = HashNode.emptySinceStartNode(i);
			}
			
			for (int i = 0; i < original.length-1; i++) 
			{
				if(original[i].State == NodeState.regular) //if node state is regular, add again,
				{
//					original[i].State = NodeState.empty_after_delete; //maybe not? the node itself it still good
					HashInsert(original[i].Key, original[i].Value);
				} //if NodeState is empty, move on
			}
		}
	}
	
	
	//check the capacity
	public boolean IsChunky() {
		return (float)this.Count / (float)this.Size >= this.Threashold;
	}
}
