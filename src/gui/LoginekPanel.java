package gui;

import facade.UserService;
import usersAndBooks.User;

import javax.swing.*;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout.Alignment;

public class LoginekPanel extends JPanel {
	private JTextField loginTextField;
	private JPasswordField passwordField;

	private StringBuilder errorBuffer;
	private User user;
	private StringBuilder whichGui;
	private MainWindow mainWindow;
	
	private DefaultDialog errorDialog;

	public LoginekPanel(MainWindow mainWindow) {

		setSize(700, 500);

		this.mainWindow = mainWindow;

		errorDialog = new DefaultDialog("Bledne dane logowania", "Blad!");
		
		JLabel lblLogowanieDoSystemu = new JLabel("Logowanie do systemu");
		lblLogowanieDoSystemu.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogowanieDoSystemu.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblHaso = new JLabel("Has\u0142o");
		lblHaso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHaso.setFont(new Font("Tahoma", Font.PLAIN, 14));

		KeyListener enterToConfirmListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && !(errorDialog.isVisible())) {
					performLogging();
					System.out.println(errorDialog.isVisible());
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		};
		
		loginTextField = new JTextField();
		loginTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginTextField.setColumns(10);
		loginTextField.addKeyListener(enterToConfirmListener);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));		
		passwordField.addKeyListener(enterToConfirmListener);

		JButton loginButton = new JButton("Zaloguj");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				performLogging();
			}
		});

		JButton btnZarejestruj = new JButton("Zarejestruj");
		btnZarejestruj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnZarejestruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.changeGui("rejestracja");
			}
		});

		JButton btnWejdzJakoGo = new JButton("Wejd\u017A jako go\u015B\u0107");
		btnWejdzJakoGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui("gosc");
			}
		});
		btnWejdzJakoGo.setFont(new Font("Tahoma", Font.PLAIN, 14));

		/*
		 * JLabel lblNewLabel = new JLabel(""); lblNewLabel.setBackground(Color.ORANGE);
		 * lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 32));
		 * lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblNewLabel.setIcon(new ImageIcon(LoginekPanel.class.getResource(
		 * "/javax/swing/plaf/metal/icons/Warn.gif"))); lblNewLabel.setBounds(10, 0, 46,
		 * 64); add(lblNewLabel);
		 */

		JLabel lblSystemObsugiBiblioteki = new JLabel("System Obs\u0142ugi Biblioteki");
		lblSystemObsugiBiblioteki.setHorizontalAlignment(SwingConstants.LEFT);
		lblSystemObsugiBiblioteki.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(66).addComponent(lblSystemObsugiBiblioteki,
						GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(234).addComponent(lblLogowanieDoSystemu,
						GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(170)
						.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(170)
						.addComponent(lblHaso, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(234)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(btnZarejestruj, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(234).addComponent(btnWejdzJakoGo,
						GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(lblSystemObsugiBiblioteki, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
				.addGap(65)
				.addComponent(lblLogowanieDoSystemu, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
				.addGap(9)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(2).addComponent(loginTextField,
								GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
				.addGap(9)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHaso, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGap(11)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnZarejestruj, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addGap(11).addComponent(btnWejdzJakoGo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)));
		setLayout(groupLayout);

	}

	private boolean loginCorrect(String login) {
		return !(login.equals(""));
	}

	private void wyswietlKomunikatOBledzie(String komunikat) {
		errorDialog.setMessage(komunikat);
		errorDialog.setVisible(true);
	}

	private void performLogging() {
		System.out.println("logowanie");
		String login = loginTextField.getText();
		String password = String.valueOf(passwordField.getPassword());
		if (!loginCorrect(login)) {
			loginTextField.setText("");
			wyswietlKomunikatOBledzie("Podano bledny login!");
		} else {

			new Thread() {
				public void run() {
					errorBuffer = new StringBuilder("");
					whichGui = new StringBuilder("");

					mainWindow.getUserService().autheniticate(login,
							password, errorBuffer, whichGui);

					try {
						Thread.sleep(1);// imitacja dlugiej opercaji, dowod, ze nie blokujemy gui
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							if (String.valueOf(errorBuffer).equals("")) {
								LoginCompleteDialog loginCompleteDialog = new LoginCompleteDialog(mainWindow,
										String.valueOf(whichGui));
								loginCompleteDialog.setVisible(true);
							} else {
								wyswietlKomunikatOBledzie("Blednie podane dane");
							}

							if (String.valueOf(errorBuffer).equals("Bledne haslo"))
								passwordField.setText("");
							else if (String.valueOf(errorBuffer).equals("Nie ma takiego uzytkownika")) {
								loginTextField.setText("");
								passwordField.setText("");
							}

						}
					});

				}
			}.start();

		}

	}

}
