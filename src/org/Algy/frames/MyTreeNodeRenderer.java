package org.Algy.frames;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class MyTreeNodeRenderer extends DefaultTreeCellRenderer{
	private static final long serialVersionUID = -8877587742169797255L;

	private ImageIcon iconFolderClosed = new ImageIcon("statics/closed-folder.png");
	private ImageIcon iconFolderOpen= new ImageIcon("statics/open-folder.png");
	private ImageIcon iconJavaClass= new ImageIcon("statics/java-class.png");
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
				super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
				
	//			setBackgroundSelectionColor(new Color(0.9f, 0.7f, 0.0f, 0.2f));
	//			setBorderSelectionColor(new Color(1f,1f,0.2f,1f));
				MyTreeNode node = (MyTreeNode)value;
				
				switch(node.getType())
				{
				case MyTreeNode.TREENODE_CLASS:
					setIcon(iconJavaClass);
					break;
				case MyTreeNode.TREENODE_INNERCLASS:
					setIcon(iconJavaClass);
					break;
				case MyTreeNode.TREENODE_PACKAGE:
					if(expanded)
						setIcon(iconFolderOpen);
					else
						setIcon(iconFolderClosed);
					break;
				}
				return this;
	}

}
