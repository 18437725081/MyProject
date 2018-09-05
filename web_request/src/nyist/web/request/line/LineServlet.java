package nyist.web.request.line;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 获取request请求行的内容 
 * 1.String getMethod() 该方法返回的是请求的方式 
 * 2.String getRequestURI()：统一资源标识符  /web_request/header
 * 3.StringBuffer getRequestURL() 统一资源定位符 http://localhost:8989/web_request/header
 *  4.String getRemoteAddr() 
 * 得到的是访问主机的IP地址 5.String getContextPath(): 得到的是web应用的上下文 /web_request
 * 
 * @author Administrator
 *
 */
public class LineServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.获得请求的方式
				String method = request.getMethod();
				System.out.println("method:"+method);
				// 2.获得请求的URI
				String requestURI = request.getRequestURI();
				System.out.println("URI:"+requestURI);
				// 3.获得请求的URL
				StringBuffer requestURL = request.getRequestURL();
				System.out.println("URL:"+requestURL);
				// 4.得到的是web应用上下文的地址
				String contextPath = request.getContextPath();
				System.out.println("contectPath:"+contextPath);
				//6.获得请求的主机
				String ip = request.getRemoteAddr();
				System.out.println("ip:"+ip);

				//7.获得请求的参数 如果为get提交，得到的是后面拼接的参数
				String queryString = request.getQueryString();
				System.out.println(queryString);
				
				System.out.println("==============");
				
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
