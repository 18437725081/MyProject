package nyist.web.content.redirct;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ���� ����ض���Ĺ��� �ض�����ص㣺 1.��������������λ����������� 2.��ַ���ᷢ���仯
 * 
 * @author Administrator
 *
 */
public class Servlet1 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.����״̬��Ϊ302����ʾҳ���ض���
//		response.setStatus(302);
		// 2.�����ض����url��ַ,ͨ��������Ӧͷ��Ϣ������Location����ֵ����Ϊ�ض���ĵ�ַ
//		response.setHeader("Location", "/web_response/servlet2");
		//��ʽ����ֱ��ʹ��response�еķ�������ض���Ĺ���
		response.sendRedirect("/web_response/servlet2");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}