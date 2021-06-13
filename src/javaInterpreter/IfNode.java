package javaInterpreter;

import java.util.Map;

public class IfNode extends TreeNode {
	Token booleanToken = new BooleanToken("true",TokenType.BOOLEAN);

	IfNode(TreeNode parent) {
		super(parent);
	}
	
	Token eval(Map<String, Integer> variableMemory) throws Exception {
		for (TreeNode children : this.children) {
			String childrenName = children.getClass().getName().substring(10);

			if (childrenName.equals("StatementNode")) {
				if (children.children.get(0).getClass().getName().substring(10).equals("OutputNode")) {
					children.children.get(0).out(children.children);
				}
				if (children.children.get(0).getClass().getName().substring(10).equals("AssignmentNode")) {
					children.children.get(0).eval(TreeNode.getNodeMemory(), children.children);
				}

			}
			if (childrenName.equals("BooleanNode"))
				
				booleanToken = children.eval(variableMemory,this.children);
				//booleanAction(this.children);
			if (booleanToken.getText().equals("false"))
				break;

		}
		return null;
	}
	
	Token syntaxCheck(Scanner scanner) throws Exception {
		TreeNode nextNode = null;
		Token token = scanner.nextToken();
		if (token.getTokenType().equals(TokenType.LEFT_PAR)) {
			nextNode = new BooleanNode(this);

		} else {
			throw new Exception("'(' was expected but found " + token.getText());
		}
		
		token = nextNode.syntaxCheck(scanner);
		
		if(token.getTokenType().equals(TokenType.RIGHT_PAR)) {
			
			if(!(token.getTokenType().equals(TokenType.LEFT_CURLY))) {
				nextNode = new StatementNode(nextNode);
				token = nextNode.syntaxCheck(scanner);
			}
			else {
				token = scanner.nextToken();
			}
		}
		
		
				
		this.parent.children.add(this);
		return token;
	}

}
