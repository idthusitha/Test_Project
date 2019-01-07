import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileRead {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new FileReader("ride_jetty_log"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		}catch(Exception e){
			e.printStackTrace();			
		} finally {
		    br.close();
		}

	}

}
