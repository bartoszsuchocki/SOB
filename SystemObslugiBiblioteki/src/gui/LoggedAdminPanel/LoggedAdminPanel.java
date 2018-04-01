package gui.LoggedAdminPanel;

import gui.AfterAuthenticationGuiPanel;
import gui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedAdminPanel extends AfterAuthenticationGuiPanel
{
    private static final String SIGN_UP_BUTTON_TEXT ="Wyloguj";
    private static final String SEARCH_FIELD_TEXT="Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107\"";
    private static final String SEARCH_BUTTON_TEXT="Szukaj";
    private static final String ADD_BOOK_BUTTON ="Dodaj książkę";

    private JLabel searchFieldLabel;
    private JButton signUpButton;
    private JButton searchButton;
    private JTextField searchBookTextField;
    private JTable booksTable;
    private JButton addBookButton;


    public LoggedAdminPanel(MainWindow mainWindow)
    {
        super(mainWindow);

        /*Przycisk 'Wyloguj się' */
        signUpButton = new JButton(SIGN_UP_BUTTON_TEXT);
        signUpButton.setBounds(576, 11, 89, 23);
        signUpButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainWindow.changeGui("logowanie");
            }
        });
        add(signUpButton);


        /*Przycisk 'Dodaj nową ksiązke'*/
        addBookButton=new JButton(ADD_BOOK_BUTTON);
        addBookButton.setBounds(53, 52, 201, 23);
        addBookButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new AddNewBookDialog();
            }
        });
        add(addBookButton);

        /*Label 'Wpisz dane blablabla'*/
        searchFieldLabel=new JLabel();
        searchFieldLabel.setText(SEARCH_FIELD_TEXT);
        searchFieldLabel.setBounds(53, 82, 301, 14);
        add(searchFieldLabel);

        /*Pole do wyszukiwania ksiązek*/
        searchBookTextField = new JTextField();
        searchBookTextField.setHorizontalAlignment(SwingConstants.LEFT);
        searchBookTextField.setBounds(53, 107, 467, 23);
        add(searchBookTextField);
        searchBookTextField.setColumns(10);

        /*Przycisk 'Wyszukaj'*/
        searchButton = new JButton(SEARCH_BUTTON_TEXT);
        searchButton.setBounds(530, 107, 89, 23);
        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
        add(searchButton);

        /*Tabelka*/
        booksTable = new JTable();
        booksTable.setBounds(52, 154, 474, 211);
        add(booksTable);
    }
}
