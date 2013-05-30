package org.Algy.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class RenamerDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2027863895274680955L;
	private final JPanel contentPanel = new JPanel();
	private JPrompt txtFromtext;
	private JPrompt txtToText;
	private JPrompt txtAdditionalname;
	private JComboBox renamingTypeComboBox; 
	
	
	public enum RenamingType
	{
		Package, Class, Method, Field
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RenamerDialog dialog = new RenamerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RenamerDialog() {
		setTitle("Rename");
		setBounds(100, 100, 285, 196);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 5, 328, 31);
			panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			contentPanel.add(panel);
			panel.setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("47px"),
					ColumnSpec.decode("90px"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					ColumnSpec.decode("75px"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					ColumnSpec.decode("85px"),},
				new RowSpec[] {
					FormFactory.LINE_GAP_ROWSPEC,
					RowSpec.decode("21px"),
					FormFactory.LINE_GAP_ROWSPEC,
					RowSpec.decode("21px"),
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,}));
			{
				renamingTypeComboBox = new JComboBox();
				renamingTypeComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						RenamingType type = (RenamingType) renamingTypeComboBox.getSelectedItem();
						
						if(type == RenamingType.Method)
						{
							txtAdditionalname.setEnabled(true);
						}
						else if( type == RenamingType.Field)
						{
							txtAdditionalname.setEnabled(true);
						}
						else
						{
							txtAdditionalname.setEnabled(false);							
						}
						
						switch (type )
						{
						case Method:
							txtAdditionalname.setPromptString("func(Ljava/lang/String/;IZJ)");
							txtFromtext.setPromptString("your.package.classname");
							txtToText.setPromptString("AlternativeMethodName");
							break;
						case Class:
							txtAdditionalname.setPromptString("");
							txtToText.setPromptString("AlternativeClassName");
							txtFromtext.setPromptString("your.package.classname");
							
							break;
						case Field:
							txtAdditionalname.setPromptString("field");
							txtToText.setPromptString("AlternativeClassName");
							txtFromtext.setPromptString("your.package.classname");
							break;
						case Package:
							txtAdditionalname.setPromptString("");
							txtToText.setPromptString("AlternativePackageName");
							txtFromtext.setPromptString("your.package.here");
							break;
						}
						txtAdditionalname.updateUI();
						txtAdditionalname.repaint();
						txtFromtext.repaint();
						txtToText.repaint();
					}
				});
				{
					JLabel lblNewLabel = new JLabel("Renaming Type");
					panel.add(lblNewLabel, "2, 2, left, center");
				}
				renamingTypeComboBox.setModel(new DefaultComboBoxModel(RenamingType.values()));
				panel.add(renamingTypeComboBox, "4, 2, left, top");
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 34, 322, 115);
			panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			panel.setAlignmentY(Component.TOP_ALIGNMENT);
			contentPanel.add(panel);
			{
				txtFromtext = new JPrompt("");
				txtFromtext.setColumns(20);
			}
			{
				lblArrowicon = new JLabel("");
				lblArrowicon.setIcon(new ImageIcon(RenamerDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/collapsed-rtl.gif")));
			}
			{
				txtAdditionalname = new JPrompt("");
				txtAdditionalname.setColumns(12);
			}
			{
				txtToText = new JPrompt("");
				txtToText.setColumns(15);
			}
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(txtToText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtFromtext, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtAdditionalname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblArrowicon)
						.addGap(69))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblArrowicon)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(txtFromtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtAdditionalname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtToText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(35, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
		
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
	
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		
		
		renamingTypeComboBox.setSelectedIndex(0);

	}
	public void relayOK()
	{
		this.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED , "OK"));
	}
	public void relayCancel()
	{
		this.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED , "Cancel"));
	}
	
	public String getFromText() { return txtFromtext.getText();}
	public void setFromText(String str) { txtFromtext.setText(str); }
	public String getToText() { return  txtToText.getText();}
	public void setToText(String str) { txtToText.setText(str);}
	public String getAdditionalName() { return txtAdditionalname.getText();}
	
	public void getAdditionalName(String str) { txtAdditionalname.setText(str);}
	
	public RenamingType getRenamingType() { 
		return (RenamingType) renamingTypeComboBox.getSelectedItem();
	}
	public void setRenamingType(RenamingType type) { 
		renamingTypeComboBox.setSelectedItem(type);
	}
	
	public boolean isAccepted = false;
	private JLabel lblArrowicon;
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if("OK".equals(cmd))
		{
			this.setVisible(false);
			isAccepted = true;
		}
		else if("Cancel".equals(cmd))
		{
			this.setVisible(false);
			isAccepted = false;
		}
	}

	public boolean isAccepted() {
		return isAccepted;
	}
}
		
	
	
