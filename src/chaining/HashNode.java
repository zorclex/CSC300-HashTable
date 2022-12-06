package chaining;

public class HashNode {
	public String Key;
	public String Value;
	public int Code;
	public HashNode Next;
	
	public HashNode(String key, String value, int code) {
		this.Key = key;
		this.Value = value;
		this.Code = code;
		this.Next = null;
	}
}
