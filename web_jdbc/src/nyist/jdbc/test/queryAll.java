package nyist.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
/**
 * ͨ��jdbc��ѯ���ݿ���е���������
 * @author Administrator
 *
 */
public class queryAll {
	/**
	 * ������
	 * 1.��������
	 * 2.�������
	 * 3.����Ԥ�������
	 * 4.ִ�в�ѯ����
	 * 5.���������
	 * 6.�ر�����
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testQueryAll(){
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement prestat =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/web01_jdbc";
			String name = "root";
			String pass = "123456";
		    conn = DriverManager.getConnection(url, name, pass);
		    String sql = "select * from t_user";
			prestat = conn.prepareStatement(sql);
			boolean b = prestat.execute();
			
			if(b) {
				rs = prestat.getResultSet();
				while(rs.next()) {
					System.out.println(rs.getInt(1));
					System.out.println(rs.getString(2));
					System.out.println(rs.getString(3));
				}
			}
					
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(prestat!=null) {
				try {
					prestat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
