package iq_program;

import java.util.Arrays;

public class SearchDemo {

	public static void main(String[] args) {

		int data[] = { 85,65,95,76,10, 20, 3, 1, 2, 5, 8, 12, 9, 55, 44, 566, 45, 89, 56, 87, 265 };
		int search = 10;

		lenarSearch(data, search);

		bianiarySearch(data, search);

	}

	private static void bianiarySearch(int[] data, int search) {
		Arrays.sort(data);
		
		

		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}

	}

	private static void lenarSearch(int[] data, int search) {
		for (int i = 0; i < data.length; i++) {
			System.out.println(i);
			if (data[i] == search) {
				System.out.println("Search value found:" + data[i]);
				break;
			}
		}
	}

}
