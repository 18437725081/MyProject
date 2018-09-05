package nyist.jdbc.test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import nyist.jdbc.utils.DBCPUtils;
import nyist.jdbc.utils.JDBCUtils_v3;

public class TestDBCP {
	private Connection conn;
	private PreparedStatement prestat;
	private ResultSet rs;
	private BasicDataSource dataSource;
	private DataSource dataSource1;

	/**
	 * ʹ�ù�������ɲ�ѯ���ݿ�Ĺ���
	 */
	@Test
	public void testQuery() {
		try {
			conn = DBCPUtils.getConn();
			String sql = "select * from t_user";
			prestat = conn.prepareStatement(sql);
			rs = prestat.executeQuery();
			while (rs.next()) {
				System.out.println("����:" + rs.getString("username"));
				System.out.println("����" + rs.getString("password"));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils_v3.release(conn, prestat, rs);
		}
	}

	/**
	 * ʹ��dbcp�Ĵ�ͳ��ʽ(�ֶ���ʽ)��ɸ��²��� 1.����dataSource���� 2���������ݿ����ӵĲ��� 3����ȡ���Ӷ��� 4.��ɸ��²���
	 */

	@Test
	public void testUpdate() {
		try {
			// 1.����dataSource����
			dataSource = new BasicDataSource();
			// 2.�������ݿ⣻���Ӳ���
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setUrl("jdbc:mysql://localhost:3306/web01_jdbc");
			dataSource.setPassword("123456");
			// 3.������Ӷ���
			conn = dataSource.getConnection();
			// 4.���Ԥ�������
			String sql = "update t_user set username = ? where uid =?";
			prestat = conn.prepareStatement(sql);
			// 5.������ݵ����
			prestat.setString(1, "С��");
			prestat.setInt(2, 5);
			// 6.ִ��update����
			int rows = prestat.executeUpdate();
			if (rows > 0) {
				System.out.println("success");
			} else {
				System.out.println("failure");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils_v3.release(conn, prestat, null);
		}

	}

	/**
	 * ʹ�������ļ��ķ�ʽ��ɶ����ݿ��ɾ������
	 */
	@Test
	public void testDelete() {
		try {
			// 1.����properties����
			Properties props = new Properties();
			// 2.���������ļ�
			props.load(new FileInputStream("src/db.properties"));
			// 3.����dataSource����
			dataSource1 = BasicDataSourceFactory.createDataSource(props);
			// 4.������Ӷ���
			conn = dataSource1.getConnection();
			// 5.���sqlԤ�������
			String sql = "delete from t_user where username =?";
			prestat = conn.prepareStatement(sql);
			prestat.setString(1, "�ǲ�˹");
			int rows = prestat.executeUpdate();
			if (rows > 0) {
				System.out.println("delete success");
			} else {
				System.out.println("delete failure");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
