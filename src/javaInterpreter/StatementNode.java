package javaInterpreter;

import java.util.ArrayList;
import java.util.Map;

public class StatementNode extends TreeNode {

	StatementNode(TreeNode parent) {
		super(parent);
	}

	Token eval(Map<String, Integer> variableMemory) throws Exception {
		for (TreeNode treeNode : this.children)
			treeNode.eval(variableMemory);
		return null;
	}

	Token syntaxCheck(Scanner scanner) throws Exception {
		Token token = scanner.nextToken();
		while (!token.getTokenType().equals(TokenType.END_OF_FILE) && !token.getTokenType().equals(TokenType.SEMI_COLON)
				&& !token.getTokenType().equals(TokenType.RIGHT_CURLY) && !token.getTokenType().equals(TokenType.IF_END)
				&& token.getText() != null) {
			TreeNode nextNode = null;
			if (token.getTokenType().equals(TokenType.IDENTIFIER))
				nextNode = new AssignmentNode(this, token);
			else if (token.getTokenType().equals(TokenType.WHILE))
				nextNode = new WhileNode(this);
			else if (token.getTokenType().equals(TokenType.IF))
				nextNode = new IfNode(this);
			else if (token.getTokenType().equals(TokenType.OUT))
				nextNode = new OutputNode(this);
			else if (token.getTokenType().equals(TokenType.IN))
				nextNode = new InputNode(this);

			if (nextNode == null && token.getTokenType().equals(TokenType.END_OF_FILE)) {
				throw new Exception("Variable or while or if was expected but Found: " + token.getText());
			} else {
				// System.out.println(this.parent.getClass().getName().substring(10));

				token = nextNode.syntaxCheck(scanner);
				if (!(this.parent.getClass().getName().substring(10).equals("RootNode"))) {
					if (this.parent.parent.getClass().getName().substring(10).equals("IfNode"))
						this.parent.parent.children.add(this);
					else if (!(this.parent.parent.getClass().getName().substring(10).equals("WhileNode")))
						this.parent.parent.parent.children.add(this);
					else {
						this.parent.parent.children.add(this);
					}
					return token;
				}
			}
			token = scanner.nextToken();
		}
		if (this.parent != null) {
			this.parent.children.add(this);
		}
		return token;
	}

}
