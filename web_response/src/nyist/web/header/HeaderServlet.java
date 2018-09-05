package nyist.web.header;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 使用response对象设置响应头
 * @author Administrator
 *
 */
public class HeaderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置状态码 302 表示重定向
		response.setStatus(302);
		//添加响应头信息
		response.addHeader("name", "zhangsan");
		//addHeader是添加响应头，并不会出现值覆盖的情况
		response.addHeader("name", "lisi");
		//value的值为int类型
		response.addIntHeader("age", 21);
		//value的类型为date的类型的值
		response.addDateHeader("date", new Date().getTime());
		//使用setHeaderd设置header的值，改方法设置的头信息会出现只覆盖的情况
		response.setHeader("name1", "suns");
		response.setHeader("name1", "zhaoliu");
		response.setHeader("age", "213");
		response.setHeader("age", "22");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}