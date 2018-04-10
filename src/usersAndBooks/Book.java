package usersAndBooks;

import java.util.Date;

public class Book {

	private String title;
	private String author;
	private String signature;
	private boolean lent;
	private String pesel;
	private Date date;
	public Book(String title, String author, String signature, String pesel,Date date)
	{
		this.title=title;
		this.author=author;
		this.signature=signature;
		this.pesel=pesel;
		if(pesel==null)
			this.lent=false;
		else
			this.lent=true;
		this.date = date;
	}
	public Book(String title, String author, String signature,boolean lent, String pesel,Date date)
	{
		this(title,author,signature,pesel,date);
		this.lent=lent;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public boolean isLent() {
		return lent;
	}
	
	public void setLent(boolean lent) {
		this.lent = lent;
	}
	
}
