package nyist.web.request.forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * request完成请求转发
 * 1.转发是服务器内部之间的请求
 * 2，转发访问服务器一次
 * 3.请求转发中的request对象和response域对象中的数据，会在转发完成后销毁，
 * @author Administrator
 *
 */
public class ForwardServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.获得转发的对象
		request.setAttribute("name", "wangshuai");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/forward1");
		// 2.使用转发对象中的forward方法完成转发操作  ,转发中的参数request中有数据，在转发的地址中可以取到	
		dispatcher.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}