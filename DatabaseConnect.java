package homeJobMarketplace;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/HomeJobMarketPlace";
	// check for lost host path u have given
	static final String USER = "root";
	static final String PASS = "123456";
	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			conn = createConnection();
			return conn;
		} else
			return conn;
	}

	public static Connection createConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			System.out.println("Exception in creating connection:  " + e);
		}

		return conn;
	}

	public static void closeConnection() {
		try {
			conn.close();
			conn = null;
		} catch (Exception e) { // Handle errors for Class.forName
			e.printStackTrace();
		}
	}

}
