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
 * 将图片通过字节的方式在浏览器中响应
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
		// 1.获得字节流
		ServletOutputStream out = response.getOutputStream();
		// 2.获得图片在web应用中的位置
		String realPath = this.getServletContext().getRealPath("/a.jpg");
		// 3.获得字节读取流对象
		InputStream in = new FileInputStream(realPath);
		// 4.定义变量
		int len = 0;
		//5.创建字节缓冲区
		byte[] buf = new byte[1024];
		//6.将图片信息通过流的形式读取到缓冲区中
		while((len=in.read(buf))!=0) {
			//7.将读取的图片字节流写入到response缓冲区中，在页面中显示图片信息
			out.write(buf, 0, len);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}