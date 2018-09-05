package nyist.web.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nyist.web.domain.User;
import nyist.web.service.UserService;

public class AutoFilter implements Filter {


	@Override
	public void destroy() {

	}

	/// 在过滤器中对web应用下的所有请求资源进行过滤，
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		// 1.获得cookie
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();// 获得客户端携带的cookie
		String username = null;
		String password = null;
		for (Cookie cookie : cookies) {
			if ("cookie_username".equals(cookie.getName())) {
				
				username = cookie.getValue(); // 获得cookie中携带的用户名，完成自动登陆的功能
				//将cookie中存储的中文 %AE4%kfj---恢复成 “王帅” 
				username = URLDecoder.decode(username, "utf-8");
			}
			if ("cookie_password".equals(cookie.getName())) {
				password = cookie.getValue();// 获得cookie中携带的密码
			}
		}
		System.out.println(username+"============="+password);
		UserService service = new UserService();
		User user = null;
		if(username!=null&&password!=null) {
			user = service.login(username, password);
			if (user != null) {
				// 表示登录成功
				session.setAttribute("user", user);
			}
		}
		chain.doFilter(request, response);//放行操作
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
