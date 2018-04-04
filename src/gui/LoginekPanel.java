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

public class LoginekPanel extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	
	public LoginekPanel(MainWindow mainWindow) {
		setLayout(null);
		
		JLabel lblLogowanieDoSystemu = new JLabel("Logowanie do systemu");
		lblLogowanieDoSystemu.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogowanieDoSystemu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogowanieDoSystemu.setBounds(234, 129, 211, 44);
		add(lblLogowanieDoSystemu);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(170, 182, 46, 20);
		add(lblLogin);
		
		JLabel lblHaso = new JLabel("Has\u0142o");
		lblHaso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHaso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHaso.setBounds(170, 213, 46, 14);
		add(lblHaso);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(234, 184, 211, 20);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(234, 213, 211, 20);
		add(passwordField);
		
		JButton loginButton = new JButton("Zaloguj");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.changeGui("wypozyczanie");
			}
		});
		loginButton.setBounds(234, 244, 98, 23);
		add(loginButton);
		
		JButton btnZarejestruj = new JButton("Zarejestruj");
		btnZarejestruj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnZarejestruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				mainWindow.changeGui("rejestracja");
			}
		});
		btnZarejestruj.setBounds(342, 244, 103, 23);
		add(btnZarejestruj);
		
		JButton btnWejdzJakoGo = new JButton("Wejdz jako go\u015B\u0107");
		btnWejdzJakoGo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnWejdzJakoGo.setBounds(234, 278, 140, 30);
		add(btnWejdzJakoGo);
		
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
		lblSystemObsugiBiblioteki.setBounds(66, 0, 634, 64);
		add(lblSystemObsugiBiblioteki);

	}
}
