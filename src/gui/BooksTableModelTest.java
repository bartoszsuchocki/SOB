package gui;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import database.DataBase;

public class BooksTableModelTest {

	private static DataBase db;
	private static BooksTableModel model;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		db=new DataBase();
		model=new BooksTableModel(db.getAllBooks());
	}

	@Test
	public void testIsCellEditable() {
		int rows=model.getRowCount();
		int columns=model.getColumnCount();
		for(int i=0; i<rows; i++)
			for(int j=0; j<columns; j++)
				assertFalse(model.isCellEditable(i, j));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testDeleteBook() {
		model.deleteBook(-5);
	}


	@Test
	public void testGetRowCount() {
		assertTrue(model.getRowCount()==8);
	}


	@Test
	public void testGetColumnNameInt() {
		boolean areNamesCorrect=
				model.getColumnName(0).equals("Nazwa ksi\u0105\u017Cki")
				&&model.getColumnName(1).equals("Autor")
				&& model.getColumnName(2).equals("Sygnatura")
				&& model.getColumnName(3).equals("Status")
				&& model.getColumnName(4).equals("Data dodania");
		assertTrue(areNamesCorrect);
	}

}
