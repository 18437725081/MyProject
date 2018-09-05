package nyist.jdbc.testutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import nyist.jdbc.utils.JDBCUtils_v1;
import nyist.jdbc.utils.JDBCUtils_v2;
import nyist.jdbc.utils.JDBCUtils_v3;

public class TestUtils {
	private Connection conn = null;
	private PreparedStatement prestat = null;
	private ResultSet rs = null;

	/**
	 * 查询数据表中的所有的记录 使用定义的jdbc的工具类(版本v1)进行测试
	 */
	@Test

	public void testQueryAll() {
		try {
			conn = JDBCUtils_v1.getConnection();
			String sql = "select * from t_user";
			prestat = conn.prepareStatement(sql);
			rs = prestat.executeQuery();
			while (rs.next()) {
				System.out.println("用户名是:" + rs.getString(2));
				System.out.println("密码是:" + rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils_v1.release(conn, prestat, rs);
		}
		

	}
	@Test
	public void testInsert() {
		try {
			conn = JDBCUtils_v2.getConnection();
			String sql ="insert into t_user values(null,?,?)";
			prestat = conn.prepareStatement(sql);
			prestat.setString(1, "张三");
			prestat.setString(2, "234");
			int row = prestat.executeUpdate();
			if(row>0) {
				System.out.println("insert success");
			}else {
				System.out.println("insert failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils_v2.release(conn, prestat, null);
		}
		
	}
	@Test
	public void testUpdate() {
		try {
			conn = JDBCUtils_v3.getConnection();
			String sql="update t_user set username=?,password=? where uid = ?";
			prestat = conn.prepareStatement(sql);
			prestat.setString(1, "suns");
			prestat.setString(2, "666");
			prestat.setInt(3, 3);
			int row = prestat.executeUpdate();
			if(row>0) {
				//如果返回中大于零，表示操作成功
				System.out.println("update success");
			}else {
				System.out.println("update failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils_v3.release(conn, prestat, null);
		}
	}
}
