package javaInterpreter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class OutputNode extends TreeNode {

	OutputNode(TreeNode parent) {
		super(parent);
	}

	void out(ArrayList<TreeNode> OutputNode) throws Exception {
		int tempNumber = -2147483648;
		Token tempToken = OutputNode.get(0).children.get(0).eval(getNodeMemory());

		for (Entry<String, Integer> entry : TreeNode.getNodeMemory().entrySet()) {
			String key = entry.getKey();
			if (key.equals(tempToken.getText())) {
				tempNumber = entry.getValue();
			}
		}
		if (tempNumber !=-2147483648)
			System.out.println(tempToken.getText()+" = "+tempNumber);
		else {
			System.out.println(tempToken.getText());
		}
	}

	Token eval(Map<String, Integer> variableMemory) {

		return null;
	}

	Token syntaxCheck(Scanner scanner) {
		this.children.add(new VariableNode(this, scanner.nextToken()));
		this.parent.children.add(this);

		return scanner.getToken();
	}
}
