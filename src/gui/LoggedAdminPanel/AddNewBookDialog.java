package gui.LoggedAdminPanel;

import javax.swing.*;


import facade.UserService;
import gui.AfterAuthenticationGuiPanel;
import gui.DefaultDialog;
import usersAndBooks.Book;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

public class AddNewBookDialog extends JFrame
{

 
	private static final long serialVersionUID = 1L;

	private static final String DIALOG_TITLE="Dodawanie ksi\u0105\u017Cki";
    
    private UserService userService;

    private class AddNewBookDialogPanel extends JPanel
    {
        
		private static final long serialVersionUID = 1L;
		private static final String NAME_LABEL_TEXT="Wpisz nazw\u0119";
        private static final String AUTHOR_LABEL_TEXT="Wpisz autora";
        private static final String SIGNATURE_LABEL_TEXT="Wpisz sygnatur\u0119";

        private static final String OK_BUTTON_TEXT="OK";
        private static final String CANCEL_BUTTON_TEXT="NIE";
        
        
        private static final String EMPTY_FIELDS_MSG="Wpisz wszystkie dane ksi\u0105\u017Cki";
        private static final String ADD_BOOK_SUCCESS_MSG="Uda\u0142o si\u0119 doda\u0107 ksi\u0105\u017Ck\u0119";
        private static final String ADD_BOOK_UNSUCCESS_MSG="Nie uda\u0142o si\u0119 doda\u0107 ksi\u0105\u017Cki";

        
        private static final int LABEL_WIDTH=100;
        private static final int LABEL_HEIGHT=15;
        private static final int TEXT_FIELD_WIDTH=300;
        private static final int TEXT_FIELD_HEIGHT=25;


        private JLabel nameLabel;
        private JLabel authorLabel;
        private JLabel signatureLabel;

        private JTextField nameTextField;
        private JTextField authorTextField;
        private JTextField signatureTextField;

        private JButton okButton;
        private JButton cancelButton;
       // private JButton undoButton; to nam sie nie przyda

        private KeyListener addBookAfterEnterReleased;
        private DefaultDialog notAbleToAddBookDialog;
        private DefaultDialog addBookSuccessDialog;
        
    
        
        public AddNewBookDialogPanel()
        {
            super();
            this.setLayout(null);

            /*Współrzędne początkowe*/
            int x=50;
            int y=50;
            int step=40;
            
            
            notAbleToAddBookDialog = new DefaultDialog(ADD_BOOK_UNSUCCESS_MSG);
            addBookSuccessDialog = new DefaultDialog(ADD_BOOK_SUCCESS_MSG);
            
            
            
            addBookAfterEnterReleased = new KeyListener() {
    			
    			@Override
    			public void keyTyped(KeyEvent e) {
    				// TODO Auto-generated method stub
    				
    			}
    			
    			@Override
    			public void keyReleased(KeyEvent e) {
    				if(e.getKeyCode()==KeyEvent.VK_ENTER && !(notAbleToAddBookDialog.isVisible()) && !(addBookSuccessDialog.isVisible()) ) {
    					new Thread(new Runnable()
                		{
                			public void run()
                			{
                				synchronized(userService)
                				{
                					addBook();
                				}
                			}
                		}).start();
    				}
    				
    			}
    			
    			@Override
    			public void keyPressed(KeyEvent e) {
    				// TODO Auto-generated method stub
    				
    			}
    		};

            nameLabel=new JLabel(NAME_LABEL_TEXT);
            nameLabel.setBounds(x, y, LABEL_WIDTH, LABEL_HEIGHT);
            this.add(nameLabel);

            nameTextField=new JTextField();
            nameTextField.setBounds(x+LABEL_WIDTH+step,  y, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
            nameTextField.addKeyListener(addBookAfterEnterReleased);
            this.add(nameTextField);

            authorLabel=new JLabel(AUTHOR_LABEL_TEXT);
            authorLabel.setBounds(x, y+=step, LABEL_WIDTH, LABEL_HEIGHT);
            this.add(authorLabel);

            authorTextField=new JTextField();
            authorTextField.setBounds(x+LABEL_WIDTH+step, y, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
            authorTextField.addKeyListener(addBookAfterEnterReleased);
            this.add(authorTextField);

            signatureLabel=new JLabel(SIGNATURE_LABEL_TEXT);
            signatureLabel.setBounds(x, y+=step, LABEL_WIDTH, LABEL_HEIGHT);
            this.add(signatureLabel);

            signatureTextField=new JTextField();
            signatureTextField.setBounds(x+LABEL_WIDTH+step, y, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
            signatureTextField.addKeyListener(addBookAfterEnterReleased);
            this.add(signatureTextField);

            
            
            
            okButton=new JButton(OK_BUTTON_TEXT);
            okButton.setBounds(x+=50, y+=2*step, 100, 25);
            okButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                	new Thread(new Runnable()
            		{
            			public void run()
            			{
            				synchronized(userService)
            				{
            					addBook();
            				}
            			}
            		}).start();
                }
            });
            this.add(okButton);

            cancelButton=new JButton(CANCEL_BUTTON_TEXT);
            cancelButton.setBounds(x+=120, y, 100, 25);
            cancelButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    dispose();
                }
            });
            this.add(cancelButton);
            this.setBackground(new Color(255, 228, 181));


        }
        private void addBook() {
        	String title= nameTextField.getText();
            String author=authorTextField.getText();
            String signature=signatureTextField.getText();
            //Data dodania - dzisiejsza
            Date currentDate=new Date();
            if(title.isEmpty()||author.isEmpty()||signature.isEmpty())
            {
            	notAbleToAddBookDialog.setMessage(EMPTY_FIELDS_MSG);
            	AfterAuthenticationGuiPanel.showMessage(notAbleToAddBookDialog);
            	
            }
            else	
            {
            	Book newBook=new Book(title, author, signature, null, currentDate);
            	if(userService.addBook(newBook)!=UserService.SUCCESS) {
            		notAbleToAddBookDialog.setMessage(ADD_BOOK_UNSUCCESS_MSG);
            		AfterAuthenticationGuiPanel.showMessage(notAbleToAddBookDialog);
            	}
            	else { 
                	AfterAuthenticationGuiPanel.showMessage(addBookSuccessDialog);
                	dispose();
            	}
            }
        }
    }

    public AddNewBookDialog(UserService us)
    {
        super();
        getContentPane().setBackground(new Color(255, 228, 181));
        setBounds(DefaultDialog.X, DefaultDialog.Y, 550, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle(DIALOG_TITLE);
        getContentPane().add(new AddNewBookDialogPanel());
        this.userService=us;

    }
    
    
    
    public void showDialog()
    {
        this.setVisible(true);

    }
    


}
