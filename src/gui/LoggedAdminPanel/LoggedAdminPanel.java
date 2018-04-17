package gui.LoggedAdminPanel;

import gui.AfterAuthenticationGuiPanel;
import gui.BooksTableModel;
import gui.MainWindow;
import usersAndBooks.Book;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import database.DataBase;
import facade.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoggedAdminPanel extends AfterAuthenticationGuiPanel
{
    private static final String SIGN_UP_BUTTON_TEXT ="Wyloguj";
    private static final String SEARCH_FIELD_TEXT="Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107";
    private static final String SEARCH_BUTTON_TEXT="Szukaj";
    private static final String ADD_BOOK_BUTTON_TEXT ="Dodaj ksi\u0105\u017Ck\u0119";
    private static final String CHANGE_STATUS_BUTTON_TEXT="Zmie\u0144 status";
    
    /*Rozne komunikaty typu, ze cos sie udalo albo ze sie nie udalo itp*/
    private static final String DELETE_BOOK_SUCCESS_MSG="Uda\u0142o si\u0119 usun\u0105\u0107 zaznaczone ksi\u0105\u017Cki";
    private static final String DELETE_BOOK_UNSUCCESS_MSG="Nie uda\u0142o si\u0119 usun\u0105\u0107 zaznaczonych ksi\u0105\u017Cek";
    private static final String GIVE_BACK_SUCCESS_MSG="Uda\u0142o si\u0119 zwr\u00F3ci\u0107 zaznaczone ksi\u0105\u017Cki";
    private static final String GIVE_BACK_UNSUCCESS_MSG="Nie uda\u0142o si\u0119 zwr\u00F3ci\u0107 zaznaczonych ksi\u0105\u017Aek";

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
        
        
        UserService us=mainWindow.getUserService();
        BooksTableModel booksTableModel=new BooksTableModel(us.getNewBooks());

        
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
                AddNewBookDialog addDialog=new AddNewBookDialog(us);
                addDialog.showDialog();
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
            	/*
            	 * Wczytujemy tytul ksiazki. Jezeli uzytkownik cos wpisal, to ok -> szukamy
            	 * Jezeli nie, to nie szukamy + odpowiedni komunikat
            	 * */
            	String title =searchBookTextField.getText();
            	if(title.isEmpty())
            	showMessage(EMPTY_SEARCH_FIELD_MSG);
            	
            	else
            		{
            			List<Book> searchedBooks=us.searchForBook(title);
            			if(searchedBooks==null||searchedBooks.size()==0)
                			showMessage(EMPTY_SEARCH_LIST_MSG);
                
                		if(searchedBooks!=null) booksTableModel.setBooks(searchedBooks);
            		}
            	
            	
            }
        });
        add(searchButton);


        /*Tabelka*/



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
                    	/*
                    	 * Zmienna pomocnicza:
                    	 * Jezeli nie uda sie usunac jakiejs ksiazki, to zminiamy jej wartosc na false.
                    	 * Na koncu wyswietlamy komunikat:
                    	 * 1) Jezeli sie udalo usunac wszystkie ksiazki, czyli isDeleted=true, to ok
                    	 * 2) Jezeli sie nie udalo, to nie ok.
                    	 * */
                    	boolean isDeleted=true;
                    	
                        for(int i=0; i<indexesSelected.length; i++)
                        {
                        	Book b=booksTableModel.getBook(indexesSelected[i]-i);
                        	if(us.deleteBook(b)==UserService.SUCCESS) 
                        	{
                        		booksTableModel.deleteBook(indexesSelected[i]-i);
                        	}
                        	else isDeleted=false;
                        		
                        	
                        	
                        }
                        if(isDeleted) showMessage(DELETE_BOOK_SUCCESS_MSG);
                        else showMessage(DELETE_BOOK_UNSUCCESS_MSG);
                    }

                    @Override
                    public void giveBackButtonPressed()
                    {
                    	/*
                    	 * Podobna sytuacja jak z Usuwaniem ksiazki. Patrz wyzej
                    	 * */
                    	boolean isGaveBack=true;
                    	
                        for(int i=0; i<indexesSelected.length; i++)
                        {
                        	Book b=booksTableModel.getBook(indexesSelected[i]);
                        	if(us.returnBook(b)==UserService.SUCCESS) 
                        	{
                        		b.setLent(!b.isLent());
                        		booksTableModel.updateBook(indexesSelected[i], b);
                        	}
                        	else isGaveBack=false;
                        		
                        }
                        if(isGaveBack) showMessage(GIVE_BACK_SUCCESS_MSG);
                        else showMessage(GIVE_BACK_UNSUCCESS_MSG);
                    }
                }).showDialog();
            }
        });
        add(changeStatusButton);
    }
}
