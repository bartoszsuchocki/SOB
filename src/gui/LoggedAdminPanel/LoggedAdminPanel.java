package gui.LoggedAdminPanel;

import gui.AfterAuthenticationGuiPanel;
import gui.BooksTableModel;
import gui.MainWindow;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedAdminPanel extends AfterAuthenticationGuiPanel
{
    private static final String SIGN_UP_BUTTON_TEXT ="Wyloguj";
    private static final String SEARCH_FIELD_TEXT="Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107\"";
    private static final String SEARCH_BUTTON_TEXT="Szukaj";
    private static final String ADD_BOOK_BUTTON_TEXT ="Dodaj książkę";
    private static final String CHANGE_STATUS_BUTTON_TEXT="Zmień status";

    private JLabel searchFieldLabel;
    private JButton signUpButton;
    private JButton searchButton;
    private JTextField searchBookTextField;
    private JTable booksTable;
    private JScrollPane booksScrollPane;
    private JButton addBookButton;
    private JButton changeStatusButton;




    public LoggedAdminPanel(MainWindow mainWindow)
    {
        super(mainWindow);
        setLayout(null);
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
        addBookButton=new JButton("Dodaj ksi\u0105\u017Ck\u0119");
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



        BooksTableModel booksTableModel=new BooksTableModel();
        booksTable = new JTable(booksTableModel);


        booksScrollPane=new JScrollPane(booksTable);



        booksScrollPane.setBounds(52, 154, 474, 211);

        add(booksScrollPane);

        changeStatusButton=new JButton("Zmie\u0144 status");
        changeStatusButton.setBounds(531, 154, 150, 23);
        changeStatusButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int indexesSelected[]=booksTable.getSelectedRows();

                new ChangeBookStatusDialog(new ChangeBookStatusInterface()
                {
                    @Override
                    public void deleteButtonPressed()
                    {
                        //Usuwamy z bazy danych ksiązki o indeksach z tablicy indexesSelected
                    }

                    @Override
                    public void giveBackButtonPressed()
                    {
                        //Oddajemy te ksiązki
                    }
                }).showDialog();
            }
        });
        add(changeStatusButton);
    }
}
