package facade;
import database.DataBase;
import usersAndBooks.User;
public class UserService 
{
	
	public UserService()
	{
		
	}
	
	public User authenticate(String login, String password) //null jesli sie nie powiodla
	{
		DataBase db = new DataBase();
		
		User resultUser = db.getUser(login);
		if(resultUser!=null && !(login.equals(resultUser.getLogin())))
		{
			resultUser=null;
		}
		
		
		return resultUser;
	}
	

}
