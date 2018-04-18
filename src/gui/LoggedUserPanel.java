package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JScrollBar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import facade.UserService;
import gui.LoggedAdminPanel.ChangeBookStatusDialog;
import usersAndBooks.Book;

import javax.swing.JScrollPane;
import java.awt.Font;

public class LoggedUserPanel extends AfterAuthenticationGuiPanel {
	
	private static final String LEND_SUCCESS_MSG="Uda\u0142o si\u0119 wypo\u0105yczy\u0107 zaznaczone ksi\u0105\u017Cki";
    private static final String LEND_UNSUCCESS_MSG="Nie uda\u0142o si\u0119 wypo\u0105yczy\u0107 zaznaczonych ksi\u0105\u017Aek";
	
	private JTextField textFieldWyszukiwanie;
	private JTable booksTable;

	private UserService us;

	public LoggedUserPanel(MainWindow mainWindow){
		super(mainWindow);
		
		JSeparator separator = new JSeparator();
		
		us=mainWindow.getUserService();
		
		/*Model tabelki*/
		BooksTableModel booksTableModel=new BooksTableModel(us.getNewBooks());

		
		/*Przycisk Moje konto*/
		
		JButton btnMojeKonto = new JButton("Moje Konto");
		btnMojeKonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui("mojeKonto");
				
			}
		});
		btnMojeKonto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		/*Przycisk Wyloguj*/
		
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
		
		JButton btnWyszukaj = new JButton("Wyszukaj");
		btnWyszukaj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnWyszukaj.setToolTipText("");
		btnWyszukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				String title =textFieldWyszukiwanie.getText();
            	if(title.isEmpty())
            	{
            		showMessage(EMPTY_SEARCH_FIELD_MSG);
            	}
            	else
            	{
            		List<Book> searchedBooks=us.searchForBook(title);
            		if(searchedBooks==null||searchedBooks.size()==0)
            			showMessage(EMPTY_SEARCH_LIST_MSG);
            
            		if(searchedBooks!=null) booksTableModel.setBooks(searchedBooks);
            	}
			}
		});
				
				/*Tabelka*/
		
				booksTable = new JTable(booksTableModel);
        
				JScrollPane scrollPaneTab = new JScrollPane(booksTable);
				
				/*Wypo�yczanie ksi��ek*/
				
				JButton btnWypozycz = new JButton("Wypo\u017Cycz zaznaczone ksi\u0105\u017Cki");
				
			
				btnWypozycz.setFont(new Font("Tahoma", Font.PLAIN, 11));
				
				btnWypozycz.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						int indexesSelected[]=booksTable.getSelectedRows();
						boolean isLend=true;
                    	
                        for(int i=0; i<indexesSelected.length; i++)
                        {
                        	Book b=booksTableModel.getBook(indexesSelected[i]);
                        	if(us.lendBook(b)==UserService.SUCCESS) 
                        	{
                        		b.setLent(!b.isLent());
                        		booksTableModel.updateBook(indexesSelected[i], b);
                        	}
                        	else isLend=false;
                        		
                        }
                        if(isLend) showMessage(LEND_SUCCESS_MSG);
                        else showMessage(LEND_UNSUCCESS_MSG);
						
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
										.addComponent(btnWypozycz))))
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
							.addComponent(btnWypozycz)
							.addGap(7)
							.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(86, Short.MAX_VALUE))
				);
				
				
				
				scrollPaneTab.setViewportView(booksTable);
				setLayout(groupLayout);
		



		
		
		
	}
}
