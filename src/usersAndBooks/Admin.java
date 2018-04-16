package usersAndBooks;

public class Admin extends User {

	final private int role = 2;
	
	public Admin(String login, String password) {
		this.setLogin(login);
		this.setPassword(password);
	}
	
	public int getRole() {
		return role;
	}
	@Override
	public String toString() {
		return ("login: " + getLogin() + "\tpassword: " + getPassword());
	}
}
