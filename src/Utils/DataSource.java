package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

	private static DataSource data;
	private Connection conn;
	private String url = "jdbc:mysql://localhost:3306/agence_bd";
	private String user = "root";
	private String pass = "";

	private DataSource() {

		try {
			conn = DriverManager.getConnection(url, user, pass);
			//System.out.println("connexion établie");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static DataSource getInstance() {
		if (data == null) {
			data = new DataSource();
		}
		return data;
	}

	public Connection getConn() {
		return conn;
	}
}