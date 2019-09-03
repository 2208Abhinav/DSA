import java.util.*; 
import java.lang.*; 
import java.io.*; 

class Suffix { 
	int index; // To store original index 
	int rank1, rank2; // To store ranks and next rank pair 
}

class SortComparator implements Comparator<Suffix> {
	@Override
	public int compare(Suffix obj1, Suffix obj2) {
		return (obj1.rank1 == obj2.rank1) ? (obj1.rank2 <= obj2.rank2 ? -1 : 1) :
			(obj1.rank1 < obj2.rank1 ? -1 : 1);
	}
}

public class SuffixArray {
	static int[] buildSuffixArray(String txt, int n) {
		Suffix[] suffixes = new Suffix[n];

		for (int i = 0; i < n; i++) {
			suffixes[i] = new Suffix();
			suffixes[i].index = i;
			suffixes[i].rank1 = txt.charAt(i) - 'a';
			suffixes[i].rank2 = ((i+1) < n)? (txt.charAt(i + 1) - 'a'): -1;
		}

		Arrays.sort(suffixes, new SortComparator());

		for (int k = 4; k < 2*n; k = k*2) {
			int rank1 = suffixes[0].rank1;
			int rank2 = suffixes[0].rank2;

			suffixes[0].rank1 = 0;
			int suffixIndex = suffixes[0].index;
			suffixes[0].rank2 = (suffixIndex + k/2 < n) ? txt.charAt(suffixIndex+k/2) : -1;

			for(int i = 1; i < n; i++) {
				int currentRank1 = suffixes[i].rank1;
				int currentRank2 = suffixes[i].rank2;
				int currentIndex = suffixes[i].index;

				if(rank1 == currentRank1 && rank2 == currentRank2) {
					suffixes[i].rank1 = suffixes[i-1].rank1;
				} else {
					suffixes[i].rank1 = suffixes[i-1].rank1 + 1;
				}
				suffixes[i].rank2 = (currentIndex + k/2 < n) ? txt.charAt(currentIndex+k/2) : -1;

				rank1 = currentRank1;
				rank2 = currentRank2;
			}

			Arrays.sort(suffixes, new SortComparator());
		}

		int[] suffixArr = new int[n];
		for (int i = 0; i < n; i++)
			suffixArr[i] = suffixes[i].index;

		return suffixArr;
	} 

	static void printArr(int arr[], int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	} 

	public static void main(String[] args) { 
		String txt = "pqrabcz";
		int n = txt.length();
		int[] suffixArr = buildSuffixArray(txt, n);
		printArr(suffixArr, n);
	} 
}
