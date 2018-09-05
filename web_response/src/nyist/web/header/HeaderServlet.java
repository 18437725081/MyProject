package nyist.web.header;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ʹ��response����������Ӧͷ
 * @author Administrator
 *
 */
public class HeaderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����״̬�� 302 ��ʾ�ض���
		response.setStatus(302);
		//�����Ӧͷ��Ϣ
		response.addHeader("name", "zhangsan");
		//addHeader�������Ӧͷ�����������ֵ���ǵ����
		response.addHeader("name", "lisi");
		//value��ֵΪint����
		response.addIntHeader("age", 21);
		//value������Ϊdate�����͵�ֵ
		response.addDateHeader("date", new Date().getTime());
		//ʹ��setHeaderd����header��ֵ���ķ������õ�ͷ��Ϣ�����ֻ���ǵ����
		response.setHeader("name1", "suns");
		response.setHeader("name1", "zhaoliu");
		response.setHeader("age", "213");
		response.setHeader("age", "22");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}