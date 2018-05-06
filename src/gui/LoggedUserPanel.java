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

	private static final String LEND_SUCCESS_MSG = "Uda\u0142o si\u0119 wypo\u0105yczy\u0107 zaznaczone ksi\u0105\u017Cki";
	private static final String LEND_UNSUCCESS_MSG = "Nie uda\u0142o si\u0119 wypo\u0105yczy\u0107 zaznaczonych ksi\u0105\u017Aek";
	

	private JTextField textFieldWyszukiwanie;
	private JTable booksTable;

	private UserService us;
	private BooksTableModel booksTableModel;
	

	public LoggedUserPanel(MainWindow mainWindow) {
		super(mainWindow);

		
		emptySearchFieldDialog = new DefaultDialog(EMPTY_SEARCH_FIELD_MSG);
		emptySearchListDialog = new DefaultDialog(EMPTY_SEARCH_LIST_MSG);
		JSeparator separator = new JSeparator();

		us = mainWindow.getUserService();

		/* Model tabelki */
		booksTableModel = new BooksTableModel();  //To te� powinno by� w odzielnym w�tku !!!

		/* Przycisk Moje konto */

		JButton btnMojeKonto = new JButton("Moje Konto");
		btnMojeKonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui("mojeKonto");

			}
		});
		btnMojeKonto.setFont(new Font("Tahoma", Font.PLAIN, 11));

		/* Przycisk Wyloguj */

		JButton btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.changeGui("logowanie");
			}
		});

		JLabel lblDaneKsiazki = new JLabel("Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107");
		lblDaneKsiazki.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textFieldWyszukiwanie = new JTextField();
		textFieldWyszukiwanie.setToolTipText("");
		textFieldWyszukiwanie.setColumns(10);
		textFieldWyszukiwanie.addKeyListener(new KeyListener() {

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
							synchronized(us)
							{
								searchBooks(textFieldWyszukiwanie, us, booksTableModel);
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

		JButton btnWyszukaj = new JButton("Szukaj");
		btnWyszukaj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnWyszukaj.setToolTipText("");
		btnWyszukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new Thread(new Runnable()
				{
					public void run()
					{
						synchronized(us)
						{
							searchBooks(textFieldWyszukiwanie, us, booksTableModel);
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

		JButton btnWypozycz = new JButton("Wypo\u017Cycz zaznaczone ksi\u0105\u017Cki");

		btnWypozycz.setFont(new Font("Tahoma", Font.PLAIN, 11));

		btnWypozycz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Thread(new Runnable()
				{
					public void run()
					{
						synchronized(us)
						{
							int indexesSelected[] = booksTable.getSelectedRows();
							boolean isLend = true;

							for (int i = 0; i < indexesSelected.length; i++) {
								Book b = booksTableModel.getBook(indexesSelected[i]);
								if (us.lendBook(b) == UserService.SUCCESS) {
									b.setLent(!b.isLent());
									booksTableModel.updateBook(indexesSelected[i], b);
								} else
									isLend = false;

							}
							if (isLend)
								showMessage(LEND_SUCCESS_MSG);
							else
								showMessage(LEND_UNSUCCESS_MSG);
						}
							
					}
				}).start();
				

			}
		});
		
		JButton btnWywietlWszystkieKsiki = new JButton("Wy\u015Bwietl wszystkie ksi\u0105\u017Cki");
		btnWywietlWszystkieKsiki.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnWywietlWszystkieKsiki.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread(new Runnable()
				{
					public void run()
					{
						synchronized(us)
						{
							booksTableModel.setBooks(us.getAllBooks());
						}
							
					}
				}).start();
			}
		});

		
		JButton btnWywietlNoweKsike = new JButton("Wy\u015Bwietl nowe ksi\u0105\u017Cki");
		btnWywietlNoweKsike.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnWywietlNoweKsike.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread(new Runnable()
				{
					public void run()
					{
						synchronized(us)
						{
							booksTableModel.setBooks(us.getNewBooks());
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
							.addComponent(btnMojeKonto)
							.addGap(5)
							.addComponent(btnWyloguj, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(lblDaneKsiazki, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPaneTab, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textFieldWyszukiwanie, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
									.addGap(5)
									.addComponent(btnWyszukaj))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnWypozycz)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnWywietlWszystkieKsiki)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnWywietlNoweKsike)))))
					.addGap(44))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnWyloguj)
							.addGap(53)
							.addComponent(lblDaneKsiazki))
						.addComponent(btnMojeKonto))
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldWyszukiwanie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnWyszukaj, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnWypozycz)
						.addComponent(btnWywietlWszystkieKsiki)
						.addComponent(btnWywietlNoweKsike))
					.addGap(7)
					.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(83, Short.MAX_VALUE))
		);

		scrollPaneTab.setViewportView(booksTable);
		setLayout(groupLayout);

	}
	public void displayFistState()
	{
		Thread t=new Thread(new Runnable()
		{
			public void run()
			{
				synchronized(us)
				{
					booksTableModel.setBooks(us.getNewBooks()); // to te� wielow�tkowo !!!
				}
			}
		});
		
		t.start();
		
		try
		{
			t.join();
			textFieldWyszukiwanie.setText("");

		}
		catch( InterruptedException ex)
		{
			ex.printStackTrace();
		}
		
	}

	


}
