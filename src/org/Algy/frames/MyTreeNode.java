package org.Algy.frames;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import org.Algy.Utils.IterEnumAdapter;

public class MyTreeNode implements TreeNode {
	
	public MyTreeNode(TreeNode parent, String className, String shortenedName,
			int type) {
		super();
		this.parent = parent;
		this.className = className;
		this.shortenedName = shortenedName;
		this.type = type;
	}

	private ArrayList<TreeNode> children = new ArrayList<TreeNode>();
	
	private TreeNode parent;
	private String className;
	private String shortenedName;
	
	private int type = TREENODE_PACKAGE;
	
	public final static int TREENODE_PACKAGE = 0;
	public final static int TREENODE_CLASS = 1;
	public final static int TREENODE_INNERCLASS = 2;
	public final static int TREENODE_UNKNOWN = 3;
	
	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		return new IterEnumAdapter<TreeNode>(children.iterator());
	}

	@Override
	public boolean getAllowsChildren() {
		return true;//type == TREENODE_PACKAGE;
	}

	@Override
	public TreeNode getChildAt(int idx) {
		return children.get(idx);
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

	@Override
	public int getIndex(TreeNode arg) {
		return children.indexOf(arg);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return children.isEmpty();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getShortenedName() {
		return shortenedName;
	}

	public void setShortenedName(String shortenedName) {
		this.shortenedName = shortenedName;
	}
	
	@Override
	public String toString()
	{
		return shortenedName;
	}

	public ArrayList<TreeNode> getChildrenList() {
		return children;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
}
