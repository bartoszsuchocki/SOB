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
		frame.setBounds(100,100,700,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		loginPanel = new LoginekPanel(this);
		registrationPanel = new RegistrationPanel(this);
		loggedUserPanel = new LoggedUserPanel(this);
		//myAccountPanel = new MyAccountPanel(this);
		guestPanel = new GuestPanel(this);
		loggedAdminPanel = new LoggedAdminPanel(this);

		frame.getContentPane().add(loginPanel, "logowanie");
		frame.getContentPane().add(registrationPanel, "rejestracja");
		frame.getContentPane().add(loggedUserPanel, "wypozyczanie");
		//frame.getContentPane().add(myAccountPanel, "mojeKonto");
		frame.getContentPane().add(guestPanel, "gosc");
		frame.getContentPane().add(loggedAdminPanel, "admin");
		
	}
	
	public void changeGui(String whichGui)
	{
		
		if(whichGui.equals("mojeKonto")) 
			{
				myAccountPanel = new MyAccountPanel(this);

				frame.getContentPane().add(myAccountPanel, "mojeKonto");
				
				
			}
		CardLayout cl = (CardLayout)frame.getContentPane().getLayout();
	
		
		cl.show(frame.getContentPane(), whichGui);
		
	}
	
	
	
	public UserService getUserService()
	{
		return userService;
	}
	
}
