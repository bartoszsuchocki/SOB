package gui;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import facade.UserService;
import usersAndBooks.Book;
import java.awt.Color;

public abstract class AfterAuthenticationGuiPanel extends JPanel
{
	protected static final String EMPTY_SEARCH_FIELD_MSG = "Wpisz dane do wyszukiwania";
	protected static final String EMPTY_SEARCH_LIST_MSG = "Nie ma takich ksi\u0105\u017Cek";
	private JLabel logoLabel; //na nazw� programu
	private JLabel iconLabel; //na ikon� programu
	MainWindow mainWindow; //potrzebny, zeby np. uzyc funkcji changeGui
	private final int width=700;
	private final int height=500;
	
	protected DefaultDialog emptySearchFieldDialog;
	protected DefaultDialog emptySearchListDialog;

	
	public AfterAuthenticationGuiPanel(MainWindow mainWindow)
	{
		setBackground(new Color(255, 228, 181));
		
		this.mainWindow = mainWindow;
		setLayout(null);
		
		setBounds(0,0,width,height);
		
		
		emptySearchFieldDialog = new DefaultDialog(EMPTY_SEARCH_FIELD_MSG);
		emptySearchListDialog = new DefaultDialog(EMPTY_SEARCH_LIST_MSG);
		
		/*tu trzeba zdefiniowac jak b�dzie wygl�da� logo naszego systemu wyswietlane na kazdym (oprocz logowania i rejestracji) gui.*/
		/*iconLabel = new JLabel("");
		iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconLabel.setIcon(new ImageIcon(AfterAuthenticationGuiPanel.class.getResource("/javax/swing/plaf/metal/icons/Question.gif")));
		iconLabel.setBounds(47, 0, 40, 64);
		add(iconLabel);*/
		
		logoLabel = new JLabel("System Obs\u0142ugi Biblioteki");
		logoLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logoLabel.setBounds(97, 0, 231, 64);
		add(logoLabel);
		
		/*koniec definiowania logo*/
		
		
	}
	
	public void searchBooks(JTextField searchTextField, UserService userService, BooksTableModel booksTableModel)
	{
		/*
		 * Wczytujemy tytul ksiazki. Jezeli uzytkownik cos wpisal, to ok -> szukamy
		 * Jezeli nie, to nie szukamy + odpowiedni komunikat
		 */
		String title = searchTextField.getText();
		if (title.isEmpty())
			showMessage(emptySearchFieldDialog);

		else {
			List<Book> searchedBooks = userService.searchForBook(title);
			if (searchedBooks == null || searchedBooks.size() == 0)
				showMessage(emptySearchListDialog);

			if (searchedBooks != null)
				booksTableModel.setBooks(searchedBooks);
		}
	}
	
	public static void showMessage(DefaultDialog dialog)
	{
		dialog.setVisible(true);
	}
	public static void showMessage(String message)
	{
		showMessage("Komunikat", message);
	}
	
	public static void showMessage(String title, String message)
	{
        DefaultDialog defaultDialog = new DefaultDialog(title, message);
        defaultDialog.setVisible(true);
	}

}
