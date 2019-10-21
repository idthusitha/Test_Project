package text;

public class SpecilaCharDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String test = "Al Seba-Street,  P.O. Box: 215373,1 32  DUBAI DUBAI";
		String returnValue = test.replaceAll("[^a-zA-Z0-9-_@,:. ]", "");

		System.out.println(returnValue);

	} 

}
