class TrieNode {
	boolean isEndOfWord;
	TrieNode[] children = new TrieNode[26];

	TrieNode() {
		this.isEndOfWord = false;
	}
}

public class Trie {
	static TrieNode root;

	// Worst case time complexity of insert: O(m)
	// m is the length of key
	static void insert(String key) {
		TrieNode nextNode = root;
		int length = key.length();

		for(int i = 0; i < length; i++) {
			// character directly maps to index number
			int index = key.charAt(i) - 'a';

			if (nextNode.children[index] == null) {
				nextNode.children[index] = new TrieNode();
			}
			nextNode = nextNode.children[index];
		}

		nextNode.isEndOfWord = true;
	}

	// Worst case time complexity of search: O(m)
	// m is the length of key
	static boolean search(String key) {
		TrieNode nextNode = root;
		int length = key.length();

		for(int i = 0; i < length; i++) {
			// character directly maps to index number
			int index = key.charAt(i) - 'a';

			if (nextNode.children[index] == null) return false;

			nextNode = nextNode.children[index];
		}

		return nextNode.isEndOfWord;
	}

	public static void main(String[] args) {
		root = new TrieNode();

		// This Trie supports small letters containing a, b,..., z
		String[] keysList = new String[]{"asa", "asasas", "avda", "dvc", "zasas", "casas"};

		int keysListLen = keysList.length;

		for(int i = 0; i < keysListLen; i++) {
			insert(keysList[i]);
		}

		String[] searchKeys = new String[]{"bcd", "asa", "dvc", "zas", "zasas", "a"};

		int searchKeysListLen = searchKeys.length;

		for(int i = 0; i < searchKeysListLen; i++) {
			boolean isPresent = search(searchKeys[i]);
			System.out.println(searchKeys[i] + " exists: " + isPresent);
		}
	}
}
