package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginCompleteDialog extends JDialog {
    private final String TITLE = "Logowanie zakonczone";
    private final String MSG = "Zalogowano pomyslnie!";


    public LoginCompleteDialog(MainWindow mainWindow, String whichGui) {
        this.setTitle(TITLE);

        setBounds(200, 200, 200, 150); //tu mozna dodac inne wspolrzedne, zeby ladniej wygladalo
        JButton btnNewButton = new JButton("OK");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginCompleteDialog.this.setVisible(false);
                mainWindow.changeGui(whichGui);
            }
        });

        JLabel lblNewLabel = new JLabel(MSG);
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
}
