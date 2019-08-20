class HeapNode {
	int key, value;

	HeapNode(int key, int value) {
		this.key = key;
		this.value = value;
	}
}

public class StackUsingMaxHeap {
	HeapNode[] heapList;
	int size = 0, maxSize, priority = 1;

	void push(int value) {
		if (size == maxSize) {
			System.out.println("Stack is full");
			return;
		}

		HeapNode heapNode = new  HeapNode(priority, value);
		priority += 1;

		insert(heapNode);
	}

	public void insert(HeapNode heapNode) {
		heapList[size] = heapNode;
		size += 1;

		swapUp();
	}

	public int top() {
		return heapList[0].value;
	}

	public int size() {
		return size;
	}

	public int pop() {
		if (size == 0) {
			System.out.println("Stack is empty");
			return 0;
		}
		return delete().value;
	}

	public HeapNode delete() {
		HeapNode deletedValue = heapList[0];
		heapList[0] = heapList[size-1];
		heapList[size-1] = null;
		size -= 1;
		priority -= 1;

		swapDown();

		return deletedValue;
	}

	public void swapUp() {
		int iOfNew = size - 1;
		int parentIndex = (iOfNew - 1)/2;

		while(iOfNew > 0 && heapList[parentIndex].key < heapList[iOfNew].key) {
			HeapNode temp = heapList[parentIndex];
			heapList[parentIndex] = heapList[iOfNew];
			heapList[iOfNew] = temp;

			iOfNew = parentIndex;
			parentIndex = (iOfNew - 1)/2;
		}
	}

	public void swapDown() {
		int toBeSwappedIndex = 0;
		int childIndex = greaterChildIndex(toBeSwappedIndex);

		while(childIndex < size && heapList[childIndex].key > heapList[toBeSwappedIndex].key) {
			HeapNode temp = heapList[childIndex];
			heapList[childIndex] = heapList[toBeSwappedIndex];
			heapList[toBeSwappedIndex] = temp;

			toBeSwappedIndex = childIndex;
			childIndex = greaterChildIndex(toBeSwappedIndex);
		}
	}

	public int greaterChildIndex(int parentIndex) {
		int leftChildIndex = 2 * parentIndex + 1;
		int rightChildIndex = 2 * parentIndex + 2;

		if (rightChildIndex >= size) return leftChildIndex;
		if (heapList[leftChildIndex].key > heapList[rightChildIndex].key) return leftChildIndex;
		return rightChildIndex;
	}

	StackUsingMaxHeap(int maxSize) {
		this.heapList = new HeapNode[maxSize];
		this.maxSize = maxSize;
	}

	public static void main(String[] args) {
		StackUsingMaxHeap stack = new StackUsingMaxHeap(5);

		stack.push(56);
		stack.push(38);
		stack.push(15);
		stack.push(10);
		stack.push(5);
		stack.push(5);

		System.out.println("TOP: " + stack.top());

		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
