package db_utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ReportMultiligualGen {

	/** Start Database Connection Properties */
	private static String HOST_NAME = "192.168.1.237";
	private static String PORT = "5432";
	private static String DB_NAME = "PGDEV";
	private static String SCHEMA_NAME = "rezbase_v3_reports";
	private static String USER_NAME = "postgres";
	private static String PASSWORD = "";
	private static String CONNECTION_STRING = "jdbc:postgresql://" + HOST_NAME + ":" + PORT + "/" + DB_NAME + "";

	static HashMap<String, String> map = new HashMap<>();

	public static void main(String[] args) {
		loadMultiligualData();

		/** Loading database connection */
		try {
			DBUtility dbUtility = DBUtility.getInstance(CONNECTION_STRING, USER_NAME, PASSWORD);
			Connection con = dbUtility.getConnection();

			//missing lable
			printMissingLabel(con);
			
			
			//select * from rezbase_v3_reports.report_labels_mapping
			String lan_id = "109";
			int id = 674;
			String query = "select report_id,report_labels_id,label_key from rezbase_v3_reports.report_labels_mapping lm, rezbase_v3_reports.report_labels l where l.id = lm.report_labels_id ";	
			Statement ste = con.createStatement();

			ResultSet rst = ste.executeQuery(query);
			while (rst.next()) {
				String report_id = rst.getString(1);
				String report_labels_id = rst.getString(2);
				String label_key = rst.getString(3);

				if (map.containsKey(label_key)) {
					String insertQuery = "insert into rezbase_v3_reports.report_labels_mapping (id,language_id,report_id,report_labels_id,label_value,status) values ("+id+",'"+lan_id+"','"+report_id+"','"+report_labels_id+"','"+map.get(label_key)+"','Y');";
					
					System.out.println(insertQuery);
					id++;
				
				}else{
					System.out.println("--------");
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void printMissingLabel(Connection con) throws SQLException {
		String query = "select id,label_key,status,(select label_value from rezbase_v3_reports.report_labels_mapping where report_labels_id = l.id limit 1) as label_value from rezbase_v3_reports.report_labels l";
		Statement ste = con.createStatement();

		ResultSet rst = ste.executeQuery(query);

		System.out.println("\n\nMissing label.............");
		while (rst.next()) {
			String id = rst.getString(1);
			String label_key = rst.getString(2);
			String status = rst.getString(3);
			String label_value = rst.getString(4);

			if (!map.containsKey(label_key)) {
				System.out.println(id+","+label_key+","+label_value+",");
			}
		}
	}

	private static void loadMultiligualData() {
		String csvFile = "/rezsystem/workspace_java8/Test/src/data/Report_label_12062017.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] label = line.split(cvsSplitBy);

				map.put(label[1], label[3]);

				System.out.println(label[0] + "," + label[1] + "," + label[2]+ "," + label[3]);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
