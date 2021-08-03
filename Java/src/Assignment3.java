
public class Assignment3 {

	public static void main(String[] args) {
		final int SIZE = 100000;
		DataArray input = new DataArray(SIZE);
		input.readFile("test.csv");
		DataArray copy = new DataArray(SIZE);
		
		//shell sort number
		copy.copyFrom(input);
		double shellTime1 = copy.shellSort(0, SIZE - 1, true, true);
		System.out.println("Shell Sort Number");
		copy.printTestCase();
		
		//shell sort string
		copy.copyFrom(input);
		double shellTime2 = copy.shellSort(0, SIZE - 1, true, false);
		System.out.println("Shell Sort String");
		copy.printTestCase();
		
		//merge sort number
		copy.copyFrom(input);
		double mergeTime1 = copy.mergeSort(0, SIZE - 1, true, true);
		System.out.println("Merge Sort Number");
		copy.printTestCase();
		
		//merge sort string
		copy.copyFrom(input);
		double mergeTime2 = copy.mergeSort(0, SIZE - 1, true, false);
		System.out.println("Merge Sort String");
		copy.printTestCase();

		//quick sort partition number
		copy.copyFrom(input);
		double quickTime1 = copy.quickSortPartition(0, SIZE - 1, true);
		System.out.println("Quick Sort Partition Number");
		copy.printTestCase();
		
		//quick sort partition string
		copy.copyFrom(input);
		double quickTime2 = copy.quickSortPartition(0, SIZE - 1, false);
		System.out.println("Quick Sort Partition String");
		copy.printTestCase();
		
		//quick sort pivot number
		copy.copyFrom(input);
		double quickTime3 = copy.quickSortPivot(0, SIZE - 1, true);
		System.out.println("Quick Sort Pivot Number");
		copy.printTestCase();

		//quick sort pivot string
		copy.copyFrom(input);
		double quickTime4 = copy.quickSortPivot(0, SIZE - 1, false);
		System.out.println("Quick Sort Pivot String");
		copy.printTestCase();
		
		//library sort number
		copy.copyFrom(input);
		double libraryTime1 = copy.librarySort(0, SIZE - 1, true);
		System.out.println("Library Sort Number");
		copy.printTestCase();
		
		//library sort string
		copy.copyFrom(input);
		double libraryTime2 = copy.librarySort(0, SIZE - 1, false);
		System.out.println("Library Sort String");
		copy.printTestCase();
		
		System.out.printf("       Sort               Number(ms)      String(ms)\n");
		System.out.printf("Shell Sort\t\t%10.2f\t%10.2f\n", shellTime1/1e6, shellTime2/1e6);
		System.out.printf("Merge Sort\t\t%10.2f\t%10.2f\n", mergeTime1/1e6, mergeTime2/1e6);
		System.out.printf("Quick Sort Partition\t%10.2f\t%10.2f\n", quickTime1/1e6, quickTime2/1e6);
		System.out.printf("Quick Sort Pivot\t%10.2f\t%10.2f\n", quickTime3/1e6, quickTime4/1e6);
		System.out.printf("Library Sort\t\t%10.2f\t%10.2f\n", libraryTime1/1e6, libraryTime2/1e6);
		System.out.printf("\nProgram written by Nathee Jaywaree 63070501035");
	}

}
