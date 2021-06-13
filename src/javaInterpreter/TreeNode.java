package javaInterpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides an easy way to create a parent-->child tree while preserving their
 * depth/history. Original Author: Jonathan,
 * https://stackoverflow.com/a/22419453/14720622
 */
public class TreeNode {
	private static HashMap<String,Integer> nodeMemory = new HashMap<>();
	
	public static HashMap<String, Integer> getNodeMemory() {
		return nodeMemory;
	}

	public static void addToNodeMemory(String key,Integer value) {
		nodeMemory.put(key, value);
	}

	protected ArrayList<TreeNode> children;
	protected TreeNode parent;

	public TreeNode() {
		this.children = new ArrayList<>();
	}

	public TreeNode(TreeNode parent) {
		// a fresh node, without a parent reference
		this.children = new ArrayList<>();
		this.parent = parent;
	}

	public TreeNode(Token data, TreeNode parent) {
		// new node with a given parent
		this.children = new ArrayList<>();
		this.parent = parent;
		parent.addChild(this);
	}
	
	public List<TreeNode> getChildren() {
		return children;
	}

	public void setParent(TreeNode parent) {
		parent.addChild(this);
		this.parent = parent;
	}

	public TreeNode getParent() {
		return this.parent;
	}

	public void addChild(TreeNode child) {
		this.children.add(child);
	}

	Token syntaxCheck(Scanner scanner) throws Exception {
		return null;
	}

	void out (ArrayList<TreeNode> children) throws Exception {
		throw new Exception("Error while printing");
	}
	Token eval(Map<String, Integer> variableMemory) throws Exception {
		return null;
	}
	
	Token eval(Map<String, Integer> variableMemory,ArrayList<TreeNode> children) throws Exception {
		return null;
	}

}