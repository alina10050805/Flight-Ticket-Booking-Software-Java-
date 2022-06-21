/**
 * class User
 * @author Xiangyi Lin
 * ticket booking software
 */
import java.util.ArrayList;
import java.util.Arrays;

public class User 
{
	private static final int PASSENGER_INFO = 8;       //how many index the passenger info have
	
	private static String userName = "";
	private static String password = "";
	private static String firstName ="";
	private static String lastName = "";
	private static String email = "";
	private static ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	private static boolean login = false;
	
	/**
	 * user log in
	 * read the information of the user and save to the class
	 * @param wholeInfo of user
	 */
	public static void userLogIn(String[] wholeInfo)
	{
		userName = wholeInfo[0];
		password = wholeInfo[1];
		firstName = wholeInfo[2];
		lastName = wholeInfo[3];
		email = wholeInfo[4];
		
		for(int i=5; i<wholeInfo.length; i+=PASSENGER_INFO)
		{
			if(wholeInfo.length <= i+PASSENGER_INFO-1)
			{
				System.out.println("Passenger infomation error.");
				break;
			}
			
			//****************************passenger*****************************
			//passengers.add(Arrays.copyOfRange(wholeInfo, i, i+PASSENGER_INFO));
		}
		
		login = true;
	}
	
	/**
	 * user log out
	 * clear the information in the class
	 */
	public static void userLogOut()
	{
		userName = "";
		password = "";
		firstName ="";
		lastName = "";
		email = "";
		passengers = new ArrayList<Passenger>();
		login = false;
	}
}
