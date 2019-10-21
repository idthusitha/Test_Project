package java_7_featrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Other_Demo {

	public static void main(String[] args) {
		/*************************************/
		// Prior JDK 7
		Map<String, List<String>> employeeRecords = new HashMap<String, List<String>>();
		List<Integer> primes = new ArrayList<Integer>();

		// In JDK 7
		Map<String, List<String>> employeeRecords2 = new HashMap<>();
		List<Integer> primes2 = new ArrayList<>();
		/*************************************/

		/*************************************/
		/**
		 * Before JDK 7, only integral types can be used as selector for switch-case statement. In JDK 7, you can use a String object as the selector. Read more:
		 * https://javarevisited.blogspot.com/2014/04/10-jdk-7-features-to-revisit-before-you.html#ixzz62zQSttaQ
		 */
		String state = "NEW";

		switch (state) {
		case "NEW":
			System.out.println("Order is in NEW state");
			break;
		case "CANCELED":
			System.out.println("Order is Cancelled");
			break;
		case "REPLACE":
			System.out.println("Order is replaced successfully");
			break;
		case "FILLED":
			System.out.println("Order is filled");
			break;
		default:
			System.out.println("Invalid");

		}
		/*************************************/

		/*************************************/
		int billion = 1_000_000_000; // 10^9
		long creditCardNumber = 1234_4567_8901_2345L; // 16 digit number
		long ssn = 777_99_8888L;
		double pi = 3.1415_9265;
		float pif = 3.14_15_92_65f;

		// double pi=3._1415_9265; // underscore just after decimal point
		// long creditcardNum2=1234_4567_8901_2345_ L; // underscore at the end of number
		// long ssn1=_777_99_8888L; // undersocre at the beginning

		/*************************************/
		
		
		
		/*************************************/

		try {

			System.out.println("aaaaaaaaaa");
			throw new RuntimeException();

		} catch (Exception ex) {
			ex.printStackTrace();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		
		try {

			System.out.println("aaaaaaaaaa");
			throw new RuntimeException();

		} catch (FileNotFoundException |IOException ex) {
			ex.printStackTrace();
		} 
		/*************************************/

	}

}
