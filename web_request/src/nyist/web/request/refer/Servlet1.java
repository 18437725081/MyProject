package nyist.web.request.refer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ģ��������Ĺ���
 * 
 * @author Administrator
 *
 */
public class Servlet1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.ͨ��request����õ��������Դ����Դ
		String refer = request.getHeader("Referer");
		response.setContentType("text/html;charset=utf-8");
		if (refer.startsWith("http://localhost")) {
			// ��ʾ�Ǳ�����������Դ����ʾ
			response.getWriter().write("����Ͱ͵���ƽ̨��ȫ");
		}else {
			//��ʾ����վ��Դ�ĵ�����
			response.getWriter().write("����Դ�������������Դ�������ڵ����ߣ��ɳ�");
			response.sendRedirect("http://www.baidu.com");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
