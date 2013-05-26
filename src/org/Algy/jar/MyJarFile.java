package org.Algy.jar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

public class MyJarFile extends JarContainer {
	File file;
	public MyJarFile(File file) throws IOException {
		super(new ZipFile(file));
		this.file = file;
	
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
		Iterator<ZipEntry> jarEntries = entries();
		while(jarEntries.hasNext())
		{
			ZipEntry entry = jarEntries.next();
			byte [] fileBuf;
			
			try {
				InputStream	entryInputStream = jar.getInputStream(entry);
				fileBuf = IOUtils.toByteArray(entryInputStream);
				entryInputStream.close();
				
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
/*
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
		*/
			
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


	public File getFile() {
		return file;
	}
	
}
