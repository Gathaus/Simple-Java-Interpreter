package javaInterpreter;

public class SpecialToken extends Token {

	SpecialToken(ProgramText source) {
		super(source);
		switch (source.curChar()) {
		case '+':
			super.setTokenType(TokenType.PLUS);
			super.setText(String.valueOf(source.curChar()));
			break;
		case '-':
			super.setTokenType(TokenType.MINUS);
			super.setText(String.valueOf(source.curChar()));
			break;
		case '*':
			super.setTokenType(TokenType.MULTIPLY);
			super.setText(String.valueOf(source.curChar()));
			break;
		case '/':
			super.setTokenType(TokenType.DIVISION);
			super.setText(String.valueOf(source.curChar()));
			break;
		case '<':
			super.setTokenType(TokenType.LESS_THAN);
			super.setText(String.valueOf(source.curChar()));
			break;
		case '=':
			if (source.nextChar() == '=') {
				super.setTokenType(TokenType.EQUAL_EQUAL);
				super.setText("==");
			} else {
				source.prevChar();
				super.setTokenType(TokenType.EQUAL);
				super.setText(String.valueOf(source.curChar()));
			}
			break;
		case ';':
			super.setTokenType(TokenType.SEMI_COLON);
			super.setText(String.valueOf(source.curChar()));
			break;
		case '{':
			super.setTokenType(TokenType.LEFT_CURLY);
			super.setText(String.valueOf(source.curChar()));
			break;
		case '}':
			super.setTokenType(TokenType.RIGHT_CURLY);
			super.setText(String.valueOf(source.curChar()));
			break;
		case '(':
			super.setTokenType(TokenType.LEFT_PAR);
			super.setText(String.valueOf(source.curChar()));
			break;
		case ')':
			super.setTokenType(TokenType.RIGHT_PAR);
			super.setText(String.valueOf(source.curChar()));
			break;
		default:
		}
	}

}
