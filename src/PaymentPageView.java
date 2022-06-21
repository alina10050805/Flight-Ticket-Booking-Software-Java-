/**
 * class PaymentPageView
 * show the Payment Page by GUI
 * @author Xiangyi Lin
 * ticket booking software
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PaymentPageView extends JPanel
{
	private JTextField cardNumField;
	private JComboBox expireMonthCombo;
	private JComboBox expireYearCombo;
	private JTextField cvvCodeField;
	private JTextField zipCodeField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JButton nextButton;
	private JLabel message;
	private BillInformation billInfo;

	public PaymentPageView(BillInformation bill)
	{
		billInfo = bill;
		
		JPanel textPanel = createTextPanel();
		
		add(textPanel);
	}
	
	public JPanel createTextPanel()
	{
		final int FIELD_WIDTH_AMOUNT = 10;
				
		JLabel cardNumLabel = new JLabel("Credit/Debit card: ");
		cardNumField = new JTextField(FIELD_WIDTH_AMOUNT);
		cardNumField.setText("");
				
		JLabel expireDateLabel = new JLabel("Expire date: ");
		JPanel expireDatePanel = createExpireDatePanel();
		
		JLabel cvvCodeLabel = new JLabel("CVV code: ");
		cvvCodeField = new JTextField(FIELD_WIDTH_AMOUNT);
		cvvCodeField.setText("");
		
		JLabel zipCodeLabel = new JLabel("Zip code: ");
		zipCodeField = new JTextField(FIELD_WIDTH_AMOUNT);
		zipCodeField.setText("");
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameField = new JTextField(FIELD_WIDTH_AMOUNT);
		firstNameField.setText("");
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameField = new JTextField(FIELD_WIDTH_AMOUNT);
		lastNameField.setText("");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2));
		panel.add(cardNumLabel);
		panel.add(cardNumField);
		panel.add(expireDateLabel);
		panel.add(expireDatePanel);
		panel.add(cvvCodeLabel);
		panel.add(cvvCodeField);
		panel.add(zipCodeLabel);
		panel.add(zipCodeField);
		panel.add(firstNameLabel);
		panel.add(firstNameField);
		panel.add(lastNameLabel);
		panel.add(lastNameField);
		
		nextButton = new JButton("Next");
		message = new JLabel("");
		
		ActionListener nextListener = new NextListener();
		nextButton.addActionListener(nextListener);
		
		panel.add(nextButton);
		panel.add(message);
		return panel;
	}
	
	private JPanel createExpireDatePanel()
	{
		expireMonthCombo = new JComboBox();
		expireMonthCombo.addItem("01");
		expireMonthCombo.addItem("02");
		expireMonthCombo.addItem("03");
		expireMonthCombo.addItem("04");
		expireMonthCombo.addItem("05");
		expireMonthCombo.addItem("06");
		expireMonthCombo.addItem("07");
		expireMonthCombo.addItem("08");
		expireMonthCombo.addItem("09");
		expireMonthCombo.addItem("10");
		expireMonthCombo.addItem("11");
		expireMonthCombo.addItem("12");
		expireMonthCombo.setEditable(true);
		
		expireYearCombo = new JComboBox();
		expireYearCombo.addItem("2021");
		expireYearCombo.addItem("2022");
		expireYearCombo.addItem("2023");
		expireYearCombo.addItem("2024");
		expireYearCombo.addItem("2025");
		expireYearCombo.addItem("2026");
		expireYearCombo.addItem("2027");
		expireYearCombo.addItem("2028");
		expireYearCombo.addItem("2029");
		expireYearCombo.addItem("2030");
		expireYearCombo.addItem("2031");
		expireYearCombo.addItem("2032");
		expireYearCombo.setEditable(true);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(expireMonthCombo);
		panel.add(expireYearCombo);
		return panel;
	}

	class NextListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(checkValue())
			{
				removeAll();
				ComfirmationPageView comfirmationPage = new ComfirmationPageView(billInfo);   //connect to ComfirmationPageView
				add(comfirmationPage);
				revalidate();
				repaint();
			}
		}
	}
	
	private boolean checkValue()
	{
		if(cardNumField.getText().isBlank())
		{
			message.setText("Card number can not be empty!");
			return false;
		}
		else if (!checkIsInteger(cardNumField.getText()) || cardNumField.getText().length()!=16)
		{
			message.setText("Card number is wrong!");
			return false;
		}
		else if(cvvCodeField.getText().isBlank())
		{
			message.setText("CVV code can not be empty!");
			return false;
		}
		else if(!checkIsInteger(cvvCodeField.getText()) || cvvCodeField.getText().length()!=3)
		{
			message.setText("CVV code is wrong!");
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
		else if(firstNameField.getText().isBlank())
		{
			message.setText("First name can not be empty!");
			return false;
		}
		else if(lastNameField.getText().isBlank())
		{
			message.setText("Last name can not be empty!");
			return false;
		}
		else
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
