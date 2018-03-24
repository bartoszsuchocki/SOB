import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class AfterAuthenticationGuiPanel extends JPanel
{
	JLabel logoLabel; //na nazwê programu
	JLabel iconLabel; //na ikonê programu
	MainWindow mainWindow; //potrzebny, zeby np. uzyc funkcji changeGui
	public AfterAuthenticationGuiPanel(MainWindow mainWindow)
	{
		this.mainWindow = mainWindow;
		
		/*tu trzeba zdefiniowac jak bêdzie wygl¹daæ logo naszego systemu wyswietlane na kazdym (oprocz logowania i rejestracji) gui.*/
		logoLabel = new JLabel("System Obs\u0142ugi Biblioteki"); 
		logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		logoLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		logoLabel.setBounds(66, 0, 634, 64);
		add(logoLabel);
		
		iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(RegistrationPanel.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconLabel.setFont(new Font("Tahoma", Font.ITALIC, 32));
		iconLabel.setBackground(Color.ORANGE);
		iconLabel.setBounds(10, 0, 46, 64);
		add(iconLabel);
		
		/*koniec definiowania logo*/
		
		
	}
	
	
	

}
