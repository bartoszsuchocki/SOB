public class DefaultUser extends User {

	final private int role = 0;
	private long pesel;
	private String name;
	private String surname;

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

}
