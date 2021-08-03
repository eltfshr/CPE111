import java.util.Scanner;
import java.io.IOException;

public class CalculatorExpression {
	int count;
	String expression;
	CalculatorToken[] tokens;
	static double ans;
	static boolean isAnsAvailable = false;
	
	public CalculatorExpression () {
		expression = new String();
	}
	
	public boolean setExpression(Scanner input) {
		boolean isCorrect = true;
		try {
			expression = input.nextLine();
			if (expression.equals("")) {
				throw new IOException();
			}
			processExpression();
		} catch (Exception e) {
			isCorrect = false;
		}
		return isCorrect;
	}
	
	private void processExpression() {
		String tmp = "";
		String operators = "+-*/()^~";
		tmp = tmp.concat("$ ");
		for (int i = 0; i < expression.length(); i++) {
			if (operators.contains(Character.toString(expression.charAt(i)))) {
				tmp = tmp.concat(" ")
						 .concat(Character.toString(expression.charAt(i)))
						 .concat(" ");
			} else {
				tmp = tmp.concat(Character.toString(expression.charAt(i)).toLowerCase());
			}
		}
		expression = tmp.trim().concat(" @");
	}
	
	public void printExpression() {
		System.out.println(expression);
	}
	
	public void splitExpression() {
		String tmp[] = expression.split("\\s+");
		count = tmp.length;
		tokens = new CalculatorToken[count];
		for (int i = 0; i < count; i++) {
			tokens[i] = new CalculatorToken(tmp[i]);
		}
	}
	
	private boolean checkStatesOrder(int i, int state, int nextState) {
		switch (state) {
			case 0:
				if (nextState == 7 || nextState == 6 || nextState == 1 || nextState == 5) {
					return true;
				} else if (nextState == 2 && tokens[i + 1].subState == 1) {
					tokens[i + 1].changeToSignState();
					return true;
				} else {
					return false;
				}
			case 1:
				if (nextState == 2 || nextState == 3 || nextState == 4 || nextState == 8 || nextState == 9) {
					if (tokens[i].subState == 1) {
						if (!isAnsAvailable) {
							return false;
						}
						tokens[i].value = ans;
					}
					return true;
				} else {
					return false;
				}
			case 2:
			case 3:
			case 4:
				if (nextState == 1 || nextState == 6 || nextState == 7 || nextState == 5) {
					return true;
				} else if (nextState == 2 && tokens[i + 1].subState == 1) {
					tokens[i + 1].changeToSignState();
					return true;
				} else {
					return false;
				}
			case 5:
				if (nextState == 1 || nextState == 6 || nextState == 7) {
					return true;
				} else {
					return false;
				}
			case 6:
				if (nextState == 7) {
					return true;
				} else {
					return false;
				}
			case 7:
				if (nextState == 7 || nextState == 6 || nextState == 1 || nextState == 5) {
					return true;
				} else if (nextState == 2 && tokens[i + 1].subState == 1) {
					tokens[i + 1].changeToSignState();
					return true;
				} else {
					return false;
				}
			case 8:
				if (nextState == 2 || nextState == 3 || nextState == 4 || nextState == 8 || nextState == 9) {
					return true;
				} else {
					return false;
				}
			case 9:
				return false;
			default:
				return false;
		}
	}
	
	public int checkStates() {
		if (count > 3) {
			boolean isSuccess = true;
			int bracketCount = 0;
			for (int i = 0; i < count - 1 && isSuccess && bracketCount >= 0; i++) {
				int state = tokens[i].checkState();
				if (state == 7) {
					bracketCount++;
				} else if (state == 8) {
					bracketCount--;
				}
				isSuccess = checkStatesOrder(i, state , tokens[i + 1].checkState());
			}
			if (isSuccess && bracketCount == 0) {
				return 1;
			} else {
				return 0;
			}
		} else {
			int state = tokens[1].checkState();
			if (state == 10) {
				return 2;
			} else if (state == 11) {
				return -1;
			} else if (state == 1) {
				if (tokens[1].subState == 1) {
					if (!isAnsAvailable) {
						return 0;
					}
					tokens[1].value = ans;
				}
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	public static double setAns(double ans) {
		isAnsAvailable = true;
		CalculatorExpression.ans = ans;
		return ans;
	}
	
}
