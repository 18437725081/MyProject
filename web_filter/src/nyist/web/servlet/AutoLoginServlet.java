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
			// ��½�ɹ�

			String autoLogin = request.getParameter("autoLogin");
			// �ж��Ƿ�ѡ�Զ���¼��ť
			if (autoLogin != null) {
				// ����ǰ��¼�û�����Ϣд��cookie��
				//���get������������⣬����ʹ��iso8859-1���룬��ʹ��u8���н���
				System.out.println(username);
				username = URLEncoder.encode(username, "utf-8"); //��������˧---%25E7%258E%258B%25E5%25B8%2585
				Cookie cookie_username = new Cookie("cookie_username", username);
				Cookie cookie_password = new Cookie("cookie_password", password);
				cookie_username.setPath("/");// ����Я��cookie��·��
				cookie_password.setPath("/");// ����Я��cookie��·��
				cookie_username.setMaxAge(60 * 60);// ����cookie�Ĵ��ʱ��Ϊһ��Сʱ
				cookie_password.setMaxAge(60 * 60);// ����cookie�Ĵ��ʱ��Ϊһ��Сʱ

				response.addCookie(cookie_username);
				response.addCookie(cookie_password);

			}
			session.setAttribute("user", user);// ���û���Ϣ����session��
			response.sendRedirect(request.getContextPath());// �ض�����ҳ
		} else {
			// ��¼ʧ��
			session.setAttribute("loginFail", "�û��������������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);// ת������¼ҳ��

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}