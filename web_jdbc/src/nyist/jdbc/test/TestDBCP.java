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
	 * 使用工具类完成查询数据库的功能
	 */
	@Test
	public void testQuery() {
		try {
			conn = DBCPUtils.getConn();
			String sql = "select * from t_user";
			prestat = conn.prepareStatement(sql);
			rs = prestat.executeQuery();
			while (rs.next()) {
				System.out.println("姓名:" + rs.getString("username"));
				System.out.println("密码" + rs.getString("password"));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils_v3.release(conn, prestat, rs);
		}
	}

	/**
	 * 使用dbcp的传统方式(手动方式)完成更新操作 1.创建dataSource对象 2，设置数据库连接的参数 3，获取连接对象 4.完成更新操作
	 */

	@Test
	public void testUpdate() {
		try {
			// 1.创建dataSource对象
			dataSource = new BasicDataSource();
			// 2.设置数据库；连接参数
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setUrl("jdbc:mysql://localhost:3306/web01_jdbc");
			dataSource.setPassword("123456");
			// 3.获得连接对象
			conn = dataSource.getConnection();
			// 4.获得预处理对象
			String sql = "update t_user set username = ? where uid =?";
			prestat = conn.prepareStatement(sql);
			// 5.完成数据的填充
			prestat.setString(1, "小马");
			prestat.setInt(2, 5);
			// 6.执行update操作
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
	 * 使用配置文件的方式完成对数据库的删除操作
	 */
	@Test
	public void testDelete() {
		try {
			// 1.创建properties对象
			Properties props = new Properties();
			// 2.加载配置文件
			props.load(new FileInputStream("src/db.properties"));
			// 3.创建dataSource对象
			dataSource1 = BasicDataSourceFactory.createDataSource(props);
			// 4.获得连接对象
			conn = dataSource1.getConnection();
			// 5.获得sql预处理对象
			String sql = "delete from t_user where username =?";
			prestat = conn.prepareStatement(sql);
			prestat.setString(1, "乔布斯");
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
