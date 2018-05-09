package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

//import com.sun.javafx.collections.SetAdapterChange;

import facade.UserService;
import usersAndBooks.Book;

public class LoggedUserPanel extends AfterAuthenticationGuiPanel {

	private static final String LEND_SUCCESS_MSG = "Uda\u0142o si\u0119 wypo\u017Cyczy\u0107";
	private static final String LEND_UNSUCCESS_MSG = "Nie uda\u0142o si\u0119 wypo\u017Cyczy\u0107";
	

	private JTextField searchTextField;
	private JTable booksTable;

	private UserService userService;
	private BooksTableModel booksTableModel;
	

	public LoggedUserPanel(MainWindow mainWindow) {
		super(mainWindow);

		
		emptySearchFieldDialog = new DefaultDialog(EMPTY_SEARCH_FIELD_MSG);
		emptySearchListDialog = new DefaultDialog(EMPTY_SEARCH_LIST_MSG);
		JSeparator separator = new JSeparator();

		userService = mainWindow.getUserService();

		/* Model tabelki */
		booksTableModel = new BooksTableModel();  //To te� powinno by� w odzielnym w�tku !!!

		/* Przycisk Moje konto */

		JButton myAccountButton = new JButton("Moje Konto");
		myAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui("mojeKonto");

			}
		});
		myAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 11));

		/* Przycisk Wyloguj */

		JButton signOutButton = new JButton("Wyloguj");
		signOutButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		signOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.changeGui("logowanie");
			}
		});

		JLabel searchLabel = new JLabel("Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107");
		

		searchTextField = new JTextField();
		searchTextField.setToolTipText("");
		searchTextField.setColumns(10);
		searchTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER &&!(emptySearchFieldDialog.isVisible()) && !(emptySearchListDialog.isVisible())) {
				
					new Thread(new Runnable()
					{
						public void run()
						{
							synchronized(userService)
							{
								searchBooks(searchTextField, userService, booksTableModel);
							}
								
						}
					}).start();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		JButton searchButton = new JButton("Szukaj");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchButton.setToolTipText("");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new Thread(new Runnable()
				{
					public void run()
					{
						synchronized(userService)
						{
							searchBooks(searchTextField, userService, booksTableModel);
						}
							
					}
				}).start();
				
			}
		});

		/* Tabelka */

		displayFistState();
		booksTable = new JTable(booksTableModel);

		JScrollPane scrollPaneTab = new JScrollPane(booksTable);

		/* Wypo�yczanie ksi��ek */

		JButton lendButton = new JButton("Wypo\u017Cycz zaznaczone ksi\u0105\u017Cki");

		lendButton.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Thread(new Runnable()
				{
					public void run()
					{
						synchronized(userService)
						{
							int indexesSelected[] = booksTable.getSelectedRows();
							boolean isLend = true;

							for (int i = 0; i < indexesSelected.length; i++) {
								Book book = booksTableModel.getBook(indexesSelected[i]);
								if (userService.lendBook(book) == UserService.SUCCESS) {
									book.setLent(!book.isLent());
									booksTableModel.updateBook(indexesSelected[i], book);
								} else
									isLend = false;

							}
							
							/*Wyświetlamy komunikaty wtw, gdy coś wybraliśmy
							 * Jeżeli nic nie wybraliśmy, to nic nie wypożyczymy a zatem komunikaty o tym, czy się udało, 
							 * czy nie są bez sensu*/
							if(indexesSelected.length>0)
							{
								if (isLend)
									showMessage(LEND_SUCCESS_MSG);
								else
									showMessage(LEND_UNSUCCESS_MSG);
							}
						}
							
					}
				}).start();
				

			}
		});
		
		JButton showAllBooksButton = new JButton("Wy\u015Bwietl wszystkie ksi\u0105\u017Cki");
		showAllBooksButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		showAllBooksButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread(new Runnable()
				{
					public void run()
					{
						synchronized(userService)
						{
							booksTableModel.setBooks(userService.getAllBooks());
						}
							
					}
				}).start();
			}
		});

		
		JButton showNewBooksButton = new JButton("Wy\u015Bwietl nowe ksi\u0105\u017Cki");
		showNewBooksButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		showNewBooksButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread(new Runnable()
				{
					public void run()
					{
						synchronized(userService)
						{
							booksTableModel.setBooks(userService.getNewBooks());
						}
						
					}
				}).start();
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(464)
							.addComponent(myAccountButton)
							.addGap(5)
							.addComponent(signOutButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(searchLabel, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPaneTab, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lendButton)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(showAllBooksButton, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(showNewBooksButton, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
										.addComponent(searchTextField, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
									.addGap(5)
									.addComponent(searchButton)))))
					.addGap(44))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(signOutButton)
							.addGap(53)
							.addComponent(searchLabel))
						.addComponent(myAccountButton))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lendButton)
						.addComponent(showNewBooksButton)
						.addComponent(showAllBooksButton))
					.addGap(7)
					.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(84, Short.MAX_VALUE))
		);

		scrollPaneTab.setViewportView(booksTable);
		setLayout(groupLayout);

	}
	public void displayFistState()
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				synchronized(userService)
				{
					booksTableModel.setBooks(userService.getNewBooks()); // to te� wielow�tkowo !!!
				}
			}
		}).start();;

		
			searchTextField.setText("");

		
	}

	


}
