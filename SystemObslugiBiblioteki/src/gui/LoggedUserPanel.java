package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoggedUserPanel extends AfterAuthenticationGuiPanel {
	private JTextField textFieldWyszukiwanie;

	/**
	 * Create the panel.
	 */
	LoggedUserPanel(MainWindow mainWindow){
		super(mainWindow);
		setLayout(null);
		
		JButton btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.changeGui("logowanie");
			}
		});
		btnWyloguj.setBounds(576, 11, 89, 23);
		add(btnWyloguj);
		
		JButton btnMojeKonto = new JButton("Moje Konto");
		btnMojeKonto.setBounds(460, 11, 106, 23);
		add(btnMojeKonto);
		
		textFieldWyszukiwanie = new JTextField();
		textFieldWyszukiwanie.setBounds(53, 107, 467, 23);
		add(textFieldWyszukiwanie);
		textFieldWyszukiwanie.setColumns(10);
		
		JLabel lblDaneKsiazki = new JLabel("Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107");
		lblDaneKsiazki.setBounds(53, 82, 301, 14);
		add(lblDaneKsiazki);
		
		JButton btnWyszukaj = new JButton("Wyszukaj");
		btnWyszukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnWyszukaj.setBounds(530, 107, 89, 23);
		add(btnWyszukaj);
		
		JList listWyszukane = new JList();
		listWyszukane.setBounds(63, 141, 1, 1);
		add(listWyszukane);
		
	}
}
