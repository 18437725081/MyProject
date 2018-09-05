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
		//1.response缓冲区默认使用的码表是iso8859-1，通过设置编码的格式为中文编码
		response.setCharacterEncoding("utf-8");
		//2.设置浏览器解析页面的编码格式
		response.setHeader("Content-type", "text/html;charSet=utf-8");
		
		
		//3.可以使用setContentType()方法，将response缓冲区的编码格式改为utf-8，同时将浏览器解析的编码格式也改为utf-8，正确的解析
//		response.setContentType("text/html;charSet=utf-8");
		
		//如果需要设置编码格式，必须在获得字符流之前；可以使用该流对象中的write()方法将数据写到response缓冲区
		PrintWriter writer = response.getWriter();
		//向response缓冲区中写入数据
		writer.write("中国，你好");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
