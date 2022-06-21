/**
 * class GUIView
 * arrange all JPanel to JFrame
 * @author Xiangyi Lin
 * ticket booking software
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIView extends JFrame
{
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 600;

	JPanel panel;
	JPanel controlPanel;

	public GUIView()
	{
		panel = new JPanel();
	    controlPanel = new JPanel();
	    
	    
//	    FlowLayout layout = new FlowLayout(FlowLayout.LEFT,50,50);
//        setLayout(layout);
        
	    controlPanel = new LoginView();   
	    
	   // controlPanel = new PassengerInformationView(new BillInformation());       //connect to PassengerInformationView
	    
	    //controlPanel = new SeatingChoicePageView(new BillInformation());
	    
	    panel.add(controlPanel);
	    //paint(getGraphics());
	    
	    add(controlPanel);
	    
	    setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	public void note()       //***********************note**************
	{
		panel.remove(controlPanel);
		
//		controlPanel = new Login();
		
		panel.setLayout(new GridLayout(2, 1));
		panel.add(controlPanel);
		
		panel.revalidate();
		panel.repaint();
		
		add(panel);
	}
}
