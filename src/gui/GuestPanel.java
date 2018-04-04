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
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GuestPanel extends AfterAuthenticationGuiPanel {

	
	private  MainWindow mainWindow;
	private JLabel logoLabel;
	private JLabel iconLabel;
	private JTextField txtWpiszDaneKsiki;
	private JTable booksTable;
	public GuestPanel(MainWindow mainWindow) {
		super(mainWindow);
		
		JButton btnPowrt = new JButton("Powr\u00F3t");
		btnPowrt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPowrt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui("logowanie");
			}
		});
		
		txtWpiszDaneKsiki = new JTextField();
		txtWpiszDaneKsiki.setHorizontalAlignment(SwingConstants.LEFT);
		txtWpiszDaneKsiki.setText("Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107");
		txtWpiszDaneKsiki.setColumns(10);
		
		JButton btnSzukaj = new JButton("Szukaj");
		btnSzukaj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
		BooksTableModel booksTableModel=new BooksTableModel();
		booksTable = new JTable(booksTableModel);

		JScrollPane scrollPaneTab = new JScrollPane(booksTable);
			
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 573, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addComponent(txtWpiszDaneKsiki, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPowrt, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSzukaj, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addGap(75))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(btnPowrt)
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtWpiszDaneKsiki, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSzukaj, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
		//logoLabel.setBounds(100, 100, 60, 30);
	}
}
