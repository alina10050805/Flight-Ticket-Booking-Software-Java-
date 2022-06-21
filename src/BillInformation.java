/**
 * class BillInformation
 * @author Xiangyi Lin
 * ticket booking software
 */

public class BillInformation 
{
	private String[] flightInfo;
	private Passenger passenger;
	private int seatRow;
	private int seatCol;
	private int seatPrice;
	
	public BillInformation()
	{
		//constructor
	}
	
	//constructor
	public BillInformation(String[] par_flightInfo)
	{
		flightInfo = par_flightInfo;
	}
	
	/**
	 * set the passenger to the class
	 * @param par_passenger
	 */
	public void setPassenger(Passenger par_passenger)
	{
		passenger = par_passenger;
	}
	
	/**
	 * set the seat information to the class
	 * @param row
	 * @param col
	 * @param price
	 */
	public void setSeat(int row, int col, int price)
	{
		seatRow = row;
		seatCol = col;
		seatPrice = price;
	}
	
	/**
	 * get the whole flight information
	 * @return array of string of flightInfo
	 */
	public String[] getFlightInfo()
	{
		return flightInfo;
	}
	
	/**
	 * get the passenger
	 * @return passenger
	 */
	public Passenger getPassenger()
	{
		return passenger;
	}
	
	/**
	 * get the seat position of row and column
	 * @return string of position
	 */
	public String seatPositionString()
	{
		return "Row: " + seatRow +", Col: " + seatCol;
	}
	
	/**
	 * get the price of bill
	 * @return
	 */
	public int getPrice()
	{
		return seatPrice + Integer.parseInt(flightInfo[8]);
	}
}
