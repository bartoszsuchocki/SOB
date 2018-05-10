package gui;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyAccountPanel extends AfterAuthenticationGuiPanel {

	private static final long serialVersionUID = 3749321531464841392L;

	private JTable lendTable;

	private BooksTableModel booksTableModel;
	private MainWindow mainWindow;

	public MyAccountPanel(MainWindow mainWindow) {
		super(mainWindow);

		this.mainWindow = mainWindow;

		JButton logOutButton = new JButton("Wyloguj");
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui(MainWindow.LOGGING);
			}
		});
		logOutButton.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JButton lendButton = new JButton("Wypo\u017Cyczanie");
		lendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui(MainWindow.LOGGEDUSER);
			}
		});
		lendButton.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JScrollPane scrollPaneTab = new JScrollPane();

		JLabel myBooksLabel = new JLabel("Moje wypo\u017Cyczone ksi\u0105\u017Cki");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup().addGap(47)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(405).addComponent(lendButton)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(logOutButton,
												GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(10)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(myBooksLabel).addComponent(scrollPaneTab,
														GroupLayout.PREFERRED_SIZE, 583, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(60, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(logOutButton)
								.addComponent(lendButton))
						.addGap(72).addComponent(myBooksLabel).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(77, Short.MAX_VALUE)));

		booksTableModel = new BooksTableModel();
		showUserBooks();

		lendTable = new JTable(booksTableModel);

		lendTable = new JTable(booksTableModel);
		scrollPaneTab.setViewportView(lendTable);
		setLayout(groupLayout);

	}

	private void showUserBooks() {
		new Thread(new Runnable() {
			public void run() {
				synchronized (mainWindow.getUserService()) {
					booksTableModel.setBooks(mainWindow.getUserService().getUsersBooks());
				}
			}
		}).start();
	}

	public void displayFistState() {
		showUserBooks();
	}

}
