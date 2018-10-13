package Lab2;
public class Node {

	private VALUE value;
	private int frequency=1;
	private boolean endCharacter=false;
	private Node leftChild;
	private Node rightChild;
	private Node parent;
		    
	public enum VALUE {S, R, B};	    
	Node(VALUE value, Node parentNode)
	{
		this.value = value;
		this.parent = parentNode;
		leftChild = null;
		rightChild = null;
	}


	public VALUE getKey() {
		return value;
	}


	public void setKey(VALUE value) {
		this.value = value;
	}

	
	public void incrementFrequency() {
	  this.frequency++;
	}


	public boolean isEndCharacter() {
		return endCharacter;
	}


	public void setEndCharacter(boolean endCharacter) {
		this.endCharacter = endCharacter;
	}


	public Node getLeftChild() {
		return leftChild;
	}


	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}


	public Node getRightChild() {
		return rightChild;
	}


	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}


	public Node getParent() {
		return parent;
	}


	public void setParent(Node parent) {
		this.parent = parent;
	}
		    
	public String toString()
	{
		return "(value: " + value.toString() + ", freq: " + Integer.toString(frequency) + ")";
	}

	public boolean isRoot()
	{
		return parent == null;
	}
  
}
