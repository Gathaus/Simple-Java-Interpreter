package javaInterpreter;

public class Main {

	public static void main(String[] args) {
		
		Tree tree = new Tree();
		ProgramText progText = new ProgramText();
		
		Scanner scan = new Scanner(progText);
		Parser parser = new Parser(scan,tree);
		
		try {
			parser.parse();
			tree.root.eval(TreeNode.getNodeMemory());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}

}
