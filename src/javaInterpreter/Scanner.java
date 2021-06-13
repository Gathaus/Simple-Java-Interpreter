package javaInterpreter;

public class Scanner {
	private Token token;
	private ProgramText source;

	public Token getToken() {
		Token token;
		char ch = source.curChar();
		while (Character.isWhitespace(ch))
			ch = source.nextChar();
		if (Character.isDigit(ch)) {
			//->123
			if (Character.isDigit(source.nextChar())) {
				//1->a3
				//1->23
				int count = 1;
				while (Character.isDigit(source.curChar())) {
					source.nextChar();
					//12->b
					//12345->6
					count++;
				}
				//123456->b
				for(int i=0;i<count;i++) {
					source.prevChar();
				}
				//12345->6b
				//1->2b
			}
			else {
				source.prevChar();
				//->1a3
			}
			// 123 ->a
			token = new NumberToken(source);
			return token;
		} // a b c
		else if (Character.isLetter(ch)) {
			// identifier token
			token = new IdentifierToken(source);
			return token;

		}
		// + - *
		else if (ch == '{' || ch == '}' || ch == '(' || ch == ')' || ch == '+' || ch == '-' || ch == '*' || ch == '/'
				|| ch == '<' || ch == ';' || ch == '=') { 
			token = new SpecialToken(source);
			return token;
		}
		
		//EOF
		token=new EOFToken(source);
		return token;
	}
	
	public Scanner(ProgramText source) {
		this.source = source;
	}
	Token nextToken() {
		Token token;
		char ch = source.nextChar();
		
		while (Character.isWhitespace(ch))
			ch = source.nextChar();
		if (Character.isDigit(ch)) {
			//->123
			if (Character.isDigit(source.nextChar())) {
				//1->a3
				//1->23
				int count = 1;
				while (Character.isDigit(source.curChar())) {
					source.nextChar();
					//12->b
					//12345->6
					count++;
				}
				//123456->b
				for(int i=0;i<count;i++) {
					source.prevChar();
				}
				//12345->6b
				//1->2b
			}
			else {
				source.prevChar();
				//->1a3
			}
			// 123 ->a
			token = new NumberToken(source);
			return token;
		} // a b c
		else if (Character.isLetter(ch)) {
			// identifier token
			token = new IdentifierToken(source);
			return token;

		}
		// + - *
		else if (ch == '{' || ch == '}' || ch == '(' || ch == ')' || ch == '+' || ch == '-' || ch == '*' || ch == '/'
				|| ch == '<' || ch == ';' || ch == '=') { 
			token = new SpecialToken(source);
			return token;
		}
		
		//EOF
		token=new EOFToken(source);
		return token;
	}

}
