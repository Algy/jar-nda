package org.Algy.jar;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MetaComment {
	public static final String LINE_SPLITTER = "$$";
	public MetaComment(int lineNumber, String comment) {
		super();
		this.lineNumber = lineNumber;
		this.comment = comment;
	}
	public int lineNumber;
	public String comment;
	
	/*
	 *  don't use $$ in comment
	 */
	public String to()  throws IllegalArgumentException
	{
		if(comment.contains(LINE_SPLITTER))
			throw new IllegalArgumentException( "do not use " + LINE_SPLITTER + " in comment");
		return  Integer.valueOf(lineNumber).toString() +"\t" + comment + LINE_SPLITTER;
	}
	public static ArrayList<MetaComment > from(String commentFormat) throws IllegalArgumentException
	{
		if(commentFormat.contains(LINE_SPLITTER))
			throw new IllegalArgumentException( "do not use " + LINE_SPLITTER + " in comment");
		
		ArrayList<MetaComment> comments = new ArrayList<MetaComment>();
		for(String line : commentFormat.split(Pattern.quote(LINE_SPLITTER)))
		{
			int tabPos = line.indexOf("\t");	
			if(tabPos == -1)
				continue;
			if(line.equals(""))
				continue;
			
			String lineNumStr = line.substring(0, tabPos);
			String comm = "";
			try
			{
				comm = line.substring(tabPos+1);
			}
			catch(IndexOutOfBoundsException e)
			{
			}
			int lineNum;
			try
			{
				lineNum = Integer.parseInt(lineNumStr);
			}
			catch(NumberFormatException e)
			{
				e.printStackTrace();
				continue;
			}
			
			comments.add(new MetaComment(lineNum, comm));
		}
		return comments;
	}
}
