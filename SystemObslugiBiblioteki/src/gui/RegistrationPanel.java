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

public class RegistrationPanel extends JPanel {
	private JTextField loginTextField;
	private JPasswordField firstPasswordField;
	private JPasswordField repeatPasswordField;
	private JTextField peselTextField;
	

	
	public RegistrationPanel(MainWindow mainWindow) {
		setLayout(null);
		
		JLabel logoLabel = new JLabel("System Obs\u0142ugi Biblioteki");
		logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		logoLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		logoLabel.setBounds(66, 0, 634, 64);
		add(logoLabel);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(RegistrationPanel.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconLabel.setFont(new Font("Tahoma", Font.ITALIC, 32));
		iconLabel.setBackground(Color.ORANGE);
		iconLabel.setBounds(10, 0, 46, 64);
		add(iconLabel);
		
		JLabel titleLabel = new JLabel("Rejestracja");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titleLabel.setBounds(267, 138, 211, 44);
		add(titleLabel);
		
		loginTextField = new JTextField();
		loginTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginTextField.setColumns(10);
		loginTextField.setBounds(267, 193, 211, 20);
		add(loginTextField);
		
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginLabel.setBounds(191, 193, 46, 20);
		add(loginLabel);
		
		JLabel peselLabel = new JLabel("Pesel");
		peselLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		peselLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		peselLabel.setBounds(203, 224, 34, 14);
		add(peselLabel);
		
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
						JOptionPane.showMessageDialog(RegistrationPanel.this, "Poprawnie zarejestrowano", "Zarejestrowano",
								JOptionPane.PLAIN_MESSAGE);
						
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
		registrationButton.setBounds(267, 306, 98, 23);
		add(registrationButton);
		
		JButton backButton = new JButton("Cofnij");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.changeGui("logowanie");
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backButton.setBounds(375, 306, 103, 23);
		add(backButton);
		
		firstPasswordField = new JPasswordField();
		firstPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		firstPasswordField.setBounds(267, 250, 211, 20);
		add(firstPasswordField);
		
		JLabel firstPasswordLabel = new JLabel("Has\u0142o");
		firstPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		firstPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		firstPasswordLabel.setBounds(203, 253, 34, 14);
		add(firstPasswordLabel);
		
		JLabel repeatPasswordLabel = new JLabel("Powt\u00F3rz has\u0142o");
		repeatPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		repeatPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		repeatPasswordLabel.setBounds(150, 281, 87, 14);
		add(repeatPasswordLabel);
		
		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		repeatPasswordField.setBounds(267, 278, 211, 20);
		add(repeatPasswordField);
		
		peselTextField = new JTextField();
		peselTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		peselTextField.setColumns(10);
		peselTextField.setBounds(267, 223, 211, 20);
		add(peselTextField);

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
