package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usersAndBooks.Admin;
import usersAndBooks.Book;
import usersAndBooks.DefaultUser;
import usersAndBooks.User;


public class DataBase 
{
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String DBPATH = "jdbc:mysql://localhost:3306/biblioteka";
	private final String USERNAME = "user1";
	private final String USER_PASSWORD = "userpassword";
	private final String GET_NEW_BOOKS_QUERY="call getnewbooks();";
	private final String GET_BOOKS_QUERY="call getbooks(?)";
	private final String GET_ALL_BOOKS_QUERY="call getallbooks();";
	private final String ADD_BOOK_QUERY="call addbook(?,?,?);";
	private final String LEND_BOOK_QUERY="call lentbook(?,?)"; 
	private final String DELETE_BOOK_QUERY="call deletebook(?);"; 
	private final String RETURN_BOOK_QUERY="call returnbook(?);";
	private final String GET_USER_QUERY="call getuser(?);";
	private final String ADD_USER_QUERY="call adduser(?,?,?,?,?);";
	private final String GET_USER_BOOKS="call getuserbooks(?);";
	
	private Connection connection = null;
	private PreparedStatement statement = null; 
	
	public DataBase()
	{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// Nie mam pomys³u co tu zrobiæ !
			e.printStackTrace();
			
		}
	}
	private synchronized void openRecources() throws SQLException {
		if (connection==null || connection.isClosed())
			connection = DriverManager.getConnection(DBPATH, USERNAME, USER_PASSWORD);

//		System.out.println("Zasoby otwarte!");
		
	}
	private void closeRecources() throws SQLException
	{
		if(statement!=null && !(statement.isClosed()) )
			statement.close();
		if(connection!=null && !(connection.isClosed()) )
			connection.close();
	}
	private List<Book> transformResultSetToBookList(ResultSet resultSet)
	{
		List<Book> bookList = new ArrayList<>();
		if(resultSet==null)
			return bookList;
		try {
			while(resultSet.next())
			{
				bookList.add(new Book(resultSet.getString("title"),
						resultSet.getString("author"),
						resultSet.getString("signature"),
						resultSet.getBoolean("islent"),
						resultSet.getString("pesel"),
						resultSet.getDate("date")
						));
			}
		} catch (SQLException e) {
			//nic zlego sie nie stanie, zwroci po prostu pusta liste
			e.printStackTrace();
		}
		return bookList;
	}
	
//	private List<Book> executeSelectBooksQuery(PreparedStatement preparedStatement)
//	{
//		List<Book> bookList=null;
//		ResultSet result=null;
//		try {
//			openRecources();
//			result=preparedStatement.executeQuery();
//		} catch (SQLException e) {
//			//najwyzej przekazemy nulla do transformatora, a on zwroci pusta liste
//			e.printStackTrace();
//		}
//		bookList = transformResultSetToBookList(result);
//		//nowy try catch, zeby transformacja byla poza nim. Dzieki temu w razie wczesniejszego bledu, transformacja zwroci pusta liste
//		try {
//			closeRecources();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return bookList;
//	}
	
	public List<Book> getNewBooks()
	{
		List<Book> bookList;
	
		ResultSet result=null;
		try {
			openRecources();
			statement = connection.prepareStatement(GET_NEW_BOOKS_QUERY);
			result = statement.executeQuery();
			
			
		} catch (SQLException e) {
			//najwyzej przekazemy nulla do transformatora, a on zwroci pusta liste
			e.printStackTrace();
		}
		bookList = transformResultSetToBookList(result);
		//nowy try catch, zeby transformacja byla poza nim. Dzieki temu w razie wczesniejszego bledu, transformacja zwroci pusta liste
		try { 
			closeRecources();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}
	public List<Book> getBooks(String title) //jeszcze nie przetestowana
	{
		List<Book> bookList;
		ResultSet result=null;
		try {
			openRecources();
			statement = connection.prepareStatement(GET_BOOKS_QUERY);
			statement.setString(1, title);
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookList = transformResultSetToBookList(result);
		try {
			closeRecources();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bookList;
	}
	public List<Book> getAllBooks()
	{
		List<Book> bookList;
		ResultSet result=null;
		try {
			openRecources();
			statement = connection.prepareStatement(GET_ALL_BOOKS_QUERY);
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookList = transformResultSetToBookList(result);
		try {
			closeRecources();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bookList;
	}
	
	public int addBook(String signature, String title, String author)
	{
		int rowsAffected=0;
		try {
			openRecources();
			statement = connection.prepareStatement(ADD_BOOK_QUERY);
			statement.setString(1, signature);
			statement.setString(2, title);
			statement.setString(3, author);
			rowsAffected=statement.executeUpdate();
			closeRecources();
		} catch (SQLException e) {
			//najwyzej rowsAffected pozostanie 0
			e.printStackTrace();
		}
		return rowsAffected;
	}
	public int addBook(Book book)
	{
		int rowsAffected=0;
		try {
			openRecources();
			statement = connection.prepareStatement(ADD_BOOK_QUERY);
			statement.setString(1, book.getSignature());
			statement.setString(2, book.getTitle());
			statement.setString(3, book.getAuthor());
			rowsAffected=statement.executeUpdate();
			closeRecources();
		} catch (SQLException e) {
			//najwyzej rowsAffected pozostanie 0
			e.printStackTrace();
		}
		return rowsAffected;
	}
	public int lendBook(String signature, String login)
	{
		int rowsAffected=0;
		try {
			openRecources();
			statement = connection.prepareStatement(LEND_BOOK_QUERY);
			statement.setString(1, signature);
			statement.setString(2, login);
			rowsAffected=statement.executeUpdate();
			closeRecources();
		} catch (SQLException e) {
			//najwyzej rowsAffected pozostanie 0
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public int deleteBook(String signature)
	{
		int rowsAffected=0;
		try {
			openRecources();
			statement = connection.prepareStatement(DELETE_BOOK_QUERY);
			statement.setString(1, signature);
			rowsAffected=statement.executeUpdate();
			closeRecources();
		} catch (SQLException e) {
			//najwyzej rowsAffected pozostanie 0
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public int returnBook(String signature)
	{
		int rowsAffected=0;
		try {
			openRecources();
			statement = connection.prepareStatement(RETURN_BOOK_QUERY);
			statement.setString(1, signature);
			rowsAffected=statement.executeUpdate();
			closeRecources();
		} catch (SQLException e) {
			//najwyzej rowsAffected pozostanie 0
			e.printStackTrace();
		}
		return rowsAffected;
	}
	private User transformResultSetToOneUser(ResultSet resultSet) 
	{
		if(resultSet==null)
			return null;
		User user=null;
		try {
			while(resultSet.next())
			{
				int rola = resultSet.getInt("role");
				if(rola==1)
				{
					user = new DefaultUser(
							resultSet.getString("name"),
							resultSet.getString("surname"),
							resultSet.getString("login"),
							resultSet.getString("password"),
							resultSet.getLong("pesel")
							);			
				}
				else if(rola==2)
				{
					user = new Admin(
							resultSet.getString("login"),
							resultSet.getString("password")
							);
				}
			}
		} catch (SQLException e) {
			//user pozostanie nullem
			e.printStackTrace();
		}
		return user;
	}
	public User getUser(String login) // UWAGA: zwraca null, jesli nie ma takiego uzytkownika | w procedurze sql moze byc niepotrzebna obsluga braku uzytkownika 
	{
		ResultSet result=null;
		User user;
		try {
			openRecources();
			statement = connection.prepareStatement(GET_USER_QUERY);
			statement.setString(1, login);
			result = statement.executeQuery();
		} catch (SQLException e) {
			
			// najwyzej transformator dostanie resultset jako null i zwroci null
			e.printStackTrace();
		}
		user = transformResultSetToOneUser(result);
		try {
			closeRecources();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public int addUser(long pesel, String name, String surname, String login, String password)
	{
		int rowsAffected=0;
		try {
			openRecources();
			statement = connection.prepareStatement(ADD_USER_QUERY);
			statement.setLong(1, pesel);
			statement.setString(2, name);
			statement.setString(3, surname);
			statement.setString(4, login);
			statement.setString(5, password);
			rowsAffected=statement.executeUpdate();
		} catch (SQLException e) {
			//zwroci 0
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public int addUser(DefaultUser user)
	{
		int rowsAffected=0;
		try {
			openRecources();
			statement = connection.prepareStatement(ADD_USER_QUERY);
			statement.setLong(1, user.getPESEL());
			statement.setString(2, user.getName());
			statement.setString(3, user.getSurname());
			statement.setString(4, user.getLogin());
			statement.setString(5, user.getPassword());
			rowsAffected=statement.executeUpdate();
		} catch (SQLException e) {
			//zwroci 0
			e.printStackTrace();
		}
		return rowsAffected;
	}
	public List<Book> getUserBooks(String login)
	{
		List<Book> bookList;
		ResultSet result=null;
		try {
			openRecources();
			statement = connection.prepareStatement(GET_USER_BOOKS);
			statement.setString(1, login);
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookList = transformResultSetToBookList(result);
		try {
			closeRecources();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bookList;
	}
	

	//do testowania/debuggowania
	public static void main(String[] args)
	{
		DataBase db = new DataBase();
		List<Book> l = db.getUserBooks("suchy");
		for(Book b: l)
		{
			System.out.println(b.getSignature()+" "+b.getTitle()+ " "+b.getAuthor());
		}
		
		System.out.println(db.addBook("BBBB011", "Programowanie sob", "Bartosz Suchocki"));
		System.out.println(db.lendBook("BBBB011", "janko"));
		System.out.println(db.returnBook("BBBB011"));
		System.out.println(db.deleteBook("BBBB011"));
		
		System.out.println(db.addUser(9811250878L, "Bartosz", "Suchocki", "suchar1270", "haselko"));
		User user = db.getUser("suchar1270");
		System.out.println(user);
		
//		User user = new DefaultUser("Jan", "Kowalik", "kowalik", "haslo", 99223308778L);
//		System.out.println(db.addUser((DefaultUser)user));
		
		//do przetestowania jeszcze: zwracane wartosci jak nie znajdzie ksiazek/usera
		
	}
	


}
