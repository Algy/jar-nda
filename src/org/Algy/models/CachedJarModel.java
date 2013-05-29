package org.Algy.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.Algy.jar.JarClassFile;
import org.Algy.jar.JarObject;
import org.Algy.jar.JarSource;
import org.Algy.jar.JavaDecompiler;
import org.Algy.jar.MetaCommentFile;
import org.Algy.jar.MyJarFile;
import org.apache.commons.io.IOUtils;


public class CachedJarModel {
	private MyJarFile jarFile;
	
	// className : JarObject  (package sparator is /)
	private HashMap<String, JarClassFile> classFiles; 
	private Set<String> directories;
	
	// className : JarSource (package sparator is /)
	private HashMap<String, JarSource> compiledSource; 
	
	//className( path - ".class") : comment file
	private HashMap<String, MetaCommentFile> comments; 
	
	private boolean analyzed = false;
	private boolean decompileSourceAtOnce;
	private JavaDecompiler decompiler;
	public CachedJarModel(File jarFile, JavaDecompiler decompiler, boolean decompileSourceAtOnce) throws IOException
	{
		if(jarFile != null)
			setJar(jarFile);
		this.decompileSourceAtOnce = decompileSourceAtOnce;
		this.decompiler = decompiler;
	}
	
	public void setJar(File jarFile) throws IOException
	{
		this.jarFile  = new MyJarFile(jarFile);
		analyzed = false;
	}
	public void analyzeJar()
	{
		classFiles = new HashMap<String, JarClassFile>();
		compiledSource = new HashMap<String, JarSource>();
		comments = new HashMap<String, MetaCommentFile>();
		directories = jarFile.getDirectories();
		
		jarFile.collectObjects(new JavaDecompiler());
		
		
		Iterator<JarObject> iter = jarFile.jarObjects();
		while(iter.hasNext())
		{
			JarObject jarObj = iter.next();
			
			if(jarObj instanceof JarClassFile)
			{
				JarClassFile jarClassFile =(JarClassFile)jarObj;
				
				classFiles.put(jarClassFile.getClassName(), jarClassFile);
			}
			else if( jarObj instanceof MetaCommentFile)
			{
				MetaCommentFile commentFile = (MetaCommentFile)jarObj;
				comments.put(commentFile.getClassName(), commentFile);
			}
		}
		
		if(decompileSourceAtOnce)
		{
			for (JarClassFile file :classFiles.values())
			{
				try {
					forceCompile(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		analyzed = true;
		
	}
	
	public JarSource forceCompile(String className) throws NoSuchClassFile, IOException
	{
		if(!classFiles.containsKey(className))
			throw new NoSuchClassFile(className);
		return forceCompile(classFiles.get(className));
		
	}
	
	public JarSource forceCompile(JarClassFile classFile) throws IOException  
	{
		JarSource source = new JarSource(classFile, decompiler, false);
		
		compiledSource.put(classFile.getClassName(), source);
		
		return source;
	}
	public JarSource compile(String className) throws NoSuchClassFile, IOException
	{
		if(compiledSource.containsKey(className))
			return compiledSource.get(className);
		
		return forceCompile(className);
		
	}
	public JarSource compile(JarClassFile classFile) throws IOException
	{
		if(compiledSource.containsKey(classFile.getClassName()))
			return compiledSource.get(classFile.getClassName());
		
		return forceCompile(classFile);
	}
	public void clearSourceData()
	{
		classFiles.clear();
		directories.clear();
		compiledSource.clear();
		
	}
	public void clearCompiledCache()
	{
		compiledSource.clear();
	}
	
	public void saveAs(File file) throws IOException
	{
		IOUtils.copy(new FileInputStream(jarFile.getFile()), new FileOutputStream(file));
		//and metadata input 
	}
	
	//safely...
	public void safeSave(File file)  throws IOException
	{
		if(jarFile == null)
			throw new IOException("No File to save");
		
		String original = file.getPath();
		File savingFile = new File(file.getPath() + ".saving");
		File oldFile =jarFile.getFile();
		
		InputStream oldFileStream = new FileInputStream(oldFile);
		OutputStream savingFileStream = new FileOutputStream(savingFile);
		IOUtils.copy(oldFileStream, savingFileStream);
		
		
		//and metadata input ...
		//TODO : metadata input
		//if metadata input suceed
		
		oldFileStream.close();
		savingFileStream.close();
		
		if(file.exists())
			file.delete();
		
		savingFile.renameTo(new File(original));
	}
	
	public boolean isAnalyzed() {
		return analyzed;
	}

	public Set<String> getDirectories() {
		return directories;
	}

	public HashMap<String, JarClassFile> getClassFiles() {
		return classFiles;
	}

	public HashMap<String, JarSource> getCompiledSource() {
		return compiledSource;
	}

	public HashMap<String, MetaCommentFile> getComments() {
		return comments;
	}

	public MyJarFile getJarFile() {
		return jarFile;
	}
	
}
