
public class BST {
	
	public static class Node{
		
		private Node p;
		private Node l;
		private Node r;
		private char data;
		private int key;
		
		public Node(int key, char data) {
			this.key = key; this.data = data;
			this.r = null; this.l = null; this.p = null;
		}
	}
	
	private Node root;
	
	public BST() {
		this.root = null;
	}
	
	public static Node getRoot(BST T) {
		return T.root;
	}
	
	public static Node getNode(Node root, int key) {
		if (root == null || key == root.key) {
			return root;
		}
		else if (key < root.key) {
			return getNode(root.l, key);
		}
		else {
			return getNode(root.r, key);
		}
	}
	
	public static Node Minimum(Node root) {
		while (root.l != null) {
			root = root.l;
		}
		return root;
	}
	
	public static Node Maximum(Node root) {
		while (root.r != null) {
			root = root.r;
		}
		return root;
	}
	
	public static void Insert(BST T, Node nn) {
		Node parent = null;
		Node insert = T.root;
		while (insert != null){
			parent = insert;
			if (nn.key < insert.key) { insert = insert.l; }
			else { insert = insert.r; }
		}
		nn.p = parent; 
		if (parent == null) { T.root = nn; }
		else if (nn.key < parent.key) { parent.l = nn; }
		else { parent.r = nn; }
	}
	
	public static void Delete(BST T, Node remove) {
		if (remove.l == null) { Transplant(T, remove, remove.r); }
		else if (remove.r == null) { Transplant(T, remove, remove.l); }
		else {
			Node succ = Minimum(remove.r);
			if (succ.p.key != remove.key) {
				Transplant(T, succ, succ.r);
				succ.r = remove.r;
				succ.r.p = succ;
			}
			Transplant(T, remove, succ);
			succ.l = remove.l;
			succ.l.p = succ;
		}
	}
	
	public static void Transplant(BST T, Node remove, Node plant) {
		if (remove.p == null) { T.root = plant; }
		else if (remove == remove.p.l) { remove.p.l = plant; }
		else { remove.p.r = plant; }
		if (plant != null) { plant.p = remove.p; }
	}
	
	public static String inOrder(Node root) {
		String inOrder = "";
		if (root != null) {
			inOrder += inOrder(root.l);
			inOrder += root.data;
			inOrder += inOrder(root.r);
		}
		return inOrder;
	}
	
	public static String preOrder(Node root) {
		String preOrder = "";
		if (root != null) {
			preOrder += root.data;
			preOrder += preOrder(root.l);
			preOrder += preOrder(root.r);
		}
		return preOrder;
	}
	
	public static String postOrder(Node root) {
		String postOrder = "";
		if (root != null) {
			postOrder += postOrder(root.l);
			postOrder += postOrder(root.r);
			postOrder += root.data;
		}
		return postOrder;
	}
	
}