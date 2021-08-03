public class Assignment2 {

	public static void main(String[] args) {
		final int SIZE = 10000;
		DataArray input = new DataArray(SIZE);
		input.readFile("test.csv");
		DataArray copy = new DataArray(SIZE);
			
		//scan sort
		copy.copyFrom(input);
		double scanTime1 = copy.scanSort(0, SIZE - 1, true);
		double scanTime2 = copy.scanSort(0, SIZE, true);
		double scanTime3 = copy.scanSort(0, SIZE, false);
		
		//selection sort
		copy.copyFrom(input);
		double selectionTime1 = copy.selectionSort(0, SIZE - 1, true);
		double selectionTime2 = copy.selectionSort(0, SIZE, true);
		double selectionTime3 = copy.selectionSort(0, SIZE, false);
		
		//selection sort
		copy.copyFrom(input);
		double insertionTime1 = copy.insertionSort(0, SIZE - 1, true);
		double insertionTime2 = copy.insertionSort(0, SIZE, true);
		double insertionTime3 = copy.insertionSort(0, SIZE, false);
		
		//bubble sort
		copy.copyFrom(input);
		double bubbleTime1 = copy.bubbleSort(0, SIZE - 1, true);
		double bubbleTime2 = copy.bubbleSort(0, SIZE, true);
		double bubbleTime3 = copy.bubbleSort(0, SIZE, false);
			
		//time table in milliseconds
		System.out.println("    Sort         Random data (n)  Insert data (n+1)   Descending (n)");
		System.out.printf("Scan Sort\t%10.2f\t%12.2f\t%15.2f\n",scanTime1/1e6,scanTime2/1e6,scanTime3/1e6);
		System.out.printf("Selection Sort\t%10.2f\t%12.2f\t%15.2f\n",selectionTime1/1e6,selectionTime2/1e6,selectionTime3/1e6);
		System.out.printf("Insertion Sort\t%10.2f\t%12.2f\t%15.2f\n",insertionTime1/1e6,insertionTime2/1e6,insertionTime3/1e6);
		System.out.printf("Bubble Sort\t%10.2f\t%12.2f\t%15.2f\n",bubbleTime1/1e6,bubbleTime2/1e6,bubbleTime3/1e6);
		
		//time table in nanoseconds
		/*System.out.println("    Sort        Random data (n)   Insert data (n+1)   Descending (n)");
		System.out.printf("Scan Sort\t%10.2f\t%12.2f\t%15.2f\n",scanTime1,scanTime2,scanTime3);
		System.out.printf("Selection Sort\t%10.2f\t%12.2f\t%15.2f\n",selectionTime1,selectionTime2,selectionTime3);
		System.out.printf("Insertion Sort\t%10.2f\t%12.2f\t%15.2f\n",insertionTime1,insertionTime2,insertionTime3);
		System.out.printf("Bubble Sort\t%10.2f\t%12.2f\t%15.2f",bubbleTime1,bubbleTime2,bubbleTime3);
*/
		System.out.println("Program written by 63070501035 Nathee Jaywaree");
	}	
}