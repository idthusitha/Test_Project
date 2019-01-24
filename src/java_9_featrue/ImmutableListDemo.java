package java_9_featrue;

import java.util.List;

public class ImmutableListDemo {

	public static void main(String[] args) {

		String version = System.getProperty("java.version");

		System.out.println(version);

		List immutableList = List.of();
		System.out.println(immutableList);

		List immutableList2 = List.of("one", "two", "three");
		System.out.println(immutableList2);
	}

}
