package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MyAccountPanel extends AfterAuthenticationGuiPanel {
	private JTable tableWypozyczone;

	
	public MyAccountPanel(MainWindow mainWindow) {
		super(mainWindow);
		
		JButton btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnNewButton = new JButton("Wypo\u017Cyczanie");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JScrollPane scrollPaneTab = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 583, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnWyloguj, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
					.addGap(61))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnWyloguj)
						.addComponent(btnNewButton))
					.addGap(92)
					.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		
		String[] columnNames = {"Sygnatura",
								"Tytu³",
								"Autor"};
		
		Object[][] data = {{"S102", "Pan Tadeusz", "Adam Mickiewicz" },
							{"S190", "Lalka", "Boles³aw Prus"}};
		
		tableWypozyczone = new JTable(data, columnNames);
		scrollPaneTab.setViewportView(tableWypozyczone);
		setLayout(groupLayout);

	}
}
