package org.Algy.dialogs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class JPrompt extends JTextField {
	
	
	
	public JPrompt(String promptStr) {
		super();
		this.promptStr = promptStr;
		
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				isFocused = false;
				invalidate();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				isFocused = true;
				invalidate();
			}
		});
		
	}
	public void invalidate()
	{
		this.repaint();
	}
	
	private boolean isFocused = false;
	
	private Color promptCol = new Color(0.5f, 0.5f, 0.5f, 0.9f);
	public Color getPromptCol() {
		return promptCol;
	}
	public void setPromptCol(Color promptCol) {
		this.promptCol = promptCol;
	}
	private int marginX = 5;
	public int getMarginX() {
		return marginX;
	}
	public void setMarginX(int marginX) {
		this.marginX = marginX;
	}
	private String promptStr = "example";
	public String getPromptString() {
		return promptStr;
	}
	public void setPromptString(String promptStr) {
		this.promptStr = promptStr;
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		if(!isFocused && this.getText().equals(""))
		{
			g.setFont( this.getFont());
			g.setColor(promptCol);
			
			g.drawString(promptStr, marginX, g.getFontMetrics().getHeight()/4 + this.getHeight()/2);
		}
	}
	
	
}
