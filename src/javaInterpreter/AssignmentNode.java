package javaInterpreter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class AssignmentNode extends TreeNode {
	int tempValue = 0;

	AssignmentNode(TreeNode parent, Token variable) {
		super(parent);
		this.children.add(new VariableNode(this, variable));
	}
	
	ArrayList<Token> identifierConverter(ArrayList<Token> stack,Token token) throws Exception {
		boolean isExistInMemory = false;
		for (Entry<String, Integer> entry : TreeNode.getNodeMemory().entrySet()) {
			String key = entry.getKey();
			if (key.equals(token.getText())) {
				isExistInMemory = true;
				for(Token tempToken:stack) {
					if(tempToken.getText().equals(key)) {
						int index = stack.indexOf(tempToken);
						stack.set(index, new NumberToken(String.valueOf(entry.getValue()), TokenType.NUMBER));
					}
				}
			}
		}
		if(!isExistInMemory)
			throw new Exception(token.getText()+" is not defined");
		return stack;
	}

	int operationAction(char operation, int x, int y) {
		switch (operation) {
		case '+':
			return y + x;

		case '*':
			return y * x;

		case '/':
			return y / x;
		case '-':
			return y - x;
		default:
			break;
		}
		return (Integer) null;
	}

	int operation(ArrayList<TreeNode> children, ArrayList<Token> stack) throws Exception {

		if (children.size() >= 2) {
			stack.add(children.get(0).eval(getNodeMemory()));
			stack.add(children.get(1).eval(getNodeMemory()));
			operation(children.get(1).children, stack);
		} else if (children.size() == 1) {
			stack.add(children.get(0).eval(getNodeMemory()));
			operation(children.get(0).children, stack);
		} else {
			for(Token token:stack) {
				if(token.getTokenType().equals(TokenType.IDENTIFIER))
					stack = identifierConverter(stack,token);
			}
			
			if (stack.size() == 1)
				return Integer.parseInt(stack.get(0).getText());
			else {
				// to make stack even
				int total = operationAction(stack.get(stack.size() - 2).getText().charAt(0),
						Integer.parseInt(stack.get(stack.size() - 1).getText()),
						Integer.parseInt(stack.get(stack.size() - 3).getText()));
				tempValue = total;
				// pop 3 elements
				stack.remove(stack.size() - 1);
				stack.remove(stack.size() - 1);
				stack.remove(stack.size() - 1);
				if (stack.size() > 0) {
					for (int i = stack.size() - 1; i > 1; i -= 2) {
						int tempNum = Integer.parseInt(stack.get(i - 1).getText());
						char operation = stack.get(i).getText().charAt(0);
						total = operationAction(operation, tempNum, total);
						tempValue = total;
					}
					return tempValue;
				} else {
					return tempValue;
				}
			}
		}

		// return Integer.parseInt(stack.get(0).text);

		return tempValue;
	}

	Token eval(Map<String, Integer> variableMemory) throws Exception {
		Token identifier = this.children.get(0).eval(variableMemory);
		int value = operation(this.children.get(1).children, new ArrayList<Token>());
		variableMemory.put(identifier.getText(), value);
		return identifier;
	}
	
	Token eval(Map<String, Integer> variableMemory, ArrayList<TreeNode> children) throws Exception {
		Token identifier = this.children.get(0).eval(variableMemory);
		int value = operation(this.children.get(1).children, new ArrayList<Token>());
		variableMemory.put(identifier.getText(), value);
		return identifier;
	}

	Token syntaxCheck(Scanner scanner) throws Exception {
		TreeNode nextNode = null;
		Token token = scanner.nextToken();

		if (token.getTokenType().equals(TokenType.END_OF_FILE)) {
			System.exit(0);
		}

		if (token.getTokenType().equals(TokenType.EQUAL)) {
			nextNode = new ExpressionNode(this);

		}
		Token returnToken = nextNode.syntaxCheck(scanner);
		this.parent.children.add(this);
		return returnToken;
	}
}
