package javaInterpreter;

import java.util.ArrayList;
import java.util.Map;

public class OperatorNode extends TreeNode {

	Token operator;

	OperatorNode(TreeNode parent, Token operator) {
		super(parent);
		this.operator = operator;
	}
	
	Token eval(Map<String,Integer> variableMemory) {
		return operator;
	}

	Token syntaxCheck(Scanner scanner) {

		if (parent.children.size() >= 2) {
			// children = getDeepestChild(this.parent.children);
			// children.add(this);

			this.parent.children.get(parent.children.size() - 1).children.add(this);
		} else {
			this.parent.children.add(this);
		}

		return operator;
	}
	/* Somehow now works
	 * ArrayList<TreeNode> getDeepestChild(ArrayList<TreeNode> children) { if
	 * (children.size() >= 2) getDeepestChild(children.get(1).children); else {
	 * return children; } return children; //somehow work twice
	 * //children.add(this);
	 * 
	 * }
	 */
}
