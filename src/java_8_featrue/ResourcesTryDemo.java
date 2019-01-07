package java_8_featrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResourcesTryDemo {

	//from java 1.7
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try (ExceptionTest test = new ExceptionTest()) {

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("test");
		}

	}

	static void readFirstLineFromFileWithFinallyBlock(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			br.readLine();
		} finally {
			if (br != null)
				br.close();
		}
		
		
		try(BufferedReader brTemp = new BufferedReader(new FileReader(path))) {
			brTemp.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}

class ExceptionTest implements AutoCloseable {

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

}
