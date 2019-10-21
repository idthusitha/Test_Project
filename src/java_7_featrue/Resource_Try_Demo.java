package java_7_featrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class demo implements AutoCloseable {

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
	}

}

public class Resource_Try_Demo {

	public static void main(String[] args) {

	}

	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 *             before java 1.7
	 */
	static String readFirstLineFromFileWithFinallyBlock(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			return br.readLine();
		} finally {
			if (br != null)
				br.close();
		}
	}

	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 *             1.7 java BufferedReader is a resource that must be closed after the program is finished with it: (AutoCloseable)
	 */
	static String readFirstLineFromFile(String path) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			return br.readLine();
		}
	}

	/**
	 * 
	 * @param zipFileName
	 * @param outputFileName
	 * @throws java.io.IOException
	 */
	public static void writeToFileZipFileContents(String zipFileName, String outputFileName) throws java.io.IOException {

		java.nio.charset.Charset charset = java.nio.charset.StandardCharsets.US_ASCII;
		java.nio.file.Path outputFilePath = java.nio.file.Paths.get(outputFileName);

		// Open zip file and create output file with
		// try-with-resources statement

		try (java.util.zip.ZipFile zf = new java.util.zip.ZipFile(zipFileName); java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(outputFilePath, charset)) {
			// Enumerate each entry
			for (java.util.Enumeration entries = zf.entries(); entries.hasMoreElements();) {
				// Get the entry name and write it to the output file
				String newLine = System.getProperty("line.separator");
				String zipEntryName = ((java.util.zip.ZipEntry) entries.nextElement()).getName() + newLine;
				writer.write(zipEntryName, 0, zipEntryName.length());
			}
		}
	}

	public static void viewTable(Connection con) throws SQLException {

		String query = "select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";

		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String coffeeName = rs.getString("COF_NAME");
				int supplierID = rs.getInt("SUP_ID");
				float price = rs.getFloat("PRICE");
				int sales = rs.getInt("SALES");
				int total = rs.getInt("TOTAL");

				System.out.println(coffeeName + ", " + supplierID + ", " + price + ", " + sales + ", " + total);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
