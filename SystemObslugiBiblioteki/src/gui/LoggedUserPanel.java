package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JScrollBar;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import java.awt.Font;

public class LoggedUserPanel extends AfterAuthenticationGuiPanel {
	private JTextField textFieldWyszukiwanie;
	private JTable tableWyszukane;
	private JTable tableWyszukane_1;

	public LoggedUserPanel(MainWindow mainWindow){
		super(mainWindow);
		
		JSeparator separator = new JSeparator();
		
		JButton btnMojeKonto = new JButton("Moje Konto");
		btnMojeKonto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.changeGui("logowanie");
			}
		});
		
		JLabel lblDaneKsiazki = new JLabel("Wpisz dane ksi\u0105\u017Cki, kt\u00F3r\u0105 chcesz wyszuka\u0107");
		lblDaneKsiazki.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		textFieldWyszukiwanie = new JTextField();
		textFieldWyszukiwanie.setToolTipText("");
		textFieldWyszukiwanie.setColumns(10);
		
		JButton btnWyszukaj = new JButton("Wyszukaj");
		btnWyszukaj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnWyszukaj.setToolTipText("");
		btnWyszukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
				String[] columnNames = {"Sygnatura",
										"Tytu³",
										"Autor"};
				Object[][] data = {{"S102", "Pan Tadeusz", "Adam Mickiewicz" },
									{"S190", "Lalka", "Boles³aw Prus"}};
				
				JScrollPane scrollPaneTab = new JScrollPane();
				GroupLayout groupLayout = new GroupLayout(this);
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(464)
									.addComponent(btnMojeKonto)
									.addGap(5)
									.addComponent(btnWyloguj, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(53)
									.addComponent(lblDaneKsiazki, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(53)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPaneTab, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addComponent(textFieldWyszukiwanie, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
											.addGap(5)
											.addComponent(btnWyszukaj)))))
							.addGap(55))
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnWyloguj)
									.addGap(53)
									.addComponent(lblDaneKsiazki))
								.addComponent(btnMojeKonto))
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldWyszukiwanie)
								.addComponent(btnWyszukaj, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(11)
							.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(162, Short.MAX_VALUE))
				);
				
				JTable tableWyszukane_1_1 = new JTable(data, columnNames);
				scrollPaneTab.setViewportView(tableWyszukane_1_1);
				setLayout(groupLayout);
		



		
		
		
	}
}
