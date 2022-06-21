/**
 * class SignupView
 * show the sign up page by GUI
 * @author Xiangyi Lin
 * ticket booking software
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * sign up a account for user add the information to the user account file
 */
public class SignupView extends JPanel
{
	private final int PASSWORD_MIN_LENGTH = 6;    //the require length for password
	
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField userNameField;
	private JTextField passwordField;
	private JTextField confirmPasswordField;
	private JTextField emailField;
	private JButton signupButton;
	private JLabel message;
	
	//constructor
	public SignupView()
	{
		JPanel signupPanel = createSignupPanel();
		
		add(signupPanel);
	}
	
	public JPanel createSignupPanel()
	{
		final int FIELD_WIDTH_AMOUNT = 10;
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameField = new JTextField(FIELD_WIDTH_AMOUNT);
		firstNameField.setText("");
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameField = new JTextField(FIELD_WIDTH_AMOUNT);
		lastNameField.setText("");
		
		JLabel userNameLabel = new JLabel("User Name: ");
		userNameField = new JTextField(FIELD_WIDTH_AMOUNT);
		userNameField.setText("");
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordField = new JTextField(FIELD_WIDTH_AMOUNT);
		passwordField.setText("");
		
		JLabel confirmPasswordLabel = new JLabel("Confirm password: ");
		confirmPasswordField = new JTextField(FIELD_WIDTH_AMOUNT);
		confirmPasswordField.setText("");
		
		JLabel emailLabel = new JLabel("Email: ");
		emailField = new JTextField(FIELD_WIDTH_AMOUNT);
		emailField.setText("");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2));
		panel.add(firstNameLabel);
		panel.add(firstNameField);
		panel.add(lastNameLabel);
		panel.add(lastNameField);
		panel.add(userNameLabel);
		panel.add(userNameField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(confirmPasswordLabel);
		panel.add(confirmPasswordField);
		panel.add(emailLabel);
		panel.add(emailField);
		
		signupButton = new JButton("Sign Up");
		message = new JLabel("");
		
		ActionListener signupListener = new SignupListener();
		signupButton.addActionListener(signupListener);
		
		panel.add(signupButton);
		panel.add(message);
		return panel;
	}
	
	class SignupListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				if(checkValue())
				{
					UserAccountReader.addLine(firstNameField.getText(), lastNameField.getText(), userNameField.getText(), passwordField.getText(), emailField.getText());
				
					removeAll();
					
					LoginView login = new LoginView();              //connect to LoginView
					add(login);
					
					revalidate();
					repaint();
				}
			}
			catch (FileNotFoundException exception) 
			{
				
				System.out.println("Could not found account file. SignupView");
			} 
			catch (IOException exception1) 
			{
				System.out.println("Could not open account file. SignupView");
			}
		}
	}
	
	public boolean checkValue() throws FileNotFoundException
	{
		if(firstNameField.getText().isBlank())
		{
			message.setText("First name can not be empty!");
			return false;
		}
		else if(lastNameField.getText().isBlank())
		{
			message.setText("Last name can not be empty!");
			return false;
		}
		else if(userNameField.getText().isBlank())
		{
			message.setText("User name can not be empty!");
			return false;
		}
		else if(UserAccountReader.appearUserName(userNameField.getText()))
		{
			message.setText("User name has been used!");
			return false;
		}
		else if(passwordField.getText().length()<PASSWORD_MIN_LENGTH)
		{
			message.setText("Password at least " + PASSWORD_MIN_LENGTH + " chars!");
			return false;
		}
		else if(confirmPasswordField.getText().compareTo(passwordField.getText())!=0)
		{
			message.setText("Confirm password is different to password!");
			return false;
		}
		else if(emailField.getText().isBlank())
		{
			message.setText("Email can not be empty!");
			return false;
		}
		else
			return true;
	}
}
