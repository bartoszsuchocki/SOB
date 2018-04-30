package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginCompleteDialog extends JDialog {
    private final String TITLE = "Logowanie zakonczone";
    private final String MSG = "Zalogowano pomyslnie!";
    private String whichGui;
    private MainWindow mainWindow;
    


    public LoginCompleteDialog(MainWindow mainWindow, String whichGui) {
        this.setTitle(TITLE);

        this.mainWindow = mainWindow;
        this.whichGui = whichGui;
        
        setBounds(200, 200, 200, 150); //tu mozna dodac inne wspolrzedne, zeby ladniej wygladalo
        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToMyAccount();
            }
        });
        btnNewButton.addKeyListener(new KeyListener() {
			
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

        JLabel lblNewLabel = new JLabel("Zalogowano pomy\u015Blnie!");
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNewLabel)
                                .addGap(35))
                        .addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup()
                                .addGap(71)
                                .addComponent(btnNewButton)
                                .addContainerGap(70, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup()
                                .addGap(30)
                                .addComponent(lblNewLabel)
                                .addGap(18)
                                .addComponent(btnNewButton)
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
