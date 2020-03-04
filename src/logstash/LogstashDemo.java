package logstash;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

public class LogstashDemo {

	public static void main(String[] args) {

		try {
			System.out.println("aaaa");

			Socket socket = new Socket("172.16.16.212", 5400);
			DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(readFile());

			/***************************/
			System.out.println(json.toString());

//			String st = json.toString().replace("\"", "\\\"");
//			String fileName = "/var/log/rezg/app/activity/2020-03-02/tixrez/ESB_Response_2.json";
//
//			String jsonString = "{\"message\": \"" + st + "\", \"file_name\": \"" + fileName.replace("/var/", "") + "\", \"env\": \"cib\" }";
//
//			os.writeBytes(jsonString);
//			os.flush();
//			socket.close();
			
			
			/*******************/
			String fileName = "/var/log/rezg/app/activity/2020-03-02/tixrez/ESB_Response_3.json";
			
			String logTextPattern = "";
			logTextPattern += "@@ENV_START@@" + "cib";
			logTextPattern += "@@URL_START@@" + fileName.replace("/var/", "");
			logTextPattern += "@@CONTENT_START@@" + json.toString() +"@@CONTENT_END@@";
			
			os.writeBytes(logTextPattern);
			os.flush();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String readFile() throws FileNotFoundException, IOException {
		String everything = "";
		try (BufferedReader br = new BufferedReader(new FileReader("/rezsystem/workspace_ride/Test_Project/src/logstash/test.json"))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			everything = sb.toString();
		}
		return everything;
	}

}
