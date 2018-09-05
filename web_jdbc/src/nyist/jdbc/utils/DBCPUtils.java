package nyist.jdbc.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * 创建dhcp的工具类 提供getDataSource方法 和 getConnection()方法
 * 
 * @author Administrator
 *
 */
public class DBCPUtils {
	private static DataSource dataSource;
	private static Connection conn;

	/**
	 * 创建静态代码块 1.获取文件输入流对象 2.创建Properties对象 3.加载配置文件 4，创建DataSource对象
	 * 
	 * @return
	 */
	static {
		try {
			InputStream is = DBCPUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties props = new Properties();
			props.load(is);
			dataSource = BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取DataSource对象
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 提供getConnection方法， 获得conn对象
	 */
	public static Connection getConn() {

		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
