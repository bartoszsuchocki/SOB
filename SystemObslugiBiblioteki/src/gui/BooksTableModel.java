package gui;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class BooksTableModel extends AbstractTableModel
{

    private Object[][] books;
    private String[] columnsHeads;

    public BooksTableModel(Object[][] books)
    {
        this.books=books;
        columnsHeads=new String[] { "Nazwa książki", "Autor", "Sygnatura", "Status"};
    }


    @Override
    public int getRowCount()
    {
        return books[1].length-1;
    }

    @Override
    public int getColumnCount()
    {
        return columnsHeads.length;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnsHeads[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
       return books[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

}
