package db_utils;


/**
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author thusitha
 * 
 */
public final class DBUtility {
	public static DBUtility dbUtility; 
	private static Connection connection;

	private DBUtility() {
	}

	public static DBUtility getInstance(final String connectionString, final String userName, final String password) throws Exception {
		if (dbUtility == null || (dbUtility != null && connection.isClosed())) {
			dbUtility = new DBUtility();
			dbUtility.connection = dbUtility.createConnection(connectionString, userName, password);
		}
		return dbUtility;
	}

	private Connection createConnection(String connectionString, String userName, String password) throws Exception {
		Class.forName("org.postgresql.Driver");

		final Connection connection = DriverManager.getConnection(connectionString, userName, password);
		return connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public ResultSet getResult(final String query) {
		ResultSet rst = null;
		try {
			final Statement ste = connection.createStatement();
			rst = ste.executeQuery(query);
		} catch (Exception e) {

		}
		return rst;
	}

	public void setData(final String query) {
		try {
			final Statement ste = connection.createStatement();
			ste.executeUpdate(query);
		} catch (Exception e) {

		}
	}
}
