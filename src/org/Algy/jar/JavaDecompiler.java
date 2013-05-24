package org.Algy.jar;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public  class JavaDecompiler {
	private final static String DECOMPILER_PATH = "jad/jad.exe";

	byte [] buf = new byte [2048]; // here it is cuz they'res synchronized method
	public synchronized String decompile(File classFile) throws IOException
	{
		System.out.println(classFile.getCanonicalPath());
		ProcessBuilder builder = new ProcessBuilder(DECOMPILER_PATH, "-ff" , "-f", "-p", classFile.getCanonicalPath());

		Process p =  builder.start();
		
		
		InputStream stream = p.getInputStream();
		int n;
		StringBuffer strBuf = new StringBuffer();
		while( (n = stream.read(buf)) != -1 )
		{
			strBuf.append(new String(buf, 0 , n));
		}
		return strBuf.toString();
	}
	public synchronized String decompile(InputStream inputStream) throws IOException
	{
		File tmpFile = File.createTempFile("tempDecompiledClass", "class");
		
		FileOutputStream tmpOutputStream = new FileOutputStream(tmpFile);
		
		byte [] buf = new byte[4096];
		int n;
		String result;
		while ( (n = inputStream.read(buf)) != -1)
		{
			tmpOutputStream.write(buf, 0, n);
		}
		 result = decompile(tmpFile);
		 
		 tmpOutputStream.close();
		// tmpFile.deleteOnExit();
		 return result;
	}
	public synchronized String decompile(byte [] buf) throws IOException
	{
		return decompile(new ByteArrayInputStream( buf ) );
	}
}
