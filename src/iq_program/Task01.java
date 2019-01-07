package iq_program;

/**
 * 
 * @author thusitha
 *
 */
public class Task01 {

	public static void main(String[] args) {
		int x = 13;
		System.out.println("\nThe number of steps :" + process(x));
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static int evenNumberProcess(int number) {
		return number / 2;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static int oddNumberProcess(int number) {
		return number * 3 + 1;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isEvenNumber(int number) {
		return number % 2 == 0;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static int process(int number) {
		int steupCount = 0;

		if (number > 0) {
			do {
				System.out.print(number + "-");
				number = isEvenNumber(number) ? evenNumberProcess(number) : oddNumberProcess(number);
				steupCount++;
			} while (number > 1);
		} else {
			throw new IllegalArgumentException("This number not a positive number");
		}
		return steupCount;
	}

}
