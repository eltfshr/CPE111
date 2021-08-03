
public class CalculatorToken {
	String token;
	int state;
	int subState;
	double value;
	
	public CalculatorToken(String token) {
		this.token = token;
	}
	
	private boolean isStart() {
		if (token.equals("$")) {
			state = 0;
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isNumber() {		
		try {
			value = Double.parseDouble(token);
			state = 1;
			subState = 0;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean isOperator() {
		switch (token) {
			case "+":
				subState = 0;
				break;
			case "-":
				subState = 1;
				break;
			default:
				switch (token) {
					case "*":
						subState = 2;
						break;
					case "/":
						subState = 3;
						break;
					default:
						switch (token) {
							case "^":
								subState = 4;
								break;
							default:
								return false;
						}
						state = 4;
						return true;
				}
				state = 3;
				return true;
		}
		state = 2;
		return true;
	}	
	
	private boolean isSign() {
		if (token.equals("~")) {
			state = 5;
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isFunction() {
		switch (token) {
			case "sin":
				subState = 0;
				break;
			case "cos":
				subState = 1;
				break;
			case "tan":
				subState = 2;
				break;
			case "asin":
				subState = 3;
				break;
			case "acos":
				subState = 4;
				break;
			case "atan":
				subState = 5;
				break;
			case "sqrt":
				subState = 6;
				break;
			case "log":
				subState = 7;
				break;
			case "exp":
				subState = 8;
				break;
			case "abs":
				subState = 9;
				break;
			default:
				return false;
		}
		state = 6;
		return true;
	}
	
	private boolean isLeftBracket() {
		if (token.equals("(")) {
			state  = 7;
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isRightBracket() {
		if (token.equals(")")) {
			state = 8;
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isConstantOrVariable() {
		switch (token) {
			case "ans":
				subState = 1;
				break;
			case "pi":
				subState = 2;
				value = Math.PI;
				break;
			case "e":
				subState = 3;
				value = Math.E;
				break;
			case "g":
				subState = 4;
				value = 9.81;
				break;
			default:
				return false;
		}
		state = 1;
		return true;
	}
	
	private boolean isStop() {
		if (token.equals("@")) {
			state = 9;
			return true;
		} else {
			return false;
		}
	}

	private boolean isHelp() {
		if (token.equals("help") || token.equals("token")) {
			state = 10;
			return true;
		} else {
			return false;
		}		
	}
	
	private boolean isExit() {
		if (token.equals("end") || token.equals("exit") || token.equals("quit")) {
			state = 11;
			return true;
		} else {
			return false;
		}
	}
	
	public void changeToSignState() {
		state = 5;
		token = token.replace("-", "~");
	}
	
	public int checkState() {
		if (isStart()) {
			return state;
		} else if (isNumber()) {
			return state;
		} else if (isOperator()) {
			return state;
		} else if (isHelp()) {
			return state;
		} else if (isExit()) {
			return state;
		} else if (isSign()) {
			return state;
		} else if (isFunction()) {
			return state;
		} else if (isLeftBracket()) {
			return state;
		} else if (isRightBracket()) {
			return state;
		} else if (isConstantOrVariable()) {
			return state;
		} else if (isStop()) {
			return state;
		} else {
			state = -1;
			return state;
		}
	}
	
}
