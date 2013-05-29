package org.Algy.dialogs;

import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;

public class JSuggestionPrompt extends JPrompt {
	
	boolean suggesting;
	int idx = 0;
	ArrayList<String> candidate = new ArrayList<String>();
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		
		
	}
	Collection<String> packageDic = new ArrayList<String>();
	
	public JSuggestionPrompt(String promptStr) {
		super(promptStr);
		// TODO Auto-generated constructor stub
		packageDic.add("AAB.CDC.EE");
	
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				onKeyPressed(e);
			}
		});
		
		
	}
	public void onKeyPressed(KeyEvent e)
	{
		// -> : sugest to next dot/slash
		// v :next  suggestion 
		// tab : suggest completely
		// other charactor : chiso
		
		if(e.getKeyCode() ==KeyEvent.VK_TAB)
		{
			complete();
			clearSuggestion();
		}
		else if(e.getKeyCode() ==KeyEvent.VK_RIGHT)
		{
			completePartially();
		}
		else if(e.getKeyCode() ==KeyEvent.VK_DOWN)
		{
			idx = (idx + 1) % candidate.size(); 
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			idx = (idx + candidate.size() - 1) % candidate.size(); 
		}
		else
		{
			suggest();
		}
	}
	public void clearSuggestion()
	{
		idx = 0;
		candidate.clear();
		suggesting = false;
	}
	
	public void suggest()
	{
		
	}
	public void completePartially()
	{
		
	}
	public void complete()
	{
		
	}
	public void onFocusLost()
	{
		
	}
	

}
