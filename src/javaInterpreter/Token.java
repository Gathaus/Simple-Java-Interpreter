package javaInterpreter;

public class Token {
	private TokenType type;
	private String text;
	private ProgramText source;

	Token(ProgramText source) {
		this.source = source;
	}
	
	Token(String text, TokenType type){
		this.text = text;
		this.type = type;
	}

	public TokenType getTokenType() {
		return type;
	}

	public void setTokenType(TokenType type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
