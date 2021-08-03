import java.util.ArrayList;
import java.util.Collections;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Assignment6 {

	public static ArrayList<DictionaryNode> readFile(String fileName) {
		ArrayList<DictionaryNode> dictionary = new ArrayList<DictionaryNode>();
		try {
			String str;
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			br.read();
			while ((str = br.readLine()) != null) {
				DictionaryNode node = new DictionaryNode(str);
				dictionary.add(node);
			}
			br.close();
		} catch (Exception e){
			System.out.println("Error Reading File");
		}
		System.out.println("Total read " + dictionary.size() + " records");
		return dictionary;
	}
	
	public static void removeDuplicates(ArrayList<DictionaryNode> dictionary) {
		int duplicatesCount = 0;
		int maxEqualWordCount = 1;
		int i = 0;
		int max = 1;
		String maxWord = "";
		while (i < dictionary.size() - 1) {
			if (dictionary.get(i).isEqual(dictionary.get(i + 1))) {
				duplicatesCount++;
				dictionary.remove(i);
			} else {
				if (dictionary.get(i).isWordEqual(dictionary.get(i + 1))) {
					max++;
					if (max > maxEqualWordCount) {
						maxEqualWordCount = max;
						maxWord = dictionary.get(i).getWord();
					}
				} else {
					max = 1;
				}
				i++;
			}
		}
		System.out.println("Total duplicates found " + duplicatesCount + " records.");
		System.out.println("Total remaining size " + dictionary.size() + " records.");
		System.out.println("Maximum Meaning " + maxWord + " have " + maxEqualWordCount + " meaning.");
	}
	
	public static void findWord(ArrayList<DictionaryNode> dictionary, DictionaryNode d) {
		int i = Collections.binarySearch(dictionary, d);
		if (i < 0) {
			System.out.printf("%s not found\n", d.getWord());
		} else {
			int min = i;
			int max = i;
			while (min != 0 && dictionary.get(min).isWordEqual(dictionary.get(min - 1))) {
				min--;
			}
			while (max != dictionary.size() - 1 && dictionary.get(max).isWordEqual(dictionary.get(max + 1))) {
				max++;
			}
			System.out.printf("found %s %d tokens at %d - %d\n", d.getWord(), (max - min) + 1, min, max);
			for (int j = min; j <= max; j++) {
				System.out.printf("%d) ", j - min + 1);
				dictionary.get(j).printNode();
			}
		}		
	}
	
	public static void main(String[] args) {
		
		boolean isEnd = false;
		ArrayList<DictionaryNode> dictionary = readFile("utf8lexitron.csv");
		Collections.sort(dictionary);
		removeDuplicates(dictionary);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String str;		
		try {
			while (!isEnd) {
				System.out.printf("Enter token: ");
				str = input.readLine();
				str = str.trim().replaceAll("\\s+", " ");
				findWord(dictionary, DictionaryNode.newWord(str));
				if (str.equalsIgnoreCase("end")) {
					isEnd = true;
				}
			}
			input.close();
		} catch (Exception e) {
			System.out.println("Invalid input");
		}
		System.out.println("\nProgram written by Nathee Jaywaree 63070501035");
	}
}
