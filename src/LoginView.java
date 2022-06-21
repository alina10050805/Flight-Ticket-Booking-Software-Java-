/**
 * class LoginView
 * show the login page by GUI
 * @author Xiangyi Lin
 * ticket booking software
 */

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * user enter user name and password or can choice sign up
 * user have three time to enter password
 */
public class LoginView extends JPanel
{
	private JTextField userNameField;
	private JTextField passwordField;
	private JButton loginButton;
	private JButton signupButton;
	private JButton guestButton;
	private JLabel message;
	private int tried;

	//constructor
	public LoginView()
	{
		tried = 0;
		
		JPanel textFieldPanel = createTextField();
		JPanel buttonPanel = createButton();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(textFieldPanel);
		panel.add(buttonPanel);
		
	    FlowLayout layout = new FlowLayout(FlowLayout.CENTER,200,100);
        setLayout(layout);

        add(panel);
	}

	public JPanel createTextField()
	{
		JLabel nameLabel = new JLabel("User Name(NOT case sensitive): ");
		final int FIELD_WIDTH_AMOUNT = 10;
		userNameField = new JTextField(FIELD_WIDTH_AMOUNT);
		userNameField.setText("");
		
		JLabel posswordLabel = new JLabel("Password(CASE sensitive): ");
		passwordField = new JTextField(FIELD_WIDTH_AMOUNT);
		passwordField.setText("");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		panel.add(nameLabel);
		panel.add(userNameField);
		panel.add(posswordLabel);
		panel.add(passwordField);
		return panel;
	}
	
	public JPanel createButton()
	{
		loginButton = new JButton("Login");
		signupButton = new JButton("Sign Up");
		guestButton = new JButton("Guest Visit");
		message = new JLabel("");
		
		ActionListener loginListener = new LoginListener();
		loginButton.addActionListener(loginListener);
		
		ActionListener signupListener = new SignupListener();
		signupButton.addActionListener(signupListener);
		
		ActionListener guestListener = new GuestListener();
		guestButton.addActionListener(guestListener);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.add(loginButton);
		
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(1, 2));
		temp.add(signupButton);
		temp.add(guestButton);
		
		panel.add(temp);
		panel.add(message);
		return panel;
	}
	
	class LoginListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			try 
			{
				login();
			} 
			catch (FileNotFoundException exception) 
			{
				
				System.out.println("Could not found account file. LoginView");
			}
		}
	}
	
	class SignupListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			removeAll();
			
			SignupView signup = new SignupView();               //connect to SignupView
			add(signup);
			
			revalidate();
			repaint();
		}
	}
	
	class GuestListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			removeAll();
			SearchMenuView searchMenu = new SearchMenuView();   //connect to SearchMenuView
			add(searchMenu);
			revalidate();
			repaint();
		}
	}
	
	public void login() throws FileNotFoundException
	{
		String userName = userNameField.getText();
		String password = passwordField.getText();
		
		String correctPass = UserAccountReader.findPassword(userName);
		if(correctPass.compareTo("-1")!=0 && correctPass.compareTo(password)==0)
		{
			User.userLogIn(UserAccountReader.getInformation(userName));
			
			removeAll();
			SearchMenuView searchMenu = new SearchMenuView();   //connect to SearchMenuView
			add(searchMenu);
			revalidate();
			repaint();
		}
		else if(correctPass.compareTo("-1")!=0)
		{
			message.setText("User name NOT found.");
		}
		else
		{
			tried ++;
			
			message.setText("Wrong user name or password. " + tried);
//			if(tried<3)
//				message.setText("Wrong user name or password. " + tried);
//			else
//			{
//				removeAll();
//				message.setText("Wrong three times. Colsed.");        //******************************
//				add(message);
//				revalidate();
//				repaint();
//			}
		}
	}
}
