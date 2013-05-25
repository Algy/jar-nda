package org.Algy.frames;

public interface ISourceTreeViewObserver {
	public void treeSelection();
	public void treePopup();
	
	public void tabSelection();
	public void tabPopup();
	
	public void tokenRead();
}
