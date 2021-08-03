import java.util.ArrayList;

public class BNode implements Comparable<BNode> {
	private String word;
	private ArrayList<String> meaning;
	
	public BNode(String word, String mean) {
		this.word = word;
		meaning = new ArrayList<String>();
		meaning.add(mean);
	}
	
	private BNode() {}
	
	public void printNode() {
		for (String x: meaning) {
			System.out.printf("%s    %s\n", word, x);
		}
	}
	
	public int compareTo(BNode x) {
		return word.compareToIgnoreCase(x.word);
	}
	
	public static BNode newWord(String str) {
		BNode x = new BNode();
		x.word = str.split(",")[0].trim().replaceAll("\\s+", " ");
		return x;
	}
	
	public boolean isMeaningEqual(String mean) {
		return meaning.contains(mean);
	}
	
	public void addMeaning(String mean) {
		meaning.add(mean);
	}
	
	public String getWord() {
		return word;
	}
	
	public ArrayList<String> getMeaning() {
		return meaning;
	}
}
