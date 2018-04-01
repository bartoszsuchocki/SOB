package gui.LoggedAdminPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeBookStatusDialog extends JDialog
{
    private static final String DIALOG_TITLE="Zmiana statusu książki";
    private static final String CANCEL_BUTTON_TEXT="Cofnij";
    private static final String DELETE_BUTTON_TEXT="Delete";
    private static final String GIVE_BACK_BUTTON="Oddaj";
    private static final String LABEL_TEXT="Zmień status książki";


    private JLabel msgLabel;
    private JButton cancelButton;
    private JButton deleteButton;
    private JButton giveBackButton;

    public ChangeBookStatusDialog()
    {
        super();
        setBounds(100, 100, 200, 150);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setTitle(DIALOG_TITLE);

        msgLabel=new JLabel(LABEL_TEXT);
        this.add(msgLabel);

        /*Przycisk 'Usuń'*/
        deleteButton=new JButton(DELETE_BUTTON_TEXT);
        deleteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, "Udało się usunąć książkę");

                //JOptionPane.showMessageDialog(null, "Nie udało sie usunąć ksiżki");
                dispose();
            }
        });
        this.add(deleteButton);

        /*Przycisk 'Oddaj'*/
        giveBackButton=new JButton(GIVE_BACK_BUTTON);
        giveBackButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, "Udało się zwrócić książkę");

                //JOptionPane.showMessageDialog(null, "Nie udało się zwrócić książki");
                dispose();
            }
        });
        this.add(giveBackButton);

        /*Przycisk 'Cofnij'*/
        cancelButton=new JButton(CANCEL_BUTTON_TEXT);
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        this.add(cancelButton);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);


    }

}
