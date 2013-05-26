package org.Algy.models;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import p.rn.name.InitOut;
import p.rn.name.Renamer;

public class JarRenamer {
	public JarRenamer()
	{
		
	}
	
	public static File remapWithConfig(File jarFile, String configure)
	{
		File configFile;
		try {
			configFile = File.createTempFile("remap", ".txt");
			configFile.deleteOnExit();
			
			FileUtils.writeStringToFile(configFile, configure,"UTF-8");
		}catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return remapWithConfig(jarFile, configFile);
	}
	
	
	
	//Warning - return File will be deleted on Exit
	public static File remapWithConfig(File jarFile, File configFile)
	{
		try
		{
			File remappedJarFile = File.createTempFile(jarFile.getName() + ".", ".remap");
			new Renamer().from(jarFile).withConfig(configFile).to(remappedJarFile);
			remappedJarFile.deleteOnExit();
			return remappedJarFile;
		}
		catch(Exception e)
		{
			//and recovering
			e.printStackTrace();
		}
		return null;
		
		
	}
	public static File DeObfuscate(File jarFile, int minLength, int maxLength)// min length = 2 max length = 40 . normally 
	{
		try {
			File initConfigFile = File.createTempFile("init", ".txt");
			new InitOut().from(jarFile).minLength(minLength).maxLength(maxLength).to(initConfigFile);
			return remapWithConfig(jarFile, initConfigFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}