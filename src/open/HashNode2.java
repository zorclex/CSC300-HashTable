package open;

public class HashNode2 {
	public int Key; //hashcode goes here
	public int Value;
	public int Code; //
	public HashNode2 Next; //chains onwards
	
	public HashNode2(int key, int value, int code) {
		this.Key = key;
		this.Value = value;
		this.Code = code;
		this.Next = null;
	}
}
