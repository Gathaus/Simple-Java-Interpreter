package javaInterpreter;

import java.util.HashMap;
import java.util.Map;

public class RootNode extends TreeNode{

    RootNode(){
        this.children.add(new StatementNode(this));
    }
    
    Token eval(Map<String,Integer> variableMemory) throws Exception {
    	return this.children.get(0).eval(variableMemory);
    }
    
    Token syntaxCheck(Scanner scanner) throws Exception {
        return this.children.get(0).syntaxCheck(scanner);
    }

}
