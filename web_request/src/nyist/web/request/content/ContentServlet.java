package nyist.web.request.content;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		System.out.println("----------------");
		//获得所有的参数
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()) {
			String parameter = parameterNames.nextElement();
			System.out.println("paramter:"+parameter);
		}
		System.out.println("================");
		//获得多个表单的值
		String[] hobbys = request.getParameterValues("hobby");
		for(String hobby : hobbys) {
			//解决get请求乱码的情况
			hobby = new String(hobby.getBytes("ISO8859-1"),"UTF-8");
			System.out.println(hobby);
		}
		System.out.println("******************");
		//4.获取所有的参数，参数封装成一个Map<String,String>
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Entry<String, String[]> map : parameterMap.entrySet()) {
			//获得参数的name的值 
			String key = map.getKey();
			System.out.println(key);
			 //获得参数name的value值
			 String[] value = map.getValue();
			 for(String val : value) {
				 //解决的是post请求的方式出现的中文乱码问题
//				 request.setCharacterEncoding("utf-8");
				 
				 //使用ISO8859-1编码，在使用utf-8解码的过程；解决的是get请求方式出现的中文乱码问题
				 val = new String(val.getBytes("ISO8859-1"),"UTF-8");
				 System.out.println(val);
			 }
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}