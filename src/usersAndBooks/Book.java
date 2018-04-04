package usersAndBooks;
public class Book {

	private String title;
	private String author;
	private String signature;
	private boolean lent;

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
