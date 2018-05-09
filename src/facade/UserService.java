package facade;

import java.util.ArrayList;
import java.util.List;

import database.DataBase;
import gui.MainWindow;
import usersAndBooks.Book;
import usersAndBooks.User;

public class UserService {

	/*
	 * ZWRACAMY SUCCESS WARTOSCI JEZELI DODAWANIE, USUNIECIE ITP SIE POWIODLY
	 * ZWRACAMY UNSUCCESS W PRZECIWNYM PRZYPADKU
	 */
	public static final int SUCCESS = 1;
	public static final int UNSUCCESS = 0;

	private DataBase DataBase;
	private User currentUser;

	public UserService() {
		DataBase = new DataBase();
	}

	public void autheniticate(String login, String password, StringBuilder errorBuffer, StringBuilder whichGui) {

		currentUser = DataBase.getUser(login);

		if (currentUser != null && !(password.equals(currentUser.getPassword()))) {
			currentUser = null;
			errorBuffer.append("Bledne haslo");
		} else if (currentUser != null) {
			if (currentUser.getRole() == 1)
                whichGui.append(MainWindow.LOGGEDUSER);
			else if (currentUser.getRole() == 2)
                whichGui.append(MainWindow.ADMIN);
		} else {
			errorBuffer.append("Nie ma takiego uzytkownika");
		}

	}

	public void register(String login, String pesel, String name, String surname, String password,
			StringBuilder errorBuffer) {

		User resultUser = DataBase.getUser(login);

		if (resultUser != null) {
			errorBuffer.append("Login zajety!");
		} else {
			if (DataBase.addUser(Long.valueOf(pesel), name, surname, login, password) == 1) {
				errorBuffer.append("");
			} else {
				errorBuffer.append("Nie udalo sie zarejestrowac");
			}
		}

	}

	/* ZLEEEE. TRZEBA TO DOKONCZYC */
	// public List<Book> getLendBooks()
	// {
	//
	// return new ArrayList();
	// }

	/* ZWRACAMY NOWE KSIAZKI */

	public List<Book> getAllBooks()
	{
		return DataBase.getAllBooks();
	}
	public List<Book> getNewBooks() {
		return DataBase.getNewBooks();
	}

	/* ZWRACAMY KSIAZKI O TYTULE 'title' */
	public List<Book> searchForBook(String title) {

		if (title.isEmpty() || title == null)
			return null;

		else
			return DataBase.getBooks(title);
	}

	
	public List<Book> getUsersBooks() {
		if (currentUser == null)
			return new ArrayList<Book>(); // bylo return null
		return DataBase.getUserBooks(currentUser.getLogin());
	}

	/*
	 * ZMIENIAMY STATUS KSIAZKI W BAZIE NA WYPOZYCZONA (JEZELI DA SIE ZMNIENIC) WPP
	 * ZWRACAMY UNSUCCESS
	 */
	public int returnBook(Book book) {
		if (DataBase.returnBook(book.getSignature()) > 0)
			return SUCCESS;
		return UNSUCCESS;
	}

	/*
	 * TO SAMO CO WYZEJ TYLKO WYPOZYCZAMY
	 */
	public int lendBook(Book book) {
		if (DataBase.lendBook(book.getSignature(), currentUser.getLogin()) > 0)
			return SUCCESS;
		return UNSUCCESS;
	}

	public int addBook(Book book) {
		if (book == null)
			return UNSUCCESS;

		if (DataBase.addBook(book) > 0)
			return SUCCESS;
		return UNSUCCESS;
	}

	public int deleteBook(Book book) {
		if (book == null)
			return UNSUCCESS;

		if (DataBase.deleteBook(book.getSignature()) > 0)
			return SUCCESS;
		return UNSUCCESS;
	}

	public User getUser() {
		return currentUser;
	}

	public void setUser(User user) {
		this.currentUser = user;
	}
}
