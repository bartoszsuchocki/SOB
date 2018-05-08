package gui;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GuestPanel extends AfterAuthenticationGuiPanel {

	private final String NO_SUCH_BOOK_MESSAGE="Nie ma takiej ksi\u0105\u017Cki";
	
	private  MainWindow mainWindow;
	private JLabel logoLabel;
	private JLabel iconLabel;
	private JTextField txtWpiszDaneKsiki;
	private JTable booksTable;
	
	private BooksTableModel booksTableModel;
	
	private DefaultDialog emptySearchFieldDialog;
	private DefaultDialog emptySearchListDialog;
	
	public GuestPanel(MainWindow mainWindow) {
		super(mainWindow);
		
		this.mainWindow=mainWindow;
		emptySearchFieldDialog = new DefaultDialog(EMPTY_SEARCH_FIELD_MSG);
		emptySearchListDialog = new DefaultDialog(EMPTY_SEARCH_LIST_MSG);
		
		booksTableModel=new BooksTableModel();
		
	
		
		JButton btnPowrt = new JButton("Powr\u00F3t");
		btnPowrt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPowrt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui("logowanie");
			}
		});
		
		txtWpiszDaneKsiki = new JTextField();
		txtWpiszDaneKsiki.setHorizontalAlignment(SwingConstants.LEFT);
		txtWpiszDaneKsiki.setColumns(10);
		txtWpiszDaneKsiki.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER &&!(emptySearchFieldDialog.isVisible()) && !(emptySearchListDialog.isVisible()) ) {
					//searchBooks(txtWpiszDaneKsiki, userService, booksTableModel); //tu wpisa� nazwy uworzonych obiekt�w UserService i BooksTableModel
					
					Thread t=new Thread(new Runnable()
					{
						public void run()
						{
							synchronized(mainWindow.getUserService())
							{
								searchBooks(txtWpiszDaneKsiki, mainWindow.getUserService(), booksTableModel);
							}
						}
					});
					
					t.start();
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
			
		});
		
		JButton btnSzukaj = new JButton("Szukaj");
		btnSzukaj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSzukaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Thread t=new Thread(new Runnable()
				{
					public void run()
					{
						synchronized(mainWindow.getUserService())
						{
							searchBooks(txtWpiszDaneKsiki, mainWindow.getUserService(), booksTableModel);
						}
					}
				});
				
				t.start();
				
			
				// searchBooks(txtWpiszDaneKsiki, mainWindow.getUserService(), booksTableModel);

				
			}
		});
		
		
	
		booksTable = new JTable(booksTableModel);

		JScrollPane scrollPaneTab = new JScrollPane(booksTable);
		
		DisplayFirstState();
		
		JLabel lblWpiszDaneKsiki = new JLabel("Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107");
			
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWpiszDaneKsiki)
						.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 573, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtWpiszDaneKsiki, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSzukaj, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPowrt, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
					.addGap(75))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(btnPowrt)
					.addGap(31)
					.addComponent(lblWpiszDaneKsiki)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtWpiszDaneKsiki)
						.addComponent(btnSzukaj, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(22)
					.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
		//logoLabel.setBounds(100, 100, 60, 30);
	}
	
	public void DisplayFirstState() 
	{
		Thread t=new Thread(new Runnable()
		{
			public void run()
			{
				synchronized(mainWindow.getUserService())
				{
					booksTableModel.setBooks(mainWindow.getUserService().getNewBooks());
				}
			}
		});
		
		t.start();
		
		//tu zrobi� booksTableModel.setBooks(UserService.getNewBooks()) w oddzialnym w�tku
		
	}
	
}
