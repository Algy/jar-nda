package org.Algy.frames;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import jsyntaxpane.DefaultSyntaxKit;

import org.Algy.controllers.MainController;
import org.Algy.jar.JarClassFile;
import org.Algy.models.CachedJarModel;

public class Mainframe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6310960608361805598L;
	private JEditorPane dtrpnVoidMain;
	
	private CachedJarModel curJarModel;
	private JTree tree;
	private MainController controller;
	
	/**
	 * Create the frame.
	 */
	public Mainframe(CachedJarModel jarModel, MainController controller) {
		
		curJarModel = jarModel;
		this.controller = controller; // replace to observer pattern laters
		
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
		splitPane.setResizeWeight(0.35);
		centeredPane.add(splitPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabbedPane.addTab("New tab", null, scrollPane, null);
		
		dtrpnVoidMain = new JEditorPane();
		dtrpnVoidMain.setEditable(false);
		dtrpnVoidMain.setContentType("text/java");
		scrollPane.setViewportView(dtrpnVoidMain);
		
		DefaultSyntaxKit.initKit();
		dtrpnVoidMain.setContentType("text/java");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setToolTipText("`");
		splitPane.setLeftComponent(scrollPane_1);
		
		tree = new JTree();
		tree.setRootVisible(false);
		scrollPane_1.setViewportView(tree);
		
		tree.setModel(new MyTreeModel(null));
		tree.setCellRenderer(new MyTreeNodeRenderer());
		initComponent();
	}
	
	private void initComponent()
	{
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				controller.treeItemSelection((MyTreeNode) e.getPath().getLastPathComponent());
			}
		});
		updateTreeModel();
	}
	public void setMainEditorText(String content)
	{
		dtrpnVoidMain.setText(content);
	}
	
	public void setTreeContent(MyTreeNode node)
	{
		tree.setModel(new MyTreeModel(node));
	}
	public JTree getTree()
	{
		return tree;
	}
	public void updateTreeModel()
	{
		HashMap<String, MyTreeNode> classNameToNode = new HashMap<>();
		
		ArrayList<MyTreeNode> topNodes = new ArrayList<>(); 
		
		for (java.util.Map.Entry<String, JarClassFile> entry : curJarModel.getClassFiles().entrySet())
		{
			String className = entry.getKey();
			String shortName = className.substring( className.lastIndexOf("/") + 1);
			MyTreeNode p = new MyTreeNode(null, className, shortName,
					(!shortName.contains("$"))?MyTreeNode.TREENODE_CLASS : MyTreeNode.TREENODE_INNERCLASS );
			
			while(true)
			{
				int ed = className.lastIndexOf("/");
				if(ed != -1)
					className = className.substring(0, ed);
				else
					className = "";
				
				if(classNameToNode.containsKey(className))
				{
					MyTreeNode parent;
					parent = classNameToNode.get(className);
					p.setParent(parent);
					parent.getChildrenList().add(p);
					break;
				}
				if(ed == -1)
				{
					topNodes.add(p);
					//not added root 
					break;
				}
				
				MyTreeNode high = new MyTreeNode(null, className, className.substring(className.lastIndexOf("/")+1),
						MyTreeNode.TREENODE_PACKAGE);
				
				
				
				high.getChildrenList().add(p);
				p.setParent(high);
				
				classNameToNode.put(className, high);
				
				p = high;
				
			}
		}
		MyTreeNode root = new MyTreeNode(null, "", "", 0);
		root.getChildrenList().addAll(topNodes);
		
		tree.setModel(new MyTreeModel(root));
	}

	public CachedJarModel getCurJarModel() {
		return curJarModel;
	}

	public void setCurJarModel(CachedJarModel curJarModel) {
		this.curJarModel = curJarModel;
	}
}