import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Assignment8 {

	public static HashMap<String, BNode> readFile(String fileName) {
		HashMap<String, BNode> dictionary = new HashMap<String, BNode>();
		int count = 0;
		int meaningSize = 0;
		int maxMeaning = 1;
		String maxWord = "";
		BNode maxNode;
		try {
			String str;
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			br.read();
			while ((str = br.readLine()) != null) {
				count++;
				String[] tmp = str.split(",");
				String word = tmp[0].trim().replaceAll("\\s+", " ");
				String mean = tmp[1].trim().replaceAll("\\s+", " ") + "(" + tmp[2].trim().replaceAll("\\s+", " ") + ")";
				BNode x = dictionary.get(word.toLowerCase());
				if (x != null) {
					if (!(x.isMeaningEqual(mean))) {
						x.addMeaning(mean);
					}
				} else {
					BNode node = new BNode(word, mean);
					dictionary.put(word.toLowerCase(), node);
				}			
			}
			br.close();
		} catch (Exception e){
			System.out.println("Error Reading File");
		}
		for (BNode node: dictionary.values()) {
			int size = node.getMeaning().size();
			if (size > maxMeaning) {
				maxMeaning = size;
				maxWord = node.getWord();
			}
			meaningSize += size;
		}
		System.out.println("Total read " + count + " records");
		System.out.println("Total word size " + dictionary.size() + " records");
		System.out.println("Total meaning size " + meaningSize + " records");
		System.out.println(maxWord + " have " + maxMeaning + " meanings");
		maxNode = dictionary.get(maxWord.toLowerCase());
		for (int i = 0; i < maxNode.getMeaning().size(); i++) {
			System.out.printf("%d) %s    %s\n", i + 1, maxWord, maxNode.getMeaning().get(i));
		}
		return dictionary;
	}
	
	public static void findWord(HashMap<String, BNode> dictionary, String word) {
		BNode node = dictionary.get(word.toLowerCase());
		if (node != null) {
			ArrayList<String> meaning = node.getMeaning();
			System.out.println("found " + word + " " + meaning.size() + " tokens");
			for (int i = 0; i < meaning.size(); i++) {
				System.out.printf("%2d) %s    %s\n",i + 1, node.getWord(), meaning.get(i));
			}
		} else {
			System.out.println(word + " not found");
		}
	}
	
	public static void main(String[] args) {
		HashMap<String, BNode> dictionary = new HashMap<String, BNode>();
		dictionary = readFile("utf8lexitron.csv");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		boolean isEnd = false;
		String str;
		try {
			while (!isEnd) {
				System.out.printf("Enter Token : ");
				str = input.readLine();
				str = str.trim().replaceAll("\\s+", " ");
				findWord(dictionary, str);
				if (str.equalsIgnoreCase("end")) {
					isEnd = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Invalid input");
		}
		System.out.println("End Program");
		System.out.println("\nProgram written by Nathee Jaywaree 63070501035");
	}

}
