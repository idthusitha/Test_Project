package file_read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileRead {

	public static void main(String[] args) {

		try {
			File f = new File(FileRead.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			File dir = f.getAbsoluteFile().getParentFile();
			String path = dir.toString();

			BufferedReader br = new BufferedReader(new FileReader(path + "/src/file_read/ride_jetty_log.txt"));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					if (line.indexOf("select ") != -1) {
						sb.append(line);
						System.out.println("select "+line.split("select ")[1]);

						sb.append(System.lineSeparator());
					}
					line = br.readLine();
				}
				String everything = sb.toString();

				// System.out.println(everything);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				br.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
