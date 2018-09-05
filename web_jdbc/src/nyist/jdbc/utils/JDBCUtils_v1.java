package nyist.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * jdbc的工具类，对外提供静态方法
 * 
 * @author Administrator
 *
 */
public class JDBCUtils_v1 {
	private static Connection conn = null;
	private static PreparedStatement prestat = null;
	private static ResultSet rs = null;

	/**
	 * 获取连接的方法
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String password = "123456";
			String username = "root";
			String url = "jdbc:mysql://localhost:3306/web01_jdbc";
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
