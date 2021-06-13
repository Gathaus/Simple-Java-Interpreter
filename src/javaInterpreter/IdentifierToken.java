package javaInterpreter;

public class IdentifierToken extends Token {

	IdentifierToken(ProgramText source) {
		super(source);

		super.setText(String.valueOf(source.curChar()));
		StringBuffer buffer = new StringBuffer();
		buffer.append(source.curChar());

		if (Character.isLetter(source.nextChar())) {
			buffer.append(source.curChar());
			while (Character.isLetter(source.nextChar())) {
				buffer.append(source.curChar());
			}
			source.prevChar();
		}
		switch (buffer.toString()) {
		case "while": {
			super.setTokenType(TokenType.WHILE);
			break;
		}
		case "if": {
			super.setTokenType(TokenType.IF);
			break;
		}
		case "sum": {
			super.setTokenType(TokenType.SUM);
			break;
		}
		case "out": {
			super.setTokenType(TokenType.OUT);
			break;
		}
		case "in": {
			super.setTokenType(TokenType.IN);
			break;
		}
		case "true":
			super.setTokenType(TokenType.TRUE);
			break;
		default:
			super.setTokenType(TokenType.IDENTIFIER);
		}

		super.setText(String.valueOf(buffer.toString()));

	}
}