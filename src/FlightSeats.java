/**
 * class FlightSeats
 * @author Xiangyi Lin
 * ticket booking software
 */

import java.util.ArrayList;

public class FlightSeats
{
	private final int AIRPLANE_WIDTH = 300;
	private final int AIRPLANE_AISLE = 50;
	private final int X_INITIAL = 100;
	private final int Y_INITIAL = 50;
	
	private ArrayList<Seat[]> seats;
	private int airplaneHight;
	
	//constructor
	public FlightSeats(ArrayList<ArrayList<Integer>> seatInfo)
	{
		seats = new ArrayList<Seat[]>();
		int price;
		boolean sold;
		int x = X_INITIAL;
		int y = Y_INITIAL;
		
		for(int index=0; index<seatInfo.size(); index++)
		{
			int col = seatInfo.get(index).size();
			int mid = col/2;
			int seatWidth = (AIRPLANE_WIDTH - AIRPLANE_AISLE) / col;
			
			Seat[] temp = new Seat[col];
			for(int j=0; j<col; j++)
			{
				if(seatInfo.get(index).get(j)<0)
					sold = true;
				else
					sold = false;
				
				price = Math.abs(seatInfo.get(index).get(j));
				
				temp[j] = new Seat(price, sold, x, y, seatWidth);
				
				x += seatWidth;
				if(j==mid-1)
					x += AIRPLANE_AISLE;
			}
			
			x = X_INITIAL;
			y += seatWidth;
			
			if(index+1<seatInfo.size() && col!=seatInfo.get(index+1).size())
				y += 20;
			seats.add(temp);
			airplaneHight = y;
		}
	}
	
	/**
	 * get the array list of seats in the class
	 * @return array list of seats
	 */
	public ArrayList<Seat[]> getSeats()
	{
		return seats;
	}
	
	/**
	 * get the maximum x of the flight seat
	 * @return the integer of value
	 */
	public int getXMax()
	{
		return AIRPLANE_WIDTH + X_INITIAL;
	}
	
	/**
	 * get the minimum x of the flight seat
	 * @return the integer of value
	 */
	public int getXInitial()
	{
		return X_INITIAL;
	}
	
	/**
	 * get the maximum y of the flight seat
	 * @return the integer of value
	 */
	public int geYMax()
	{
		return airplaneHight + Y_INITIAL;
	}
	
	/**
	 * get the minimum y of the flight seat
	 * @return the integer of value
	 */
	public int getYInitial()
	{
		return Y_INITIAL;
	}
}
