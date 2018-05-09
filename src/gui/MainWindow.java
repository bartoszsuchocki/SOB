package gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import facade.UserService;
import gui.LoggedAdminPanel.LoggedAdminPanel;

public class MainWindow {

	public static final String LOGGING = "logowanie";
	public static final String REGISTRATION = "rejestracja";
	public static final String MYACCOUNT = "mojeKonto";
	public static final String LOGGEDUSER = "wypozyczenia";
	public static final String ADMIN = "admin";
	public static final String GUEST = "gosc";

	private JFrame frame;
	private LoginekPanel loginPanel;
	private RegistrationPanel registrationPanel;
	private GuestPanel guestPanel;
	private LoggedUserPanel loggedUserPanel;
	private MyAccountPanel myAccountPanel;
	private LoggedAdminPanel loggedAdminPanel;
	private UserService userService;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initialize();
	}

	private void initialize() {
		userService = new UserService();
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JFrame frame = (JFrame) e.getSource();

				int result = JOptionPane.showConfirmDialog(
						frame,
						"Czy na pewno chcesz opuscic aplikacje?",
						"Opuszczanie aplikacji",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION)
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

		loginPanel = new LoginekPanel(this);

		frame.getContentPane().add(loginPanel, MainWindow.LOGGING);


	}

	public void changeGui(String whichGui) {

		if (whichGui.equals(MainWindow.MYACCOUNT)) {
			if (myAccountPanel == null) {
				myAccountPanel = new MyAccountPanel(this);
				frame.getContentPane().add(myAccountPanel, MainWindow.MYACCOUNT);
			} else {
				myAccountPanel.displayFistState();
			}

		} else if (whichGui.equals(MainWindow.LOGGEDUSER)) {
			if (loggedUserPanel == null) {
				loggedUserPanel = new LoggedUserPanel(this);
				frame.getContentPane().add(loggedUserPanel, MainWindow.LOGGEDUSER);
			} else
				loggedUserPanel.displayFistState();
		} else if (whichGui.equals(MainWindow.ADMIN)) {
			if (loggedAdminPanel == null) {
				loggedAdminPanel = new LoggedAdminPanel(this);
				frame.getContentPane().add(loggedAdminPanel, MainWindow.ADMIN);
			} else {
				loggedAdminPanel.displayFirstState();

			}
		} else if (whichGui.equals(MainWindow.GUEST)) {
			if (guestPanel == null) {
				guestPanel = new GuestPanel(this);
				frame.getContentPane().add(guestPanel, MainWindow.GUEST);
			} else {
				guestPanel.DisplayFirstState();
			}

		} else if (whichGui.equals(MainWindow.REGISTRATION)) {
			if (registrationPanel == null) {
				registrationPanel = new RegistrationPanel(this);
				frame.getContentPane().add(registrationPanel, MainWindow.REGISTRATION);
			} else {
				registrationPanel.displayFirstState();
			}
		} else if (whichGui.equals(MainWindow.LOGGING)) {
			loginPanel.displayFirstState();

		}
		CardLayout cl = (CardLayout) frame.getContentPane().getLayout();

		cl.show(frame.getContentPane(), whichGui);

	}

	public UserService getUserService() {
		return userService;
	}

}
