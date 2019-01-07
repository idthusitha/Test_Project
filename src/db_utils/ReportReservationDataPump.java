package db_utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class ReportReservationDataPump {

	/** Start Database Connection Properties */
	private static String HOST_NAME = "192.168.1.237";
	private static String PORT = "5432";
	private static String DB_NAME = "PGDEV";
	private static String SCHEMA_NAME = "rezbase_v3_reports";
	private static String USER_NAME = "postgres";
	private static String PASSWORD = "";
	private static String CONNECTION_STRING = "jdbc:postgresql://" + HOST_NAME + ":" + PORT + "/" + DB_NAME + "";

	static HashMap<String, String> map = new HashMap<>();

	static ArrayList<String> hotel_reservation = new ArrayList<String>();
	static ArrayList<String> hotel_search = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		DBUtility dbUtility = DBUtility.getInstance(CONNECTION_STRING, USER_NAME, PASSWORD);
		/** Loading database connection */
		Connection con = dbUtility.getConnection();
		try {
			ArrayList<String> list = null;
			// list = loadHotelData(con, "resconfirmsv", "rezbase_v3");
			// runQuery(con, list);

			//loadHotelCriteriaData(con, "resconfirmsv", "rezbase_v3");

			//runQuery(con, hotel_reservation);
			//runQuery(con, hotel_search);
			//
			list = loadHotelData(con, "reshoteldetailssv", "rezbase_v3");
			runQuery(con, list);

			list = loadHotelData(con, "resroomssv", "rezbase_v3");
			runQuery(con, list);

			list = loadHotelData(con, "resdailyratessv", "rezbase_v3");
			runQuery(con, list);

			// --select count(*) from rezbase_v3_reports.hotel_reservation

			// select count(*) from rezbase_v3_reports.hotel_reservation_criteria
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

	}

	private static ArrayList<String> loadHotelCriteriaData(Connection con, String tableName, String clientId) {
		ArrayList<String> list = new ArrayList<String>();
		String hashKeyMappingId = null;
		Vector<String> columnNames = new Vector<String>();

		try {
			String query = "select r.*,h.vendorid,h.hotelname,h.checkindate from " + clientId + "_hotels.resconfirmsv r, " + clientId + "_hotels.reshoteldetailssv h where r.id=h.resconfirmid order by r.id asc";
			Statement ste = con.createStatement();

			ResultSet rst = ste.executeQuery(query);

			if (rst != null) {
				/*********************************************/
				ResultSetMetaData columns = rst.getMetaData();
				int i = 0;
				while (i < columns.getColumnCount()) {
					i++;
					if (!"vendorid".equals(columns.getColumnName(i)) || !"checkindate".equals(columns.getColumnName(i)) || !"hotelname".equals(columns.getColumnName(i))) {
						columnNames.add(columns.getColumnName(i));
					}
				}
				/*********************************************/

				int rowCount = 1;
				while (rst.next()) {
					String row = "";
					String idV = "";
					for (i = 0; i < columnNames.size(); i++) {
						String value = rst.getString(columnNames.get(i));

						idV = rst.getString("id");

						if ("resconfirmsv".equals(tableName)) {
							hashKeyMappingId = null;
						} else if ("reshoteldetailssv".equals(tableName)) {
							hashKeyMappingId = rst.getString("resconfirmid");
						} else if ("resroomssv".equals(tableName)) {
							hashKeyMappingId = rst.getString("reshoteldetailssvid");
						} else if ("resdailyratessv".equals(tableName)) {
							hashKeyMappingId = rst.getString("resroomssvid");
						}
						row += columnNames.get(i) + "=" + (value != null ? (value.replaceAll("'", "`").replaceAll(",", " ")) : "") + ", ";
					}

					String insertStr = "insert into " + clientId + "_reports.hotel_reservation " + "(id,hash_key,table_name,column_data,hash_key_mapping)" + " values(" + rowCount + "," + idV + ",'" + tableName + "','" + row + "'," + hashKeyMappingId
							+ ");";

					hotel_reservation.add(insertStr);

					/*********************************************/

					String reservation_no = rst.getString("reservationno");
					String reservation_date = rst.getString("reservationdate");

					String reservation_status = rst.getString("resstatus");
					String reservation_channel = rst.getString("ressource");
					String reservation_type = "N".equals(rst.getString("packagebooking")) ? "Shopping" : "Package";
					String partner_type = rst.getString("partnertype");
					String selling_currency = rst.getString("currency");
					String user_code = rst.getString("usercode");
					String transaction_id = "123456";
					String client_id = clientId;

					String first_element_date = rst.getString("checkindate");
					String supplier = rst.getString("vendorid");
					String hotelName = rst.getString("hotelname").replaceAll("'", "`").replaceAll(",", " ");

					insertStr = "insert into " + clientId + "_reports.hotel_reservation_criteria "
							+ "(reservation_data_id,reservation_no,reservation_date,first_element_date,reservation_status,reservation_channel,reservation_type,partner_type,selling_currency,supplier,user_code,transaction_id,client_id,hotelname)"
							+ " values(" + rowCount + ",'" + reservation_no + "','" + reservation_date + "','" + first_element_date + "','" + reservation_status + "','" + reservation_channel + "','" + reservation_type + "','" + partner_type + "','"
							+ selling_currency + "','" + supplier + "','" + user_code + "','" + transaction_id + "','" + client_id + "','" + hotelName + "');";

					hotel_search.add(insertStr);
					/*********************************************/
					rowCount++;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private static void runQuery(Connection con, ArrayList<String> list) {
		System.out.println("tabale data loaded");
		String queryFull = "";
		int queryCount = 1;

		for (String sql : list) {
			try {
				if (queryCount % 500 != 0) {
					queryFull += sql;
				} else {
					Statement ste = con.createStatement();
					ste.executeUpdate(queryFull);
					queryFull = "";
				}
				// Thread.sleep(600);
			} catch (Exception e) {
				e.printStackTrace();
				// System.err.println(queryFull);
			}

			queryCount++;
		}

		if (!"".equals(queryFull)) {
			try {
				Statement ste = con.createStatement();
				ste.executeUpdate(queryFull);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("tabale data saved");
	}

	private static ArrayList<String> loadHotelData(Connection con, String tableName, String clientId) throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		String hashKeyMappingId = null;
		try {

			String query = "select * from " + clientId + "_hotels." + tableName + " order by id asc";
			Statement ste = con.createStatement();

			ResultSet rst = ste.executeQuery(query);
			Vector<String> columnNames = new Vector<String>();

			if (rst != null) {
				ResultSetMetaData columns = rst.getMetaData();
				int i = 0;
				while (i < columns.getColumnCount()) {
					i++;
					columnNames.add(columns.getColumnName(i));
				}

				while (rst.next()) {
					String row = "";
					String idV = "";
					for (i = 0; i < columnNames.size(); i++) {
						String value = rst.getString(columnNames.get(i));

						idV = rst.getString("id");

						if ("resconfirmsv".equals(tableName)) {
							hashKeyMappingId = null;							
						} else if ("reshoteldetailssv".equals(tableName)) {
							hashKeyMappingId = rst.getString("resconfirmid");
						} else if ("resroomssv".equals(tableName)) {
							hashKeyMappingId = rst.getString("reshoteldetailssvid");
						} else if ("resdailyratessv".equals(tableName)) {
							hashKeyMappingId = rst.getString("resroomssvid");
						}

						row += columnNames.get(i) + "=" + (value != null ? (value.replaceAll("'", "`").replaceAll(",", " ")) : "") + ", ";

					}

					String insertStr = "insert into rezbase_v3_reports.hotel_reservation " + "(hash_key,table_name,column_data,hash_key_mapping)" + " values(" + idV + ",'" + tableName + "','" + row + "'," + hashKeyMappingId + ");";
					list.add(insertStr);
				}
			}

			/** for print data **/
			// list.forEach(i -> {
			// System.out.println(i);
			// });

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
