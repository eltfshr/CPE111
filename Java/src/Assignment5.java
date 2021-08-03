import java.util.Scanner;

public class Assignment5 {

	public static void main(String[] args) {
		boolean isContinue = true;
		Scanner input = new Scanner(System.in);
		while (isContinue) {
			CalculatorExpression c = new CalculatorExpression();
			System.out.printf("expression> ");
			boolean isSuccess = c.setExpression(input);
			if (isSuccess) {
				c.splitExpression();
				int action = c.checkStates();
				if (action == -1) {
					isContinue = false;
				} else {
					System.out.printf("answer> ");
					if (action == 1) {
						PostFixCalculator pc = new PostFixCalculator(c.tokens);
						pc.setPostFixExpression();
						try {
							System.out.printf("%.5f\n", CalculatorExpression.setAns(pc.calculate()));
						} catch (Exception e) {
							System.out.printf("error\n");
						}		
					} else if (action == 2) {
						System.out.printf("token = sin cos tan asin acos atan sqrt log exp abs + - * / ^ ( ) ans pi e g\n");
					} else if (action == 0) {
						System.out.printf("error\n");
					}
				}
			} else {
				System.out.printf("error\n");
			}
		}
		input.close();
		System.out.println("End Program");
		System.out.println("Program written by Nathee Jaywaree 63070501035");
	}
}
