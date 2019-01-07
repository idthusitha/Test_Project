package thread;

public class ThreadWaitDemo {

	public static void main(String[] args) {

		Demo a = new Demo();
		a.start();
		DemoB b = new DemoB();
		b.start();

		a.interrupt();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		b.interrupt();
	}

}

class Demo extends Thread {
	public void run() {
		try {
			System.out.println("Demo1");
			Thread.sleep(5000);
			System.out.println("Demo2");
		} catch (InterruptedException e) {
			System.out.println("Demo "+e.getMessage());
			// e.printStackTrace();
		}
	}
}

class DemoB extends Thread {
	public void run() {
		try {
			System.out.println("DemoB1");
			synchronized (this) {
				this.wait(5000);
			}
			System.out.println("DemoB2");
		} catch (InterruptedException e) {
			//e.printStackTrace();
			System.out.println("DemoB "+e.getMessage());
		}
	}
}
