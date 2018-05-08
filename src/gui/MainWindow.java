package gui;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import facade.UserService;
import gui.LoggedAdminPanel.LoggedAdminPanel;

public class MainWindow {

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		loginPanel = new LoginekPanel(this);

		frame.getContentPane().add(loginPanel, "logowanie");

	}

	public void changeGui(String whichGui) {

		if (whichGui.equals("mojeKonto")) {
			if (myAccountPanel == null) {
				myAccountPanel = new MyAccountPanel(this);
				frame.getContentPane().add(myAccountPanel, "mojeKonto");
			} else {
				myAccountPanel.displayFistState();
			}

		}

		else if (whichGui.equals("wypozyczanie")) {
			if (loggedUserPanel == null) {
				loggedUserPanel = new LoggedUserPanel(this);
				frame.getContentPane().add(loggedUserPanel, "wypozyczanie");
			} else
				loggedUserPanel.displayFistState();
		} else if (whichGui.equals("admin")) {
			if (loggedAdminPanel == null) {
				loggedAdminPanel = new LoggedAdminPanel(this);
				frame.getContentPane().add(loggedAdminPanel, "admin");
			} else {
				loggedAdminPanel.displayFirstState();

			}
		} else if (whichGui.equals("gosc")) {
			if (guestPanel == null) {
				guestPanel = new GuestPanel(this);
				frame.getContentPane().add(guestPanel, "gosc");
			} else {
				guestPanel.DisplayFirstState();
			}

		} else if (whichGui.equals("rejestracja")) {
			if (registrationPanel == null) {
				registrationPanel = new RegistrationPanel(this);
				frame.getContentPane().add(registrationPanel, "rejestracja");
			} else {
				// Tu zrobi� displayFirstState
			}
		} else if (whichGui.equals("logowanie")) {
			// Tu zrobi� displayFirstState

		}
		CardLayout cl = (CardLayout) frame.getContentPane().getLayout();

		cl.show(frame.getContentPane(), whichGui);

	}

	public UserService getUserService() {
		return userService;
	}

}
