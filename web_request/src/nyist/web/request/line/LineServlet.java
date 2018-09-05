package nyist.web.request.line;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ��ȡrequest�����е����� 
 * 1.String getMethod() �÷������ص�������ķ�ʽ 
 * 2.String getRequestURI()��ͳһ��Դ��ʶ��  /web_request/header
 * 3.StringBuffer getRequestURL() ͳһ��Դ��λ�� http://localhost:8989/web_request/header
 *  4.String getRemoteAddr() 
 * �õ����Ƿ���������IP��ַ 5.String getContextPath(): �õ�����webӦ�õ������� /web_request
 * 
 * @author Administrator
 *
 */
public class LineServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.�������ķ�ʽ
				String method = request.getMethod();
				System.out.println("method:"+method);
				// 2.��������URI
				String requestURI = request.getRequestURI();
				System.out.println("URI:"+requestURI);
				// 3.��������URL
				StringBuffer requestURL = request.getRequestURL();
				System.out.println("URL:"+requestURL);
				// 4.�õ�����webӦ�������ĵĵ�ַ
				String contextPath = request.getContextPath();
				System.out.println("contectPath:"+contextPath);
				//6.������������
				String ip = request.getRemoteAddr();
				System.out.println("ip:"+ip);

				//7.�������Ĳ��� ���Ϊget�ύ���õ����Ǻ���ƴ�ӵĲ���
				String queryString = request.getQueryString();
				System.out.println(queryString);
				
				System.out.println("==============");
				
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
