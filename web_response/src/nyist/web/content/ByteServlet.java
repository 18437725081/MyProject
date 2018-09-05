package nyist.web.content;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ͼƬͨ���ֽڵķ�ʽ�����������Ӧ
 * 
 * @author Administrator
 *
 */
public class ByteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.����ֽ���
		ServletOutputStream out = response.getOutputStream();
		// 2.���ͼƬ��webӦ���е�λ��
		String realPath = this.getServletContext().getRealPath("/a.jpg");
		// 3.����ֽڶ�ȡ������
		InputStream in = new FileInputStream(realPath);
		// 4.�������
		int len = 0;
		//5.�����ֽڻ�����
		byte[] buf = new byte[1024];
		//6.��ͼƬ��Ϣͨ��������ʽ��ȡ����������
		while((len=in.read(buf))!=0) {
			//7.����ȡ��ͼƬ�ֽ���д�뵽response�������У���ҳ������ʾͼƬ��Ϣ
			out.write(buf, 0, len);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}