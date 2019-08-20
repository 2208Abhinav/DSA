public class MaxHeap {
	int[] heapList;
	int size, maxSize;

	MaxHeap(int root, int maxSize) {
		this.heapList = new int[maxSize];
		this.heapList[0] = root;
		this.maxSize = maxSize;
		this.size = 1;
	}

	// return index of parent in heapList for element
	// having index i in heapList
	public int parent(int i) {
		return (i-1)/2;
	}

	// return index of left child in heapList for element
	// having index i in healList
	public int leftChild(int i) {
		return 2*i + 1;
	}

	// return index of right child in heapList for element
	// having index i in healList
	public int rightChild(int i) {
		return 2*i + 2;
	}

	public void swapUp(int value) {
		int iOfNew = size-1;
		while(heapList[parent(iOfNew)] < value && iOfNew != 0) {
			int parentIndex = parent(iOfNew);

			// swap value with parent
			int temp = heapList[parentIndex];
			heapList[iOfNew] = temp;
			heapList[parentIndex] = value;
			iOfNew = parentIndex;
		}
	}

	public void insert(int value) {
		// If heap is already full then return
		if (size == maxSize) return;

		heapList[size] = value;
		size = size + 1;

		swapUp(value);
	}

	public int maxChild(int i) {
		// left child may exist without right child beacuse tree
		// is completed, in that case return left child index
		if (rightChild(i) >= size) return leftChild(i);
		else if (heapList[leftChild(i)] > heapList[rightChild(i)]) return leftChild(i);
		return rightChild(i);
	}

	public void swapDown(int i) {
		// restore heap structure
		int toBeSwappedIndex = i;
		int maxChildIndex = maxChild(toBeSwappedIndex);

		while(maxChildIndex < size && heapList[maxChildIndex] > heapList[toBeSwappedIndex]) {
			int temp = heapList[maxChildIndex];
			heapList[maxChildIndex] = heapList[toBeSwappedIndex];
			heapList[toBeSwappedIndex] = temp;

			toBeSwappedIndex = maxChildIndex;
			maxChildIndex = maxChild(toBeSwappedIndex);
		}
	}

	// for max heap delete operation deletes element with
	// max value
	public int delete() {
		// swap root with last element
		int deletedVal = heapList[0];
		heapList[0] = heapList[size-1];
		heapList[size-1] = 0;
		size = size - 1;

		swapDown(0);

		return deletedVal;
	}

	public static MaxHeap heapify(int[] items) {
		MaxHeap maxHeap = new MaxHeap(0, items.length);
		maxHeap.heapList = items;
		int itemsCount = items.length;
		maxHeap.size = itemsCount;

		for(int i = itemsCount-1; i >= 0; i--) {
			maxHeap.swapDown(i);
		}

		return maxHeap;
	}

	public static MaxHeap merge2Heaps(MaxHeap heap1, MaxHeap heap2) {
		int[] mergedArr = new int[heap1.size + heap2.size];

		for(int i = 0; i < heap1.size; i++) {
			mergedArr[i] = heap1.heapList[i];
		}

		for(int i = 0; i < heap2.size; i++) {
			mergedArr[heap1.size + i] = heap2.heapList[i];
		}

		MaxHeap mergedHeap = heapify(mergedArr);

		return mergedHeap;
	}

	public void printHeap() {
		for(int i = 0; i < size; i++) {
			System.out.println(heapList[i]);
		}
	}

	// function to find minimum element in max heap
	public int findMin() {
		// startIndex is the index where leaf nodes start in heap list
		int startIndex = parent(size-1) + 1;
		int endIndex = size-1;
		int min = heapList[startIndex];
		startIndex++;

		while (startIndex <= endIndex) {
			if (heapList[startIndex] < min) min = heapList[startIndex];
			startIndex++;
		}

		return min;
	}

	public static void printDescendingPairs(int[] arrA, int[] arrB) {
		MaxHeap heapA = heapify(arrA);
		MaxHeap heapB = heapify(arrB);

		int n = arrA.length;

		for(int i = 0; i < n; i++) {
			System.out.println("(" + heapA.delete() + ", " + heapB.delete() + ")");
		}
	}

	public static void main(String[] args) {
		// MaxHeap maxHeap = new MaxHeap(-56, 6);
		// maxHeap.insert(10);
		// maxHeap.insert(60);
		// maxHeap.insert(70);
		// maxHeap.insert(-40);
		// maxHeap.insert(65);

		// maxHeap.printHeap();

		// System.out.println("After deleting: " + maxHeap.delete());
		// maxHeap.printHeap();
		// System.out.println("After deleting: " + maxHeap.delete());
		// maxHeap.printHeap();
		// System.out.println("After deleting: " + maxHeap.delete());
		// maxHeap.printHeap();
		// System.out.println("After deleting: " + maxHeap.delete());
		// maxHeap.printHeap();
		// System.out.println("After deleting: " + maxHeap.delete());
		// maxHeap.printHeap();
		// System.out.println("After deleting: " + maxHeap.delete());
		// maxHeap.printHeap();

		// maxHeap.insert(-56);
		// maxHeap.insert(10);
		// maxHeap.insert(60);
		// maxHeap.insert(70);
		// maxHeap.insert(-40);
		// maxHeap.insert(65);
		// System.out.println("After inserting again:");
		// maxHeap.printHeap();

		// int[] items = new int[]{-56, 10, 60, 70, -40, 65};
		// MaxHeap maxHeap2 = heapify(items);
		// maxHeap2.printHeap();

		// System.out.println("Minimum element: " + maxHeap2.findMin());

		// MaxHeap heap1 = new MaxHeap(100, 5);
		// heap1.insert(110);
		// heap1.insert(56);
		// heap1.insert(10);
		// heap1.insert(15);

		// System.out.println("Heap 1");
		// heap1.printHeap();

		// MaxHeap heap2 = new MaxHeap(50, 4);
		// heap2.insert(90);
		// heap2.insert(38);
		// heap2.insert(140);

		// System.out.println("Heap 2");
		// heap2.printHeap();

		// MaxHeap mergedHeap = merge2Heaps(heap1, heap2);
		// System.out.println("Merged heap:");
		// mergedHeap.printHeap();

		int[] arrA = new int[]{34, 56, 89, 10, 90, -56};
		int[] arrB = new int[]{1, 30, 56, 38, 100, 65};
		printDescendingPairs(arrA, arrB);
	}
}
