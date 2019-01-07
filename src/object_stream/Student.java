package object_stream;

import java.io.Serializable;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int id;
	String name;
	transient int age = 33;// Now it will not be serialized

	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
