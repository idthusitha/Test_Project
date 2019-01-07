package iq_program;


public class StaticDemo {

	static {
		System.out.println("testing ....................");
	}

	final int speedlimit;// blank final variable

	StaticDemo() {
		speedlimit = 70;
		System.out.println(speedlimit);
	}

}
