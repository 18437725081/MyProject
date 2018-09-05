package nyist.web.downloadfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ����ļ����صĹ��� 1.�ļ���ʱ���أ� �����������ļ��н������ܣ����������ܽ������ļ�������ֱ�Ӵ򿪣�������ܽ������ļ�����ʾ����ѡ����
 * �������������ܹ�������ʱ�������ļ� 2.��ʱ��Ҫ��д�ļ����صĴ��� ������������Ҫ���ص��ļ��ܹ����н���ʱ����Ҫ��д�����ļ��Ĵ��룬��ֹ���������
 * 
 * @author Administrator
 *
 */
public class DownLoadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * �ļ����صĲ��� 1.������ص��ļ����ļ��� 2.����ļ��ڷ������е�λ�� 3.����Content-type��ֵ����ʾ���ص��ļ���
	 * 4.�������������������������� 5.ͨ��response����õ���������� 6.�������������ж�ȡ���������е�����д�뵽�����������
	 * 7.�ر�ͨ��new����������������(ͨ��response��õ������������Բ��ùرգ���������ͨ������Զ��ر�)
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.������ص��ļ���
		String fileName = request.getParameter("fileName");
		// 8.��ȡ���ص��ļ����� ��ͨ������MIME��ֵ
		// 8.1��������ļ�������
		response.setContentType(this.getServletContext().getMimeType(fileName));
		// 8.2 ����������򿪵ķ�ʽ�����أ���ֹ���������
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		// 2.���Ҫ���ص��ļ��ڷ������е�λ��
		String path = this.getServletContext().getRealPath("download/" + fileName);
		// 3.��������������
		byte[] buf = new byte[1024];
		int len = 0;
		// 4.��������������
		InputStream in = new FileInputStream(path);
		// 5.ͨ��response������������
		ServletOutputStream out = response.getOutputStream();
		// 6.���ļ���ȡ����������
		while ((len = in.read(buf)) > 0) {
			// 7.���ļ�ͨ�������д��response��������
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
