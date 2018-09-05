package nyist.web.cookie.sendcookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * �ͻ��˱���cookie�Ĳ���
 * 1.�ڷ���������cookie����
 * 2.����cookie�������ʱ�䣨Ĭ��Ϊ�������ر�cookie�������٣�
 * 3.����cookie��Я��·����Ĭ�ϱ�ʾ����cookie��·����
 * �� ����cookie�ķ���·��Ϊ http://localhost:8989/web_session&cookie/cookie��
 * �������webӦ���е���Դ·��Ϊ��http://localhost:8989/web_session&cookie/*���ͻ��˶�����Я��cookie
 * @author Administrator
 *
 */
public class SendCookie extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.����cookie����
		Cookie cookie = new Cookie("name","zhangsan");
		//2.����cookie����Чʱ��
		cookie.setMaxAge(10*60);  //����cookie����Чʱ��Ϊ10���ӣ�cookie��Ĭ�ϴ��ʱ���ڷ������رյ�ʱ����Զ����٣�
		//3.���÷���Я���ĵ�ַ���� 
		cookie.setPath(request.getContextPath()); //���ʵ�ǰwebӦ���е��κ���Դ����Я��cookie��/web_session&cookie��
		//4.����cookie
		response.addCookie(cookie); //��cookie���͵��ͻ���
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}