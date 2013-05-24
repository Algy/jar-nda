package org.Algy.jar;

import java.util.ArrayList;

public class MetaCommentFile extends JarObject {
	private String className; // full-path
	private ArrayList<MetaComment> comments = new ArrayList<>();
	public ArrayList<MetaComment> getCommentList() {
		return comments;
	}
	
	public static MetaCommentFile getFromString(String str) throws IllegalArgumentException
	{
		MetaCommentFile newInst = new MetaCommentFile();
		
		newInst.getCommentList().addAll(MetaComment.from(str));
		return newInst;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
