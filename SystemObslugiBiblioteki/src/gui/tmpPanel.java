package gui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class tmpPanel extends JPanel {

	public tmpPanel() {
		setBounds(0,0,700,500);
		setLayout(null);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconLabel.setIcon(new ImageIcon(tmpPanel.class.getResource("/javax/swing/plaf/metal/icons/Question.gif")));
		iconLabel.setBounds(47, 0, 40, 64);
		add(iconLabel);
		
		JLabel logoLabel = new JLabel("System Obs\u0142ugi Biblioteki");
		logoLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logoLabel.setBounds(97, 0, 231, 64);
		add(logoLabel);
		
	}
}
