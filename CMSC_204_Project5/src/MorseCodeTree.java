import java.util.ArrayList;

/**
 * 
 * This is a MorseCodeTree which is specifically used for the conversion of morse code to english. 
 * It relies on a root (reference to root of the tree) The root is set to null when the tree is empty. 
 * The class uses an external generic TreeNode class which consists of a reference to the data 
 * and a reference to the left and right child. The TreeNode is parameterized as a String, 
 * TreeNode This class uses a private member root (reference to a TreeNode). 
 * The constructor will call the buildTree method
 * 
 * @author Derek Luong
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	private TreeNode<String> rootOfTree;
	
	public MorseCodeTree() {
		rootOfTree = new TreeNode<String>("");
		buildTree();
	}
	
	@Override
	public TreeNode<String> getRoot() {
		return rootOfTree;
	}

	@Override
	public void setRoot(TreeNode<String> newNode) {
		rootOfTree = newNode;
	}

	@Override
	public LinkedConverterTreeInterface<String> insert(String code,
			String result) {
		
		addNode(rootOfTree, code, result);
		return this;
	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		// if code is empty, create node with given letter
		if (code == null || code.length() == 0) {
			root.setData(letter);
			return;
		}
		
		// if there are more code which need to traverse, call addNode with next root
		char firstChar = code.charAt(0);
		if (firstChar == '.') {
			if (root.getLeftNode() == null) {
				root.setLeftNode(new TreeNode<String>(""));
			}
			addNode(root.getLeftNode(), code.substring(1), letter);
		} else {
			if (root.getRightNode() == null) {
				root.setRightNode(new TreeNode<String>(""));
			}
			addNode(root.getRightNode(), code.substring(1), letter);
		}
	}

	@Override
	public String fetch(String code) {
		return fetchNode(rootOfTree, code);
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		// if code is empty, return data of node
		if (code == null || code.length() == 0) {
			return root.getData();
		}
		
		// if there are more code which need to traverse, call fetchNode with next root
		String letter;
		char firstChar = code.charAt(0);
		if (firstChar == '.') {
			letter = fetchNode(root.getLeftNode(), code.substring(1));
		} else {
			letter = fetchNode(root.getRightNode(), code.substring(1));
		}
		return letter;
	}

	@Override
	public LinkedConverterTreeInterface<String> delete(String data)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedConverterTreeInterface<String> update()
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buildTree() {
		String[] letterCodes = new String[] {".", "-", 
									"..", ".-", "-.", "--", 
									"...", "..-", ".-.", ".--", "-..", "-.-", "--.", "---", 
									"....", "...-", "..-.", ".-..", ".--.", ".---", "-...", "-..-", "-.-.", "-.--", "--..", "--.-"};
		String[] letters = new String[] {"e", "t", 
									"i", "a", "n", "m", 
									"s", "u", "r", "w", "d", "k", "g", "o", 
									"h", "v", "f", "l", "p", "j", "b", "x", "c", "y", "z", "q"};
		
		for (int i=0; i < letterCodes.length; i++) {
			insert(letterCodes[i], letters[i]);
		}
	}

	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> result = new ArrayList<String>();
		LNRoutputTraversal(rootOfTree, result);
		return result;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root.getLeftNode() != null) {
			LNRoutputTraversal(root.getLeftNode(), list);
		}
		list.add(root.getData());
		if (root.getRightNode() != null) {
			LNRoutputTraversal(root.getRightNode(), list);
		}
	}

}
