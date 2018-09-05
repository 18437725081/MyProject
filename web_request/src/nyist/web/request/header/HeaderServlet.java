package nyist.web.request.header;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HeaderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得请求的参数
		String header = request.getHeader("User-Agent");
		System.out.println("header:"+header);
		//获得的是资源的来源
		String refer = request.getHeader("Referer");
		System.out.println(refer);
		//获取请求中所有的header
		Enumeration<String> headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			//遍历得到每一个请求头的name值
			String header1 = headers.nextElement();
			//得到name值对应的value值
			String value = request.getHeader(header1);
			System.out.println(header1+":"+value);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}