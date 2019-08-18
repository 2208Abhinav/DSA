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

	public void insert(int value) {
		// If heap is already full then return
		if (size == maxSize) return;

		heapList[size] = value;
		int iOfNew = size;
		size = size + 1;

		while(heapList[parent(iOfNew)] < value && iOfNew != 0) {
			int parentIndex = parent(iOfNew);

			// swap value with parent
			int temp = heapList[parentIndex];
			heapList[iOfNew] = temp;
			heapList[parentIndex] = value;
			iOfNew = parentIndex;
		}
	}

	public void printHeap() {
		for(int i = 0; i < size; i++) {
			System.out.println(heapList[i]);
		}
	}

	public static void main(String[] args) {
		MaxHeap maxHeap = new MaxHeap(-56, 6);
		maxHeap.insert(10);
		maxHeap.insert(60);
		maxHeap.insert(70);
		maxHeap.insert(-40);
		maxHeap.insert(65);

		maxHeap.printHeap();
	}
}
