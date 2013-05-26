package org.Algy.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sun.awt.SunHints.Key;

import java.awt.Dialog.ModalityType;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

public class RenamerDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2027863895274680955L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtFromtext;
	private JTextField txtToText;
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
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Rename");
		setBounds(100, 100, 343, 217);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 5, 328, 64);
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
							lblAdditionallabel.setText("method name(type)");
							lblAdditionallabel.setVisible(true);
							txtAdditionalname.setVisible(true);
						}
						else if( type == RenamingType.Field)
						{
							lblAdditionallabel.setText("field name");
							lblAdditionallabel.setVisible(true);
							txtAdditionalname.setVisible(true);
						}
						else
						{
							lblAdditionallabel.setVisible(false);
							txtAdditionalname.setVisible(false);							
						}
					}
				});
				{
					JLabel lblNewLabel = new JLabel("Renaming Type");
					panel.add(lblNewLabel, "2, 2, left, center");
				}
				renamingTypeComboBox.setModel(new DefaultComboBoxModel(RenamingType.values()));
				panel.add(renamingTypeComboBox, "4, 2, left, top");
			}
			{
				lblAdditionallabel = new JLabel("additionalLabel");
				panel.add(lblAdditionallabel, "1, 4, 2, 1, right, center");
			}
			{
				txtAdditionalname = new JTextField();
				panel.add(txtAdditionalname, "4, 4, 3, 1, default, top");
				txtAdditionalname.setColumns(12);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 79, 322, 55);
			panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			panel.setAlignmentY(Component.TOP_ALIGNMENT);
			contentPanel.add(panel);
			{
				txtFromtext = new JTextField();
				panel.add(txtFromtext);
				txtFromtext.setColumns(20);
			}
			{
				JLabel lblArrowicon = new JLabel("");
				lblArrowicon.setIcon(new ImageIcon(RenamerDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/collapsed-rtl.gif")));
				panel.add(lblArrowicon);
			}
			{
				txtToText = new JTextField();
				panel.add(txtToText);
				txtToText.setColumns(15);
			}
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
		
		lblAdditionallabel.setVisible(false);
		txtAdditionalname.setVisible(false);

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
	private JTextField txtAdditionalname;
	private JLabel lblAdditionallabel;
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
		
	
	
