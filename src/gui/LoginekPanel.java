package gui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class LoginekPanel extends JPanel {
	private JTextField textFieldLogin;
	private JPasswordField passwordField;

	
	public LoginekPanel(MainWindow mainWindow) {
		
		setSize(700,500);
		
		JLabel lblLogowanieDoSystemu = new JLabel("Logowanie do systemu");
		lblLogowanieDoSystemu.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogowanieDoSystemu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblHaso = new JLabel("Has\u0142o");
		lblHaso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHaso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textFieldLogin = new JTextField();
		textFieldLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldLogin.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton loginButton = new JButton("Zaloguj");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldLogin.getText().equals("user"))
					mainWindow.changeGui("wypozyczanie");
				else if(textFieldLogin.getText().equals("admin"))
					mainWindow.changeGui("admin");
			}
		});
		
		JButton btnZarejestruj = new JButton("Zarejestruj");
		btnZarejestruj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnZarejestruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				mainWindow.changeGui("rejestracja");
			}
		});
		
		JButton btnWejdzJakoGo = new JButton("Wejdz jako go\u015B\u0107");
		btnWejdzJakoGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui("gosc");
			}
		});
		btnWejdzJakoGo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		/*JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(LoginekPanel.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
		lblNewLabel.setBounds(10, 0, 46, 64);
		add(lblNewLabel);*/
		
		JLabel lblSystemObsugiBiblioteki = new JLabel("System Obs\u0142ugi Biblioteki");
		lblSystemObsugiBiblioteki.setHorizontalAlignment(SwingConstants.LEFT);
		lblSystemObsugiBiblioteki.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addComponent(lblSystemObsugiBiblioteki, GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(234)
					.addComponent(lblLogowanieDoSystemu, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(170)
					.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textFieldLogin, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(170)
					.addComponent(lblHaso, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(234)
					.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnZarejestruj, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(234)
					.addComponent(btnWejdzJakoGo, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblSystemObsugiBiblioteki, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(65)
					.addComponent(lblLogowanieDoSystemu, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(textFieldLogin, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHaso, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnZarejestruj, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(btnWejdzJakoGo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);

	}

}
