package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import usersAndBooks.Book;

public class BooksTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -4525215671974558753L;
	private final String IS_LENT = "Wypo\u017Cyczona";
	private final String NOT_IS_LENT = "Niewypo\u017Cyczona";

	private List<Book> books;
	private String[] columnsHeads;

	public BooksTableModel() {
		columnsHeads = new String[] { "Nazwa ksi\u0105\u017Cki", "Autor", "Sygnatura", "Status", "Data dodania" };
		books = new ArrayList<>();

	}

	public BooksTableModel(List<Book> books) {
		columnsHeads = new String[] { "Nazwa ksi\u0105\u017Cki", "Autor", "Sygnatura", "Status", "Data dodania" };
		this.books = books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
		this.fireTableDataChanged();
	}

	public void deleteBook(int index) {
		try {
			if (index < 0 || index > books.size() - 1)
				throw new ArrayIndexOutOfBoundsException();
			books.remove(index);
			this.fireTableRowsDeleted(index, index);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public Book getBook(int index) {
		try {
			if (index < 0 || index > books.size() - 1)
				throw new ArrayIndexOutOfBoundsException();
			return books.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateBook(int index, Book newBook) {
		try {
			if (index < 0 || index > books.size() - 1)
				throw new ArrayIndexOutOfBoundsException();
			books.set(index, newBook);
			this.fireTableRowsUpdated(index, index);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public int getColumnCount() {
		return columnsHeads.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnsHeads[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book book = books.get(rowIndex);
		String status = book.isLent() ? IS_LENT : NOT_IS_LENT;

		Object ob[] = new Object[] { book.getTitle(), book.getAuthor(), book.getSignature(), status, book.getDate(), };

		return ob[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
