package logstash;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.json.JSONObject;

import net.sf.json.JSONArray;

public class LogstashDemo {

	public static void main(String[] args) {

		try {
			System.out.println("aaaa");

			 Socket socket = new Socket("172.16.16.212", 5400);
			 DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			 
			 net.sf.json.JSONObject json = new net.sf.json.JSONObject();
			 json.accumulate("test", "abc");
			 System.out.println(json.toString());
			 
			 String jsonString = "{\"message\": \""+json.toString()+"\", \"file_name\": \"log/rezg/app/activity/2020-02-28/tixrez/tixrez_hbactivity_availability_ESB_Request_11594_2020-02-28T15-54-42-094+05:32.json\", \"env\": \"cib\" }";
			 
			 
			 
			 os.writeBytes(jsonString);
			 os.flush();
			 socket.close();	
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
