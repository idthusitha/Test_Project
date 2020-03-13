package logstash;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class LogstashDemo {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		directTCPCall();
		System.out.println(System.currentTimeMillis() - start);

		//start = System.currentTimeMillis();
		//asyTCPCall();
		System.out.println(System.currentTimeMillis() - start);

	}

	private static void directTCPCall() {
		try {
			System.out.println("aaaa");

			Socket socket = new Socket("172.16.16.212", 5400);
			//Socket socket = new Socket("logstash.mon.prod.ride.use1.aws.rezos.io", 6044);
			//Socket socket = new Socket("10.32.12.82", 6044);

			DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(readFile());

			/***************************/
			//System.out.println(json.toString());

			// String st = json.toString().replace("\"", "\\\"");
			// String fileName = "/var/log/rezg/app/activity/2020-03-02/tixrez/ESB_Response_2.json";
			//
			// String jsonString = "{\"message\": \"" + st + "\", \"file_name\": \"" + fileName.replace("/var/", "") + "\", \"env\": \"cib\" }";
			//
			// os.writeBytes(jsonString);
			// os.flush();
			// socket.close();

			/*******************/

			String fileName = "/var/log/rezg/app/activity/2020-03-02/tixrez/ESB_Response_9.json";

			String logTextPattern = "";
			logTextPattern += "@@FIELDS@@" +"{\"source\": \"ride_sup_logs\",\"env\": \"lmt\",\"type\": \"supp\",\"name\": \"wso2ei-common\",\"portal\": \"ride\"}";
			logTextPattern += "@@URL@@" + fileName;
			logTextPattern += "@@CONTENT_START@@" + json.toString().replace("\n", "") + "@@CONTENT_END@@";
			
			System.out.println(logTextPattern);
			//@@FIELDS@@{"source": "ride_sup_logs","env": "lmt","type": "supp","name": "wso2ei-common","portal": "ride"}@@URL@@/var/log/rezg/app/activity/2020-03-02/tixrez/ESB_Response_9.json@@CONTENT_START@@{"request":{"supplierId":"hbactivity","portal":"tixrez","checkIn":"2020-03-19T00:00:00","checkOut":"2020-03-20T00:00:00","currencyCode":"USD","RequestID":"101010","traceId":"11594","markup":{"value":[0.12,0.12,0.12,0.12]},"language":"en","noOfNights":3,"adultsNum":2,"childNum":0,"items":null,"country":"55","city":"3086"}}@@CONTENT_END@@
			//@@FIELDS@@%{GREEDYDATA:esb_fields}@@URL@@%{GREEDYDATA:esb_url}@@CCONTENT_START@@%{GREEDYDATA:esb_file_content}@@CONTENT_END@@
			
			//@@ENV@@%{GREEDYDATA:esb_env}@@SERVICE@@{GREEDYDATA:service}@@URL@@%{GREEDYDATA:esb_file_name}@@CONTENT_START@@%{GREEDYDATA:esb_file_content}@@CONTENT_END@@


			os.writeBytes(logTextPattern);
			os.flush();
			socket.close();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void asyTCPCall() {
		try {
			System.out.println("testtttt");
			AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
			InetSocketAddress hostAddress = new InetSocketAddress("172.16.16.212", 5400);
			//InetSocketAddress hostAddress = new InetSocketAddress("logstash.mon.prod.ride.use1.aws.rezos.io", 6044);
			Future<Void> future = client.connect(hostAddress);
			future.get();
			
			String fileName = "/var/log/rezg/app/activity/2020-03-12/tixrez/ESB_Response_3.json";
			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(readFile());
			
			String logTextPattern = "";
			logTextPattern += "@@FIELDS@@" +"{\"source\": \"ride_sup_logs\",\"env\": \"lmt\",\"type\": \"supp\",\"name\": \"wso2ei-common\",\"portal\": \"ride\"}";
			logTextPattern += "@@URL@@" + fileName;
			logTextPattern += "@@CONTENT_START@@" + json.toString().replace("\n", "") + "@@CONTENT_END@@";

			
			byte[] byteMsg = new String(logTextPattern).getBytes();
		    ByteBuffer buffer = ByteBuffer.wrap(byteMsg);
		    Future<Integer> writeResult = client.write(buffer);
		 
		  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String readFile() throws FileNotFoundException, IOException {
		String everything = "";
		try (BufferedReader br = new BufferedReader(new FileReader("/rezsystem/workspace_ride/Test_Project/src/logstash/test3.json"))) {
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
