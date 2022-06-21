/**
 * class FlightScheduleReader
 * @author Xiangyi Lin
 * ticket booking software
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * read the CVS file of flight Schedule information
 */
public class FlightScheduleReader 
{
	private final static String FLIGHT_FILE_NAME = "FlightSchedule.csv";  //constant flight schedule file name
	private final static File FLIGHT_SCHEDULE_FILE = new File(FLIGHT_FILE_NAME);
	
	private static ArrayList<String[]> flightInfo = new ArrayList<String[]>();
	
	/**
	 * read the flight schedule file and add the information to the flightInfo
	 * @throws FileNotFoundException
	 */
	public static void readFlightFile() throws FileNotFoundException
	{
		flightInfo = new ArrayList<String[]>();
		Scanner in = new Scanner(FLIGHT_SCHEDULE_FILE);
		
		while(in.hasNextLine())
		{
			String line = in.nextLine();
			String[] info = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			
			flightInfo.add(info);
		}

		in.close();
	}
	
	/**
	 * search the flight that follow these input information
	 * @param date of flight
	 * @param depart from 
	 * @param depart to
	 * @param number of passengers
	 * @return the array list of flight result
	 */
	public static ArrayList<String[]> searchByInfo(String date, String from, String to, int passengers)
	{
		ArrayList<String[]> result = new ArrayList<String[]>();
		
		for(int i=0; i<flightInfo.size(); i++)
		{
			if(from.toUpperCase().compareTo(flightInfo.get(i)[1].toUpperCase())==0 
					&& to.toUpperCase().compareTo(flightInfo.get(i)[4].toUpperCase())==0
					&& date.compareTo(flightInfo.get(i)[2])==0
					&& passengers <= Integer.parseInt(flightInfo.get(i)[9]))
			{
				result.add(flightInfo.get(i));
			}
		}
		
		return result;
	}
	
	/**
	 * sort base on the price
	 * @param flightSchedule that need sort
	 * @return a array list after sort form high price to low price
	 */
	public static ArrayList<String[]> sortByPrice(ArrayList<String[]> flightSchedule)
	{
		ArrayList<String[]> sortResult = sortByIndexInStringArray(flightSchedule, 8);
		
		return sortResult;
	}

	/**
	 * sort base on the duration
	 * @param flightSchedule that need sort
	 * @return a array list after sort form long duration to short duration
	 */
	public static ArrayList<String[]> sortByDuration(ArrayList<String[]> flightSchedule)
	{
		ArrayList<String[]> sortResult = sortByIndexInStringArray(flightSchedule, 7);
		
		return sortResult;
	}

	/**
	 * sort base on the Departure time
	 * @param flightSchedule that need sort
	 * @return a array list after sort form late Departure time to early Departure time
	 */
	public static ArrayList<String[]> sortByDepartureTime(ArrayList<String[]> flightSchedule)
	{
		ArrayList<String[]> sortResult = sortByIndexInStringArray(flightSchedule, 3);
		
		return sortResult;
	}
	
	/**
	 * sort base on the Arrival time
	 * @param flightSchedule that need sort
	 * @return a array list after sort form late Arrival time to early Arrival time
	 */
	public static ArrayList<String[]> sortByArrivalTime(ArrayList<String[]> flightSchedule)
	{
		ArrayList<String[]> sortResult = sortByIndexInStringArray(flightSchedule, 6);
		
		return sortResult;
	}
	
	/**
	 * sort base on the value at index in the array
	 * @param arrayList that need sort
	 * @param index for check
	 * @return a array list after sort from maximum to minimum
	 */
	private static ArrayList<String[]> sortByIndexInStringArray(ArrayList<String[]> arrayList, int index)
	{
		ArrayList<String[]> temp = new ArrayList<String[]>(arrayList);
		ArrayList<String[]> sortResult = new ArrayList<String[]>();
		
		while(temp.size()>0)
		{
			String[] max = Arrays.copyOf(temp.get(0), temp.get(0).length);
			int maxNum = 0;
			
			for(int i=1; i<temp.size(); i++)
			{
				if(Double.parseDouble(temp.get(i)[index]) > Double.parseDouble(max[index]))
				{
					max = Arrays.copyOf(temp.get(i), temp.get(i).length);
					maxNum = i;
				}
			}
			
			temp.remove(maxNum);
			sortResult.add(max);
		}
		
		return sortResult;
	}
	
	/**
	 * get the string of each flight information
	 * @param arrayData
	 * @return
	 */
	public static String toString(String[] arrayData)
	{
		String temp = "";
		
		for(int i=0; i<arrayData.length; i++)
		{
			if(i==3 || i==6 || i==7)
			{
				double time = Double.parseDouble(arrayData[i]);
				int hour = (int) (time);
				int minues = (int) ((time*100)%100);
				
				if(hour>=0 && hour <10)
					temp += "0" + hour;
				else if(hour>=10 && hour<24)
					temp += hour;
				else
					temp += "WrongHour";
				
				temp += ":";
				
				if(minues>=0 && minues<10)
					temp += "0" + minues;
				else if(minues>=10 && minues<60)
					temp += minues;
				else
					temp += "WrongMinues";
				
				temp += "  ";
			}
			else if(i==8)
			{
				temp += "$" + arrayData[i] + "  ";
			}
			else
			{
				temp += arrayData[i] + "  ";
			}
		}
		
		return temp;
	}
}
