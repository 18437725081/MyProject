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

	/// �ڹ������ж�webӦ���µ�����������Դ���й��ˣ�
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		// 1.���cookie
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();// ��ÿͻ���Я����cookie
		String username = null;
		String password = null;
		for (Cookie cookie : cookies) {
			if ("cookie_username".equals(cookie.getName())) {
				
				username = cookie.getValue(); // ���cookie��Я�����û���������Զ���½�Ĺ���
				//��cookie�д洢������ %AE4%kfj---�ָ��� ����˧�� 
				username = URLDecoder.decode(username, "utf-8");
			}
			if ("cookie_password".equals(cookie.getName())) {
				password = cookie.getValue();// ���cookie��Я��������
			}
		}
		System.out.println(username+"============="+password);
		UserService service = new UserService();
		User user = null;
		if(username!=null&&password!=null) {
			user = service.login(username, password);
			if (user != null) {
				// ��ʾ��¼�ɹ�
				session.setAttribute("user", user);
			}
		}
		chain.doFilter(request, response);//���в���
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
