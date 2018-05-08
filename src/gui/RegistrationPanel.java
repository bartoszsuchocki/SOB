package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import database.DataBase;
import usersAndBooks.DefaultUser;
import usersAndBooks.User;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class RegistrationPanel extends JPanel {
    
	private final String INCORRECT_LOGIN_MESSAGE = "Podano b³êdny login!";
	private final String INCORRECT_PESEL_MESSAGE = "Podano b³êdny pesel!";
	private final String INCORRECT_PASSWORD_MESSAGE = "Któreœ has³o jest b³êdne!";
	private final String ERROR_TITLE = "B³êdnie podane dane.";
	
	
	
	private JTextField loginTextField;
    private JPasswordField firstPasswordField;
    private JPasswordField repeatPasswordField;
    private JTextField peselTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    
    private KeyListener pressEnterToRegistrateListener;
    
    private DataBase dataBase;
    private DefaultUser defaultUser;
    private User user;
    private MainWindow mainWindow;
    private StringBuilder errorBuffer;
   
    private RegistrationCompleteDialog registrationCompleteDialog;
    private DefaultDialog errorDialog;

    public RegistrationPanel(MainWindow mainWindow) {

        this.mainWindow = mainWindow;
        
    	setSize(700, 500);
        
        errorDialog = new DefaultDialog(ERROR_TITLE,"");
        registrationCompleteDialog = new RegistrationCompleteDialog(
                mainWindow);
        
        pressEnterToRegistrateListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER && !(errorDialog.isVisible()) && !(registrationCompleteDialog.isVisible()) ) {
					register();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
        
        JLabel logoLabel = new JLabel("System Obs\u0142ugi Biblioteki");
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        logoLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));

        JLabel iconLabel = new JLabel("");
        /*
         * iconLabel.setIcon(new ImageIcon(RegistrationPanel.class.getResource(
         * "/javax/swing/plaf/metal/icons/Error.gif")));
         * iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
         * iconLabel.setFont(new Font("Tahoma", Font.ITALIC, 32));
         * iconLabel.setBackground(Color.ORANGE); iconLabel.setBounds(10, 0, 46, 64);
         * add(iconLabel);
         */

        JLabel titleLabel = new JLabel("Rejestracja");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));

        loginTextField = new JTextField();
        loginTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        loginTextField.setColumns(10);
        loginTextField.addKeyListener(pressEnterToRegistrateListener);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel peselLabel = new JLabel("Pesel");
        peselLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        peselLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        firstPasswordField = new JPasswordField();
        firstPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        firstPasswordField.addKeyListener(pressEnterToRegistrateListener);

        JLabel firstPasswordLabel = new JLabel("Has\u0142o");
        firstPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        firstPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel repeatPasswordLabel = new JLabel("Powt\u00F3rz has\u0142o");
        repeatPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        repeatPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        repeatPasswordField = new JPasswordField();
        repeatPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        repeatPasswordField.addKeyListener(pressEnterToRegistrateListener);

        peselTextField = new JTextField();
        peselTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        peselTextField.setColumns(10);
        peselTextField.addKeyListener(pressEnterToRegistrateListener);

        JButton registrationButton = new JButton("Zarejestruj");
        registrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	register();
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

        nameTextField = new JTextField();
        nameTextField.setColumns(10);

        surnameTextField = new JTextField();
        surnameTextField.setColumns(10);

        JLabel nameLabel = new JLabel("Imie");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel surnameLabel = new JLabel("Nazwisko");
        surnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        surnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(66)
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
                                .addGap(191)
                                .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addGap(30)
                                .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(165)
                                .addComponent(surnameLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                .addGap(30)
                                .addComponent(surnameTextField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
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
                                .addGap(11)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(11)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(surnameLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(surnameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(11)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(firstPasswordLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(firstPasswordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                                .addGap(9)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(repeatPasswordLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(repeatPasswordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                                .addGap(11)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(registrationButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
        );
        setLayout(groupLayout);

    }

    
    private void register() {
    	if (!loginCorrect(loginTextField.getText())) {
            loginTextField.setText("");
            wyswietlKomunikatOBledzie(INCORRECT_LOGIN_MESSAGE);

        } else if (!peselCorrect(peselTextField.getText())) {
            peselTextField.setText("");
            wyswietlKomunikatOBledzie(INCORRECT_PESEL_MESSAGE);
        } else if (!(passwordsCorrect(String.valueOf(firstPasswordField.getPassword()),
                String.valueOf(repeatPasswordField.getPassword())))) {
            firstPasswordField.setText("");
            repeatPasswordField.setText("");
            wyswietlKomunikatOBledzie(INCORRECT_PASSWORD_MESSAGE);
        } else {

            new Thread() {
                public void run() {

                    errorBuffer = new StringBuilder("");

                    mainWindow.getUserService().register(loginTextField.getText(),
                            peselTextField.getText(), nameTextField.getText(), surnameTextField.getText(),
                            String.valueOf(firstPasswordField.getPassword()), errorBuffer);

                    
                   
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            if (String.valueOf(errorBuffer).equals("")) {
                                //wyswietl, ze jest super za pomoca RegistrationCompleteDialog
                                registrationCompleteDialog.setVisible(true);
                            } else {
                                wyswietlKomunikatOBledzie(errorBuffer.toString());
                            	
                            }

                            if (String.valueOf(errorBuffer).equals("Login zajety!"))
                                loginTextField.setText("");
                            else if (String.valueOf(errorBuffer).equals("Nie udalo sie zarejestrowac")) {
                                loginTextField.setText("");
                                firstPasswordField.setText("");
                                repeatPasswordField.setText("");
                                peselTextField.setText("");
                                surnameTextField.setText("");
                                nameTextField.setText("");
                            }

                        }
                    });

                }
            }.start();

        }
    }
    
    private boolean loginCorrect(String login) {
        return !(login.equals(""));
    }

    private boolean passwordsCorrect(String password1, String password2) {
        return !(password1.equals("")) && password1.equals(password2);
    }

    private boolean peselCorrect(String pesel) {
        if (pesel.trim().length() == 11) {
            for (int i = 0; i < 11; i++) {
                int tmp = (int) (pesel.charAt(i)) - 48;
                if (tmp > 9 || tmp < 0) {
                    return false;
                }

            }
        }
        return !(pesel.equals("")) && pesel.trim().length() == 11;
    }

    private void wyswietlKomunikatOBledzie(String komunikat) {
        errorDialog.setMessage(komunikat);
        errorDialog.setVisible(true);
    }
}
