package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.Color;

public class DefaultDialog extends JDialog {

	public static final int X = 200;
	public static final int Y = 200;
	public static final int WIDTH = 200;
	public static final int HEIGHT = 150;

	private JButton okButton;
	private JLabel messageLabel;

	private String title;
	private String statement;

	public DefaultDialog(String title, String message) {

		setBounds(X, Y, WIDTH, HEIGHT);
		getContentPane().setBackground(new Color(255, 228, 181));
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultDialog.this.setVisible(false);
				System.out.println("OK");
			}
		});
		okButton.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				System.out.println("released default");
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					DefaultDialog.this.setVisible(false);
				}

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		messageLabel = new JLabel("Domy\u015Blny komunikat.");

		this.setTitle(title);
		this.setMessage(message);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(messageLabel).addGap(35))
				.addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup().addGap(71)
						.addComponent(okButton).addContainerGap(70, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(
				GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup().addGap(30).addComponent(messageLabel)
						.addGap(18).addComponent(okButton).addContainerGap(26, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

	}

	public DefaultDialog(String statement) {

		this("komunikat", statement);

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		super.setTitle(title);
		this.title = title;
	}

	public String getMessage() {
		return statement;
	}

	public void setMessage(String statement) {
		this.statement = statement;
		messageLabel.setText(statement);
	}

	/**
	 * @wbp.parser.constructor
	 */

	public void setWindowListener(WindowAdapter windowAdapter) {
		this.addWindowListener(windowAdapter);
	}

	public void setOnOkButtonPressedListener(ActionListener listener) {
		okButton.addActionListener(listener);
	}

}
