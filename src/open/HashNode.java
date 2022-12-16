package open;

public class HashNode {
	public int Key;
	public int Value;
	public int Code;
	public NodeState State = NodeState.regular; //state: 0 empty since start, 1 empty after delete, 2 regular/occupied
	
	public HashNode(int key, int value, int code) {
		this.Key = key;
		this.Value = value;
		this.Code = code;
		this.State = NodeState.regular;
	}
	
	public static HashNode emptySinceStartNode(int code) {
		HashNode value = new HashNode(0, 0, code);
		value.State = NodeState.empty_since_start;
		return value;
	}
	
	public static HashNode emptyAfterDeleteNode(int code) {
		HashNode value = new HashNode(0, 0, code);
		value.State = NodeState.empty_after_delete;
		return value;
	}
}


