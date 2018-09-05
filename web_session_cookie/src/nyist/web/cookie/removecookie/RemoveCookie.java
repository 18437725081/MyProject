
package nyist.web.cookie.removecookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * �Ƴ�cookie�ķ�ʽ��ֻҪ����ͬ��cookiede��Чʱ��Ϊ0��ͬʱ����Я��cookie��·����Ҫ���ٵ�cookie�Ĵ���·��һ�� ����ʾcookie������
 * @author Administrator
 *
 */
public class RemoveCookie extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.���ͬ��cookie
		Cookie cookie = new Cookie("name","");
		//2.����cookie����Чʱ��Ϊ0����ʾ����cookie
		cookie.setMaxAge(0);
		//3.����cookie��Я��·����Ҫ���ǵ�cookie�Ĵ���·����ͬ
		cookie.setPath(request.getContextPath());
		
		//3.��ͻ��˷���cookie
		response.addCookie(cookie);
		/*Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie2 : cookies) {
				if("name".equals(cookie2.getName())) {
					response.getWriter().write(cookie2.getValue());
				}else {
					response.getWriter().write("cookie has removed");
				}
			}
		}*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}