/**
 * class FlightSeatFlieReader
 * @author Xiangyi Lin, Chia-Yun Chen
 * ticket booking software
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * read the flight seat file 
 */
public class FlightSeatFlieReader
{
	private final static String FLIGHT_SEAT_FILE_NAME = "FlightSeat.csv";  //constant flight seat file name
	private final static File FLIGHT_SEAT_FILE = new File(FLIGHT_SEAT_FILE_NAME);

	/**
	 * read the file save the value for the seats and get the array list of seat value
	 * @param flightNumber
	 * @return 2d array list of seat value
	 * @throws FileNotFoundException
	 */
	public static ArrayList<ArrayList<Integer>> findFlightSeat(String flightNumber) throws FileNotFoundException
	{
		ArrayList<ArrayList<Integer>> seatInfo = new ArrayList<ArrayList<Integer>>();
		Scanner in = new Scanner(FLIGHT_SEAT_FILE);
		
		while(in.hasNextLine())
		{
			String line = in.nextLine();
			String[] info = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			
			if(info[0].compareTo(flightNumber)==0)
			{
				ArrayList<Integer> temp = new ArrayList<Integer>();
				
				for(int i=1; i<info.length; i++)
				{
					if(Integer.parseInt(info[i])==0)
					{
						seatInfo.add(temp);
						temp = new ArrayList<Integer>();
					}
					else
					{
						temp.add(Integer.parseInt(info[i]));
					}
				}
				
				break;
			}
		}

		in.close();
		return seatInfo;
	}
}
