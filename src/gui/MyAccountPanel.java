package gui;

import javax.swing.JPanel;
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
	private JTable tableWypozyczone;

	
	public MyAccountPanel(MainWindow mainWindow) {
		super(mainWindow);
		
		JButton btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui("logowanie");
			}
		});
		btnWyloguj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnNewButton = new JButton("Wypo\u017Cyczanie");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui("wypozyczanie");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JScrollPane scrollPaneTab = new JScrollPane();
		
		JLabel lblMojeWypoyczoneKsiki = new JLabel("Moje wypo\u017Cyczone ksi\u0105\u017Cki");
		lblMojeWypoyczoneKsiki.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(405)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnWyloguj, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMojeWypoyczoneKsiki)
								.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 583, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnWyloguj)
						.addComponent(btnNewButton))
					.addGap(72)
					.addComponent(lblMojeWypoyczoneKsiki)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTab, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		
		BooksTableModel booksTableModel=new BooksTableModel();
		tableWypozyczone = new JTable(booksTableModel);
		
		tableWypozyczone = new JTable(booksTableModel);
		scrollPaneTab.setViewportView(tableWypozyczone);
		setLayout(groupLayout);

	}
}
