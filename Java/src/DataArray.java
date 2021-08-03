import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class DataArray {
	Data[] array;
	int count;
	Data[] temp;
	Data[] temp2;
	
	public DataArray(int count) {
		this.count = count;
		array = new Data[count];
		temp = new Data[count];
	}
		
	//read file
	public void readFile(String name) {
		Scanner reader;
		try {
			File fs = new File(name);
			reader = new Scanner(fs);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return;
		}
		for (int i = 0; reader.hasNextLine() && i < count;i++) {
			String tmp = reader.nextLine();
			String tmp2[] = tmp.split(",");
			array[i] = new Data(Integer.parseInt(tmp2[0]), Long.parseLong(tmp2[1]), tmp2[2], tmp2[3]);			
		}
		reader.close();
	}
	
	//copy array
	public void copyFrom(DataArray source) {
		for (int i = 0; i < count; i++) {
			array[i] = source.array[i];
		}
	}
	
	//print data
	public void printData(int count) {
		for (int i = 0; i < count; i++) {
			array[i].printData();
		}
	}
	
	//print test case
	public void printTestCase() {
		array[0].printData();
		array[49999].printData();
		array[99999].printData();
		System.out.printf("\n");
	}
	
	//swap data
	private void swap(int i, int j) {
		Data tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	//scan sort
	public double scanSort(int startLocation, int stopLocation, boolean isAscend) {
		double start = System.nanoTime();
		for (int i = startLocation;i < stopLocation - 1; i++) {
			for (int j = i + 1; j < stopLocation; j++) {
				if ((array[j].number > array[i].number) ^ isAscend) {
					swap(i,j);
				}
			}
		}
		double end = System.nanoTime();
		return end - start;
	}
	
	//selection sort
	public double selectionSort(int startLocation, int stopLocation, boolean isAscend) {
		double start = System.nanoTime();
		for (int i = startLocation; i < stopLocation - 1; i++) {
			int ref = i;
			for(int j = i + 1; j < stopLocation; j++) {
				if ((array[j].number > array[ref].number) ^ isAscend) {
					ref = j;
				}
			}
			swap(i,ref);
		}
		double end = System.nanoTime();
		return end - start;
	}
	
	//insertion sort
	public double insertionSort(int startLocation, int stopLocation, boolean isAscend) {
		double start = System.nanoTime();
		for (int i = startLocation + 1; i < stopLocation; i++) {
			Data x = array[i];
			int j = 0;
			for (j = i; j > 0 && (x.number > array[j-1].number) ^ isAscend; j--) {
				array[j] = array[j-1];
			}
			array[j] = x;
		}
		double end = System.nanoTime();
		return end - start;
	}
	
	//bubble sort
	public double bubbleSort(int startLocation, int stopLocation, boolean isAscend) {
		double start = System.nanoTime();
		boolean doMore = true;
		for (int i = startLocation; i < stopLocation - 1 && doMore; i++) {
			doMore = false;
			for (int j = stopLocation - 1; j > i; j--) {
				if ((array[j].number > array[j-1].number) ^ isAscend) {
					swap(j-1,j);
					doMore = true;
				}
			}			
		}
		double end = System.nanoTime();
		return end - start;
	}
	
	//shell sort
	public double shellSort(int startLocation, int stopLocation, boolean isAscend, boolean isNumber) {
		double start = System.nanoTime();
		int gap = stopLocation - startLocation + 1;
		boolean isChanged = false;
		if (isNumber) {
			do {		
				gap = gap / 2;
				do {
					isChanged = false;
					for (int i = startLocation; i < stopLocation - gap + 1; i++) {
						if ((array[i].number < array[i + gap].number) ^ isAscend) {
							swap(i, i + gap);
							isChanged = true;
						}
					}					
				} while (isChanged);
			} while (gap > 1);
		} else {
			do {		
				gap = gap / 2;
				do {
					isChanged = false;
					for (int i = startLocation; i < stopLocation - gap + 1; i++) {
						if ((array[i].string2.compareTo(array[i + gap].string2) < 0) ^ isAscend) {
							swap(i, i + gap);
							isChanged = true;
						}
					}					
				} while (isChanged);
			} while (gap > 1);
		}
		double end = System.nanoTime();
		return end - start;
	}
		
	//merge sort
	public double mergeSort(int startLocation, int stopLocation, boolean isAscend, boolean isNumber) {
		double start = System.nanoTime();
		if (startLocation < stopLocation) {
			int mid = (startLocation + stopLocation) / 2;
			mergeSort(startLocation, mid, isAscend, isNumber);
			mergeSort(mid + 1, stopLocation, isAscend, isNumber);
			mergeData(startLocation, mid, stopLocation, isAscend, isNumber);
		}
		double end = System.nanoTime();
		return end - start;
	}
	
	//merge data for merge sort
	public void mergeData(int startLocation, int mid, int stopLocation, boolean isAscend, boolean isNumber) {		
		int j = startLocation;
		int k = mid + 1;
		if (isNumber) {
			for (int i = 0; i <= stopLocation - startLocation; i++) {
				if (j <= mid && k <= stopLocation) {
					if ((array[j].number > array[k].number) ^ isAscend) {
						temp[i] = array[j++];
					} else {
						temp[i] = array[k++];
					}
				} else if (j > mid) {
					temp[i] = array[k++];
				} else if (k > stopLocation) {
					temp[i] = array[j++];
				}			
			}
		} else {
			for (int i = 0; i <= stopLocation - startLocation; i++) {
				if (j <= mid && k <= stopLocation) {
					if ((array[j].string2.compareTo(array[k].string2) > 0) ^ isAscend) {
						temp[i] = array[j++];
					} else {
						temp[i] = array[k++];
					}
				} else if (j > mid) {
					temp[i] = array[k++];
				} else if (k > stopLocation) {
					temp[i] = array[j++];
				}			
			}
		}
		
		for (int i = 0; i <= stopLocation - startLocation; i++) {
			array[startLocation + i] = temp[i];
		}
	}
	
	//quick sort partition
	public double quickSortPartition(int startLocation, int stopLocation, boolean isNumber) {
		double start = System.nanoTime();
		int i = startLocation;
		int j = stopLocation;
		if (startLocation < stopLocation) {
			if (isNumber) {
				do {
					while ((array[i].number <= array[j].number) && (i < j)) {
						j--;
					}
					if (array[i].number > array[j].number) {
						swap(i,j);
						i++;
					}
					while ((array[i].number <= array[j].number) && (i < j)) {
						i++;
					}
					if (array[i].number > array[j].number) {
						swap(i,j);
						j--;
					}
				} while (i < j);
				if (startLocation < j - 1) {
					quickSortPartition(startLocation, j - 1, isNumber);
				}
				if (i + 1 < stopLocation) {
					quickSortPartition(i + 1, stopLocation, isNumber);
				}
			} else {
				do {
					while ((array[i].string2.compareTo(array[j].string2) <= 0) && (i < j)) {
						j--;
					}
					if (array[i].string2.compareTo(array[j].string2) > 0) {
						swap(i,j);
						i++;
					}
					while ((array[i].string2.compareTo(array[j].string2) <= 0) && (i < j)) {
						i++;
					}
					if (array[i].string2.compareTo(array[j].string2) > 0) {
						swap(i,j);
						j--;
					}
				} while (i < j);
				if (startLocation < j - 1)
					quickSortPartition(startLocation, j - 1, isNumber);
				if (i + 1 < stopLocation)
					quickSortPartition(i + 1, stopLocation, isNumber);
			}
		}		
		double end = System.nanoTime();
		return end - start;
	}
	
	//quick sort pivot
	public double quickSortPivot(int startLocation, int stopLocation, boolean isNumber) {
		double start = System.nanoTime();
		int i = startLocation;
		int j = stopLocation;
		if (startLocation < stopLocation) {
			if (isNumber) {
				long pivot = array[(startLocation + stopLocation) / 2].number;
				while (i < j) {
					while (array[i].number < pivot)
						i++;
					while (array[j].number > pivot)
						j--;
					if (i <= j) {
						swap(i,j);
						i++;
						j--;
					}
				}
				if (startLocation < j) 
					quickSortPivot(startLocation, j, isNumber);
				if (i < stopLocation)
					quickSortPivot(i, stopLocation, isNumber);
			} else {
				String pivot = array[(startLocation + stopLocation) / 2].string2;
				while (i < j) {
					while (array[i].string2.compareTo(pivot) < 0) {
						i++;
					}						
					while (array[j].string2.compareTo(pivot) > 0) {
						j--;
					}						
					if (i <= j) {
						swap(i,j);
						i++;
						j--;
					}
				}
				if (startLocation < j) 
					quickSortPivot(startLocation, j, isNumber);
				if (i < stopLocation)
					quickSortPivot(i, stopLocation, isNumber);
			}
		} 
		
		double end = System.nanoTime();
		return end - start;
	}
	
	//sort by library
	public double librarySort(int startLocation, int stopLocation, boolean isNumber) {
		temp2 = new Data[stopLocation - startLocation + 1];
		for (int i = startLocation, j = 0; i <= stopLocation; i++, j++) {
			temp2[j] = array[i];
		}		
		double start = System.nanoTime();
		if (isNumber) {
			Arrays.sort(temp2, new SortByNumber());
		} else {
			Arrays.sort(temp2, new SortByString2());
		}
		double end = System.nanoTime();
		for (int i = startLocation, j = 0; i <= stopLocation; i++, j++) {
			array[i] = temp2[j];
		}
		return end - start;
	}
	
}

class SortByNumber implements Comparator<Data> {
	
	public int compare(Data a, Data b) {
		return a.number < b.number ? -1 : 1;
	}
}

class SortByString2 implements Comparator<Data> {
	
	public int compare(Data a, Data b) {
		return a.string2.compareTo(b.string2);
	}
}