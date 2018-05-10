package gui;

import javax.swing.*;
import java.awt.Font;

import java.awt.event.*;

import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LoginekPanel extends JPanel {

	private static final long serialVersionUID = 7920179334847473668L;
	private JTextField loginTextField;
	private JPasswordField passwordField;

	private StringBuilder errorBuffer;
	private StringBuilder whichGui;
	private MainWindow mainWindow;

	private DefaultDialog errorDialog;

	public LoginekPanel(MainWindow mainWindow) {
		setBackground(new Color(255, 228, 181));

		setSize(700, 500);

		this.mainWindow = mainWindow;

		errorDialog = new DefaultDialog("B\u0142\u0119dne dane logowania", "B\u0142\u0105d!");

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
		loginButton.setForeground(new Color(0, 0, 0));
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
				mainWindow.changeGui(MainWindow.REGISTRATION);
			}
		});

		JButton guestButton = new JButton("Wejd\u017A jako go\u015B\u0107");
		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.changeGui(MainWindow.GUEST);
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
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(234).addComponent(signInLabel,
								GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(170)
								.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, 211,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(170)
								.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 211,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(255, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap(232, Short.MAX_VALUE)
						.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(guestButton).addGap(208))
				.addGroup(groupLayout.createSequentialGroup().addGap(276)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(316, Short.MAX_VALUE))
				.addComponent(logoLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE).addGap(65)
						.addComponent(signInLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE).addGap(9)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(2).addComponent(loginTextField,
										GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 14,
												GroupLayout.PREFERRED_SIZE))
						.addGap(14)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(guestButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 31,
										GroupLayout.PREFERRED_SIZE))
						.addGap(169)));
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
			showErrorMessage("Podano b\u0142\u0119dny login!");
		} else {

			new Thread() {
				public void run() {
					synchronized (mainWindow.getUserService()) {
						errorBuffer = new StringBuilder("");

						whichGui = new StringBuilder("");

						mainWindow.getUserService().autheniticate(login, password, errorBuffer, whichGui);
					}

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							if (String.valueOf(errorBuffer).equals("")) {
								LoginCompleteDialog loginCompleteDialog = new LoginCompleteDialog(mainWindow,
										String.valueOf(whichGui));
								loginCompleteDialog.setVisible(true);
							} else {
								showErrorMessage("B\u0142\u0119dnie podane dane");
							}

							if (String.valueOf(errorBuffer).equals("B\u0142\u0119dne has\u0142o"))
								passwordField.setText("");
							else if (String.valueOf(errorBuffer).equals("Nie ma takiego u\u017Cytkownika")) {
								loginTextField.setText("");
								passwordField.setText("");
							}

						}
					});

				}
			}.start();

		}

	}

	public void displayFirstState() {
		loginTextField.setText("");
		passwordField.setText("");
	}

}
