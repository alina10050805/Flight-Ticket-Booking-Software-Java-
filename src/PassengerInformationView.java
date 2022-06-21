/**
 * class PassengerInformationView
 * show the passenger information page by GUI
 * @author Xiangyi Lin
 * ticket booking software
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PassengerInformationView extends JPanel
{
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField birthDateField;
	private JRadioButton maleButton;
	private JRadioButton femaleButton;
	private JTextField addressField;
	private JTextField zipCodeField;
	private JTextField emailField;
	private JTextField phoneField;
	private JButton addButton;
	private JLabel message;
	private BillInformation billInfo;

	public PassengerInformationView(BillInformation bill)
	{
		billInfo = bill;
		
		JPanel newPassengerPanel = createNewPassengerPanel();
		
		//setLayout(new GridLayout(2, 1));
		add(newPassengerPanel);
	}
	
	public JPanel createNewPassengerPanel()
	{
		final int FIELD_WIDTH_AMOUNT = 10;
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameField = new JTextField(FIELD_WIDTH_AMOUNT);
		firstNameField.setText("");
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameField = new JTextField(FIELD_WIDTH_AMOUNT);
		lastNameField.setText("");
		
		JLabel birthDateLabel = new JLabel("Date of birth: ");
		birthDateField = new JTextField(FIELD_WIDTH_AMOUNT);
		birthDateField.setText("");
		
		maleButton = new JRadioButton("Male");
		maleButton.setSelected(true);

		femaleButton = new JRadioButton("Female");
		
		ButtonGroup group = new ButtonGroup();
		group.add(maleButton);
		group.add(femaleButton);
		
		JLabel genderLabel = new JLabel("Gender: ");
		JPanel genderButtonPanel = new JPanel();
		genderButtonPanel.add(maleButton);
		genderButtonPanel.add(femaleButton);
		
		JLabel addressLabel = new JLabel("Address: ");
		addressField = new JTextField(FIELD_WIDTH_AMOUNT);
		addressField.setText("");
		
		JLabel zipCodeLabel = new JLabel("Zip code: ");
		zipCodeField = new JTextField(FIELD_WIDTH_AMOUNT);
		zipCodeField.setText("");
		
		JLabel emailLabel = new JLabel("Email: ");
		emailField = new JTextField(FIELD_WIDTH_AMOUNT);
		emailField.setText("");
		
		JLabel phoneLabel = new JLabel("Phone number: ");
		phoneField = new JTextField(FIELD_WIDTH_AMOUNT);
		phoneField.setText("");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9, 2));
		panel.add(firstNameLabel);
		panel.add(firstNameField);
		panel.add(lastNameLabel);
		panel.add(lastNameField);
		panel.add(birthDateLabel);
		panel.add(birthDateField);
		panel.add(genderLabel);
		panel.add(genderButtonPanel);
		panel.add(addressLabel);
		panel.add(addressField);
		panel.add(zipCodeLabel);
		panel.add(zipCodeField);
		panel.add(emailLabel);
		panel.add(emailField);
		panel.add(phoneLabel);
		panel.add(phoneField);
		
		addButton = new JButton("Add");
		message = new JLabel("");
		
		ActionListener addListener = new AddListener();
		addButton.addActionListener(addListener);
		
		panel.add(addButton);
		panel.add(message);
		return panel;
	}
	
	class AddListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			//if(checkValue())
			//{
				String gender;
				if(maleButton.isSelected())
					gender = "Male";
				else
					gender = "Female";
				
				Passenger passenger = new Passenger(firstNameField.getText(), lastNameField.getText(), 
						birthDateField.getText(), gender, addressField.getText(), zipCodeField.getText(), 
						emailField.getText(), phoneField.getText());
			
				billInfo.setPassenger(passenger);
				
				removeAll();
				revalidate();
				
//				repaint();
				update(getGraphics());
				
				SeatingChoicePageView seatChoice = new SeatingChoicePageView(billInfo);     //connect to SeatingChoicePageView
				
				
				
				
				seatChoice.paint(getGraphics());
				add(seatChoice);
				revalidate();
				
				//update(getGraphics());
				//repaint();
			//}
		}
	}
	
	public boolean checkValue()
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
		else if(birthDateField.getText().isBlank())
		{
			message.setText("Date of birth can not be empty!");
			return false;
		}
		else if(!checkDateString(birthDateField.getText()))
		{
			message.setText("Date of birth is wrong!");
			return false;
		}
		else if(addressField.getText().isBlank())
		{
			message.setText("Address can not be empty!");
			return false;
		}
		else if(zipCodeField.getText().isBlank())
		{
			message.setText("Zip code can not be empty!");
			return false;
		}
		else if(!checkIsInteger(zipCodeField.getText()) || zipCodeField.getText().length()!=5)
		{
			message.setText("Zip code is wrong!");
			return false;
		}
		else if(emailField.getText().isBlank())
		{
			message.setText("Email can not be empty!");
			return false;
		}
		else if(phoneField.getText().isBlank())
		{
			message.setText("Phone numner can not be empty!");
			return false;
		}
		else if(!checkIsInteger(phoneField.getText()) || phoneField.getText().length()!=10)
		{
			message.setText("Phone number is wrong!");
			return false;
		}
		else
			return true;
	}
	
	/**
	 * check the formal of date
	 * year/month/date
	 * @param date of string
	 * @return return true if the date is correct, return false if not
	 */
	private boolean checkDateString(String date)
	{
		int first = date.indexOf("/");
		if(first==-1)
			return false;
		
		int second = date.indexOf("/", first+1);
		if(second==-1)
			return false;
		
		if(!checkIsInteger(date.substring(0, first)) || Integer.parseInt(date.substring(0, first))<1900 || Integer.parseInt(date.substring(0, first))>2020)
			return false;
		else if(!checkIsInteger(date.substring(first+1, second)) || Integer.parseInt(date.substring(first+1, second))<1 || Integer.parseInt(date.substring(first+1, second))>12)
			return false;
		else if(!checkIsInteger(date.substring(second+1)) || Integer.parseInt(date.substring(second+1))<1 || Integer.parseInt(date.substring(second+1))>31) 
			return false;
		
		return true;
	}
	
	/**
	 * check if string value is integer
	 * @param string of value
	 * @return return true if is integer, return false if not
	 */
	private boolean checkIsInteger(String value)
	{
		for(int i=0; i<value.length(); i++)
		{
			if(!Character.isDigit(value.charAt(i)))
					return false;
		}
		
		return true;
	}
}
