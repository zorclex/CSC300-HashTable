package chaining;

public class HashNode {
	public String Key; //hashcode goes here
	public String Value;
	public int Code; //
	public HashNode Next; //chains onwards
	
	public HashNode(String key, String value, int code) {
		this.Key = key;
		this.Value = value;
		this.Code = code;
		this.Next = null;
	}
}
