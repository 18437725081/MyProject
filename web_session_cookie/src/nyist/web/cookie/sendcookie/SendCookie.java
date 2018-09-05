package nyist.web.cookie.sendcookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 客户端保存cookie的步骤
 * 1.在服务器创建cookie对象
 * 2.设置cookie的最大存活时间（默认为服务器关闭cookie随着销毁）
 * 3.设置cookie的携带路径（默认表示创建cookie的路径）
 * 如 创建cookie的访问路径为 http://localhost:8989/web_session&cookie/cookie）
 * 如果访问web应用中的资源路径为：http://localhost:8989/web_session&cookie/*，客户端都都会携带cookie
 * @author Administrator
 *
 */
public class SendCookie extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.创建cookie对象
		Cookie cookie = new Cookie("name","zhangsan");
		//2.设置cookie的有效时间
		cookie.setMaxAge(10*60);  //设置cookie的有效时间为10分钟（cookie的默认存活时间在服务器关闭的时候会自动销毁）
		//3.设置访问携带的地址参数 
		cookie.setPath(request.getContextPath()); //访问当前web应用中的任何资源都会携带cookie（/web_session&cookie）
		//4.发送cookie
		response.addCookie(cookie); //将cookie发送到客户端
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}