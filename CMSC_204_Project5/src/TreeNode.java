
/**
 * The external Tree Node for Linked Trees
 * 
 * @author Derek Luong
 *
 * @param <T> - data type of TreeNode
 */
public class TreeNode<T> {

	private T data;
	private TreeNode<T> leftNode;
	private TreeNode<T> rightNode;
	
	/**
	 * Create a new TreeNode with left and right child set to null and data set to the dataNode
	 * 
	 * @param dataNode - the data to be stored in the TreeNode
	 */
	public TreeNode(T dataNode) {
		this.data = dataNode;
	}
	
	/**
	 * used for making deep copies
	 * 
	 * @param node - node to make copy of
	 */
	public TreeNode(TreeNode<T> node) {
		this.data = node.getData();
		leftNode = node.leftNode;
		rightNode = node.rightNode;
	}
	
	/**
	 * Return the data within this TreeNode
	 * 
	 * @return the data within the TreeNode
	 */
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public TreeNode<T> getLeftNode() {
		return leftNode;
	}
	
	public void setLeftNode(TreeNode<T> leftNode) {
		this.leftNode = leftNode;
	}
	
	public TreeNode<T> getRightNode() {
		return rightNode;
	}
	
	public void setRightNode(TreeNode<T> rightNode) {
		this.rightNode = rightNode;
	}
}
