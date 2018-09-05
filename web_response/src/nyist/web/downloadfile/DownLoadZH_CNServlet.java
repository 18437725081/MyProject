package nyist.web.downloadfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

/**
 * ������ص��ļ��������ĵ�����
 * 
 * @author Administrator
 *
 */
public class DownLoadZH_CNServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.������ص��ļ�����
		String fileName = request.getParameter("fileName");
		// ����֮�� fileName= ��Ů.jpg�������������أ����غ���ļ���Ϊ jpg������ǰ׺ͼƬ��������ʾ
		fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8"); // ���ͼƬ������ʾΪ
																		// "???.jpg",������ʾ���ĵ�����

		// 9.�������ͷ�е�User-Agent
		String agent = request.getHeader("User-Agent"); // ����������غ���ļ���Ϊ"jpg"������
		// ���ݲ�ͬ��������в�ͬ�ı���
		String filenameEncoder = "";
		if (agent.contains("MSIE")) {
			// IE�����
			filenameEncoder = URLEncoder.encode(fileName, "utf-8");
			filenameEncoder = filenameEncoder.replace("+", " ");
		} else if (agent.contains("Firefox")) {
			// ��������
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filenameEncoder = "=?utf-8?B?" + base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";
		} else {
			// ���������
			filenameEncoder = URLEncoder.encode(fileName, "utf-8");
		}

		// 2.�������ص��ļ�������
		response.setContentType(this.getServletContext().getMimeType(fileName));
		// 3.���߿ͻ��ˣ������������Դ����ֱ�ӽ�������ʾ���� filename��ֵ���Ѿ������������ļ���
		response.setHeader("Content-Disposition", "attachmnet;filename=" + filenameEncoder);
		// 4.������ص��ļ��ڷ������еĵ�ַ
		String realPath = this.getServletContext().getRealPath("download/" + fileName);
		// 5.����д��������
		InputStream in = new FileInputStream(realPath);
		// 6.ͨ��response�������ļ����������
		ServletOutputStream out = response.getOutputStream();
		// 7.�������������� �������
		byte[] buf = new byte[1024];
		int len = 0;
		// 8.�����ص��ļ���ȡ���������Ļ�������
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		// 10.�ر�������
		in.close();
		// out.close(); //���Բ��ùرգ����������Զ���⣬�رո�������
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}