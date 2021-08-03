import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.ArithmeticException;

public class PostFixCalculator {
	CalculatorToken[] infixExpression;
	Stack<CalculatorToken> operators;
	Queue<CalculatorToken> postFixExpression;
	Stack<CalculatorToken> result;
	
	PostFixCalculator(CalculatorToken[] infixExpression) {
		this.infixExpression = infixExpression;
		operators = new Stack<CalculatorToken>();
		postFixExpression = new LinkedList<CalculatorToken>();
		result = new Stack<CalculatorToken>();
	}
	
	private void addBrackets() {
		infixExpression[0].token = "(";
		infixExpression[0].state = 7;
		infixExpression[infixExpression.length - 1].token = ")";
		infixExpression[infixExpression.length - 1].state = 8;
	}
	
	public void printPostFixExpression() {
		int count = postFixExpression.size();
		Queue<CalculatorToken> tmp = new LinkedList<CalculatorToken>(postFixExpression);
		for (int i = 0; i < count ; i++) {
			System.out.printf("%s",tmp.remove().token);
		}
		System.out.printf("\n");
	}
	
	public void setPostFixExpression() {
		addBrackets();
		for (int i = 0; i < infixExpression.length; i++) {
			if (infixExpression[i].state == 7) {
				operators.push(infixExpression[i]);
			} else if (infixExpression[i].state == 1) {
				postFixExpression.add(infixExpression[i]);
			} else if (infixExpression[i].state == 8) {
				while (operators.peek().state != 7) {
					postFixExpression.add(operators.pop());
				}
				operators.pop();
			} else {
				while (operators.peek().state != 7 && infixExpression[i].state <= operators.peek().state) {
					postFixExpression.add(operators.pop());
				}
				operators.push(infixExpression[i]);
			}
		}
	}
	
	public double calculate() {
		int count = postFixExpression.size();
		CalculatorToken tmp;
		for (int i = 0; i < count; i++) {
			CalculatorToken c = postFixExpression.remove();
			if (c.state == 1) {
				result.push(c);
			} else if (c.state == 5) {
				tmp = result.pop();
				tmp.value = tmp.value * -1;
				result.push(tmp);
			} else if (c.state == 6) {
				switch (c.subState) {
					case 0:
						tmp = result.pop();
						tmp.value = Math.sin((tmp.value) * Math.PI / 180);
						result.push(tmp);
						break;
					case 1:
						tmp = result.pop();
						tmp.value = Math.cos((tmp.value) * Math.PI / 180);
						result.push(tmp);
						break;
					case 2:
						tmp = result.pop();
						tmp.value = Math.tan((tmp.value) * Math.PI / 180);
						if (Double.isNaN(tmp.value) || Double.isInfinite(tmp.value)) {
							throw new ArithmeticException();
						}
						result.push(tmp);
						break;
					case 3:
						tmp = result.pop();
						tmp.value = Math.asin(tmp.value);
						tmp.value = tmp.value * 180 / Math.PI;
						if (Double.isNaN(tmp.value) || Double.isInfinite(tmp.value)) {
							throw new ArithmeticException();
						}
						result.push(tmp);
						break;
					case 4:
						tmp = result.pop();
						tmp.value = Math.acos(tmp.value);
						tmp.value = tmp.value * 180 / Math.PI;
						if (Double.isNaN(tmp.value) || Double.isInfinite(tmp.value)) {
							throw new ArithmeticException();
						}
						result.push(tmp);
						break;
					case 5:
						tmp = result.pop();
						tmp.value = Math.atan(tmp.value);
						tmp.value = tmp.value * 180 / Math.PI;
						if (Double.isNaN(tmp.value) || Double.isInfinite(tmp.value)) {
							throw new ArithmeticException();
						}
						result.push(tmp);
						break;
					case 6:
						tmp = result.pop();
						tmp.value = Math.sqrt(tmp.value);
						if (Double.isNaN(tmp.value) || Double.isInfinite(tmp.value)) {
							throw new ArithmeticException();
						}
						result.push(tmp);
						break;
					case 7:
						tmp = result.pop();
						tmp.value = Math.log10(tmp.value);
						if (Double.isNaN(tmp.value) || Double.isInfinite(tmp.value)) {
							throw new ArithmeticException();
						}
						result.push(tmp);
						break;
					case 8:
						tmp = result.pop();
						tmp.value = Math.exp(tmp.value);
						result.push(tmp);
						break;
					case 9:
						tmp = result.pop();
						tmp.value = Math.abs(tmp.value);
						result.push(tmp);
						break;
				}
			} else if (c.state >= 2 && c.state <= 4) {
				switch (c.subState) {
					case 0:
						tmp = result.pop();
						tmp.value = result.pop().value + tmp.value;
						result.push(tmp);
						break;
					case 1:
						tmp = result.pop();
						tmp.value = result.pop().value - tmp.value;
						result.push(tmp);
						break;
					case 2:
						tmp = result.pop();
						tmp.value = result.pop().value * tmp.value;
						result.push(tmp);
						break;
					case 3:
						tmp = result.pop();
						tmp.value = result.pop().value / tmp.value;
						if (Double.isNaN(tmp.value) || Double.isInfinite(tmp.value)) {
							throw new ArithmeticException();
						}
						result.push(tmp);
						break;
					case 4:
						tmp = result.pop();
						tmp.value = Math.pow(result.pop().value, tmp.value);
						result.push(tmp);
						break;
				}
			}
		}
		return result.pop().value;
	}
	
}
