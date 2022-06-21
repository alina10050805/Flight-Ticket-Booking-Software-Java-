/**
 * class SchedulePageView
 * show the flight schedule page by GUI
 * @author Xiangyi Lin
 * ticket booking software
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * show the whole flight schedule result that user find
 */
public class SchedulePageView extends JPanel
{
	private JComboBox sortCombo;
	private ArrayList<String[]> schedule;
	private JPanel scheduleText;
	private ArrayList<JRadioButton> choiceButton;
	private JButton nextButton;
	
	public SchedulePageView(ArrayList<String[]> filghtSchedule)
	{
		schedule = FlightScheduleReader.sortByPrice(filghtSchedule);
		Collections.reverse(schedule);
		
		JPanel comboPanel = createComboBox();
		scheduleText = createScheduleText();
		
		setLayout(new GridLayout(2, 1));
		add(comboPanel);
		add(scheduleText);
		
		setSize(100, 100);
	}
	
	public SchedulePageView(ArrayList<String[]> departFilghtSchedule, ArrayList<String[]> returnFilghtSchedule)
	{
		//*********************************schedule********************
		
		JPanel comboPanel = createComboBox();
		
		setLayout(new GridLayout(2, 1));
		add(comboPanel);
	}

	
	public JPanel createComboBox()
	{
		JLabel sortLabel = new JLabel("Sort by: ");
		sortCombo = new JComboBox();
		sortCombo.addItem("Price(Lowest)");
		sortCombo.addItem("Price(Highest)");
		sortCombo.addItem("Duration(Shortest)");
		sortCombo.addItem("Duration(Longest)");
		sortCombo.addItem("Departure(Earliest)");
		sortCombo.addItem("Departure(Latest)");
		sortCombo.addItem("Arrival(Earliest)");
		sortCombo.addItem("Arrival(Latest)");
		sortCombo.setEditable(true);
		
		ActionListener listener = new SortListener();
		sortCombo.addActionListener(listener);
		
		JPanel panel = new JPanel();
		panel.add(sortLabel);
		panel.add(sortCombo);
		return panel;
	}
	
	class SortListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			int sortChoice = sortCombo.getSelectedIndex();
			
			remove(scheduleText);
			
			if(sortChoice==0)
			{
				schedule = FlightScheduleReader.sortByPrice(schedule);
				Collections.reverse(schedule);
				scheduleText = createScheduleText();
			}
			else if(sortChoice==1)
			{
				schedule = FlightScheduleReader.sortByPrice(schedule);
				scheduleText = createScheduleText();
			}
			else if(sortChoice==2)
			{
				schedule = FlightScheduleReader.sortByDuration(schedule);
				Collections.reverse(schedule);
				scheduleText = createScheduleText();
			}
			else if(sortChoice==3)
			{
				schedule = FlightScheduleReader.sortByDuration(schedule);
				scheduleText = createScheduleText();
			}
			else if(sortChoice==4)
			{
				schedule = FlightScheduleReader.sortByDepartureTime(schedule);
				Collections.reverse(schedule);
				scheduleText = createScheduleText();
			}
			else if(sortChoice==5)
			{
				schedule = FlightScheduleReader.sortByDepartureTime(schedule);
				scheduleText = createScheduleText();
			}
			else if(sortChoice==6)
			{
				schedule = FlightScheduleReader.sortByArrivalTime(schedule);
				Collections.reverse(schedule);
				scheduleText = createScheduleText();
			}
			else if(sortChoice==7)
			{
				schedule = FlightScheduleReader.sortByArrivalTime(schedule);
				scheduleText = createScheduleText();
			}
			else
			{
				System.out.println("Out of choice. SchedulePageView");
			}
			
			add(scheduleText);
			revalidate();
			repaint();
		}
	}
	
	public JPanel createScheduleText()
	{
		choiceButton = new ArrayList<JRadioButton>();
		ButtonGroup group = new ButtonGroup();

		for(int i=0; i<schedule.size(); i++)
		{
			String[] temp = schedule.get(i);
			choiceButton.add(new JRadioButton(FlightScheduleReader.toString(temp)));
			group.add(choiceButton.get(i));
		}
		
		nextButton = new JButton("Next");
		ActionListener nextListener = new NextListener();
		nextButton.addActionListener(nextListener);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(choiceButton.size()+1, 1));
		for(int i=0; i<choiceButton.size(); i++)
		{
			panel.add(choiceButton.get(i));
		}
		panel.add(nextButton);
		
		return panel;
	}
	
	class NextListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			for(int i=0; i<choiceButton.size(); i++)
			{
				if(choiceButton.get(i).isSelected())
				{
					removeAll();
					
					PassengerInformationView passengerInfoPage = new PassengerInformationView(new BillInformation(schedule.get(i)));       //connect to PassengerInformationView
					add(passengerInfoPage);
					
					revalidate();
					repaint();
					
					break;
				}
			}
		}
	}
	
}
