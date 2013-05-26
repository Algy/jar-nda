package org.Algy.jar;

import java.io.IOException;

public class JarSource extends JarObject {
	private JarClassFile classFile;
	private JavaDecompiler decompiler; // for lazy loading
	
	private boolean isDecompiled = false;
	
	private String rawSource;
	
	public JarSource(JarClassFile classFile, JavaDecompiler decompiler, boolean lazyDecomiling) throws IOException {
		super();
		this.classFile = classFile;
		this.decompiler = decompiler;
		
		if(!lazyDecomiling)
		{
			lazyDecompile();
		}
	}
	
	public void lazyDecompile() throws IOException
	{
		rawSource = decompiler.decompile(classFile.getClassContent());
		
		/*
		 * source parsing here
		 */
		isDecompiled = true;
	}
	
	public String getRawSource()
	{
		if(!isDecompiled)
			try {
				lazyDecompile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return rawSource;
	}
}
