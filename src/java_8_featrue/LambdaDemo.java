package java_8_featrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaDemo {

	public static void main(String[] args) {
		/*********************************************/
		List<Integer> integers = Arrays.asList(3, 9, 7, 6, 10, 20);
		integers.forEach(i -> System.out.println(50 / i));
		/*********************************************/
		
		
		
		
		System.out.println("\n\n");
		
		integers.forEach(i -> {
		    try {
		        System.out.println(50 / i);
		        Thread.sleep(1000);
		    } catch (ArithmeticException e) {
		        System.err.println(
		          "Arithmetic Exception occured : " + e.getMessage());
		    } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		/*********************************************/
		
		/*********************************************/
		System.out.println("\n\n");
		
		integers.forEach(
		  consumerWrapper(
		    i -> System.out.println(50 / i), 
		    ArithmeticException.class));
		/*********************************************/
		
		
		integers.forEach(i -> {
			try {
				writeToFile(i);
			} catch (IOException e) {				
				e.printStackTrace();
			}
		});
		/*********************************************/

	}
	
	static void writeToFile(Integer integer) throws IOException {
	    // logic to write to file which throws IOException
	}
	
	static <T, E extends Exception> Consumer<T>
	  consumerWrapper(Consumer<T> consumer, Class<E> clazz) {
	  
	    return i -> {
	        try {
	            consumer.accept(i);
	        } catch (Exception ex) {
	            try {
	                E exCast = clazz.cast(ex);
	                System.err.println("Exception occured : " + exCast.getMessage());
	            } catch (ClassCastException ccEx) {
	                throw ex;
	            }
	        }
	    };
	}

}
