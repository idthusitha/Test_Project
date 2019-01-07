package iq_program;

import java.io.*;
import java.util.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class AutoDemo implements AutoCloseable{

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}

public class DemoS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**1-100*/
		int i[] = {1,2,3,2,4,1};
		HashSet<String> s = new HashSet<String>();
		s.contains("a");
		
		String aa = "abc";
		String aaa = "abc";
		
		String bb = new String("abc");
		String bbb = new String("abc");
		
		System.out.println(aa==aaa);
		System.out.println(bb==bbb);
		System.out.println(bb.equals(bbb));
		
		System.out.println(bb.intern()==bbb.intern());

		System.out.println("aaaaaaaaaaaa");

		try {
			File f = new File("");
			f.createNewFile();

		} catch (RuntimeException | IOException e) {
			// TODO: handle exception
		}
		String temp = "aaaaaaaasdfdsgdtyertertebtertbertvcdgrthfrhrtryryry";
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(String t :temp.split("")){
			if(map.containsKey(t)){
				map.put(t, map.get(t)+1);
			}else{
				map.put(t, 1);
			}
		}
		System.out.println(map);
		
		
		HashMap<String,Integer> map2 = new HashMap<String,Integer>();		
		Arrays.asList(temp.split("")).parallelStream().forEach(t ->{
			if(map2.containsKey(t)){
				map2.put(t, map2.get(t)+1);
			}else{
				map2.put(t, 1);
			}
		});
		System.out.println(map2);
		

	}

	static {
		System.out.println("bbbbbbbbbbb");
	}

	public void metho() {
		BufferedReader reader = null;

		try {
			URL url = new URL("http://www.yoursimpledate.server/");
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = reader.readLine();
			SimpleDateFormat format = new SimpleDateFormat("MM/DD/YY");
			Date date = format.parse(line);
		} catch (MalformedURLException exception) {
			// handle passing in the wrong type of URL.
		} catch (IOException exception) {
			// handle I/O problems.
		} catch (ParseException exception) {
			// handle date parse problems.
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void testaa() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://www.yoursimpledate.server/").openStream()))) {
			String line = reader.readLine();
			SimpleDateFormat format = new SimpleDateFormat("MM/DD/YY");
			Date date = format.parse(line);
		
		} catch (ParseException | IOException exception) {
			// handle I/O problems.
		}
		
		try(Connection f = null){
			
		}catch(Exception e){
			
		}
		try(AutoDemo autoDemo= null){
			
		}catch(Exception e){}
	}

}
