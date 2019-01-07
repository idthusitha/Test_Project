package iq_program;

import java.util.ArrayList;

public class HashCodeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HashCodeDemo().method();
		System.out.println();
		new HashCodeDemo().method();
		System.out.println();
		new HashCodeDemo().method();
		System.out.println();
		new HashCodeDemo().method();

	}
	public void method(){

		ArrayList list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		
		list.stream().forEach(i ->{
			//System.out.println(i);
			System.out.println(this.hashCode()+"  "+this.toString());
		});
	}

}
