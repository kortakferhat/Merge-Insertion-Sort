
import java.util.Random;
import java.util.Scanner;
import java.lang.management.*;

public class Test {
	private static Random rand = new Random();
	private static int[] aThousand = new int[1000];
	private static int[] tenThousand = new int[10000];
	private static int[] oneHundredThousand = new int[100000];
	private static int[] aMillion = new int[1000000];
	private static long startTime, endTime;

	public static long getCPUTime() {
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadCpuTime() : 0L;
	}

	public static void main(String[] args) {
		// Fill the necessary parts
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter a number to select a scenario -> \n(Best : 1 | Worst : 2 | Avarage : 3) : ");
		int input = sc.nextInt();
		if (input == 1)
			FillArrayforBestCase();
		else if (input == 2)
			FillArrayforWorstCase();
		else if (input == 3)
			FillArrayforAvarageCase();
		System.out.print(
				"Which size will you use? \nEnter a number ->  (A Thousand : '1' | Ten Thousand : '2' | One Hundred Thousand : '3' | A Billion : '4') : ");
		int size = sc.nextInt();
		System.out.print("Which sort method will you use? \nEnter a number -> (Insertion Sort : 1 | MergeSort : 2) : ");
		int method = sc.nextInt();

		// --------------------------------------------
		// StartTime will be change at the Sort method's top line

		if (method == 1) {
			// For insertion sort
			if (size == 1) {
				InsertionSort(aThousand);
			} else if (size == 2) {
				InsertionSort(tenThousand);
			} else if (size == 3) {
				InsertionSort(oneHundredThousand);
			} else if (size == 4) {
				InsertionSort(aMillion);
			}
		} else if (method == 2) {
			// For MergeSort
			if (size == 1) {
				startMergeSort(aThousand);
			} else if (size == 2) {
				startMergeSort(tenThousand);
			} else if (size == 3) {
				startMergeSort(oneHundredThousand);
			} else if (size == 4) {
				startMergeSort(aMillion);
			}
		}
		// Sort

		endTime = System.currentTimeMillis();
		// --------------------------------------------
		System.out
				.println("Random Ordered Sorting Time : " + ((double) endTime - (double) startTime) + " milliseconds");
	}

	public static void startMergeSort(int[] selectedArray) {
		System.out.println("Sorting operation have just started...");
		startTime = System.currentTimeMillis(); // gives CPU time in nanoseconds
		mergeSort(selectedArray, 1, selectedArray.length);
		System.out.println("Merge Sort operation successfully completed.");
	}

	// DIVIDE & CONQUER
	private static void mergeSort(int[] selectedArray, int left, int right) {

		if (left < right) {
			int center = (left + right) / 2; // q=(p+r)/2 Runtime-> 0(1)
			// Runtime = 2T(n/2) | Recursive Methods for traverses the tree
			mergeSort(selectedArray, left, center); // (ARRAY,LEFT,RIGHT)
			mergeSort(selectedArray, center + 1, right);
			merge(selectedArray, left, center, right); // Runtime -> 0(n)
		}
	}

	// Runtime -> O(n)
	private static void merge(int[] selectedArray, int left, int right, int rightEnd) {
		// Merge(Array,p,q,r)
		int n1 = right - left + 1;
		int n2 = rightEnd - right; // Define the boundaries
		int[] leftArray = new int[n1 + 1];
		int[] rightArray = new int[n2 + 1];

		for (int i = 0; i < n1; i++)
			leftArray[i] = selectedArray[left + i - 1];

		for (int j = 0; j < n2; j++)
			rightArray[j] = selectedArray[right + j];

		leftArray[n1] = Integer.MAX_VALUE;
		rightArray[n2] = Integer.MAX_VALUE;

		int i = 0;
		int j = 0;

		for (int k = left - 1; k < rightEnd; k++) {
			if (leftArray[i] <= rightArray[j]) {
				selectedArray[k] = leftArray[i];
				i++;
			} else {
				selectedArray[k] = rightArray[j];
				j++;
			}
		}
	}

	public static void InsertionSort(int[] selectedArray) {
		System.out.println("Sorting operation have just started...");
		startTime = System.currentTimeMillis(); // gives CPU time in nanoseconds
		for (int i = 1; i < selectedArray.length; i++) {
			int current = selectedArray[i]; // key value
			int j = i;
			// create right place by moving elements
			while (j > 0 && selectedArray[j - 1] > current) {
				// move
				selectedArray[j] = selectedArray[j - 1];
				j--;
			}
			// found the right place, insert now
			selectedArray[j] = current;
		}
		System.out.println();
	}

	private static void FillArrayforAvarageCase() {
		for (int i = 0; i < 10000000; i++) {
			if (i < 1000)
				aThousand[i] = rand.nextInt(1000);
			if (i < 10000)
				tenThousand[i] = rand.nextInt(10000);
			if (i < 100000)
				oneHundredThousand[i] = rand.nextInt(1000);
			if (i < 1000000)
				aMillion[i] = rand.nextInt(100);
		}
	}

	private static void FillArrayforWorstCase() {
		for (int i = 0; i < 10000000; i++) {
			if (i < 1000)
				aThousand[i] = 1000 - i;
			if (i < 10000)
				tenThousand[i] = 10000 - i;
			if (i < 100000)
				oneHundredThousand[i] = 100000 - i;
			if (i < 1000000)
				aMillion[i] = 1000000 - i;
		}

	}

	private static void FillArrayforBestCase() {
		for (int i = 0; i < 10000000; i++) {
			if (i < 1000)
				aThousand[i] = i + 1;
			if (i < 10000)
				tenThousand[i] = i + 1;
			if (i < 100000)
				oneHundredThousand[i] = i + 1;
			if (i < 1000000)
				aMillion[i] = i + 1;
		}
	}
}
