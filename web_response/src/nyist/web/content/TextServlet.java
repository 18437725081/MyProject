package nyist.web.content;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TextServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.response������Ĭ��ʹ�õ������iso8859-1��ͨ�����ñ���ĸ�ʽΪ���ı���
		response.setCharacterEncoding("utf-8");
		//2.�������������ҳ��ı����ʽ
		response.setHeader("Content-type", "text/html;charSet=utf-8");
		
		
		//3.����ʹ��setContentType()��������response�������ı����ʽ��Ϊutf-8��ͬʱ������������ı����ʽҲ��Ϊutf-8����ȷ�Ľ���
//		response.setContentType("text/html;charSet=utf-8");
		
		//�����Ҫ���ñ����ʽ�������ڻ���ַ���֮ǰ������ʹ�ø��������е�write()����������д��response������
		PrintWriter writer = response.getWriter();
		//��response��������д������
		writer.write("�й������");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
