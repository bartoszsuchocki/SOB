package gui.LoggedAdminPanel;

import gui.AfterAuthenticationGuiPanel;
import gui.BooksTableModel;
import gui.DefaultDialog;
import gui.MainWindow;
import usersAndBooks.Book;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import database.DataBase;
import facade.UserService;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class LoggedAdminPanel extends AfterAuthenticationGuiPanel {
	private static final String SIGN_UP_BUTTON_TEXT = "Wyloguj";
	private static final String SEARCH_FIELD_TEXT = "Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107";
	private static final String SEARCH_BUTTON_TEXT = "Szukaj";
	private static final String ADD_BOOK_BUTTON_TEXT = "Dodaj ksi\u0105\u017Ck\u0119";
	private static final String CHANGE_STATUS_BUTTON_TEXT = "Zmie\u0144 status";
	private static final String SHOW_ALL_BOOKS_BUTTON_TEXT="Wy\u015Bwietl wszystkie ksi\u0105zki";
	private static final String SHOW_NEW_BOOKS_BUTTON_TEXT="Wy\u015Bwietl nowe ksi\u0105\u017Cki";

	/* Rozne komunikaty typu, ze cos sie udalo albo ze sie nie udalo itp */
	private static final String DELETE_BOOK_SUCCESS_MSG = "Uda\u0142o si\u0119 usun\u0105\u0107 zaznaczone ksi\u0105\u017Cki";
	private static final String DELETE_BOOK_UNSUCCESS_MSG = "Nie uda\u0142o si\u0119 usun\u0105\u0107 zaznaczonych ksi\u0105\u017Cek";
	private static final String GIVE_BACK_SUCCESS_MSG = "Uda\u0142o si\u0119 zwr\u00F3ci\u0107 zaznaczone ksi\u0105\u017Cki";
	private static final String GIVE_BACK_UNSUCCESS_MSG = "Nie uda\u0142o si\u0119 zwr\u00F3ci\u0107 zaznaczonych ksi\u0105\u017Aek";

	private JLabel searchFieldLabel;
	private JButton signUpButton;
	private JButton searchButton;
	private JTextField searchBookTextField;
	private JTable booksTable;
	private JScrollPane booksScrollPane;
	private JButton addBookButton;
	private JButton changeStatusButton;
	private JButton showAllBooksButton;
	private JButton showNewBooksButton;

	private BooksTableModel booksTableModel;
	private UserService userService;
	


	public LoggedAdminPanel(MainWindow mainWindow) {
		super(mainWindow);
		setLayout(null);
		
		
		
		userService = mainWindow.getUserService();
		booksTableModel = new BooksTableModel(); 

		
		
		
		
		/* Przycisk 'Wyloguj się' */
		signUpButton = new JButton(SIGN_UP_BUTTON_TEXT);
		signUpButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		signUpButton.setBounds(576, 11, 89, 23);
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui("logowanie");
			}
		});
		add(signUpButton);

		/* Przycisk 'Dodaj nową ksiązke' */
		addBookButton = new JButton(ADD_BOOK_BUTTON_TEXT);
		addBookButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		addBookButton.setBounds(53, 52, 201, 23);
		addBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable()
					
				{
					public void run()
					{
						AddNewBookDialog addDialog = new AddNewBookDialog(userService);
						addDialog.showDialog();
					}
						
				});
				
			}
		});
		add(addBookButton);

		/* Label 'Wpisz dane blablabla' */
		searchFieldLabel = new JLabel();
		searchFieldLabel.setText(SEARCH_FIELD_TEXT);
		searchFieldLabel.setBounds(53, 82, 301, 14);
		add(searchFieldLabel);

		/* Pole do wyszukiwania ksiązek */
		searchBookTextField = new JTextField();
		searchBookTextField.setHorizontalAlignment(SwingConstants.LEFT);
		searchBookTextField.setBounds(53, 107, 467, 23);
		searchBookTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				System.out.println(emptySearchFieldDialog.isVisible() +" "+ emptySearchListDialog.isVisible());
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER &&!(emptySearchFieldDialog.isVisible()) && !(emptySearchListDialog.isVisible()) ) {
					new Thread(new Runnable()
	        		{
	        			public void run()
	        			{
	        				synchronized(userService)
	        				{
	        					searchBooks(searchBookTextField,userService,booksTableModel);
	        				}
	        			}
	        		}).start();
				}

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		add(searchBookTextField);
		searchBookTextField.setColumns(10);

		/* Przycisk 'Wyszukaj' */
		searchButton = new JButton(SEARCH_BUTTON_TEXT);
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchButton.setBounds(530, 107, 89, 23);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable()
        		{
        			public void run()
        			{
        				synchronized(userService)
        				{
        					searchBooks(searchBookTextField,userService,booksTableModel);
        				}
        			}
        		}).start();
			}
		});
		add(searchButton);

		/* Tabelka */
		displayFirstState();
		booksTable = new JTable(booksTableModel);

		booksScrollPane = new JScrollPane(booksTable);

		booksScrollPane.setBounds(53, 169, 566, 211);

		add(booksScrollPane);

		changeStatusButton = new JButton(CHANGE_STATUS_BUTTON_TEXT);
		changeStatusButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		changeStatusButton.setBounds(53, 135, 150, 23);
		changeStatusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int indexesSelected[] = booksTable.getSelectedRows();

				new ChangeBookStatusDialog(new ChangeBookStatusInterface() {
					@Override
					public void deleteButtonPressed() {
						new Thread(new Runnable()
	            		{
	            			public void run()
	            			{
	            				synchronized(userService)
	            				{
	            					deleteSelectedBooks(indexesSelected);
	            				}
	            			}
	            		}).start();
					}

					@Override
					public void giveBackButtonPressed() {
						new Thread(new Runnable()
	            		{
	            			public void run()
	            			{
	            				synchronized(userService)
	            				{
	            					giveBackSelectedBooks(indexesSelected);
	            				}
	            			}
	            		}).start();
					}
				}).showDialog();
			}
		});
		add(changeStatusButton);
		showAllBooksButton = new JButton(SHOW_ALL_BOOKS_BUTTON_TEXT);
		showAllBooksButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		showAllBooksButton.setBounds(213, 135, 162, 23);
		showAllBooksButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				booksTableModel.setBooks(userService.getAllBooks());
			}
		});

		add(showAllBooksButton);
		
		showNewBooksButton = new JButton(SHOW_NEW_BOOKS_BUTTON_TEXT);
		showNewBooksButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		showNewBooksButton.setBounds(385, 135, 142, 23);
		showNewBooksButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				displayFirstState();
			}
		});
		add(showNewBooksButton);
		
	}
	public void displayFirstState()
	{
		Thread t=new Thread(new Runnable()
		{
			public void run()
			{
				synchronized(userService)
				{
					booksTableModel.setBooks(userService.getNewBooks());
				}
			}
		});
		
		t.start();
		
		try
		{
			t.join();
			searchBookTextField.setText("");

		}
		catch( InterruptedException ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void giveBackSelectedBooks(int[] indexesSelected)
	{
		boolean isGaveBack = true;

		for (int i = 0; i < indexesSelected.length; i++) {
			Book b = booksTableModel.getBook(indexesSelected[i]);
			if (userService.returnBook(b) == UserService.SUCCESS) {
				b.setLent(!b.isLent());
				booksTableModel.updateBook(indexesSelected[i], b);
			} else
				isGaveBack = false;

		}
		if (isGaveBack)
			showMessage(GIVE_BACK_SUCCESS_MSG);
		else
			showMessage(GIVE_BACK_UNSUCCESS_MSG);
	}
	
	private void deleteSelectedBooks(int[] indexesSelected)
	{
		/*
		 * Zmienna pomocnicza: Jezeli nie uda sie usunac jakiejs ksiazki, to zminiamy
		 * jej wartosc na false. Na koncu wyswietlamy komunikat: 1) Jezeli sie udalo
		 * usunac wszystkie ksiazki, czyli isDeleted=true, to ok 2) Jezeli sie nie
		 * udalo, to nie ok.
		 */
		boolean isDeleted = true;

		for (int i = 0; i < indexesSelected.length; i++) {
			Book b = booksTableModel.getBook(indexesSelected[i] - i);
			if (userService.deleteBook(b) == UserService.SUCCESS) {
				booksTableModel.deleteBook(indexesSelected[i] - i);
			} else
				isDeleted = false;

		}
		if (isDeleted)
			showMessage(DELETE_BOOK_SUCCESS_MSG);
		else
			showMessage(DELETE_BOOK_UNSUCCESS_MSG);
	}
	
}
