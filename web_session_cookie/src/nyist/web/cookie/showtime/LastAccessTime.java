package nyist.web.cookie.showtime;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 显示客户端上次访问的时间
 * 
 * @author Administrator
 *
 */
public class LastAccessTime extends HttpServlet {
	/**
	 * 1.获得当前对象，并进行日期格式的转换 2.创建cookie对象 3.将cookie对象响应给客户端
	 * 4.下次访问的时候从cookie中获得存储的时间，显示给客户端 5.重复上面1--4的操作
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.创建日期对象
		Date date = new Date();
		// 2.进行日期格式的转换,将日期格式转化称字符串类型的
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = format.format(date);
		// 3.创建cookie对象
		Cookie cook = new Cookie("lastAccessTime", time);
		//将cookie对象添加到response响应头中
		response.addCookie(cook);

		// 4.获得cookie中上次访问的时间
		String lastTime = null;
		// 5.获得cookie中的所有cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("lastAccessTime".equals(cookie.getName())) {
					lastTime = cookie.getValue();
				}
			}
		}
		response.setContentType("text/html;charset=utf-8");
		if (lastTime == null) {
			response.getWriter().write("你是第一次访问");
		} else {
			response.getWriter().write("你上次访问的时间是:" + lastTime);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}