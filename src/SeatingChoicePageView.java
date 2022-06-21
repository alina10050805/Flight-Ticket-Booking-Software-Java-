/**
 * class SeatingChoicePageView
 * show Seating Choice Page by GUI
 * @author Xiangyi Lin
 * ticket booking software
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeatingChoicePageView extends JPanel
{
	private ArrayList<Seat[]> seatArr;
	private FlightSeats seats;
	private JButton nextButton;
	private JLabel message;
	private Seat choiceSeat;
	//private boolean clickNext;
	private BillInformation flightBill;
	private int choiceRow;
	private int choiceCol;
	
	public SeatingChoicePageView(BillInformation bill)
	{
		flightBill = bill;
		
		try 
		{
			seats = new FlightSeats(FlightSeatFlieReader.findFlightSeat("J035"));
			
			seatArr = seats.getSeats();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.print("File can not open. SeatingChoicePageView");
		}

		
		//repaint();
		
//		clickNext = false;
		JPanel buttonPanel = createButton();
		choiceSeat = new Seat();
		MouseListener mouseSeatListener = new MouseSeatListener();
		
		addMouseListener(mouseSeatListener);
		add(buttonPanel, BorderLayout.EAST);
		
		//repaint();
	}
	
	public JPanel createButton()
	{
		message = new JLabel("");
		nextButton = new JButton("next");
		
		ActionListener nextListener = new NextListener();
		nextButton.addActionListener(nextListener);
		
		JPanel panel = new JPanel();
		panel.add(message);
		panel.add(nextButton);
		return panel;
	}
	
	class MouseSeatListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			int clickX = e.getX();
			int clickY = e.getY();
			
			if(clickX>=seats.getXInitial() && clickX<=seats.getXMax() && clickY<=seats.geYMax() && clickY>=seats.getYInitial())
			{
				for(int i=0; i<seatArr.size(); i++)
				{
					for(int j=0; j<seatArr.get(i).length; j++)
					{
						int tempX = seatArr.get(i)[j].getX();
						int tempY = seatArr.get(i)[j].getY();
						int tempWidth = seatArr.get(i)[j].getWidth();
						
						if(!seatArr.get(i)[j].getSold() && clickX>=tempX && clickX<=tempX+tempWidth 
								&& clickY>=tempY && clickY<=tempY+tempWidth)
						{
							choiceSeat = new Seat(seatArr.get(i)[j].getPrice(), Color.ORANGE, tempX, tempY, tempWidth);
					
							choiceRow = i;
							choiceCol = j;
							
							repaint();
							return;
						}
					}
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class NextListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
//			if(checkValue())
//			{
//				//*************************next************************
//			}
			
			//clickNext = true;
			removeAll();
			
			seatArr = new ArrayList<Seat[]>();
			choiceSeat = new Seat();
			
			repaint();
			
			removeAll();
			
			
			flightBill.setSeat(choiceRow, choiceCol, choiceSeat.getPrice());
			PaymentPageView paymentPage = new PaymentPageView(flightBill);              //connect to PaymentPageView
			
			
			add(paymentPage);
			
			revalidate();
			repaint();

		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		for(int i=0; i<seatArr.size(); i++)
		{
			for(int j=0; j<seatArr.get(i).length; j++)
				seatArr.get(i)[j].drawSeat(g);
		}
		
		choiceSeat.drawSeat(g);
	}
	
	
}
