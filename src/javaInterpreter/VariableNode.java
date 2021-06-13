package javaInterpreter;

import java.util.Map;

public class VariableNode extends TreeNode {
	Token variable;

	VariableNode(TreeNode parent, Token variable) {
		super(parent);
		this.variable = variable;
	}
	
    Token eval(Map<String,Integer> variableMemory) {
        return this.variable;
    }

	// take whole variable
	Token syntaxCheck(Scanner scanner) {
		this.parent.children.add(this);
		return scanner.nextToken();
	}
}
