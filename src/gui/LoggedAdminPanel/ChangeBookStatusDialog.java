package gui.LoggedAdminPanel;

import javax.swing.*;

import gui.DefaultDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface ChangeBookStatusInterface
{

    void deleteButtonPressed();
    void giveBackButtonPressed();
}

public class ChangeBookStatusDialog extends JDialog
{


    private static final String DIALOG_TITLE="Zmiana statusu ksi\u0105\u017Cki";
    private static final String CANCEL_BUTTON_TEXT="Cofnij";
    private static final String DELETE_BUTTON_TEXT="Usu\u0144";
    private static final String GIVE_BACK_BUTTON="Oddaj";
    private static final String LABEL_TEXT="Zmie\u0144 status ksi\u0105\u017Cki";



    private JLabel msgLabel;
    private JButton cancelButton;
    private JButton deleteButton;
    private JButton giveBackButton;

    ChangeBookStatusInterface changeBookStatusInterface;


    public ChangeBookStatusDialog(ChangeBookStatusInterface changeBookStatusInterface)
    {
        super();
        this.changeBookStatusInterface=changeBookStatusInterface;

        setBounds(DefaultDialog.X, DefaultDialog.Y, DefaultDialog.WIDTH, DefaultDialog.HEIGHT);
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
              
                changeBookStatusInterface.deleteButtonPressed();
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
                
                changeBookStatusInterface.giveBackButtonPressed();

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



    }

    public void showDialog()
    {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);


    }

}
