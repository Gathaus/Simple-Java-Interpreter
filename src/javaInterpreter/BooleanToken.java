package javaInterpreter;

public class BooleanToken extends Token{

	BooleanToken(ProgramText source) {
		super(source);
		super.setTokenType(TokenType.END_OF_FILE); 
	}
	
	BooleanToken(String text, TokenType type){
		super(text,type);
	}

}
