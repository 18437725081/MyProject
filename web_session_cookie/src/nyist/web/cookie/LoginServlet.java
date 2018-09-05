package nyist.web.cookie;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import nyist.web.cookie.domain.User;
import nyist.web.cookie.utils.C3P0Utils;
/**
 * 完成登录过程验证码的验证过程
 * 1.获得生成验证码图片的内容
 * 2.获得用户输入的验证码的值
 * 3.将第一步和第二步中的值进行比对，如果相同，获得用户名和密码验证登录，如果不相同，就return，终止操作
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//表单的提交方式为post提交，解决乱码问题可以使用下面的方式
		request.setCharacterEncoding("utf-8");
		//1.获得用户输入的验证码的值
		String checkcode_input = request.getParameter("checkCode");
		//2.获得生成验证码的原始值（可以使用session技术）
		String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
		request.getSession().removeAttribute(checkcode_session);
		//3.将两次的验证码进行比较
		if(!checkcode_input.equals(checkcode_session)) {
			//3.1表示用户输入错误，终止登录操作，回显错误信息
			request.setAttribute("info", "你输入的验证码不正确");
			//3.2将页面转发到登录界面
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			//3.3终止登录操作
			return;
		}
		//4.表示验证码正确，获得用户名的密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//5.查询数据库
		QueryRunner qr  = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select username ,password from user where username=? and password=?";
		User user=null;
		try {
			 user = qr.query(sql, new BeanHandler<User>(User.class),username,password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//6.对查询结果进行验证
		if(user!=null) {
			//6.1表示登录成功
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else {
			//6.2登录失败，回显错误信息
			request.setAttribute("info", "用户名或者密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}