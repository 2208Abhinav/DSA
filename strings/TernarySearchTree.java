class TSTNode {
	char data;
	TSTNode eq, left, right;
	boolean isEnd;

	TSTNode(char val) {
		this.data = val;
		this.isEnd = false;
	}
}

public class TernarySearchTree {
	static TSTNode root;

	static TSTNode insert(TSTNode node, String word, int pos) {
		if(node == null)
			node = new TSTNode(word.charAt(pos));

		if(word.charAt(pos) < node.data)
			node.left = insert(node.left, word, pos);
		else if(word.charAt(pos) > node.data)
			node.right = insert(node.right, word, pos);
		else {
			if(pos + 1 < word.length())
				node.eq = insert(node.eq, word, pos+1);
			else
				node.isEnd = true;
		}
		return node;
	}

	static void insert(String word) {
		root = insert(root, word, 0);
	}

	static boolean search(TSTNode node, String word, int pos) {
		if(node == null || pos >= word.length())
			return false;
		if(word.charAt(pos) < node.data)
			return search(node.left, word, pos);
		else if(word.charAt(pos) > node.data)
			return search(node.right, word, pos);
		else {
			if(node.isEnd && pos+1==word.length())
				return true;
			return search(node.eq, word, pos+1);
		}
	}

	static boolean search(String word) {
		return search(root, word, 0);
	}

	static void traverse(TSTNode node, String prefix) {
		if(node != null) {
			traverse(node.left, prefix);
			if(node.isEnd) {
				System.out.println(prefix + node.data);
			}
			traverse(node.eq, prefix + node.data);
			traverse(node.right, prefix);
		}
	}

	static void traverse(TSTNode node) {
		traverse(node, "");
	}

	public static void main(String[] args) {
		insert("abc");
		insert("bat");
		insert("cats");
		insert("boats");
		System.out.println("'b' exists: " + search("b"));
		System.out.println("'bat' exists: " + search("bat"));
		System.out.println("'cats' exists: " + search("cats"));
		System.out.println("'boats' exists: " + search("boats"));
		System.out.println("'boat' exists: " + search("boat"));

		traverse(root);
	}
}
