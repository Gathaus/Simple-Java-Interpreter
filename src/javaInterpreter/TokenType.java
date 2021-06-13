package javaInterpreter;

public enum TokenType {
	LEFT_CURLY("{"), RIGHT_CURLY("}"), LEFT_PAR("("), RIGHT_PAR(")"), EQUAL("="), PLUS("+"), MINUS("-"), MULTIPLY("*"),
	DIVISION("/"), SEMI_COLON(";"), LESS_THAN("<"), GREATER_THAN(">"), LESS_EQUAL("<="), EQUAL_EQUAL("=="),
	NOT_EQUAL("!="), GREATER_EQUAL(">="), WHILE("while"), SUM("sum"), SPECIAL, END_OF_FILE, IF("if"), IN, OUT, TERM,
	IDENTIFIER, NUMBER, ELSE, ROOT, WHILE_BEGIN, WHILE_END, IF_BEGIN, IF_END,TRUE,FALSE,BOOLEAN;

	private String text;

	TokenType(String text) {
		this.text = text;
	}

	TokenType() {
		this.text = this.toString();
	}
}
