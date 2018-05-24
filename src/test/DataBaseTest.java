package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;

import org.junit.After;

import org.junit.BeforeClass;
import org.junit.Test;

import database.DataBase;
import usersAndBooks.Book;
import usersAndBooks.DefaultUser;
import usersAndBooks.User;

import static org.junit.Assert.*;

public class DataBaseTest {

	private static DataBase dataBase;

	@BeforeClass
	public static void initDatabaseObject() {
		dataBase = new DataBase("jdbc:mysql://localhost:3306/biblioteka_testowa", "user1", "userpassword");
	}

	@Test
	public void testGetNewBooks() {
		List<Book> resultList;
		Book book;

		resultList = dataBase.getNewBooks();
		assertEquals(2, resultList.size());

		book = resultList.get(0);
		assertEquals("Antygona", book.getTitle());
		assertEquals("Sofokles", book.getAuthor());

		book = resultList.get(1);
		assertEquals("Potop", book.getTitle());
		assertEquals("Henryk Sienkiewicz", book.getAuthor());

	}

	@Test
	public void testGetBooks() {
		List<Book> resultList;
		Book book;

		resultList = dataBase.getBooks("Antygona");
		assertEquals(1, resultList.size());

		book = resultList.get(0);
		assertEquals("Antygona", book.getTitle());

		resultList = dataBase.getBooks("Nie ma takiej ksi��ki");
		assertEquals(0, resultList.size());

	}

	@Test
	public void testGetAllBooks() {
		List<Book> resultList;
		Book book;

		resultList = dataBase.getAllBooks();
		assertEquals(3, resultList.size());

		book = resultList.get(0);
		assertEquals("Illiada", book.getTitle());
		assertEquals("Homer", book.getAuthor());

		book = resultList.get(1);
		assertEquals("Antygona", book.getTitle());
		assertEquals("Sofokles", book.getAuthor());

		book = resultList.get(2);
		assertEquals("Potop", book.getTitle());
		assertEquals("Henryk Sienkiewicz", book.getAuthor());

	}

	@Test
	public void testDeleteBook() {
		String signature = "S002";

		assertEquals(1, dataBase.deleteBook(signature));
		List<Book> potopBooks = dataBase.getBooks("Potop");
		for (Book book : potopBooks)

			assertFalse(book.getSignature().equals(signature));

	}

	@After
	public void addPotop() {
		String signature = "S002";
		String author = "Henryk Sienkiewicz";
		String title = "Potop";

		Book book = new Book(title, author, signature, false, null, new Date());
		dataBase.addBook(book);

	}

	@Test
	public void testReturnBook() {

		assertEquals(1, dataBase.returnBook("H001"));
		List<Book> illiadaBooks = dataBase.getBooks("Illiada");
		for (Book book : illiadaBooks) {
			if (book.getSignature().equals("H001"))
				assertFalse(book.isLent());
		}
	}

	/*
	 * Zmieniamy z powrotem status Illiady po testowaniu returnBook();
	 */

	@After
	public void lendIlliada() {
		String signature = "H001";
		String userName = "janko";

		dataBase.lendBook(signature, userName);
	}

	@Test
	public void testAddBookStringStringString() {
		List<Book> resultList;

		assertEquals(1, dataBase.addBook("G001", "Ferdydurke", "Witold Gombrowicz"));

		resultList = dataBase.getAllBooks();

		assertEquals(4, resultList.size());
		assertEquals("G001", resultList.get(0).getSignature());
		assertEquals("Ferdydurke", resultList.get(0).getTitle());
		assertEquals("Witold Gombrowicz", resultList.get(0).getAuthor());
	}

	@After
	public void deleteFerdydurke() {
		dataBase.deleteBook("G001");
	}

	@Test
	public void testAddBookBook() {
		List<Book> resultList;
		Book book = new Book("Faust", "Goethe", "G002", null, new Date(5));

		assertEquals(1, dataBase.addBook(book));

		resultList = dataBase.getAllBooks();

		assertEquals("G002", resultList.get(0).getSignature());
		assertEquals("Faust", resultList.get(0).getTitle());
		assertEquals("Goethe", resultList.get(0).getAuthor());
	}

	@After
	public void deleteFaust() {
		dataBase.deleteBook("G002");
	}

	@Test
	public void testGetUser() {

		User corUser = dataBase.getUser("janko");

		assertEquals(98050489784L, ((DefaultUser) corUser).getPESEL());

		User incUser = dataBase.getUser("NieMaTakiegoLoginu");

		assertNull(incUser);

	}

	@Test
	public void testAddUser() {
		int result = dataBase.addUser(98081109876L, "Pan", "Testowy", "testowy", "test123");

		User user = dataBase.getUser("testowy");
		assertEquals(1, result);
		assertEquals("testowy", user.getLogin());

	}

	@After
	public void deleteAddedUser() {

		long userPesel = 98081109876L;

		dataBase.deleteUser(userPesel);

	}

	@Test
	public void testGetUserBooks() {
		List<Book> resultList;

		resultList = dataBase.getUserBooks("janko");

		assertEquals(1, resultList.size());
		assertEquals("Illiada", resultList.get(0).getTitle());

		resultList = dataBase.getUserBooks("kowal");
		assertEquals(0, resultList.size());

	}

}
