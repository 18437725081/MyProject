package nyist.web.content.redirct;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 需求： 完成重定向的功能 重定向的特点： 1.会请求服务器两次或者两次以上 2.地址栏会发生变化
 * 
 * @author Administrator
 *
 */
public class Servlet1 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.设置状态码为302，表示页面重定向
//		response.setStatus(302);
		// 2.设置重定向的url地址,通过设置响应头信息，设置Location，将值设置为重定向的地址
//		response.setHeader("Location", "/web_response/servlet2");
		//方式二：直接使用response中的方法完成重定向的功能
		response.sendRedirect("/web_response/servlet2");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}