package nyist.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * jdbc：java database connectivity，通过java操作数据库，是一种执行sql
 * 的Java API
 * jdbc开发步骤:
 * 1.加载驱动 Class.forName("com.mysql.jdbc.Driver")
 * 2.获取连接 
 * 3，获取语句执行者
 * 4.执行sql语句
 * 5.结果集处理
 * @author Administrator
 *
 */
public class jdbcLogin {
	@Test
	public void test() {
		try {
//			login1("王帅","456");
			login("王帅", "456");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用statement对象操作sql语句
	 * @param username
	 * @param password
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void login(String username, String password) throws ClassNotFoundException, SQLException {
		//1.加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2.获得连接
		String url = "jdbc:mysql://localhost:3306/web01_jdbc";
		String name = "root";
		String pass = "123456";
		//DriverManager 是工具类，1.获得连接，2.加载驱动，存在二次注册的问题
//		DriverManager.registerDriver(new Driver()); 
		Connection conn = DriverManager.getConnection(url , name, pass);
		
		//3.创建处理sql语句的对象
		Statement stat = conn.createStatement();
		String sql = "select * from t_user where " + "username='"+username+"' and password='"+password+"'";
		//4.执行sql语句
		ResultSet rs = stat.executeQuery(sql);
		//5.结果集处理
		if(rs.next()) {
			System.out.println("恭喜你 "+username+"，登陆成功");
			System.out.println(sql);
		}else {
			System.out.println("用户名或者密码错误");
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
	 * 使用预编译对象处理sql语句
	 * @param username
	 * @param password
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void login1(String username, String password) throws ClassNotFoundException, SQLException {
		//1.加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		String pass="123456";
		String name="root";
		String url="jdbc:mysql://localhost:3306/web01_jdbc";
		//2.获得连接
		Connection conn = DriverManager.getConnection(url,name,pass);
		//3.获得预编译对象
		String sql="select * from t_user where username = ? and password =?";
		PreparedStatement prestat = conn.prepareStatement(sql);
		prestat.setString(1, username);
		prestat.setString(2, password);
//		prestat.executeQuery();
		boolean rs = prestat.execute();
		ResultSet rs1 = null;
		//如果返回结果为true，执行的是select操作，使用 rs.getResultSet()方法得到结果
		if(rs) {
			rs1 = prestat.getResultSet();
			if(rs1.next()) {
				System.out.println("恭喜你"+rs1.getString(2)+"，登录成功");
				System.out.println(sql);
			}else {
				System.out.println("用户名或者密码错误");
			}
		
		}else {
			System.out.println("执行的sql语句非select操作");
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
