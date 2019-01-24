package java_9_featrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryResourceDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	void testARM_Before_Java9() throws IOException {
		BufferedReader reader1 = new BufferedReader(new FileReader("journaldev.txt"));
		try (BufferedReader reader2 = reader1) {
			System.out.println(reader2.readLine());
		}
	}

	void testARM_Java9() throws IOException {
		BufferedReader reader1 = new BufferedReader(new FileReader("journaldev.txt"));
		try (reader1) {
			System.out.println(reader1.readLine());
		}
	}

}
