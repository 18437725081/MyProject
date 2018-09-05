package nyist.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import nyist.jdbc.DataSource.MyDataSource;

public class TestDataPool {
	private Connection conn = null;
	private PreparedStatement prestat;
	private MyDataSource dataSource = new MyDataSource();
	@Test
	public void testInsert() {
		try {
		
			conn = dataSource.getConnection();
			String sql = "insert into t_user values(null,?,?)";
			prestat = conn.prepareStatement(sql );
			prestat.setString(1, "ÇÇ²¼Ë¹");
			prestat.setString(2, "666");
			int rows = prestat.executeUpdate();
			if(rows>0) {
				System.out.println("insert success");
			}else {
				System.out.println("insert failure");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			dataSource.close(conn);
		}
	}
}
