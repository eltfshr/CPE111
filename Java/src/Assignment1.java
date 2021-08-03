import java.util.Scanner;
import java.io.IOException;

public class Assignment1 {

	public static void main(String[] args) {
		int n = 0;
		char exit;
		long ans = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("My Recursion Programs.");
		System.out.println("Program calculate n! by recursion (n<=15)");		
		do {		
			n = getInt(15,0);
			ans = factorial(n);
			System.out.println("Complete calculation of " + n + "!, answer = " + ans);
			System.out.printf("press [y] to continue, others to exit. ");
			exit = input.next().charAt(0);
		} while (exit == 'y' || exit == 'Y');
		input.close();
		System.out.println("End Program");
		System.out.println("Program written by 63070501035 Nathee Jaywaree");
	}
	
	static int getInt(int max, int min) {
		int n = 0;
		Scanner in = new Scanner(System.in);
		boolean check = false;
		do {
			try {
				System.out.printf("Enter n = ");
				n = in.nextInt();
				if (n > max || n < min) { 
					throw new IOException();
				}
				check = true;
			} catch(Exception err) {
				System.out.printf("Input error, please enter number between %d-%d\n",min,max);		
				in.nextLine();
			}
		} while (!check);
		return n;
	}
	
	static long factorial(int n) {
		long ans = 0;
		if(n == 0) {
			System.out.println("0! is base case return answer of 0! = 1");
			System.out.println("Calculate 0! complete.");
			return 1;
		}
		else {
			System.out.printf("%d! is recursive case. Answer = %d * recursion of %d!\n",n,n,n-1);
			System.out.println("	Recursion to calculate " + (n-1) + "!");
			ans = factorial(n-1);
			if(n-1 != 0) {
				System.out.println("Calculate " + (n-1) + "! complete.");
			}			
			System.out.printf("	Return answer from %d! = %d to calculate %d! = [%d * %d!] = %d * %d = %d\n",
					n-1,ans,n,n,n-1,n,ans,ans*n);
			return n * ans;
		}
	}
}
