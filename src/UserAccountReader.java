/**
 * class UserAccountReader
 * read the user account information file
 * @author Xiangyi Lin, Chia-Yun Chen
 * ticket booking software
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * the read file should be CSV file
 */
public class UserAccountReader 
{
	private final static String ACCOUNT_FILE_NAME = "UserAccount.csv";     //constant user account file name
	private final static File USER_ACCOUNT_FILE = new File(ACCOUNT_FILE_NAME);
	
	/**
	 * find the password that userName match
	 * @param string of userName
	 * @return string of password, return string of -1 for NOT userName found
	 * @throws FileNotFoundException
	 */
	public static String findPassword(String userName) throws FileNotFoundException
	{
		Scanner in = new Scanner(USER_ACCOUNT_FILE);
		
		while(in.hasNextLine())
		{
			String line = in.nextLine();
			String[] info = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			
			if(info[0].toUpperCase().compareTo(userName.toUpperCase())==0)
				return info[1];
		}

		in.close();
		return "-1";
	}
	
	/**
	 * check if the user name is appear in the file
	 * @param string of userName
	 * @return true if userName is appeared, false if not
	 * @throws FileNotFoundException
	 */
	public static boolean appearUserName(String userName) throws FileNotFoundException
	{
		Scanner in = new Scanner(USER_ACCOUNT_FILE);
		
		while(in.hasNextLine())
		{
			String line = in.nextLine();
			String[] info = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			
			if(info[0].toUpperCase().compareTo(userName.toUpperCase())==0)
				return true;
		}

		in.close();
		return false;
	}
	
	/**
	 * add a new user account information to the end of CVS file
	 * the information to the file is follow: ***user name, password, first name, last name, email***
	 * @param firstName of user
	 * @param lastName of user
	 * @param userName of user
	 * @param password of user
	 * @param email of user
	 * @throws IOException
	 */
	public static void addLine(String firstName, String lastName, String userName, String password, String email) throws IOException
	{
		FileWriter out = new FileWriter(ACCOUNT_FILE_NAME, true);
		out.write(userName + "," + password + "," + firstName + "," + lastName + "," + email + "\n");
		out.close();
	}
	
	/**
	 * get all information of the user which user name is userName
	 * @param userName of user
	 * @return the array of information, return string -1 in the array when the user Name is Not Found
	 * @throws FileNotFoundException
	 */
	public static String[] getInformation(String userName) throws FileNotFoundException
	{
		Scanner in = new Scanner(USER_ACCOUNT_FILE);
		
		while(in.hasNextLine())
		{
			String line = in.nextLine();
			String[] info = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			
			if(info[0].toUpperCase().compareTo(userName.toUpperCase())==0)
				return info;
		}

		in.close();
		return new String[] {"-1"};
	}
}
