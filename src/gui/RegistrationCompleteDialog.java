package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class RegistrationCompleteDialog extends JDialog {
	private final String TITLE="Registration complete"; 
	private final String MSG="Registrated succesfully!";

	private MainWindow mainWindow;
	
	public RegistrationCompleteDialog(MainWindow mainWindow) {
		
		this.mainWindow = mainWindow;
		this.setTitle(TITLE);
		
		
		
		setBounds(DefaultDialog.X, DefaultDialog.Y, DefaultDialog.WIDTH, DefaultDialog.HEIGHT);
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToLoginPanel();
			}
		});
		btnNewButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					returnToLoginPanel();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JLabel lblNewLabel = new JLabel(MSG);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(35))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(71)
					.addComponent(btnNewButton)
					.addContainerGap(70, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	private void returnToLoginPanel()
	{
		RegistrationCompleteDialog.this.setVisible(false);
		mainWindow.changeGui("logowanie");
	}
}
