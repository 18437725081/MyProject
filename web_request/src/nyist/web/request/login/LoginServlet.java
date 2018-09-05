package nyist.web.request.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import nyist.web.request.domain.User;
import nyist.web.request.utils.C3P0Utils;

/**
 * 实现用户登录的过程
 * 
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.获得请求的参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 2.查询数据库中是否存在此条记录
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select username,password from user where username=? and password=?";
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class), username, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user != null) {
			// 表示登录成功，重定向跳转到首页（地址栏的地址需要变化，所以使用重定向）
			request.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			// 表示登录失败，请求转发到登录页面，回显错误信息
			request.setAttribute("info", "用户名或者密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}