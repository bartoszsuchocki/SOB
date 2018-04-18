package gui;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import facade.UserService;
import usersAndBooks.Book;

import javax.swing.JTable;

public class GuestGui extends AfterAuthenticationGuiPanel {

	/**
	 * Create the panel.
	 */
	private  MainWindow mainWindow;
	private JLabel logoLabel;
	private JLabel iconLabel;
	private JTextField txtWpiszDaneKsiki;
	private JTable table;
    private JScrollPane booksScrollPane;

	public GuestGui(MainWindow mainWindow) {
		super(mainWindow);
		
		JButton btnPowrt = new JButton("Powr\u00F3t");
		btnPowrt.setBounds(601, 11, 89, 23);
		add(btnPowrt);
		
		UserService us=mainWindow.getUserService();
		
		BooksTableModel booksTableModel=new BooksTableModel(us.getNewBooks());
		
		txtWpiszDaneKsiki = new JTextField();
		txtWpiszDaneKsiki.setHorizontalAlignment(SwingConstants.LEFT);
		txtWpiszDaneKsiki.setText("Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107");
		txtWpiszDaneKsiki.setBounds(52, 98, 474, 34);
		
		add(txtWpiszDaneKsiki);
		txtWpiszDaneKsiki.setColumns(10);
		
		JButton btnSzukaj = new JButton("Szukaj");
		btnSzukaj.addActionListener(new ActionListener()
	    {
	       @Override
	       public void actionPerformed(ActionEvent e)
	       {
	    	   
           	String title =txtWpiszDaneKsiki.getText();
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
		btnSzukaj.setBounds(536, 98, 89, 34);
		add(btnSzukaj);
		
		table = new JTable(booksTableModel);
        booksScrollPane=new JScrollPane(table);
        booksScrollPane.setBounds(52, 154, 474, 211);

		add(booksScrollPane);
		//logoLabel.setBounds(100, 100, 60, 30);
	}
}
