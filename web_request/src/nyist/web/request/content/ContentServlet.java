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
		//��ȡ����
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		System.out.println("----------------");
		//������еĲ���
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()) {
			String parameter = parameterNames.nextElement();
			System.out.println("paramter:"+parameter);
		}
		System.out.println("================");
		//��ö������ֵ
		String[] hobbys = request.getParameterValues("hobby");
		for(String hobby : hobbys) {
			//���get������������
			hobby = new String(hobby.getBytes("ISO8859-1"),"UTF-8");
			System.out.println(hobby);
		}
		System.out.println("******************");
		//4.��ȡ���еĲ�����������װ��һ��Map<String,String>
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Entry<String, String[]> map : parameterMap.entrySet()) {
			//��ò�����name��ֵ 
			String key = map.getKey();
			System.out.println(key);
			 //��ò���name��valueֵ
			 String[] value = map.getValue();
			 for(String val : value) {
				 //�������post����ķ�ʽ���ֵ�������������
//				 request.setCharacterEncoding("utf-8");
				 
				 //ʹ��ISO8859-1���룬��ʹ��utf-8����Ĺ��̣��������get����ʽ���ֵ�������������
				 val = new String(val.getBytes("ISO8859-1"),"UTF-8");
				 System.out.println(val);
			 }
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}