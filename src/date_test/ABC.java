package date_test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ABC {
	public static void main(String ar[]) {

		String dailyDate = "2018-03-29";

		Date date = new Date("" + (isNumeric(dailyDate) ? Long.parseLong(dailyDate) : dailyDate));

		String dailyDateTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		String dailyDateTimeStampArr[] = dailyDateTimeStamp.split(" ");
		String returnDate = dailyDateTimeStampArr[0] + "T" + dailyDateTimeStampArr[1] + "Z";

		System.out.println(returnDate);

	}  

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
