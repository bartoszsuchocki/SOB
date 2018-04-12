package usersAndBooks;

public class DefaultUser extends User {

	final private int role = 0;
	private String name;
	private String surname;
	private long pesel;
	
	public DefaultUser(String name, String surname, String login, String password, long pesel) {
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
		this.setLogin(login);
		this.setPassword(password);
	}

	public int getRole() {
		return role;
	}

	public long getPESEL() {
		return pesel;
	}

	public void setPESEL(long pesel) {
		this.pesel = pesel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Override
	public String toString() {
		return "login: " + getLogin() + "\tpassword: " + getPassword() + "\tname: " + name + "\tsurname: " + surname + "\tpesel: " + pesel;
	}
}
