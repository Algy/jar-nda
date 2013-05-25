package org.Algy.controllers;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

import org.Algy.frames.Mainframe;
import org.Algy.frames.MyTreeNode;
import org.Algy.jar.JarSource;
import org.Algy.jar.JavaDecompiler;
import org.Algy.jar.MyJarFile;
import org.Algy.models.CachedJarModel;
import org.Algy.models.NoSuchClassFile;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MainController implements Runnable{
	private CachedJarModel jarModel;
	private Mainframe frame;
	
	public static final void main(String args[])
	{
		MainController controller = new MainController();
		controller.startView();
	}
	
	public void startView()
	{	
		try {
			jarModel = new CachedJarModel(new MyJarFile(new JarFile("test/b.jar")), new JavaDecompiler(), false);
			jarModel.analyzeJar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		EventQueue.invokeLater(this);
	}
	@Override
	public void run() {				
			try {
			frame = new Mainframe(jarModel, this);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean openFile(File file) throws IOException
	{
		jarModel.setJar(new MyJarFile(new JarFile(file)));
		jarModel.analyzeJar();
		
		frame.updateTreeModel();
		return true;
	}
	
	
	public boolean saveFileAs(File file, boolean overwrite) throws IOException
	{
		throw new NotImplementedException();
	}
	
	
	public void treeItemSelection(MyTreeNode node)
	{
		if(node.getType() == MyTreeNode.TREENODE_CLASS || node.getType() == MyTreeNode.TREENODE_INNERCLASS)
		{
			try {
				JarSource source = jarModel.compile(node.getClassName());
				frame.setMainEditorText(source.getRawSource());
			} catch (NoSuchClassFile e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}

	
	
	
}
