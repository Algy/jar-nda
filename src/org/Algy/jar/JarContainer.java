package org.Algy.jar;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.Algy.Utils.IterEnumAdapter;

public class JarContainer {
	protected ZipFile jar;
	public JarContainer(ZipFile jar)
	{
		this.jar=jar;
	}
	
	
	
	public ArrayList<String> getEntrieNames() 
	{
		ArrayList<String> list = new ArrayList<String>();
		Iterator<ZipEntry> iter = this.entries();
		while(iter.hasNext())
		{
			ZipEntry entry = iter.next();
			
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



	@SuppressWarnings("unchecked")
	public Iterator<ZipEntry> entries() {

		return new IterEnumAdapter<ZipEntry>((Enumeration<ZipEntry>)jar.entries());
	}
	
}
