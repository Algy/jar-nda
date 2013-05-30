package org.Algy.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class CommandRenameDialog extends JDialog {
	private IDialogOK okHandler = null;
	public CommandRenameDialog(IDialogOK okHandler) {
		this();
		this.okHandler = okHandler;
	}

	private final JPanel contentPanel = new JPanel();
	private JEditorPane dtrpnEditor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CommandRenameDialog dialog = new CommandRenameDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CommandRenameDialog() {
		super();
		setTitle("rename command");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				dtrpnEditor = new JEditorPane();
				scrollPane.setViewportView(dtrpnEditor);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if(okHandler != null)
							okHandler.onOK();
						invisible();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						invisible();
					}
				});
			}
		}
		
	}
	public String getText()
	{
		return dtrpnEditor.getText();
	}
	
	public void invisible()
	{
		this.setVisible(false);
	}

}
