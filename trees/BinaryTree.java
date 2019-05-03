import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.*;

class Node {
    int value;
    Node left, right;

    Node(int data, Node left, Node right) {
        this.value = data;
        this.left = left;
        this.right = right;
    }

    public void setData(int data) {
        this.value = data;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public int getData() {
        return this.value;
    }
}

public class BinaryTree {
    static Queue<Node> helperQueue = new LinkedList<>();
    static Stack<Node> helperStack = new Stack<Node>();

    static int totalSumRootToLeaf = 0;

    static List<Integer> collection = new ArrayList<>();
    static List<List<Integer>> wholeCollection = new ArrayList<List<Integer>>();

    public static void main(String[] args) {
        // Node root = new Node(1, null, null);
        //
        // insert(root, 2);
        // insert(root, 3);
        // insert(root, 4);
        // insert(root, 5);
        // insert(root, 6);
        // insert(root, 7);
        // insert(root, 8);
        // insert(root, 9);
        // insert(root, 10);
        // insert(root, 11);
        // insert(root, 12);
        // insert(root, 13);

        // Node root2 = new Node(1, null, null);

        // insert(root2, 2);
        // insert(root2, 3);
        // insert(root2, 4);
        // insert(root2, 5);
        // insert(root2, 9);
        // insert(root2, 6);
        // insert(root2, 7);

        // inOrderTraversal(root);

        // System.out.println("Deepest Node:");
        // System.out.println(deepestNodes(root));

        // maximumSumLevel(root);

        // rootToLeaf(root);

        // System.out.println(totalSumRootToLeaf);

        // inOrderTraversal(root);
        // mirror(root);
        // System.out.println("After mirroring");
        // inOrderTraversal(root);

        // inOrderTraversal(root);
        // root2 has become mirror of root because initially root and root1 were same.
        // recursiveMirror(root2);

        // System.out.println(checkMirror(root, root2));

        // System.out.printf("Difference sum of all nodes in odd and even level: %d\n", diffEvenOddLevels(root));

        // Node node1 = root.getRight();
        // Node node2 = root.getLeft().getRight();

        // System.out.printf("LCA: %d\n", findLCA(root, node1, node2).getData());

        // printZigZag(root);

        // columnWiseTraversal(root);
        // Node expTreeNode = expressionTree("a+b-c*d/e");

        // printExpressionTree should print the exact string as
        // passed to expressionTree function i.e., infix string.
        // Note: Brackets are not preserved in postfix expressions
        // so you won't get then after printing expressionTree.
        // printExpressionTree(expTreeNode);
        // System.out.println();

        Node root = new Node(15, null, null);

        bstInsert(root, 8);
        bstInsert(root, 18);
        bstInsert(root, 7);
        bstInsert(root, 9);
        bstInsert(root, 17);
        bstInsert(root, 20);
        bstInsert(root, 6);
        bstInsert(root, 10);
        bstInsert(root, 16);
        bstInsert(root, 21);

        // Inorder traversal can be used to verify the BST generated.
        // inOrderTraversal(root);

        // System.out.println(bstSearch(root, 21));
        // System.out.println(bstSearch(root, 22));

        // System.out.println(iterativeBstSearch(root, 21));
        // System.out.println(iterativeBstSearch(root, 22));

        // System.out.println(bstFindMin(root));
        // System.out.println(bstFindMax(root));

        System.out.println(bstFindLCA(root, 6, 21).getData());
    }

    // Following algorithm is for insertion in BST.
    public static void bstInsert(Node root, int nodeValue) {
        if (root != null) {
            if (root.getData() < nodeValue) {
                if (root.getRight() == null) {
                    root.setRight(new Node(nodeValue, null, null));
                } else {
                    bstInsert(root.getRight(), nodeValue);
                }
            } else {
                if (root.getLeft() == null) {
                    root.setLeft(new Node(nodeValue, null, null));
                } else {
                    bstInsert(root.getLeft(), nodeValue);
                }
            }
        } else {
            root.setData(nodeValue);
        }
    }

    public static boolean iterativeBstSearch(Node root, int key) {
        Node currentNode = root;

        while (currentNode != null) {
            if (currentNode.getData() == key)
                return true;
            if (currentNode.getData() < key)
                currentNode = currentNode.getRight();
            else
                currentNode = currentNode.getLeft();
        }

        return false;
    }

    public static boolean bstSearch(Node root, int key) {
        if (root != null) {
            if (root.getData() == key) {
                return true;
            }
            if (root.getData() < key)
                return bstSearch(root.getRight(), key);
            // the only case left is to search right subtree.
            return bstSearch(root.getLeft(), key);
        }
        return false;
    }

    public static int bstFindMin(Node node) {
        if (node.getLeft() == null) {
            return node.getData();
        }
        return bstFindMin(node.getLeft());
    }

    public static int bstFindMax(Node node) {
        if (node.getRight() == null) {
            return node.getData();
        }
        return bstFindMax(node.getRight());
    }

    public static Node bstFindLCA(Node root, int a, int b) {
        if (root != null) {
            if ((root.getData() >= a && root.getData() <= b) || (root.getData() >= b && root.getData() <= a)) {
                return root;
            }
            if (root.getData() > a) {
                return bstFindLCA(root.getLeft(), a, b);
            }
            return bstFindLCA(root.getRight(), a, b);
        }
        return null;
    }

    public static void printExpressionTree(Node root) {
        if (root.getLeft() != null) {
            printExpressionTree(root.getLeft());
        }
        System.out.printf("%c", root.getData());
        if (root.getRight() != null) {
            printExpressionTree(root.getRight());
        }
    }

    public static Node expressionTree(String infix) {
        // We are  converting postfix string to array of chars because our Node
        // supports int as data and not string. So if we change Node than other
        // code will break. In order to prevent this we are using chars and while
        // adding to tree, chars are converted to ascii values i.e., integers.
        char[] postfixArray = infixToPostfix(infix).toCharArray();
        char postFixChar;
        int limit = postfixArray.length;

        for(int i = 0; i < limit; i++) {
            postFixChar = postfixArray[i];
            // Check whether operand or not i.e., [A-Za-z] using ascii values.
            if ((postFixChar >= 65 && postFixChar <= 90) || (postFixChar >= 97 && postFixChar <= 122)) {
                Node newNode = new Node((int) postFixChar, null, null);
                helperStack.push(newNode);
            } else {
                Node rightChild = helperStack.pop();
                Node leftChild = helperStack.pop();
                Node newNode = new Node((int) postFixChar, leftChild, rightChild);
                helperStack.push(newNode);
            }
        }

        return helperStack.pop();
    }

    public static String infixToPostfix(String infix) {
        Stack<String> symbolsStack = new Stack<String>();
        String[] stringChars = infix.split("");
        String stackTop;
        String postfix = "";
        int limit = stringChars.length;

        Pattern operandsRegex = Pattern.compile("[A-Za-z]");

        Map<String, Integer> priorityMap = new HashMap<String, Integer>();
        priorityMap.put("^", 3);
        priorityMap.put("*", 2);
        priorityMap.put("/", 2);
        priorityMap.put("+", 1);
        priorityMap.put("-", 1);
        priorityMap.put("(", 0);

        for(int i = 0; i < limit; i++) {
            if (stringChars[i].equals("(")) {
                symbolsStack.push("(");
            } else if (operandsRegex.matcher(stringChars[i]).matches()) {
                postfix += stringChars[i];
            } else if (stringChars[i].equals(")")) {
                while (true) {
                    stackTop = symbolsStack.pop();
                    if (stackTop == "(") {
                        break;
                    } else {
                        postfix += stackTop;
                    }
                }
            } else {
                while (!symbolsStack.isEmpty()) {
                    stackTop = symbolsStack.pop();
                    if (priorityMap.get(stackTop) < priorityMap.get(stringChars[i])) {
                        symbolsStack.push(stackTop);
                        break;
                    } else {
                        postfix += stackTop;
                    }
                }

                symbolsStack.push(stringChars[i]);
            }
        }

        while (!symbolsStack.isEmpty()) {
            postfix += symbolsStack.pop();
        }

        return postfix;
    }

    public static void columnWiseTraversal(Node root) {
        // TreeMap implements SortedMap and sorts values accoding to keys.
        Map<Integer, List<Integer>> helperMap = new TreeMap<Integer, List<Integer>>();

        // Since map is pass by reference the map will also be changed here.
        prepareMap(helperMap, root, 0);
        printMap(helperMap);
        helperMap.clear();
    }

    public static void printMap(Map<Integer, List<Integer>> map) {
        List<Integer> column;
        int columnSize;
        int i;
        for (Map.Entry<Integer, List<Integer>> mapEntry : map.entrySet()) {
            column = mapEntry.getValue();
            columnSize = column.size();
            i = 0;
            while (i < columnSize) {
                System.out.println(column.get(i));
                i++;
            }
        }
    }

    public static void prepareMap(Map<Integer, List<Integer>> helperMap, Node node, int ref) {
        List<Integer> columnNodeList = new ArrayList<>();
        if (helperMap.containsKey(ref)) {
            columnNodeList = helperMap.get(ref);
            columnNodeList.add(node.getData());
        } else {
            columnNodeList.add(node.getData());
            helperMap.put(ref, columnNodeList);
        }

        if (node.getLeft() != null) {
            prepareMap(helperMap, node.getLeft(), ref - 1);
        }
        if (node.getRight() != null) {
            prepareMap(helperMap, node.getRight(), ref + 1);
        }

        return;
    }

    public static void printZigZag(Node root) {
        Stack<Node> stack1 = new Stack<Node>();
        Stack<Node> stack2 = new Stack<Node>();
        Node node;
        int level = -1;

        stack1.push(root);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            level++;

            if (level % 2 == 0) {
                while (!stack1.isEmpty()) {
                    node = stack1.pop();
                    if (node.getLeft() != null) {
                        stack2.push(node.getLeft());
                    }
                    if (node.getRight() != null) {
                        stack2.push(node.getRight());
                    }
                    System.out.println(node.getData());
                }
            } else {
                while (!stack2.isEmpty()) {
                    node = stack2.pop();
                    if (node.getRight() != null) {
                        stack1.push(node.getRight());
                    }
                    if (node.getLeft() != null) {
                        stack1.push(node.getLeft());
                    }
                    System.out.println(node.getData());
                }
            }
        }
    }

    public static Node findLCA(Node root, Node node1, Node node2) {
        helperQueue.add(root);
        int count;
        Node node, LCA;
        boolean isDescendant;

        while (!helperQueue.isEmpty()) {
            count = helperQueue.size();

            while (count > 0) {
                node = helperQueue.remove();

                System.out.println(helperStack);

                isDescendant = findNode(node.getLeft(), node1, node2) && findNode(node.getRight(), node1, node2);

                if (isDescendant) {
                    helperStack.push(node);
                }

                if (node.getLeft() != null) {
                    helperQueue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    helperQueue.add(node.getRight());
                }

                count--;
            }
        }

        if (helperStack.size() == 0) {
            return null;
        }
        LCA = helperStack.pop();
        helperStack.clear();

        return LCA;
    }

    public static boolean findNode(Node node, Node node1, Node node2) {
        if (node == null) {
            return false;
        }
        if (node == node1 || node == node2) {
            return true;
        }
        boolean isInLeftSubtree = findNode(node.getLeft(), node1, node2);
        boolean isInRightSubtree = findNode(node.getRight(), node1, node2);

        return isInLeftSubtree || isInRightSubtree;
    }

    public static boolean checkMirror(Node root1, Node root2) {
        if (root1 != null && root2 != null) {
            if (root1.getData() != root2.getData()) {
                return false;
            }
            boolean isLeftSubtreeMirror = checkMirror(root1.getLeft(), root2.getRight());
            boolean isRightSubtreeMirror = checkMirror(root1.getRight(), root2.getLeft());
            return isLeftSubtreeMirror && isRightSubtreeMirror;
        }
        return true;
    }

    public static void recursiveMirror(Node root) {
        if (root != null) {
            Node temp;
            recursiveMirror(root.getLeft());
            recursiveMirror(root.getRight());
            temp = root.getLeft();
            root.setLeft(root.getRight());
            root.setRight(temp);
        }
        return;
    }

    public static int diffEvenOddLevels(Node root) {
        int oddSum = 0, evenSum = 0, level = -1, count, nodeValue;
        Node node;

        System.out.println(helperQueue);

        helperQueue.add(root);

        while (!helperQueue.isEmpty()) {
            count = helperQueue.size();
            level++;

            while (count > 0) {
                node = helperQueue.remove();

                if (node.getLeft() != null) {
                    helperQueue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    helperQueue.add(node.getRight());
                }

                nodeValue = node.getData();
                if (level % 2 == 0) {
                    evenSum += nodeValue;
                } else {
                    oddSum += nodeValue;
                }

                count--;
            }
        }

        return evenSum - oddSum;
    }

    public static void mirror(Node root) {
        helperQueue.add(root);
        Node node, leftNode, rightNode;

        while (!helperQueue.isEmpty()) {
            node = helperQueue.remove();

            if (node.getLeft() == null && node.getRight() == null) {
                continue;
            }

            leftNode = node.getLeft();
            rightNode = node.getRight();

            node.setLeft(rightNode);
            node.setRight(leftNode);

            helperQueue.add(node.getLeft());
            helperQueue.add(node.getRight());
        }
    }

    public static void rootToLeaf(Node root) {
        if (root == null || root.getData() == 0) {
            return;
        }
        helperStack.push(root);

        rootToLeaf(root.getLeft());

        if (root.getLeft() == null && root.getRight() == null) {
            sumRootToLeaf(helperStack);
        }

        rootToLeaf(root.getRight());

        helperStack.pop();
    }

    public static void sumRootToLeaf(Stack<Node> stk) {
        int sum = 0;
        int stkSize = stk.size();
        int i = 0;

        while (i < stkSize) {
            sum += stk.get(i).getData();
            sum *= 10;
            i++;
        }

        sum /= 10;

        totalSumRootToLeaf += sum;
    }

    public static void printStack(Stack<Node> stk) {
        int stkSize = stk.size();
        int i = 0;

        while (i < stkSize) {
            System.out.println(stk.get(i).getData());
            i++;
        }

        System.out.println("##########################");
    }

    public static void maximumSumLevel(Node root) {
        int level = -1, maxSum = Integer.MIN_VALUE, maxLevel = -1, sum, count;
        Node node;

        helperQueue.add(root);

        while (!helperQueue.isEmpty()) {
            level++;
            count = helperQueue.size();
            sum = 0;

            while (count > 0) {
                node = helperQueue.remove();
                if (node != null) {
                    sum += node.getData();
                    helperQueue.add(node.getLeft());
                    helperQueue.add(node.getRight());
                }
                count--;
            }
            if (sum > maxSum) {
                maxSum = sum;
                maxLevel = level;
            }
        }

        System.out.printf("Level %d has maximum sum of %d\n", maxLevel, maxSum);
    }

    public static int deepestNodes(Node root) {
        helperQueue.add(root);
        Node node;
        int deepestNode = 0;

        while (!helperQueue.isEmpty()) {
            node = helperQueue.remove();

            if (node.getLeft() == null && node.getRight() == null) {
                deepestNode = node.getData();
            } else {
                if (node.getLeft() != null) {
                    helperQueue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    helperQueue.add(node.getRight());
                }
            }
        }

        return deepestNode;
    }

    public static void copy(List<Integer> toCopy) {
        List<Integer> toCopyClone = new ArrayList<>();

        for (Integer i : toCopy) {
            toCopyClone.add(i);
        }

        wholeCollection.add(toCopyClone);
    }

    public static int treeSize(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + treeSize(root.getLeft()) + treeSize(root.getRight());
    }

    public static void insert(Node root, int nodeValue) {
        helperQueue.add(root);
        while (true) {
            if (helperQueue.peek().getLeft() == null) {
                Node newNode = new Node(nodeValue, null, null);
                helperQueue.peek().setLeft(newNode);
                break;
            }
            if (helperQueue.peek().getRight() == null) {
                Node newNode = new Node(nodeValue, null, null);
                helperQueue.peek().setRight(newNode);
                break;
            }

            Node head = helperQueue.remove();
            helperQueue.add(head.getLeft());
            helperQueue.add(head.getRight());
        }
        helperQueue.clear();
    }

    public static void reverseLevelOrderTraversal(Node root) {
        helperQueue.add(root);

        while (!helperQueue.isEmpty()) {
            Node head = helperQueue.remove();
            helperStack.push(head);

            if (head.getRight() != null) {
                helperQueue.add(head.getRight());
            }
            if (head.getLeft() != null) {
                helperQueue.add(head.getLeft());
            }
        }

        while (!helperStack.isEmpty()) {
            System.out.println(helperStack.pop().getData());
        }
    }

    public static int height(Node currentNode) {
        if (currentNode == null) {
            return 0;
        }

        return 1 + Math.max(height(currentNode.getLeft()), height(currentNode.getRight()));
    }

    public static void levelOrderTraversal(Node root) {
        Node node;
        helperQueue.add(root);

        while (!helperQueue.isEmpty()) {
            node = helperQueue.remove();
            System.out.println(node.getData());

            if (node.getLeft() != null) {
                helperQueue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                helperQueue.add(node.getRight());
            }
        }
    }

    public static boolean deleteNode(Node node, int data) {
        if (node.getLeft() != null) {
            if (node.getLeft().getData() == data) {
                node.setLeft(null);
                return true;
            }
            if (deleteNode(node.getLeft(), data)) {
                return true;
            }
        }
        if (node.getRight() != null) {
            if (node.getRight().getData() == data) {
                node.setRight(null);
                return true;
            }
            if (deleteNode(node.getRight(), data)) {
                return true;
            }
        }

        return false;
    }

    public static void inOrderTraversal(Node root) {
        if (root.getLeft() != null) {
            inOrderTraversal(root.getLeft());
        }
        System.out.println(root.getData());
        if (root.getRight() != null) {
            inOrderTraversal(root.getRight());
        }
    }

    public static int findMaximum(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;

        int res = node.getData();
        int lres = findMaximum(node.getLeft());
        int rres = findMaximum(node.getRight());

        if (lres > res)
            res = lres;
        if (rres > res)
            res = rres;
        return res;
    }

    public static Boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (root.getData() == key) {
            return true;
        }

        Boolean leftSubtree = search(root.getLeft(), key);

        Boolean rightSubtree = search(root.getRight(), key);

        return leftSubtree || rightSubtree;
    }
}
