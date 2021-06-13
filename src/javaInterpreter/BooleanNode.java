package javaInterpreter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class BooleanNode extends TreeNode {
	boolean booleanResult = true;
	BooleanNode(TreeNode parent) {
		super(parent);
	}
	
	Token eval(Map<String,Integer> variableMemory, ArrayList<TreeNode> children) throws Exception {
		ArrayList<Integer> tempNumber = new ArrayList<>();
		Token tempToken = null;
		String operator = null;
		boolean defined = false;
		int iteration = 0;
		for (TreeNode children1 : children.get(0).children) {
			iteration++;
			String childrenName = children1.getClass().getName().substring(10);
			if (childrenName.equals("StatementNode"))
				break;
			switch (childrenName) {
			case "VariableNode":
				tempToken = children1.eval(getNodeMemory());
				for (Entry<String, Integer> entry : TreeNode.getNodeMemory().entrySet()) {
					String key = entry.getKey();
					if (key.equals(tempToken.getText())) {
						tempNumber.add(entry.getValue());
						defined = true;
					}
				}
				if (defined == false)
					throw new Exception(tempToken.getText() + " is not defined");
				break;
			case "NumberNode":
				tempToken = children1.eval(getNodeMemory());
				tempNumber.add(Integer.parseInt(tempToken.getText()));
				break;
			case "SubBooleanNode":
				tempToken = children1.eval(getNodeMemory());
				operator = tempToken.getText();
			}
			if (iteration % 3 == 0)
				break;

		}
		while (operator != "break") {
			switch (operator) {
			case "<":
				if (tempNumber.get(0) < tempNumber.get(1)) {
					booleanResult = true;
					operator = "break";
				} else {
					booleanResult = false;
					operator = "break";
				}
				break;
			case "<=":
				if (tempNumber.get(0) <= tempNumber.get(1)) {
					booleanResult = true;
					operator = "break";
				} else {
					booleanResult = false;
					operator = "break";
				}
				break;
			case "==":
				if (tempNumber.get(0) == tempNumber.get(1)) {
					booleanResult = true;
					operator = "break";
				} else {
					booleanResult = false;
					operator = "break";
				}
				break;
			case "!=":
				if (tempNumber.get(0) != tempNumber.get(1)) {
					booleanResult = true;
					operator = "break";
				} else {
					booleanResult = false;
					operator = "break";
				}
				break;
			case ">":
				if (tempNumber.get(0) > tempNumber.get(1)) {
					booleanResult = true;
					operator = "break";
				} else {
					booleanResult = false;
					operator = "break";
				}
				break;
			case ">=":
				if (tempNumber.get(0) >= tempNumber.get(1)) {
					booleanResult = true;
					operator = "break";
				} else {
					booleanResult = false;
					operator = "break";
				}
				break;
			}
		}
		return new BooleanToken(String.valueOf(booleanResult),TokenType.BOOLEAN);
	}
	
	Token syntaxCheck(Scanner scanner) {
		TreeNode nextNode = null;
		Token token = scanner.getToken();
		while (true) {
			token = scanner.nextToken();
			if(token.getTokenType().equals(TokenType.RIGHT_PAR))
				break;
			if (token.getTokenType().equals(TokenType.IDENTIFIER))
				this.children.add(new VariableNode(this, token));
			else if(token.getTokenType().equals(TokenType.NUMBER))
				this.children.add(new NumberNode(this, token));
			else if (token.getTokenType().equals(TokenType.LESS_THAN)
					|| token.getTokenType().equals(TokenType.LESS_EQUAL)
					|| token.getTokenType().equals(TokenType.EQUAL_EQUAL)
					|| token.getTokenType().equals(TokenType.EQUAL)
					|| token.getTokenType().equals(TokenType.NOT_EQUAL)
					|| token.getTokenType().equals(TokenType.GREATER_THAN)
					|| token.getTokenType().equals(TokenType.LESS_THAN))
				this.children.add(new SubBooleanNode(this, token));
		}

		this.parent.children.add(this);
		return token;
	}

}
