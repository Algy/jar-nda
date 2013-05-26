package org.Algy.controllers;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import org.Algy.dialogs.RenamerDialog;
import org.Algy.frames.Mainframe;
import org.Algy.frames.MyTreeNode;
import org.Algy.jar.JarSource;
import org.Algy.jar.JavaDecompiler;
import org.Algy.models.CachedJarModel;
import org.Algy.models.JarRenamer;
import org.Algy.models.NoSuchClassFile;
import org.Algy.models.RemapFormater;

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
			jarModel = new CachedJarModel(null, new JavaDecompiler(), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		jarModel.setJar(file);
		jarModel.analyzeJar();
		
		frame.clearMainEditorText();
		frame.updateTreeModel();
		return true;
	}
	
	public boolean saveFileAs(File file, boolean overwrite) throws IOException
	{
		if(file.exists() && !overwrite)
			return false;
		
		jarModel.safeSave(file);
		
		return true;
	}
	
	
	MyTreeNode selected = null;
	public void treeItemSelection(MyTreeNode node)
	{
		selected = node;
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
	
	
	public void deobfucate() throws IOException
	{
		int min = 2, max = 40;
		File oldFile = jarModel.getJarFile().getFile();
		try
		{
			File file = JarRenamer.DeObfuscate(oldFile, min, max);
			jarModel.setJar(file);
			jarModel.analyzeJar();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			jarModel.setJar(oldFile);
			jarModel.analyzeJar();
		}
		
		frame.clearMainEditorText();
		frame.updateTreeModel();
	}
	public void singleRename() throws IOException
	{
		RenamerDialog dlg = new RenamerDialog();
		dlg.setVisible(true);
		
		if(!dlg.isAccepted())
			return;
		RemapFormater formatter = new RemapFormater();
		switch(dlg.getRenamingType())
		{
		case Class:
			formatter.putClass(dlg.getFromText(), dlg.getToText());
			break;
		case Field:
			formatter.putField(dlg.getFromText(), dlg.getAdditionalName(), dlg.getToText());
			break;
		case Method:
			formatter.putMethod(dlg.getFromText(),dlg.getAdditionalName(), dlg.getToText());
			break;
		case Package:
			formatter.putPackage(dlg.getFromText(), dlg.getToText());
			
			break;
		}
		rename(formatter);
	}
	public void rename(RemapFormater formatter) throws IOException
	{
		System.out.print("cmd:");
		System.out.println(formatter.formatCommand());
		String cmd = formatter.formatCommand();
		
		File f = JarRenamer.remapWithConfig(jarModel.getJarFile().getFile(), cmd);

		jarModel.clearSourceData();
		jarModel.setJar(f);
		jarModel.analyzeJar();

		frame.clearMainEditorText();
		frame.updateTreeModel();
		if(selected != null)
			frame.selectPath(replaceClassName(selected.getClassName(), formatter), selected.getType());
	}
	
	public static String replaceClassName(String className, RemapFormater remapper)
	{
		Stack<String> stack = new Stack<>();
		
		String str = new String(className);
		int ed;
		
		int cnt = 0;
		while(!str.equals(""))
		{
			ed = str.lastIndexOf("/");
			String stub = str.substring(ed+1);
			
			if(cnt == 0)
			{
				//class
				HashMap<String, String>classMap = remapper.getClassMap();
				
				if(classMap.containsKey(str))
				{
					//class remapped
					stack.push(classMap.get(str));
				}
				else
					stack.push(stub);
					
			}
			else
			{
				//package
				HashMap<String, String> packageMap = remapper.getPakageMap();
				
				if(packageMap.containsKey(str))
				{
					//package remapped
					stack.push(packageMap.get(str));
				}
				else
					stack.push(stub);
				
			}
			if(ed == -1)
				break;
			str = str.substring(0, ed);
			cnt++;
		}
		// reconstruct
		String result = "";
		if(!stack.isEmpty())
			result += stack.pop();
		
		while(!stack.isEmpty())
		{
			result += "/";
			result += stack.pop();
		}
		return result;
	}
	
}
