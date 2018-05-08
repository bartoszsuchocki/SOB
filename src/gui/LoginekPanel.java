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
		
		JLabel signInLabel = new JLabel("Logowanie do systemu");
		signInLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signInLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel loginLabel = new JLabel("Login");
		loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel passwordLabel = new JLabel("Has\u0142o");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

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

		JButton registerButton = new JButton("Zarejestruj");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.changeGui("rejestracja");
			}
		});

		JButton guestButton = new JButton("Wejd\u017A jako go\u015B\u0107");
		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui("gosc");
			}
		});
		guestButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

		/*
		 * JLabel lblNewLabel = new JLabel(""); lblNewLabel.setBackground(Color.ORANGE);
		 * lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 32));
		 * lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblNewLabel.setIcon(new ImageIcon(LoginekPanel.class.getResource(
		 * "/javax/swing/plaf/metal/icons/Warn.gif"))); lblNewLabel.setBounds(10, 0, 46,
		 * 64); add(lblNewLabel);
		 */

		JLabel logoLabel = new JLabel("System Obs\u0142ugi Biblioteki");
		logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		logoLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(66).addComponent(logoLabel,
						GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(234).addComponent(signInLabel,
						GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(170)
						.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(170)
						.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(234)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(234).addComponent(guestButton,
						GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
				.addGap(65)
				.addComponent(signInLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
				.addGap(9)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(2).addComponent(loginTextField,
								GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
				.addGap(9)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGap(11)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
				.addGap(11).addComponent(guestButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)));
		setLayout(groupLayout);

	}

	private boolean loginCorrect(String login) {
		return !(login.equals(""));
	}

	private void showErrorMessage(String komunikat) {
		errorDialog.setMessage(komunikat);
		errorDialog.setVisible(true);
	}

	private void performLogging() {
		System.out.println("logowanie");
		String login = loginTextField.getText();
		String password = String.valueOf(passwordField.getPassword());
		if (!loginCorrect(login)) {
			loginTextField.setText("");
			showErrorMessage("Podano bledny login!");
		} else {

			new Thread() {
				public void run() {
					synchronized(mainWindow.getUserService()) {
						errorBuffer = new StringBuilder("");
					
						whichGui = new StringBuilder("");
	
						mainWindow.getUserService().autheniticate(login,
								password, errorBuffer, whichGui);
					}
						
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							if (String.valueOf(errorBuffer).equals("")) {
								LoginCompleteDialog loginCompleteDialog = new LoginCompleteDialog(mainWindow,
										String.valueOf(whichGui));
								loginCompleteDialog.setVisible(true);
							} else {
								showErrorMessage("Blednie podane dane");
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
