
package nyist.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import nyist.jdbc.utils.C3p0Utils;
import nyist.jdbc.utils.JDBCUtils_v3;

public class TestC3P0 {

	private ComboPooledDataSource dataSource;
	private Connection conn;
	private PreparedStatement prestat;
	private ResultSet rs;

	/**
	 * 1.创建c3p0对象，会自动加载配置文件 2.获得连接 3.创建预编译对象 4.执行查询操作 5.结果集处理 6.管理资源
	 */
	@Test
	public void testQuery() {
		try {
			// 1.参数表示配置文件中name属性值 <named-config name="nyist">
			dataSource = new ComboPooledDataSource("nyist");
			// dataSource = new ComboPooledDataSource();
			// 2.
			conn = dataSource.getConnection();
			String sql = "select * from t_user";
			prestat = conn.prepareStatement(sql);
			rs = prestat.executeQuery();
			while (rs.next()) {
				System.out.println("姓名" + rs.getString("username"));
				System.out.println("密码" + rs.getString("password"));

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils_v3.release(conn, prestat, rs);

		}

	}

	/**
	 * 使用c3p0的工具类向数据库中插入一条记录
	 */
	@Test
	public void testInsert() {
		try {
			conn = C3p0Utils.getConn();
			String sql = "insert into t_user values(null,?,?)";
			prestat = conn.prepareStatement(sql);
			prestat.setString(1, "wangshuai");
			prestat.setString(2, "1235");
			int rows = prestat.executeUpdate();
			if(rows>0) {
				System.out.println("success");
			}else {
				System.out.println("failure");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
