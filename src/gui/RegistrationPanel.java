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
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class RegistrationPanel extends JPanel {
    private JTextField loginTextField;
    private JPasswordField firstPasswordField;
    private JPasswordField repeatPasswordField;
    private JTextField peselTextField;

    private DataBase db;
    private DefaultUser duser;
    private User user;
    private StringBuilder errorBuffer;
    private JTextField nameTextField;
    private JTextField surnameTextField;

    public RegistrationPanel(MainWindow mainWindow) {

        setSize(700, 500);

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

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel peselLabel = new JLabel("Pesel");
        peselLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        peselLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

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

        JButton registrationButton = new JButton("Zarejestruj");
        registrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if (!loginCorrect(loginTextField.getText())) {
                    loginTextField.setText("");
                    wyswietlKomunikatOBledzie("Podano b³êdny login!");

                } else if (!peselCorrect(peselTextField.getText())) {
                    peselTextField.setText("");
                    wyswietlKomunikatOBledzie("Podano b³êdny pesel!");
                } else if (!(passwordsCorrect(String.valueOf(firstPasswordField.getPassword()),
                        String.valueOf(repeatPasswordField.getPassword())))) {
                    firstPasswordField.setText("");
                    repeatPasswordField.setText("");
                    wyswietlKomunikatOBledzie("Któreœ has³o jest b³êdne!");
                } else {

                    new Thread() {
                        public void run() {

                            errorBuffer = new StringBuilder("");

                            mainWindow.getUserService().register(loginTextField.getText(),
                                    peselTextField.getText(), nameTextField.getText(), surnameTextField.getText(),
                                    String.valueOf(firstPasswordField.getPassword()), errorBuffer);

                            try {
                                Thread.sleep(1);// imitacja dlugiej opercaji, dowod, ze nie blokujemy gui
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    if (String.valueOf(errorBuffer).equals("")) {
                                        //wyswietl, ze jest super za pomoca RegistrationCompleteDialog
                                        RegistrationCompleteDialog registrationCompleteDialog = new RegistrationCompleteDialog(
                                                mainWindow);
                                        registrationCompleteDialog.setVisible(true);
                                    } else {
                                        JOptionPane.showMessageDialog(RegistrationPanel.this, errorBuffer, "Blednie podane dane",
                                                JOptionPane.ERROR_MESSAGE);
                                    }

                                    nameTextField.setText("");
                                    surnameTextField.setText("");
                                    loginTextField.setText("");
                                    peselTextField.setText("");
                                    firstPasswordField.setText(""); //to dla przykladu, ze z tego w¹tku mogê zmieniaæ wszystkie komponenty gui
                                    repeatPasswordField.setText("");

                                }
                            });

                        }
                    }.start();

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
        JOptionPane.showMessageDialog(RegistrationPanel.this, komunikat, "Blednie podane dane",
                JOptionPane.ERROR_MESSAGE);
    }
}
