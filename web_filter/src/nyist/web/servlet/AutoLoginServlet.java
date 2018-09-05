package nyist.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nyist.web.domain.User;
import nyist.web.service.UserService;

public class AutoLoginServlet extends HttpServlet {

	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		username = new String(username.getBytes("ISO8859-1"),"utf-8");
		String password = request.getParameter("password");
		UserService service = new UserService();
		User user = service.login(username, password);
		if (user != null) {
			// 登陆成功

			String autoLogin = request.getParameter("autoLogin");
			// 判断是否勾选自动登录按钮
			if (autoLogin != null) {
				// 将当前登录用户的信息写入cookie中
				//解决get请求乱码的问题，首先使用iso8859-1编码，在使用u8进行解码
				System.out.println(username);
				username = URLEncoder.encode(username, "utf-8"); //将中文王帅---%25E7%258E%258B%25E5%25B8%2585
				Cookie cookie_username = new Cookie("cookie_username", username);
				Cookie cookie_password = new Cookie("cookie_password", password);
				cookie_username.setPath("/");// 设置携带cookie的路径
				cookie_password.setPath("/");// 设置携带cookie的路径
				cookie_username.setMaxAge(60 * 60);// 设置cookie的存活时间为一个小时
				cookie_password.setMaxAge(60 * 60);// 设置cookie的存活时间为一个小时

				response.addCookie(cookie_username);
				response.addCookie(cookie_password);

			}
			session.setAttribute("user", user);// 将用户信息存入session中
			response.sendRedirect(request.getContextPath());// 重定向到首页
		} else {
			// 登录失败
			session.setAttribute("loginFail", "用户名或者密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);// 转发到登录页面

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}