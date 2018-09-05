package nyist.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.omg.CORBA.portable.ApplicationException;

/**
 * 通过IO流的方式读取文件获得数据库配置的信息
 * 
 * @author Administrator 获得类加载器
 */
public class JDBCUtils_v3 {

	private static InputStream is;
	private static Properties props;
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static Connection conn = null;

	static {
		try {

			is = JDBCUtils_v3.class.getClassLoader().getResourceAsStream("db.properties");
			props = new Properties();
			props.load(is);
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			username = props.getProperty("username");
			password = props.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
