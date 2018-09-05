package nyist.web.cookie.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource(); // ����dataSource����
	// ThreadLocalʱ��Thread�ľֲ���������������Thread
	private static ThreadLocal<Connection> t1 = new ThreadLocal<Connection>(); // �����Ӻͱ����̰߳�
	private static Connection conn = null;

	/**
	 * ���dataSource����
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * �����ͨ��conn
	 * 
	 * @return
	 */
	public static Connection getConn() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ����뱾���̰߳󶨵�conn
	 * 
	 * @return
	 */
	public static Connection getCurrentConnection() {
		conn = t1.get();
		if (conn == null) {
			conn = getConn();
			t1.set(conn); // �������������뱾���̰߳�
		}
		return conn;
	}

	/**
	 * ��������ķ���
	 */
	public static void startTransaction() {
		conn = getCurrentConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false); // ���������ύΪ�ֶ��ύ
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ����ع�
	 */
	public static void commitTransaction() {
		conn = getCurrentConnection();
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ύ���񣬹ر����ӣ��������߳���conn�󶨽��
	 */
	public static void commitAndRelease() {
		conn = getCurrentConnection();
		if (conn != null) {
			try {
				conn.commit();
				conn.close();
				t1.remove(); // �ӱ����߳����Ƴ�conn
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ͷ���Դ�ķ���
	 */
	public static void closeConnection() {
		conn = getCurrentConnection();
		if (conn != null) {
			try {
				
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void closePreparestatement(PreparedStatement prestat) {
		if(prestat!=null) {
			try {
				prestat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void closeStatement(Statement stat) {
		if(stat!=null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void closeResultSet(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
