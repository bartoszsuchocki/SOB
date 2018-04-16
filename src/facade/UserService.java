package facade;
import database.DataBase;
import usersAndBooks.User;
public class UserService 
{

	public UserService()
	{

	}

	public User authenticate(String login) //null jesli sie nie powiodla
	{
		DataBase db = new DataBase();
		
		User resultUser = db.getUser(login);
		if(resultUser!=null && !(login.equals(resultUser.getLogin())))
		{
			resultUser=null;
		}

		return resultUser;
	}


	public User autheniticate(String login, String password) {

		DataBase db = new DataBase();

		User resultUser = db.getUser(login);

		if (resultUser != null && !(login.equals(resultUser.getLogin())) && !(password.equals(resultUser.getPassword()))) {
			resultUser = null;
		}

		return resultUser;
	}

	public String register(String login, String pesel, String name, String surname, String password) {

		DataBase db = new DataBase();

		String errorB = new String("");

		if (db.addUser(Long.valueOf(pesel), name, surname, login, password) == 1) {
			errorB = "";
		} else {
			errorB = "Nie udalo sie zarejestrowac";
		}

		return errorB;
	}



}
