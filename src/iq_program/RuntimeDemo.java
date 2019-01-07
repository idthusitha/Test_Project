package iq_program;


public class RuntimeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runtime r = Runtime.getRuntime();
		System.out.println("Total Memory: " + r.totalMemory());
		System.out.println("Free Memory: " + r.freeMemory());

		for (int i = 0; i < 10000; i++) {
			new RuntimeDemo();
		}
		System.out.println("After creating 10000 instance, Free Memory: " + r.freeMemory());
		System.gc();
		System.out.println("After gc(), Free Memory: " + r.freeMemory());
		
		System.out.println(((r.totalMemory()-r.freeMemory())/r.totalMemory())*100.0);
		
		
		//Class.forName("RuntimeDemo");
		
		//Deque d = new Deque();
	}

}
