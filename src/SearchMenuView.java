/**
 * class SearchMenuView
 * show the search menu page by GUI
 * @author Xiangyi Lin
 * ticket booking software
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * ask and get the flight information user want to find
 */
public class SearchMenuView extends JPanel
{
	private JRadioButton roundtripButton;
	private JRadioButton onewayButton;
	private JTextField departDateField;
	private JTextField returnDateField;
	private JTextField departFromField;
	private JTextField departToField;
	private JTextField passengersField;
	private JButton searchButton;
	private JLabel message;
	private boolean roundtrip = true;

	public SearchMenuView()
	{
		JPanel radioButtonPanel = createRadioButton();
		roundtripButton.setSelected(true);
		JPanel textFieldPanel = createTextField();
		JPanel buttonPanel = createButton();
		
		setLayout(new GridLayout(3, 1));
		add(radioButtonPanel);
		add(textFieldPanel);
		add(buttonPanel);
	}
	
	public JPanel createRadioButton()
	{
		roundtripButton = new JRadioButton("Roundtrip");
		ActionListener roundtripListener = new RoundtripListener();
		roundtripButton.addActionListener(roundtripListener);
		
		onewayButton = new JRadioButton("One-way");
		ActionListener onewayListener = new OnewayListener();
		onewayButton.addActionListener(onewayListener);
		
		ButtonGroup group = new ButtonGroup();
		group.add(roundtripButton);
		group.add(onewayButton);
		
		JPanel panel = new JPanel();
		panel.add(roundtripButton);
		panel.add(onewayButton);
		
		return panel;
	}
	
	class RoundtripListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			roundtrip = true;
			
			removeAll();
			
			JPanel radioButtonPanel = createRadioButton();
			roundtripButton.setSelected(true);
			JPanel textFieldPanel = createTextField();
			JPanel buttonPanel = createButton();
			
			setLayout(new GridLayout(3, 1));
			add(radioButtonPanel);
			add(textFieldPanel);
			add(buttonPanel);
			
			revalidate();
			repaint();
		}
	}
	
	class OnewayListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			roundtrip = false;
			removeAll();
			
			JPanel radioButtonPanel = createRadioButton();
			onewayButton.setSelected(true);
			JPanel textFieldPanel = createTextField();
			JPanel buttonPanel = createButton();
			
			setLayout(new GridLayout(3, 1));
			add(radioButtonPanel);
			add(textFieldPanel);
			add(buttonPanel);
			
			revalidate();
			repaint();
		}
	}
	
	public JPanel createTextField()
	{
		final int FIELD_WIDTH_AMOUNT = 10;
		
		JLabel departDateLabel = new JLabel("Depart Date: ");
		departDateField = new JTextField(FIELD_WIDTH_AMOUNT);
		departDateField.setText("");
		JLabel departDateMessLabel = new JLabel("Depart Date: ");
		
		JLabel returnDateLabel = new JLabel("Return Date: ");
		returnDateField = new JTextField(FIELD_WIDTH_AMOUNT);
		returnDateField.setText("");
		
		JLabel departFromLabel = new JLabel("Depart From: ");
		departFromField = new JTextField(FIELD_WIDTH_AMOUNT);
		departFromField.setText("");
		
		JLabel departToLabel = new JLabel("Depart To: ");
		departToField = new JTextField(FIELD_WIDTH_AMOUNT);
		departToField.setText("");
		
		JLabel passengersLabel = new JLabel("# Passengers: ");
		passengersField = new JTextField(FIELD_WIDTH_AMOUNT);
		passengersField.setText("");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		panel.add(departDateLabel);
		panel.add(departDateField);
		
		if(roundtrip)
		{
			panel.add(returnDateLabel);
			panel.add(returnDateField);
		}
		
		panel.add(departFromLabel);
		panel.add(departFromField);
		panel.add(departToLabel);
		panel.add(departToField);
		panel.add(passengersLabel);
		panel.add(passengersField);
		
		
		return panel;
	}
	
	public JPanel createButton()
	{
		searchButton = new JButton("Search");
		message = new JLabel("");
		
		ActionListener searchListener = new SearchListener();
		searchButton.addActionListener(searchListener);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(searchButton);
		panel.add(message);
		
		return panel;
	}
	
	class SearchListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				if(checkValue())
				{
					SchedulePageView schedulePage;
					
					
					FlightScheduleReader.readFlightFile();
					
					if(roundtrip)
					{
						ArrayList<String[]> departResult = FlightScheduleReader.searchByInfo(departDateField.getText(), departFromField.getText(), departToField.getText(), Integer.parseInt(passengersField.getText()));
						ArrayList<String[]> returnResult = FlightScheduleReader.searchByInfo(returnDateField.getText(), departToField.getText(), departFromField.getText(), Integer.parseInt(passengersField.getText()));
	
						schedulePage = new SchedulePageView(departResult, returnResult);
					}
					else
					{
						ArrayList<String[]> result = FlightScheduleReader.searchByInfo(departDateField.getText(), departFromField.getText(), departToField.getText(), Integer.parseInt(passengersField.getText()));
						schedulePage = new SchedulePageView(result);
					}
					
					removeAll();
					
					add(schedulePage);
					
					revalidate();
					repaint();
				}
			}
			catch (FileNotFoundException e1)
			{
				System.out.println("Flight Schedule file not found. SearchMenuView");
			}
		}
	}
	
	public boolean checkValue()
	{
		if(!checkDateString(departDateField.getText()))
		{
			message.setText("Depart date is wrong!");
			return false;
		}
		else if(roundtrip && !checkDateString(returnDateField.getText()))
		{
			message.setText("Return date is wrong!");
			return false;
		}
		else if(departFromField.getText().isBlank())
		{
			message.setText("Depart from can not be empty!");
			return false;
		}
		else if(departToField.getText().isBlank())
		{
			message.setText("Depart to can not be empty!");
			return false;
		}
		else if (passengersField.getText().isBlank())
		{
			message.setText("Passengers number can not be empty!");
			return false;
		}
		else if(!checkIsInteger(passengersField.getText()) || Integer.parseInt(passengersField.getText())<1 || Integer.parseInt(passengersField.getText())>100)
		{
			passengersField.getText();
			message.setText("Passengers number is wrong!");
			return false;
		}
		
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
		
		if(!checkIsInteger(date.substring(0, first)) || Integer.parseInt(date.substring(0, first))<2020 || Integer.parseInt(date.substring(0, first))>2022)
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
