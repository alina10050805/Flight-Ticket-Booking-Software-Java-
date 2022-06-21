/**
 * class ComfirmationPageView
 * show the Comfirmation Page by GUI
 * @author Xiangyi Lin
 * ticket booking software
 */

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComfirmationPageView extends JPanel
{
	private BillInformation billInfo;
	
	public ComfirmationPageView(BillInformation bill)
	{
		billInfo = bill;
		
		JPanel comfirmationText = crateComfirmationText();
		
		add(comfirmationText);
	}
	
	public JPanel crateComfirmationText()
	{
		String[] flightInfo = billInfo.getFlightInfo();
		JLabel comfirmation = new JLabel("Comfirmation");
		JLabel message = new JLabel("Your flight has successfuly book!");
		JLabel flight = new JLabel("Flight Number: " + flightInfo[0]);
		JLabel departPlace = new JLabel("Depart From: " + flightInfo[1]);
		JLabel departTime = new JLabel("Depart Time: " + flightInfo[2] + " " + flightInfo[3]);
		JLabel arrivalPlace = new JLabel("Arrival Place: " + flightInfo[4]);
		JLabel arrivalTime = new JLabel("Arrival Time: " + flightInfo[5] + " " + flightInfo[6]);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 1));
		panel.add(comfirmation);
		panel.add(message);
		panel.add(flight);
		panel.add(departPlace);
		panel.add(departTime);
		panel.add(arrivalPlace);
		panel.add(arrivalTime);
		return panel;
	}
}
