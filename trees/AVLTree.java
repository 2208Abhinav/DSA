import java.util.LinkedList;
import java.util.Queue;


class Node {
	int data, height;
	Node left, right;

	Node(int value) {
		this.data = value;
		this.height = 1;
	}

	public int getData() {
		return this.data;
	}

	public void setLeft(Node leftNode) {
		this.left = leftNode;
	}

	public void setRight(Node rightNode) {
		this.right = rightNode;
	}

	public void setHeight(int newHeight) {
		this.height = newHeight;
	}

	public Node getLeft() {
		return this.left;
	}

	public Node getRight() {
		return this.right;
	}

	public int getHeight() {
		return this.height;
	}
}

public class AVLTree {
	public static Queue<Node> helperQueue = new LinkedList<Node>();

	public static int height(Node node) {
		if (node == null) return 0;

		return node.getHeight();
	}

	public static Node leftRotate(Node root) {
		Node rightSubtree = root.getRight();
		Node rightLeftSubtree = root.getRight().getLeft();

		rightSubtree.setLeft(root);
		root.setRight(rightLeftSubtree);

		rightSubtree.setHeight(1 + Math.max(height(rightSubtree.getLeft()), height(rightSubtree.getRight())));
		root.setHeight(1 + Math.max(height(root.getLeft()), height(root.getRight())));

		return rightSubtree;
	}

	public static Node rightRotate(Node root) {
		Node leftSubtree = root.getLeft();
		Node leftRightSubtree = root.getLeft().getRight();

		leftSubtree.setRight(root);
		root.setLeft(leftRightSubtree);

		leftSubtree.setHeight(1 + Math.max(height(leftSubtree.getLeft()), height(leftSubtree.getRight())));
		root.setHeight(1 + Math.max(height(root.getLeft()), height(root.getRight())));

		return leftSubtree;
	}

	public static Node insert(Node root, int newNodeValue) {
		/****************************
		* Simple BST insertion part *
		****************************/
		if (root == null) return (new Node(newNodeValue));

		if (newNodeValue <= root.getData()) {
			root.setLeft(insert(root.getLeft(), newNodeValue));
		} else {
			root.setRight(insert(root.getRight(), newNodeValue));
		}

		// Update height for the root
		root.setHeight(1 + Math.max(height(root.getLeft()), height(root.getRight())));

		/****************************
		*    Self balancing part    *
		****************************/
		int balanceFactor = height(root.getLeft()) - height(root.getRight());

		// Left Left case -> single rotation fix
		if (balanceFactor > 1 && newNodeValue <= root.getLeft().getData()) {
			return rightRotate(root);
		}

		// Right Right case -> single rotation fix
		if (balanceFactor < -1 && newNodeValue > root.getRight().getData()) {
			return leftRotate(root);
		}

		// Left Right case -> double rotation fix
		if (balanceFactor > 1 && newNodeValue > root.getLeft().getData()) {
			root.setLeft(leftRotate(root.getLeft()));
			return rightRotate(root);
		}

		// Right Left case -> double rotation fix
		if (balanceFactor < -1 && newNodeValue < root.getRight().getData()) {
			root.setRight(rightRotate(root.getRight()));
			return leftRotate(root);
		}

		// Return root to replace the old one
		return root;
	}

	public static void main(String[] args) {
		// Root may change due to self balancing
		Node root = insert(null, 10);

		root = insert(root, 15);
		root = insert(root, 20);
		root = insert(root, 30);
		root = insert(root, 50);
		root = insert(root, 65);
		root = insert(root, 10);
	}
}
