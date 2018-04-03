package gui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class RegistrationPanel extends JPanel {
	private JTextField loginTextField;
	private JPasswordField firstPasswordField;
	private JPasswordField repeatPasswordField;
	private JTextField peselTextField;
	

	
	public RegistrationPanel(MainWindow mainWindow) {
		
		JLabel logoLabel = new JLabel("System Obs\u0142ugi Biblioteki");
		logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		logoLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(RegistrationPanel.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconLabel.setFont(new Font("Tahoma", Font.ITALIC, 32));
		iconLabel.setBackground(Color.ORANGE);
		
		JLabel titleLabel = new JLabel("Rejestracja");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		loginTextField = new JTextField();
		loginTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginTextField.setColumns(10);
		
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel peselLabel = new JLabel("Pesel");
		peselLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		peselLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton registrationButton = new JButton("Zarejestruj");
		registrationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String komunikatOBledzie="";
				if(!loginCorrect(loginTextField.getText()))
				{
					komunikatOBledzie="Podano bledny login!";
				}
				else if(!(peselCorrect(peselTextField.getText())))
				{
					komunikatOBledzie="Podano bledny pesel!";
				}
				else if(!(passwordsCorrect(String.valueOf(firstPasswordField.getPassword()),
						String.valueOf(repeatPasswordField.getPassword()))))
				{
					komunikatOBledzie="Ktores haslo jest bledne!";
				}
				else
				{
					
					
					
					//sprawdz czy jest juz taki uzytkownik
						//jesli tak, to komunikat o bledzie
						//komunikatOBledzie="Ten login jest zajety"
						
					
					//zarejestruj
					
					
					
					   //jesli sie powiodlo - dialog z przyciskiem ok i powrot do logowania
						RegistrationCompleteDialog registrationCompleteDialog = new RegistrationCompleteDialog(mainWindow);
						registrationCompleteDialog.setVisible(true);
						
						komunikatOBledzie="";
					
						
					   //jesli nie - dialog z komunikatem i przyciskiem ok
						//komunikatOBledzie="Nie udalo sie zarejestrowac. Sproboj ponownie"
					    
				}
				if(!(komunikatOBledzie.equals("")))
				{
					JOptionPane.showMessageDialog(RegistrationPanel.this, komunikatOBledzie, "Blednie podane dane",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		registrationButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton backButton = new JButton("Cofnij");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.changeGui("logowanie");
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		firstPasswordField = new JPasswordField();
		firstPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel firstPasswordLabel = new JLabel("Has\u0142o");
		firstPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		firstPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel repeatPasswordLabel = new JLabel("Powt\u00F3rz has\u0142o");
		repeatPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		repeatPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		peselTextField = new JTextField();
		peselTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		peselTextField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(iconLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(267)
					.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(191)
					.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(203)
					.addComponent(peselLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(peselTextField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(203)
					.addComponent(firstPasswordLabel)
					.addGap(30)
					.addComponent(firstPasswordField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(150)
					.addComponent(repeatPasswordLabel)
					.addGap(30)
					.addComponent(repeatPasswordField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(267)
					.addComponent(registrationButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(iconLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addGap(74)
					.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(loginTextField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(peselLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(peselTextField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(firstPasswordLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(firstPasswordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(repeatPasswordLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(repeatPasswordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(registrationButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
		);
		setLayout(groupLayout);

	}
	private boolean loginCorrect(String login)
	{
		return !(login.equals(""));
	}
	private boolean passwordsCorrect(String password1, String password2)
	{
		return !(password1.equals("")) && password1.equals(password2);
	}
	private boolean peselCorrect(String pesel)
	{
		for(int i=0; i<11; i++)
		{
			int tmp = (int)(pesel.charAt(i))-48; 
			if(tmp>9 || tmp<0)
			{
				return false;
			}
			
		}
		return !(pesel.equals("")) && pesel.trim().length()==11;
	}
}
