package nyist.web.request.forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * request�������ת��
 * 1.ת���Ƿ������ڲ�֮�������
 * 2��ת�����ʷ�����һ��
 * 3.����ת���е�request�����response������е����ݣ�����ת����ɺ����٣�
 * @author Administrator
 *
 */
public class ForwardServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.���ת���Ķ���
		request.setAttribute("name", "wangshuai");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/forward1");
		// 2.ʹ��ת�������е�forward�������ת������  ,ת���еĲ���request�������ݣ���ת���ĵ�ַ�п���ȡ��	
		dispatcher.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}