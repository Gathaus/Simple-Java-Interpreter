package javaInterpreter;

public class EOFToken extends Token{

	EOFToken(ProgramText source) {
		super(source);
		super.setTokenType(TokenType.END_OF_FILE); 
	}

}
