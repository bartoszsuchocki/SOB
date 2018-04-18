package gui;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class AfterAuthenticationGuiPanel extends JPanel
{
	protected static final String EMPTY_SEARCH_FIELD_MSG="Wpisz dane do wyszukiwania";
	protected static final String EMPTY_SEARCH_LIST_MSG="Nie ma takich ksi\u0105\u017Cek";
	
	private JLabel logoLabel; //na nazw� programu
	private JLabel iconLabel; //na ikon� programu
	MainWindow mainWindow; //potrzebny, zeby np. uzyc funkcji changeGui
	private final int width=700;
	private final int height=500;
	public AfterAuthenticationGuiPanel(MainWindow mainWindow)
	{
		
		this.mainWindow = mainWindow;
		setLayout(null);
		
		setBounds(0,0,width,height);
		
		
		/*tu trzeba zdefiniowac jak b�dzie wygl�da� logo naszego systemu wyswietlane na kazdym (oprocz logowania i rejestracji) gui.*/
		/*iconLabel = new JLabel("");
		iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconLabel.setIcon(new ImageIcon(AfterAuthenticationGuiPanel.class.getResource("/javax/swing/plaf/metal/icons/Question.gif")));
		iconLabel.setBounds(47, 0, 40, 64);
		add(iconLabel);*/
		
		logoLabel = new JLabel("System Obs\u0142ugi Biblioteki");
		logoLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logoLabel.setBounds(97, 0, 231, 64);
		add(logoLabel);
		
		/*koniec definiowania logo*/
		
		
	}
	
	
	protected void showMessage(String msg)
	{
        JOptionPane.showMessageDialog(null, msg);

	}

}
