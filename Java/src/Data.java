
public class Data {
	int no;
	long number;
	String string1;
	String string2;
	
	Data(int no, long number, String string1, String string2) {
		this.no = no;
		this.number = number;
		this.string1 = string1;
		this.string2 = string2;
	}
	
	public void printData() {
		System.out.printf("%5d  %d  %s  %s\n",no,number,string1,string2);
	}
}

