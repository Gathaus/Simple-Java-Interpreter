package javaInterpreter;

public class ExpressionNode extends TreeNode {
	boolean flag = false;

	ExpressionNode(TreeNode parent) {
		super(parent);
	}

	Token syntaxCheck(Scanner scanner) throws Exception {
		TreeNode nextNode = null;
		Token token = scanner.getToken();
		while (!token.getTokenType().equals(TokenType.SEMI_COLON) && !token.getText().equals("<")
				&& !token.getText().equals("<=") && !token.getText().equals("==") && !token.getText().equals("!=")
				&& !token.getText().equals(">") && !token.getText().equals(">=")) {
			token = scanner.nextToken();
			if(token.getTokenType().equals(TokenType.SEMI_COLON))
				break;
			if (token.getTokenType().equals(TokenType.LEFT_PAR) || token.getTokenType().equals(TokenType.RIGHT_PAR)) {
				// Factor(token);
			} else if (token.getTokenType().equals(TokenType.MULTIPLY)
					|| token.getTokenType().equals(TokenType.DIVISION)) {
				// Term(token);
			} else if (token.getTokenType().equals(TokenType.PLUS) || token.getTokenType().equals(TokenType.MINUS)) {
				OperatorNode operatorNode = new OperatorNode(this, token);
				token = operatorNode.syntaxCheck(scanner);
			} else if (token.getTokenType().equals(TokenType.NUMBER)) {
				DigitNode digitNode = new DigitNode(this, token);
				token = digitNode.syntaxCheck(scanner);
			} else if (token.getTokenType().equals(TokenType.IDENTIFIER)) {
				this.children.add(new VariableNode(this, token));
			}
			else {
				throw new Exception(
						"Invaled expression '(' or ')' or '*' or ''/ or '+' or '-' or 'Number' expected. But Token is"
								+ token.getText());
			}
		}
		this.parent.children.add(this);
		return token;
	}
}
