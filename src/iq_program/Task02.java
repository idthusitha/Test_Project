package iq_program;

/**
 * 
 * @author thusitha
 *
 */
public class Task02 {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 1, 5 };
		int sumNoneDuplicate = 0;

		sumNoneDuplicate = getSumOfNoneDupplicateNumber(arr);
		System.out.println("\nThe sum of every non-duplicate number: " + sumNoneDuplicate);

	}

	/**
	 * 
	 * @param arr
	 * @return
	 */
	private static int getSumOfNoneDupplicateNumber(int[] arr) {
		int total = 0;

		if (arr != null && arr.length <= 100) {
			char[] i = new char[100];
			for (int k = 0; k < arr.length; k++) {
				if (i[arr[k]] != (char) arr[k]) {
					i[arr[k]] = (char) arr[k];
					total += arr[k];
				} else {
					System.out.println("duplicate >> " + arr[k]);
				}
			}
		} else {
			throw new IllegalArgumentException("The arr size is grater than 100 or empty");
		}
		return total;
	}

}
