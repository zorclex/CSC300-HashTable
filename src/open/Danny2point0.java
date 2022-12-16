package open;


public class Danny2point0 {
	public HashNode2 buckets[];
	public int Size; //capacity
	public int Count; //items in the hash table
	public final float threashold = 0.7f;
	private final int initialSize = 10;
	
	public Danny2point0() {
		this.Size = initialSize;
		this.Count = 0;
		this.buckets = new HashNode2[this.Size];
	}
	
	public int GetHash(int key) {
		int hash = Integer.hashCode(key);
		if (hash < 0) {
			hash += 2147483648L;
		}
		return hash % this.Size;
	}
	
	public void Add(int key, int value) {
		//use gethash to find the bucket index
		int code = this.GetHash(key);
		//then append it to the tail of the link on the bucket
		HashNode2 curr = this.buckets[code];
		if (curr == null) {
			HashNode2 newNode = new HashNode2(key, value, code);
			this.buckets[code] = newNode;
		} else {
			while (curr != null) {
				if (curr.Key == key) {
					curr.Value = value;
					return;
				} else {
					if (curr.Next == null) {
						HashNode2 newNode = new HashNode2(key, value, code);
						curr.Next = newNode;
						this.Count++;
						this.Resize();
						return;
					}
					curr = curr.Next;
				}
			}
		}
	}
	
	public HashNode2 Get(int key) {
		int code = this.GetHash(key);
		HashNode2 tar = this.buckets[code];
		if (tar == null) {
			return null;
		} else {
			HashNode2 curr = tar;
			while (curr != null) {
				if (curr.Key == key) {
					return curr;
				} else {
					curr = curr.Next;
				}
			}
			return null;
		}
	}
	
	public void Remove(int key) {
		int code = this.GetHash(key);
		HashNode2 curr = this.buckets[code];
		if (curr == null) {
			return;
		} else {
			if (curr.Key == key) {
				HashNode2 next = curr.Next;
				this.buckets[code] = next;
				curr.Next = null;
			} else {
				HashNode2 pre = curr;
				curr = curr.Next;
				while (curr != null) {
					if (curr.Key == key) {
						pre.Next = curr.Next;
						curr.Next = null;
						return;
					} else {
						pre = curr;
						curr = curr.Next;
					}
				}
			}
		}
	}
	
	private void Resize() {
		if (this.Chunky()) {
			this.Size = this.Size * 2;
			this.Count = 0;
			HashNode2[] original = this.buckets;
			this.buckets = new HashNode2[this.Size];
			for (int i = 0; i < original.length; i++) {
				HashNode2 curr = original[i];
				while (curr != null) {
					this.Add(curr.Key, curr.Value);
					curr = curr.Next;
				}
			}
//			System.out.print("\nResizing!\n");
		}
	}
	
	private boolean Chunky() {
		return (float)this.Count / (float)this.Size > this.threashold;
	}
}
