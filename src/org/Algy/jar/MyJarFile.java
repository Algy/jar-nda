package org.Algy.jar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.commons.io.IOUtils;

public class MyJarFile extends JarContainer {
	public MyJarFile(JarFile jar) {
		super(jar);
	}
	
	private ArrayList<JarObject> jarObjectList = null;		
	
	// filename - ".class" or ".comm" : each Object
	/*
	private HashMap<String, MetaCommentFile> unmatchedCommentFiles = new HashMap<>();
	private HashMap<String, MetaCommentFile> unmatchedClassFiles = new HashMap<>();
	*/
	private static final String COMMENT_META_SUFFIX =".comm";
	
	public final void collectObjects(JavaDecompiler decompiler)
	{
		/*
		unmatchedCommentFiles = new HashMap<>();
		unmatchedClassFiles = new HashMap<>();
		*/
		
		jarObjectList = new ArrayList<>();
		Iterator<JarEntry> jarEntries = entries();
		while(jarEntries.hasNext())
		{
			JarEntry entry = jarEntries.next();

			byte [] fileBuf;
			try {
				fileBuf = IOUtils.toByteArray(jar.getInputStream(entry));
			} catch (IOException e1) {
				e1.printStackTrace();
				continue;
			}

			if (entry.getName().endsWith(".class"))
			{
				System.out.println(entry.getName());
				String entryName = entry.getName();
				
				
				String className = entryName.substring(0, entryName.length() - 6);
				JarClassFile jarClassFile = new JarClassFile(className, fileBuf);
				
				if(jarClassFile.signatureCheck())
				{
					jarObjectList.add(jarClassFile);
					
					
				}
				else
				{
					System.out.println("signature chk Failed");
				}
			}
			else if(entry.getName().endsWith(COMMENT_META_SUFFIX))
			{
				String fileString = new String(fileBuf);
				MetaCommentFile meta = MetaCommentFile.getFromString(fileString);
				
				String className = entry.getName().substring(0, entry.getName().length() - COMMENT_META_SUFFIX.length());
				meta.setClassName(className);
				
				jarObjectList.add(meta);
			}
			else
			{
			}
		}

		Manifest manifest;
		try {
			manifest = jar.getManifest();
			if(manifest != null)
			{
				jarObjectList.add(new JarManifest(manifest));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	} 
	
	
	public void save()
	{
		
	}
		
	
	protected void onCollectObject(ArrayList<JarObject> resultList, JavaDecompiler decompiler) {}
	public Iterator<JarObject> jarObjects()
	{
		if(jarObjectList == null)
			throw new RuntimeException("collect objects first");
		return jarObjectList.iterator();
	}
	
}
