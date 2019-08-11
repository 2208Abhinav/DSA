class Node {
	int data;
	Node left, right;

	Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public void setData(int data) {
		this.data = data;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getData() {
		return this.data;
	}

	public Node getLeft() {
		return this.left;
	}

	public Node getRight() {
		return this.right;
	}
}

public class BalancedBST {
	static int[] sortedArr = {1, 2, 3, 4, 5, 6, 7};

	public static void main(String[] args) {
		Node balancedBstHead = sortedArrToBalancecBST(0, sortedArr.length - 1);

		preorderTraversal(balancedBstHead);

		System.out.print("Balanced: ");
		System.out.println(isBalanced(balancedBstHead));
	}

	// If Balanced BST was build correctly then this function should
	// print back the sortedArr
	public static void preorderTraversal(Node node) {
		if (node.getLeft() != null) preorderTraversal(node.getLeft());

		System.out.println(node.getData());

		if (node.getRight() != null) preorderTraversal(node.getRight());
	}

	public static Node sortedArrToBalancecBST(int start, int end) {
		if (start > end) return null;

		int mid = (end + start)/2;

		Node node = new Node(sortedArr[mid]);

		node.setLeft(sortedArrToBalancecBST(start, mid - 1));

		node.setRight(sortedArrToBalancecBST(mid + 1, end));

		return node;
	}

	// Check if BST is balanced or not. O(n^2)
	public static boolean isBalanced(Node node) {
		if (node == null) return true;

		int leftSubtreeHeight = height(node.getLeft());
		int rightSubtreeHeight = height(node.getRight());

		int balanceFactor = leftSubtreeHeight - rightSubtreeHeight;

		if (!(balanceFactor >= -1 && balanceFactor <= 1)) {
			return false;
		}

		return isBalanced(node.getLeft()) && isBalanced(node.getRight());
	}

	public static int height(Node node) {
		if (node == null) return 0;

		return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
	}
}
