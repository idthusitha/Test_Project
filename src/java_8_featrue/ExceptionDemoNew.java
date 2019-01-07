package java_8_featrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionDemoNew {

	public static void main(String[] args) {

		// Try.catchExceptions(FileNotFoundException.class,IOException.class)
		// .init(()->new BufferedReader(new FileReader("file.txt")))
		// .tryWithResources(this::read);
		
		Try<Integer> just = Try.success(10);
		
	}
	
    public native Try<String,IOException> readLine();
    
    
	
	private String read(BufferedReader br) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		String everything = sb.toString();
		return everything;
	}

}
