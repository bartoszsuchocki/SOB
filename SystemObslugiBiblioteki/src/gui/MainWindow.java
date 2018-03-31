package gui;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private LoginekPanel loginPanel;
	private RegistrationPanel registrationPanel;
	private GuestGui guestGui;
	private LoggedUserPanel loggedUserPanel;

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
		frame = new JFrame();
		frame.setBounds(100,100,700,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		loginPanel = new LoginekPanel(this);
		registrationPanel = new RegistrationPanel(this);
		loggedUserPanel = new LoggedUserPanel(this);

		frame.getContentPane().add(loginPanel, "logowanie");
		frame.getContentPane().add(registrationPanel, "rejestracja");
		frame.getContentPane().add(loggedUserPanel, "wyszukajUser");
		
		
	}
	
	public void changeGui(String whichGui)
	{
		CardLayout cl = (CardLayout)frame.getContentPane().getLayout();
		cl.show(frame.getContentPane(), whichGui);
	}
}
