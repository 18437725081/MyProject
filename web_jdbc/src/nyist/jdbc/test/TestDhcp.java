package nyist.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import nyist.jdbc.utils.JDBCUtils_v3;

public class TestDhcp {
	private Connection conn;
	private PreparedStatement prestat;
	private ResultSet rs;

	@Test
	public void testQuery() {
		try {
//			conn = dhcpUtils.getConnection();
			String sql = "select * from t_user";
			prestat = conn.prepareStatement(sql);
			rs = prestat.executeQuery();
			while (rs.next()) {
				System.out.println("–’√˚£∫" + rs.getString("username"));
				System.out.println("√‹¬Î£∫" + rs.getString("password"));

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils_v3.release(conn, prestat, rs);
		}

	}
}
