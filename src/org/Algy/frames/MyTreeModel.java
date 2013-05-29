package org.Algy.frames;

import java.util.ArrayList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class MyTreeModel implements TreeModel {

	public MyTreeModel(TreeNode rootNode) {
		super();
		this.rootNode = rootNode;
	}

	private TreeNode rootNode;
	private ArrayList<TreeModelListener> listeners = new ArrayList<TreeModelListener>();
	
	@Override
	public void addTreeModelListener(TreeModelListener arg) {
		listeners.add(arg);
	}

	@Override
	public Object getChild(Object parent, int idx) {
		TreeNode p = (TreeNode)parent;
		return p.getChildAt(idx);
		
	}

	@Override
	public int getChildCount(Object parent) {
		TreeNode p = (TreeNode)parent;
		return p.getChildCount();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		TreeNode p = (TreeNode) parent;
		return p.getIndex((TreeNode) child);
	}

	@Override
	public Object getRoot() {
		return rootNode;
	}

	@Override
	public boolean isLeaf(Object node) {
		TreeNode p = (TreeNode) node;
		return p.isLeaf();
	}

	@Override
	public void removeTreeModelListener(TreeModelListener arg) {
		listeners.remove(arg);
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
	}

}
