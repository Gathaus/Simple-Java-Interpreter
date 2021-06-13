package javaInterpreter;
//TODO check is paranthesis is block statement

import java.util.ArrayList;

//P -> S+        //+ here means one or more (it is a shortcut)
//S -> while(E){S+}
//S -> if(E){S+}
//E -> E + T
//...
public class Parser {

	private Scanner scanner;
	private Tree tree;
	boolean EOS = false;

	ArrayList<Token> buffer = new ArrayList<Token>();

	Parser(Scanner scanner, Tree tree) {
		this.scanner = scanner;
		this.tree = tree;
		this.EOS = true;
	}
	// it will take nodes

	// get set for eos
	// eos will be change from token classes

	void parse() throws Exception {
		tree.root.syntaxCheck(scanner);
		
		/*
		 * Token token = scanner.nextToken(); P(token);
		 * 
		 * while (!(token instanceof EOFToken)) {
		 * System.out.printf("token text: %s, token type: %s\n", token.getText(),
		 * token.getTokenType()); token = scanner.nextToken(); }
		 * 
		 * if (token.getTokenType().equals(TokenType.END_OF_FILE)) {
		 * System.out.println("SÝL BUNU" + token.getTokenType()); return; }
		 */
	}

	void P(Token token) {
		S(token);

	}

	void S(Token token) {
		// Epsilon
		if (token.getTokenType().equals(TokenType.END_OF_FILE)) {

			return;
		}
		// S1 or S
		else {
			S1(token);
		}

	}

	void S1(Token token) {
		try {
			if (token.getTokenType().equals(TokenType.END_OF_FILE)) {
				return;
			} else if (token.getTokenType().equals(TokenType.IF)) {
				token = scanner.nextToken();
				if (token.getTokenType().equals(TokenType.LEFT_PAR)) {
					System.out.println("token " + token.getText());
					token = scanner.nextToken();
					System.out.println("token " + token.getText());
					while (!(token.getTokenType().equals(TokenType.RIGHT_PAR))) {
						Exp(token);
						token = scanner.nextToken();
						System.out.println("token " + token.getText());

					}
					if (token.getTokenType().equals(TokenType.RIGHT_PAR)) {
						token = scanner.nextToken();
						System.out.println("token " + token.getText());
						if (token.getTokenType().equals(TokenType.LEFT_CURLY)) {
							token = scanner.nextToken();
							System.out.println("token " + token.getText());
							Matched(token);
							if (token.getTokenType().equals(TokenType.RIGHT_CURLY)) {
								token = scanner.nextToken();
								System.out.println("token " + token.getText());
								Opt(token);
							}
						} else if (token.getTokenType().equals(TokenType.OUT)) {
							token = scanner.nextToken();
							S1(token);
							return;
						}
					}
				}
			} else if (token.getTokenType().equals(TokenType.WHILE)) {
				token = scanner.nextToken();
				if (token.getTokenType().equals(TokenType.LEFT_PAR)) {
					System.out.println("token " + token.getText());
					token = scanner.nextToken();
					System.out.println("token " + token.getText());
					while (!(token.getTokenType().equals(TokenType.RIGHT_PAR))) {
						Exp(token);
						token = scanner.nextToken();
						System.out.println("token " + token.getText());
					}
					if (token.getTokenType().equals(TokenType.RIGHT_PAR)) {
						token = scanner.nextToken();
						System.out.println("token " + token.getText());
						if (token.getTokenType().equals(TokenType.LEFT_CURLY)) {
							// body node add
							token = scanner.nextToken();
							System.out.println("token " + token.getText());
							S1(token);// out
							// token = scanner.nextToken();
							// S1(token);//x
							if (token.getTokenType().equals(TokenType.RIGHT_CURLY)) {
								System.out.println("token " + token.getText());
								token = scanner.nextToken();
								System.out.println("token " + token.getText());
								S1(token);
								return;
							}
						}
					}
				}
			} else if (token.getTokenType().equals(TokenType.IDENTIFIER)) {
				buffer.add(token);
				System.out.println("token " + token.getText());
				token = scanner.nextToken();
				if (token.getTokenType().equals(TokenType.END_OF_FILE)) {
					System.exit(0);
				}
				System.out.println("token " + token.getText());
				if (token.getTokenType().equals(TokenType.EQUAL)) {
					buffer.add(token);
					token = scanner.nextToken();
					System.out.println("token " + token.getText());
					while (!(token.getTokenType().equals(TokenType.SEMI_COLON))) {
						// must odd times
						Exp(token);
						token = scanner.nextToken();
						System.out.println("token " + token.getText());
					}
					if (token.getTokenType().equals(TokenType.SEMI_COLON)) {
						token = scanner.nextToken();
						System.out.println("token " + token.getText());
						if (token.getTokenType().equals(TokenType.RIGHT_CURLY)) {
							token = scanner.nextToken();
							System.out.println("token " + token.getText());
						}
						S1(token);
						return;
					}

				}

			} else if (token.getTokenType().equals(TokenType.IN)) {
				token = scanner.nextToken();
				while (!(token.getTokenType().equals(TokenType.SEMI_COLON))) {
					Id(token);
					token = scanner.nextToken();
				}
				if (token.getTokenType().equals(TokenType.SEMI_COLON)) {
					System.out.println("in semicolon girdi");
				}

			} else if (token.getTokenType().equals(TokenType.OUT)) {
				token = scanner.nextToken();
				System.out.println("token " + token.getText());
				while (!(token.getTokenType().equals(TokenType.SEMI_COLON))) {
					// add out node
					Exp(token);
					token = scanner.nextToken();
					System.out.println("token " + token.getText());
				}
				if (token.getTokenType().equals(TokenType.SEMI_COLON)) {
					token = scanner.nextToken();
					S1(token);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	void Matched(Token token) {
		try {
			if (token.getTokenType().equals(TokenType.IF)) {
				if (token.getTokenType().equals(TokenType.LEFT_PAR)) {
					token = scanner.nextToken();
					Exp(token);
					if (token.getTokenType().equals(TokenType.RIGHT_PAR)) {
						token = scanner.nextToken();
						if (token.getTokenType().equals(TokenType.LEFT_CURLY)) {
							token = scanner.nextToken();
							Matched(token);
							if (token.getTokenType().equals(TokenType.RIGHT_CURLY)) {
							}
						}
					}
				}
			} else if (token.getTokenType().equals(TokenType.ELSE)) {
				if (token.getTokenType().equals(TokenType.LEFT_CURLY)) {
					token = scanner.nextToken();
					Matched(token);
					if (token.getTokenType().equals(TokenType.RIGHT_CURLY)) {
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void Opt(Token token) {
		try {
			if (token.getTokenType().equals(TokenType.ELSE)) {
				Tail(token);
			}

			// epsilon
			if (token.getTokenType().equals(TokenType.END_OF_FILE)) {
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void Tail(Token token) {
		try {
			if (token.getTokenType().equals(TokenType.IF)) {
				token = scanner.nextToken();
				if (token.getTokenType().equals(TokenType.LEFT_PAR)) {
					token = scanner.nextToken();
					Exp(token);
					if (token.getTokenType().equals(TokenType.RIGHT_PAR)) {
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void Exp(Token token) {
		// Isnodecreated
		try {
			// x = 1 + 2 + 23 + 12 * (5 + 3) * 2;
			// 23
			// ||= ||23 + 12
			if (token.getTokenType().equals(TokenType.LEFT_PAR) || token.getTokenType().equals(TokenType.RIGHT_PAR)) {
				Factor(token);
			} else if (token.getTokenType().equals(TokenType.MULTIPLY)
					|| token.getTokenType().equals(TokenType.DIVISION)) {
				Term(token);
			} else if (token.getTokenType().equals(TokenType.PLUS) || token.getTokenType().equals(TokenType.MINUS)) {
				System.out.println("PLUS");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	void Term(Token token) {
		try {
			if (token.getTokenType().equals(TokenType.LEFT_PAR) || token.getTokenType().equals(TokenType.RIGHT_PAR)) {
				Factor(token);
			} else if (token.getTokenType().equals(TokenType.MULTIPLY)
					|| token.getTokenType().equals(TokenType.DIVISION)) {

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	void Factor(Token token) {
		try {
			Num(token);
			if (token.getTokenType().equals(TokenType.LEFT_PAR)) {
				token = scanner.nextToken();
				Exp(token);
				if (token.getTokenType().equals(TokenType.RIGHT_PAR)) {
					// done
				}
			}
			Id(token);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void Id(Token token) {
		try {
			Char(token);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void Char(Token token) {
		try {
			char ch;

			for (ch = 'a'; ch <= 'z'; ++ch) {
				if (ch == token.getText().charAt(0)) {
					System.out.println("Char is:" + ch);
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void Num(Token token) {
		try {
			Digit(token);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void Digit(Token token) {
		try {
			int a;
			for (a = 0; a <= 9; a++) {
				if (a == Integer.parseInt(token.getText())) {
					System.out.println("Digit is:" + a);
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void Boolean(Token token) {
		try {
			switch (token.getText()) {
			case "<":
				System.out.println("<");
				break;
			case "<=":
				System.out.println("<=");
				break;
			case "==":
				System.out.println("==");
				break;
			case "!=":
				System.out.println("!=");
				break;
			case ">":
				System.out.println(">");
				break;
			case ">=":
				System.out.println(">=");
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + token.getText());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void eval(ArrayList<Token> tokenArray) {
		/*
		 * for (Token token : tokenArray) { System.out.print(token.getText() + " "); }
		 * Map<Integer, Node> map = new HashMap<>(); Node root = null; int i = 0;
		 * 
		 * if (tokenArray.get(i).getTokenType() == TokenType.IDENTIFIER) { i++; if
		 * (tokenArray.get(i).getTokenType() == TokenType.EQUAL) { // i-1 leftnode // i
		 * root // i+1 right node i++; while (tokenArray.get(i).getTokenType() !=
		 * TokenType.SEMI_COLON) { i++; } i++; }
		 * 
		 * }
		 * 
		 * if (tokenArray.get(i).getTokenType() == TokenType.WHILE) { // root while i++;
		 * if (tokenArray.get(i).getTokenType() == TokenType.LEFT_PAR) { i += 2; } if
		 * (tokenArray.get(i).getTokenType() == TokenType.LESS_THAN) { // 2.root less //
		 * i-1 left // i+1 right i += 2; } while (tokenArray.get(i).getTokenType() !=
		 * TokenType.RIGHT_PAR) { i++; } i++; if (tokenArray.get(i).getTokenType() ==
		 * TokenType.LEFT_CURLY) { i++;
		 * 
		 * if (tokenArray.get(i).getTokenType() == TokenType.OUT) { // 3.root out // i+1
		 * right i++; } //semi while (tokenArray.get(i).getTokenType() !=
		 * TokenType.SEMI_COLON) { i++; } i++; if (tokenArray.get(i).getTokenType() ==
		 * TokenType.IDENTIFIER) { // 3.root out // i+1 right i++; if
		 * (tokenArray.get(i).getTokenType() == TokenType.EQUAL) { // i-1 leftnode // i
		 * root // i+1 right node i++; while (tokenArray.get(i).getTokenType() !=
		 * TokenType.SEMI_COLON) { i++; } i++; } }
		 * 
		 * while (tokenArray.get(i).getTokenType() != TokenType.RIGHT_CURLY) { i++; } }
		 * i++;
		 * 
		 * } if (tokenArray.get(i).getTokenType() == TokenType.IF) { i++; if
		 * (tokenArray.get(i).getTokenType() == TokenType.LEFT_PAR) { i += 2; } if
		 * (tokenArray.get(i).getTokenType() == TokenType.EQUAL && tokenArray.get(i +
		 * 1).getTokenType() == TokenType.EQUAL) { i++;//== // i-2 leftnode // i ve i-1
		 * root (==) // i+1 right node i++; // 100
		 * 
		 * i++; //) } if (tokenArray.get(i).getTokenType() == TokenType.RIGHT_PAR) {
		 * i++; } if (tokenArray.get(i).getTokenType() == TokenType.OUT) { // 2.root out
		 * // i+1 right i++; //true } while (tokenArray.get(i).getText() != "null") {
		 * i++; } }
		 */
	}

	// DFT
	// Crete parse tree and compare with program's parse tree
	// ASTgenerator ast=new ASTgenerator();
	// ast.createTree();

}
