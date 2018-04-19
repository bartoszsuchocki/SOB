package facade;

import java.util.ArrayList;
import java.util.List;

import database.DataBase;
import usersAndBooks.Book;
import usersAndBooks.User;

public class UserService 
{
	
	/*
	 * ZWRACAMY SUCCESS WARTOSCI JEZELI DODAWANIE, USUNIECIE ITP SIE POWIODLY
	 * ZWRACAMY UNSUCCESS W PRZECIWNYM PRZYPADKU
	 * */
	public static final int SUCCESS =1;
	public static final int UNSUCCESS=0;
	
	
    private DataBase db;
    private User u;

    public UserService() 
    {
        db = new DataBase();
    }
    
  

    public void autheniticate(String login, String password, StringBuilder errorBuffer,
                              StringBuilder whichGui) {


        u = db.getUser(login);

        if (u != null && !(password.equals(u.getPassword()))) {
            u = null;
            errorBuffer.append("Bledne haslo");
        } else if (u != null) {
            if (u.getRole() == 1)
                whichGui.append("wypozyczanie");
            else if (u.getRole() == 2)
                whichGui.append("admin");
        } else {
            errorBuffer.append("Nie ma takiego uzytkownika");
        }


    }

    public void register(String login, String pesel, String name, String surname, String password,
                         StringBuilder errorBuffer) {

        User resultUser = db.getUser(login);

        if (resultUser != null) {
            errorBuffer.append("Login zajety!");
        } else {
            if (db.addUser(Long.valueOf(pesel), name, surname, login, password) == 1) {
                errorBuffer.append("");
            } else {
                errorBuffer.append("Nie udalo sie zarejestrowac");
            }
        }

    }

    
    /*ZLEEEE. TRZEBA TO DOKONCZYC*/
//    public List<Book> getLendBooks()
//    {
//    
//    	return new ArrayList();
//    }
    
    
    /*ZWRACAMY NOWE KSIAZKI*/
    public List<Book> getNewBooks()
    {
    	return db.getNewBooks();
    }
   
    /*ZWRACAMY KSIAZKI O TYTULE 'title'*/
    public List<Book> searchForBook(String title)
    {
    	
    	if(title.isEmpty()||title==null) return null;
    	
    	else return db.getBooks(title);
    }
    
    
    public List<Book> getUsersBooks()
    {
    	if(u==null) return null;
    	return db.getUserBooks(u.getLogin());
    }
    
    /*ZMIENIAMY STATUS KSIAZKI W BAZIE NA WYPOZYCZONA (JEZELI DA SIE ZMNIENIC)
     * WPP ZWRACAMY UNSUCCESS*/
    public int returnBook(Book book)
    {
    	if(db.returnBook(book.getSignature())>0) return SUCCESS;
    	return UNSUCCESS;
    }
    
    /*
     * TO SAMO CO WYZEJ TYLKO WYPOZYCZAMY
     * */
    public int lendBook(Book book)
    {
    	if(db.lendBook(book.getSignature(), u.getLogin())>0) return SUCCESS;
    	return UNSUCCESS;
    }
    
    
    public int addBook(Book book)
    {
    	if(book==null) return UNSUCCESS;
    	
    	if(db.addBook(book)>0) return SUCCESS;
    	return UNSUCCESS;
    }
    
    
    
    public int deleteBook(Book book)
    {
    	if(book==null) return UNSUCCESS;
    	
    	if(db.deleteBook(book.getSignature())>0) return SUCCESS;
    	return UNSUCCESS; 
    }
    
    
    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
}
