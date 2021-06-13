package javaInterpreter;

import java.util.ArrayList;
import java.util.Map;

public class DigitNode extends TreeNode{
	Token digit;
	
    DigitNode(TreeNode parent, Token digit) {
        super(parent);
        this.digit = digit;
    }
    
    Token eval(Map<String,Integer> variableMemory) {
        return this.digit;
    }
    
	Token syntaxCheck(Scanner scanner) {
		if(parent.children.size()>=2) {
			this.parent.children.get(parent.children.size()-1).children.add(this);
		}
		else {
			this.parent.children.add(this);
		}
		
		return digit;
	}
	
	//NOT WORK SOME HOW
	/*
	void getDeepestElement(ArrayList<TreeNode> children) {
		if(children.size() >= 2)
			getDeepestElement(children.get(1).children);
		children.add(this);
		
	}
	*/
}
