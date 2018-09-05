package nyist.web.cookie.showtime;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ʾ�ͻ����ϴη��ʵ�ʱ��
 * 
 * @author Administrator
 *
 */
public class LastAccessTime extends HttpServlet {
	/**
	 * 1.��õ�ǰ���󣬲��������ڸ�ʽ��ת�� 2.����cookie���� 3.��cookie������Ӧ���ͻ���
	 * 4.�´η��ʵ�ʱ���cookie�л�ô洢��ʱ�䣬��ʾ���ͻ��� 5.�ظ�����1--4�Ĳ���
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.�������ڶ���
		Date date = new Date();
		// 2.�������ڸ�ʽ��ת��,�����ڸ�ʽת�����ַ������͵�
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = format.format(date);
		// 3.����cookie����
		Cookie cook = new Cookie("lastAccessTime", time);
		//��cookie������ӵ�response��Ӧͷ��
		response.addCookie(cook);

		// 4.���cookie���ϴη��ʵ�ʱ��
		String lastTime = null;
		// 5.���cookie�е�����cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("lastAccessTime".equals(cookie.getName())) {
					lastTime = cookie.getValue();
				}
			}
		}
		response.setContentType("text/html;charset=utf-8");
		if (lastTime == null) {
			response.getWriter().write("���ǵ�һ�η���");
		} else {
			response.getWriter().write("���ϴη��ʵ�ʱ����:" + lastTime);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}