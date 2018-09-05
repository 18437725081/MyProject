package nyist.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * jdbc��java database connectivity��ͨ��java�������ݿ⣬��һ��ִ��sql
 * ��Java API
 * jdbc��������:
 * 1.�������� Class.forName("com.mysql.jdbc.Driver")
 * 2.��ȡ���� 
 * 3����ȡ���ִ����
 * 4.ִ��sql���
 * 5.���������
 * @author Administrator
 *
 */
public class jdbcLogin {
	@Test
	public void test() {
		try {
//			login1("��˧","456");
			login("��˧", "456");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ʹ��statement�������sql���
	 * @param username
	 * @param password
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void login(String username, String password) throws ClassNotFoundException, SQLException {
		//1.��������
		Class.forName("com.mysql.jdbc.Driver");
		//2.�������
		String url = "jdbc:mysql://localhost:3306/web01_jdbc";
		String name = "root";
		String pass = "123456";
		//DriverManager �ǹ����࣬1.������ӣ�2.�������������ڶ���ע�������
//		DriverManager.registerDriver(new Driver()); 
		Connection conn = DriverManager.getConnection(url , name, pass);
		
		//3.��������sql���Ķ���
		Statement stat = conn.createStatement();
		String sql = "select * from t_user where " + "username='"+username+"' and password='"+password+"'";
		//4.ִ��sql���
		ResultSet rs = stat.executeQuery(sql);
		//5.���������
		if(rs.next()) {
			System.out.println("��ϲ�� "+username+"����½�ɹ�");
			System.out.println(sql);
		}else {
			System.out.println("�û��������������");
		}
		if(rs!=null) {
			rs.close();
		}
		if(stat!=null) {
			stat.close();
		}
		if(conn!=null) {
			conn.close();
		}
	}
	/**
	 * ʹ��Ԥ���������sql���
	 * @param username
	 * @param password
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void login1(String username, String password) throws ClassNotFoundException, SQLException {
		//1.��������
		Class.forName("com.mysql.jdbc.Driver");
		String pass="123456";
		String name="root";
		String url="jdbc:mysql://localhost:3306/web01_jdbc";
		//2.�������
		Connection conn = DriverManager.getConnection(url,name,pass);
		//3.���Ԥ�������
		String sql="select * from t_user where username = ? and password =?";
		PreparedStatement prestat = conn.prepareStatement(sql);
		prestat.setString(1, username);
		prestat.setString(2, password);
//		prestat.executeQuery();
		boolean rs = prestat.execute();
		ResultSet rs1 = null;
		//������ؽ��Ϊtrue��ִ�е���select������ʹ�� rs.getResultSet()�����õ����
		if(rs) {
			rs1 = prestat.getResultSet();
			if(rs1.next()) {
				System.out.println("��ϲ��"+rs1.getString(2)+"����¼�ɹ�");
				System.out.println(sql);
			}else {
				System.out.println("�û��������������");
			}
		
		}else {
			System.out.println("ִ�е�sql����select����");
		}
		if(rs1!=null) {
			rs1.close();
		}
		if(prestat!=null) {
			prestat.close();
		}
		if(conn!=null) {
			conn.close();
		}
	}
}
