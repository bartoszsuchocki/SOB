package gui;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class GuestGui extends AfterAuthenticationGuiPanel {

	/**
	 * Create the panel.
	 */
	private  MainWindow mainWindow;
	private JLabel logoLabel;
	private JLabel iconLabel;
	private JTextField txtWpiszDaneKsiki;
	private JTable table;
	public GuestGui(MainWindow mainWindow) {
		super(mainWindow);
		
		JButton btnPowrt = new JButton("Powr\u00F3t");
		btnPowrt.setBounds(601, 11, 89, 23);
		add(btnPowrt);
		
		txtWpiszDaneKsiki = new JTextField();
		txtWpiszDaneKsiki.setHorizontalAlignment(SwingConstants.LEFT);
		txtWpiszDaneKsiki.setText("Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107");
		txtWpiszDaneKsiki.setBounds(52, 98, 474, 34);
		
		add(txtWpiszDaneKsiki);
		txtWpiszDaneKsiki.setColumns(10);
		
		JButton btnSzukaj = new JButton("Szukaj");
		btnSzukaj.setBounds(536, 98, 89, 34);
		add(btnSzukaj);
		
		table = new JTable();
		table.setBounds(52, 154, 474, 211);
		add(table);
		//logoLabel.setBounds(100, 100, 60, 30);
	}
}
