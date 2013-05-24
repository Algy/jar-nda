package org.Algy.jar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class JarClassFile extends JarObject {
	private String className; 
	
	private byte [] classContent; 
	public JarClassFile(String className, File file) throws IOException
	{
		this.className = className;
		InputStream inputStream= new FileInputStream(file);
		
		classContent = IOUtils.toByteArray(inputStream);
		
		inputStream.close();
	}
	
	public JarClassFile(String className, byte [] classContent)
	{
		this.className = className;
		this.classContent = classContent.clone();
	}
	public boolean signatureCheck()
	{
		try
		{	
			// signature : CAFEBABE
			if(classContent[0] == (byte)0xCA &&
					classContent[1] == (byte)0xFE &&
					classContent[2] == (byte)0xBA &&
					classContent[3] == (byte)0xBE)
				return true;
			
			
		}
		catch(IndexOutOfBoundsException e)
		{
		}
		return false;
	}


	public byte[] getClassContent() {
		return classContent;
	}

	public String getClassName() {
		return className;
	}

	
}
