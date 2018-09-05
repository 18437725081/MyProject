
package nyist.web.cookie.removecookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 移除cookie的方式：只要设置同名cookiede有效时间为0，同时设置携带cookie的路径和要销毁的cookie的创建路径一致 即表示cookie的销毁
 * @author Administrator
 *
 */
public class RemoveCookie extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获得同名cookie
		Cookie cookie = new Cookie("name","");
		//2.设置cookie的有效时间为0，表示销毁cookie
		cookie.setMaxAge(0);
		//3.设置cookie的携带路径和要覆盖的cookie的创建路径相同
		cookie.setPath(request.getContextPath());
		
		//3.向客户端发送cookie
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