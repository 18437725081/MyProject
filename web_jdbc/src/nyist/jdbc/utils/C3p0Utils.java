package nyist.jdbc.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 创建c3p0的工具类
 * 
 * @author Administrator
 *
 */
public class C3p0Utils {
	private static ComboPooledDataSource dataSource;
	private static Connection conn;

	static {
		// 1.加载配置文件，创建对象
		dataSource = new ComboPooledDataSource();

	}
	public static ComboPooledDataSource getDataSource() {
		
		return dataSource;
	}

	public static Connection getConn() {
		try {
			return getDataSource().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
