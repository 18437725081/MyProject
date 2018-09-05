package nyist.jdbc.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * ����dhcp�Ĺ����� �ṩgetDataSource���� �� getConnection()����
 * 
 * @author Administrator
 *
 */
public class DBCPUtils {
	private static DataSource dataSource;
	private static Connection conn;

	/**
	 * ������̬����� 1.��ȡ�ļ����������� 2.����Properties���� 3.���������ļ� 4������DataSource����
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
	 * ��ȡDataSource����
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * �ṩgetConnection������ ���conn����
	 */
	public static Connection getConn() {

		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
