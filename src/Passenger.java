/**
 * class Passenger
 * @author Xiangyi Lin
 * ticket booking software
 */

/**
 * save the passenger information
 */
public class Passenger 
{
	private String firstName;
	private String lastName;
	private String birthDate;
	private String gender;
	private String address;
	private String zipCode;
	private String email;
	private String phone;
	
	//constructor
	public Passenger(String par_firstName, String par_lastName, String par_birth, String par_gender,
						String par_address, String par_zip, String par_email, String par_phone)
	{
		firstName = par_firstName;
		lastName = par_lastName;
		birthDate = par_birth;
		gender = par_gender;
		address = par_address;
		zipCode = par_zip;
		email = par_email;
		phone = par_phone;
	}
	
	/**
	 * set the address and zipCode to the class
	 * @param par_address
	 * @param par_zip
	 */
	public void setAddress(String par_address, String par_zip)
	{
		address = par_address;
		zipCode = par_zip;
	}
	
	/**
	 * set the email to the class
	 * @param par_email
	 */
	public void setEmail(String par_email)
	{
		email = par_email;
	}
	
	/**
	 * set the phone number to the class
	 * @param par_phone
	 */
	public void setPhone(String par_phone)
	{
		phone = par_phone;
	}
	
	/**
	 * to string method
	 * get the whole string value of the class
	 * @return string of class
	 */
	public String toString()
	{
		return firstName + " " + lastName + "; " + birthDate + "; " + gender + "; " 
				+ address + ", " + zipCode + "; " + email + "; " + phone;
	}
}
