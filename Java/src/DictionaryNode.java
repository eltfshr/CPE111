
public class DictionaryNode implements Comparable<DictionaryNode> {
	private String word;
	private String definition;
	private String type;
	
	public DictionaryNode(String str) {
		String[] tmp = str.split(",");
		word = tmp[0].trim().replaceAll("\\s+", " ");
		definition = tmp[1].trim().replaceAll("\\s+", " ");
		type = tmp[2].trim().replaceAll("\\s+", " ");
	}
	
	private DictionaryNode() {} 
	
	public void printNode() {
		System.out.printf("%s    %s(%s)\n", word, definition, type);
	}
	
	public int compareTo(DictionaryNode bar) {
		return word.compareToIgnoreCase(bar.word);
	}
	
	public boolean isEqual(DictionaryNode foo) {
		if (word.equalsIgnoreCase(foo.word) && definition.equalsIgnoreCase(foo.definition) && type.equalsIgnoreCase(foo.type)) {
			return true;
		}
		return false;
	}
	
	public boolean isWordEqual(DictionaryNode bar) {
		if (word.equalsIgnoreCase(bar.word)) {
			return true;
		}
		return false;
	}
	
	public static DictionaryNode newWord(String word) {
		DictionaryNode x = new DictionaryNode();
		x.word = word;
		x.definition = "";
		x.type = "";
		return x;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public String getType() {
		return type;
	}
}
