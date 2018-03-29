import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuestGui extends AfterAuthenticationGuiPanel {

	/**
	 * Create the panel.
	 */
	private  MainWindow mainWindow;
	private JLabel logoLabel;
	private JLabel iconLabel;
	public GuestGui(MainWindow mainWindow) {
		super(mainWindow);
		//logoLabel.setBounds(100, 100, 60, 30);
	}

}
