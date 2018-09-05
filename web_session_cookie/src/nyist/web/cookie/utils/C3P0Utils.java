package nyist.web.cookie.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource(); // 创建dataSource对象
	// ThreadLocal时候Thread的局部变量，本身并不是Thread
	private static ThreadLocal<Connection> t1 = new ThreadLocal<Connection>(); // 将连接和本地线程绑定
	private static Connection conn = null;

	/**
	 * 获得dataSource对象
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 获得普通的conn
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
	 * 获得与本地线程绑定的conn
	 * 
	 * @return
	 */
	public static Connection getCurrentConnection() {
		conn = t1.get();
		if (conn == null) {
			conn = getConn();
			t1.set(conn); // 将创建的连接与本地线程绑定
		}
		return conn;
	}

	/**
	 * 开启事务的方法
	 */
	public static void startTransaction() {
		conn = getCurrentConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false); // 设置事务提交为手动提交
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 事务回滚
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
	 * 提交事务，关闭连接，将本地线程与conn绑定解除
	 */
	public static void commitAndRelease() {
		conn = getCurrentConnection();
		if (conn != null) {
			try {
				conn.commit();
				conn.close();
				t1.remove(); // 从本地线程中移除conn
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 释放资源的方法
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
