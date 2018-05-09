package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

public class LoginCompleteDialog extends JDialog {
    private final String TITLE = "Logowanie zakonczone";
    private final String MSG = "Zalogowano pomyslnie!";
    private String whichGui;
    private MainWindow mainWindow;
    


    public LoginCompleteDialog(MainWindow mainWindow, String whichGui) {
    	getContentPane().setBackground(new Color(255, 228, 181));
        this.setTitle(TITLE);

        this.mainWindow = mainWindow;
        this.whichGui = whichGui;
        
        setBounds(DefaultDialog.X, DefaultDialog.Y, DefaultDialog.WIDTH, DefaultDialog.HEIGHT);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToMyAccount();
            }
        });
        okButton.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					goToMyAccount();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

        JLabel succesLogged = new JLabel("Zalogowano pomy\u015Blnie!");
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(succesLogged)
                                .addGap(35))
                        .addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup()
                                .addGap(71)
                                .addComponent(okButton)
                                .addContainerGap(70, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup()
                                .addGap(30)
                                .addComponent(succesLogged)
                                .addGap(18)
                                .addComponent(okButton)
                                .addContainerGap(26, Short.MAX_VALUE))
        );
        getContentPane().setLayout(groupLayout);
    }
    private void goToMyAccount()
    {
    	LoginCompleteDialog.this.setVisible(false);
        mainWindow.changeGui(whichGui);
    }
}
