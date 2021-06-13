package javaInterpreter;

import java.util.Map;

public class SubBooleanNode extends TreeNode {
	Token variable;

	SubBooleanNode(TreeNode parent, Token variable) {
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
