package org.Algy.jar;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarContainer {
	protected JarFile jar;
	public JarContainer(JarFile jar)
	{
		this.jar=jar;
	}
	
	
	
	public ArrayList<String> getEntrieNames() 
	{
		ArrayList<String> list = new ArrayList<String>();
		Enumeration<JarEntry> iter =  jar.entries();
		while(iter.hasMoreElements())
		{
			JarEntry entry = iter.nextElement();
			
			list.add(entry.getName());
		}
		return list;
	}
	
	
	public Set<String> getDirectories()
	{
		HashSet<String> pathes = new HashSet<>();
		
		for (String entryName : getEntrieNames())
		{
			int ed;
			if((ed = entryName.lastIndexOf("/")) != -1)
			{
				pathes.add(entryName.substring(0, ed));
			}
		}
		return pathes;
	}



	public Iterator<JarEntry> entries() {
		return new IterEnumAdapter<JarEntry>(jar.entries());
	}
	
}
