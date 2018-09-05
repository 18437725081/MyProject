package nyist.web.request.refer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 模拟防盗链的过程
 * 
 * @author Administrator
 *
 */
public class Servlet1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.通过request对象得到请求的资源的来源
		String refer = request.getHeader("Referer");
		response.setContentType("text/html;charset=utf-8");
		if (refer.startsWith("http://localhost")) {
			// 表示是本服务器的资源，显示
			response.getWriter().write("阿里巴巴的云平台安全");
		}else {
			//表示是网站资源的盗窃者
			response.getWriter().write("该资源不是属于你的资源，你属于盗窃者，可耻");
			response.sendRedirect("http://www.baidu.com");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
