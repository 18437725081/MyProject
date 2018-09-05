package nyist.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * 读取配置文件中的数据库连接的信息
 * 
 * @author Administrator
 *
 */
public class JDBCUtils_v2 {
	private static Connection conn = null;
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	static {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("db");
			driver = bundle.getString("driver");
			url = bundle.getString("url");
			username = bundle.getString("username");
			password = bundle.getString("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

	public static void release(Connection conn, PreparedStatement prestat, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (prestat != null) {
			try {
				prestat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
