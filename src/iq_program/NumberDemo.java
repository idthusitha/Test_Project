package iq_program;

import java.util.Arrays;
import java.util.HashSet;

public class NumberDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int numbers[] = new int[] { 10, 20, 30, 50, 10, 30 };

		
		
		HashSet<String> set = new HashSet<String>();
		
		Arrays.asList(numbers).forEach(val ->{
			set.add(""+val);
			
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// for(int i : numbers){
		// System.out.println(i);
		// }

		int numbersTemp[] = new int[] {};
		String temp = "#";
		for (int i = 0; i < numbers.length; i++) {
			if (temp.indexOf(("#" + numbers[i] + "#")) != -1) {
				System.out.println(numbers[i]);
			}
			temp += numbers[i] + "#";
		}

		
		
		
		
		
		
		
		
		
		/**********************/
		char[] i = new char[100];
		int[] arr = { 1, 2, 3, 3, 4, 1, 3, 8, 9, 9, 23, 55, 12, 56, 67, 11, 22, 46, 66, 46, 6, 97, 47, 2, 4 };

		for (int k = 0; k < arr.length; k++) {
			if (i[arr[k]] != (char) arr[k]) {
				i[arr[k]] = (char) arr[k];
			} else {
				System.out.println("duplicate >> " + arr[k]);
			}
		}
		
		
		
		
		
		
		Arrays.asList(i).forEach( val ->{
			System.out.println(val);
		});
		
		int aaa = 128;
		byte b = 127;
		
		
		

	}

}
