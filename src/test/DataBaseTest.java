package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.DataBase;
import usersAndBooks.Book;

public class DataBaseTest {

	private DataBase dataBase;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Before
	public void initDatabaseObject() {
		dataBase = new DataBase("jdbc:mysql://localhost:3306/biblioteka_testowa","user1","userpassword");
	}
	@After
	public void closeDatabaseResources() {
		
	}

	@Test
	public void testGetNewBooks() {
		List<Book> resultList;
		Book book;
		
		resultList = dataBase.getNewBooks();
		assertEquals(2,resultList.size());
		
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
		assertEquals(1,resultList.size());

		
		book = resultList.get(0);
		assertEquals("Antygona",book.getTitle());
		
		
		resultList = dataBase.getBooks("Nie ma takiej ksi��ki");
		assertEquals(0, resultList.size());
		
	}

	@Test
	public void testGetAllBooks() {
		List<Book> resultList;
		Book book;
		
		resultList = dataBase.getAllBooks();
		assertEquals(3,resultList.size());
		
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
	public void testDeleteBook() 
	{	
		String signature="S002";
	
		assertEquals(1, dataBase.deleteBook(signature));
		List<Book> potopBooks=dataBase.getBooks("Potop");
		for(Book book: potopBooks)
		
			assertFalse(book.getSignature().equals(signature));
		
	}
	
	
	/*
	 * Po testowaniu usunięcia z bazy chcemy z powrotem dodać
	 * "Potop". Jeżeli już jest, to się nie doda więc w każdym razie będzie ok
	 * */
	
	@After
	public void addPotop()
	{
		String signature="S002";
		String author="Henryk Sienkiewicz";
		String title="Potop";
		
		Book book=new Book(title, author, signature, false, null, new Date());
		dataBase.addBook(book);
		
		
	}

	

	@Test
	public void testReturnBook() 
	{
		
		assertEquals(1, dataBase.returnBook("H001"));
		List<Book> illiadaBooks=dataBase.getBooks("Illiada");
		for(Book book: illiadaBooks)
		{
			if(book.getSignature().equals("H001")) assertFalse(book.isLent());
		}
	}


	
	/*
	 * Zmieniamy z powrotem status Illiady
	 * po testowaniu returnBook();
	 * */
	
	@After 
	public void lendIlliada()
	{
		String signature="H001";
		String userName="janko";
		
		dataBase.lendBook(signature, userName);
	}
	


	/*
	@Test
	public void testAddBookStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBookBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testLendBook() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

*/
	@Test
	public void testGetUserBooks() {
		List<Book> resultList;
		
		resultList = dataBase.getUserBooks("janko");
		
		assertEquals(1, resultList.size());
		assertEquals("Illiada", resultList.get(0).getTitle());
		
		resultList = dataBase.getUserBooks("kowal");
		assertEquals(0,resultList.size());
		
	}

}
