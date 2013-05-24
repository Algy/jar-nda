package org.Algy.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;

import org.Algy.jar.JavaDecompiler;
import org.Algy.jar.MyJarFile;
import org.Algy.models.CachedJarModel;

public class Mainframe extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainframe frame = new Mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mainframe() {
		setTitle("jar-nda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1053, 843);
		
		JPanel headerPane = new JPanel();
		getContentPane().add(headerPane, BorderLayout.NORTH);
		headerPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		headerPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JToolBar toolBar = new JToolBar();
		headerPane.add(toolBar, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("New button");
		toolBar.add(btnNewButton);
		
		JPanel footerPane = new JPanel();
		getContentPane().add(footerPane, BorderLayout.SOUTH);
		footerPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		footerPane.add(panel, BorderLayout.NORTH);
		
		JEditorPane logPane = new JEditorPane();
		footerPane.add(logPane, BorderLayout.SOUTH);
		
		JPanel centeredPane = new JPanel();
		getContentPane().add(centeredPane, BorderLayout.CENTER);
		centeredPane.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		centeredPane.add(splitPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JEditorPane dtrpnEditorpane = new JEditorPane();
		dtrpnEditorpane.setContentType("text/java");
		dtrpnEditorpane.setText("editorPane");
		tabbedPane.addTab("New tab", null, dtrpnEditorpane, null);
		
		JTree tree = new JTree();
		splitPane.setLeftComponent(tree);
		
		
	}
	
}
