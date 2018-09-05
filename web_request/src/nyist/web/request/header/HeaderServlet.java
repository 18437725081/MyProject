package nyist.web.request.header;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HeaderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������Ĳ���
		String header = request.getHeader("User-Agent");
		System.out.println("header:"+header);
		//��õ�����Դ����Դ
		String refer = request.getHeader("Referer");
		System.out.println(refer);
		//��ȡ���������е�header
		Enumeration<String> headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			//�����õ�ÿһ������ͷ��nameֵ
			String header1 = headers.nextElement();
			//�õ�nameֵ��Ӧ��valueֵ
			String value = request.getHeader(header1);
			System.out.println(header1+":"+value);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}