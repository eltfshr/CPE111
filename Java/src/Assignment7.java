import java.util.TreeSet;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Assignment7 {

	public static TreeSet<BNode> readFile(String fileName) {
		TreeSet<BNode> dictionary = new TreeSet<BNode>();
		int count = 0;
		int count2 = 0;
		int max = 1;
		BNode maxNode;
		String maxWord = "";
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
				BNode x = dictionary.floor(BNode.newWord(str));
				if (x != null && x.getWord().equalsIgnoreCase(word) && !(x.isMeaningEqual(mean))) {
					x.addMeaning(mean);
				} else {
					BNode node = new BNode(word, mean);
					dictionary.add(node);
				}			
			}
			br.close();
		} catch (Exception e){
			System.out.println("Error Reading File");
		}
		for (BNode x: dictionary) {
			int meaningSize = x.getMeaning().size();
			if (meaningSize > max) {
				max = meaningSize;
				maxWord = x.getWord();
			}
			count2 += meaningSize;
		}
		System.out.println("Total read " + count + " records");
		System.out.println("Total word size " + dictionary.size() + " records");
		System.out.println("Total meaning size " + count2 + " records");
		System.out.println(maxWord + " have " + max + " meanings");
		maxNode = dictionary.floor(BNode.newWord(maxWord));
		for (int i = 0; i < maxNode.getMeaning().size(); i++) {
			System.out.printf("%d) %s    %s\n", i + 1, maxWord, maxNode.getMeaning().get(i));
		}
		return dictionary;
	}
	
	public static void findWord(TreeSet<BNode> dictionary, String word) {
		BNode x = dictionary.floor(BNode.newWord(word));
		if (x.getWord().equalsIgnoreCase(word)) {
			ArrayList<String> meaning = x.getMeaning();
			System.out.println("found " + word + " " + meaning.size() + " tokens");
			for (int i = 0; i < meaning.size(); i++) {
				System.out.printf("%d) %s   "
						+ " %s\n", i + 1, x.getWord(), meaning.get(i));
			}
		} else {
			System.out.println(word + " not found");
		}
	}
	
	public static void main(String[] args) {
		boolean isEnd = false;
		TreeSet<BNode> dictionary = readFile("utf8lexitron.csv");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String str;		
		try {
			while (!isEnd) {
				System.out.printf("Enter token: ");
				str = input.readLine();
				str = str.trim().replaceAll("\\s+", " ");
				findWord(dictionary, str);
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
