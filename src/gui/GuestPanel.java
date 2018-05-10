package gui;

import javax.swing.JLabel;
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

	private static final long serialVersionUID = -5818961712683287837L;
	private MainWindow mainWindow;
	private JTextField searchBookField;
	private JTable booksTable;
	private JButton signOutButton;
	private JButton searchButton;

	private BooksTableModel booksTableModel;

	private DefaultDialog emptySearchFieldDialog;
	private DefaultDialog emptySearchListDialog;

	public GuestPanel(MainWindow mainWindow) {
		super(mainWindow);

		this.mainWindow = mainWindow;
		emptySearchFieldDialog = new DefaultDialog(EMPTY_SEARCH_FIELD_MSG);
		emptySearchListDialog = new DefaultDialog(EMPTY_SEARCH_LIST_MSG);

		booksTableModel = new BooksTableModel();

		signOutButton = new JButton("Powr\u00F3t");
		signOutButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		signOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui(MainWindow.LOGGING);
			}
		});

		searchBookField = new JTextField();
		searchBookField.setHorizontalAlignment(SwingConstants.LEFT);
		searchBookField.setColumns(10);
		searchBookField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER && !(emptySearchFieldDialog.isVisible())
						&& !(emptySearchListDialog.isVisible())) {
					// searchBooks(txtWpiszDaneKsiki, userService, booksTableModel); //tu wpisa�
					// nazwy uworzonych obiekt�w UserService i BooksTableModel

					new Thread(new Runnable() {
						public void run() {
							synchronized (mainWindow.getUserService()) {
								searchBooks(searchBookField, mainWindow.getUserService(), booksTableModel);
							}
						}
					}).start();
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		searchButton = new JButton("Szukaj");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new Thread(new Runnable() {
					public void run() {
						synchronized (mainWindow.getUserService()) {
							searchBooks(searchBookField, mainWindow.getUserService(), booksTableModel);
						}
					}
				}).start();
				;

				// searchBooks(txtWpiszDaneKsiki, mainWindow.getUserService(), booksTableModel);

			}
		});

		booksTable = new JTable(booksTableModel);

		JScrollPane scrollPaneTab = new JScrollPane(booksTable);

		DisplayFirstState();

		JLabel searchLabel = new JLabel("Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(52)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(searchLabel)
						.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 573, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(
										searchBookField, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(signOutButton, GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(75)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(19).addComponent(signOutButton).addGap(31)
						.addComponent(searchLabel).addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(searchBookField).addComponent(searchButton, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(22)
						.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)));
		setLayout(groupLayout);
		// logoLabel.setBounds(100, 100, 60, 30);
	}

	public void DisplayFirstState() {
		new Thread(new Runnable() {
			public void run() {
				synchronized (mainWindow.getUserService()) {
					booksTableModel.setBooks(mainWindow.getUserService().getNewBooks());
				}
			}
		}).start();
		;

		// tu zrobi� booksTableModel.setBooks(UserService.getNewBooks()) w oddzialnym
		// w�tku

	}

}
