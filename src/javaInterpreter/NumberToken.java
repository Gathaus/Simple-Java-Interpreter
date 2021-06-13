package javaInterpreter;

public class NumberToken extends Token{
	
	NumberToken(ProgramText source) {
		super(source);
		super.setTokenType(TokenType.NUMBER);
		super.setText(String.valueOf(source.curChar()));
		StringBuffer buffer = new StringBuffer();
		buffer.append(source.curChar());
		// ->123456b
		if (Character.isDigit(source.nextChar())) {
			// 1->23456b
			buffer.append(source.curChar());
			while (Character.isDigit(source.nextChar())) {
				// 123456->b
				// 12->3456b
				buffer.append(source.curChar());
			}
			source.prevChar();
		}
		super.setText(buffer.toString());
	}
	
	NumberToken(String text, TokenType type){
		super(text,type);
	}
}
