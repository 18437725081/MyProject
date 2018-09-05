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
 * 解决下载的文件名是中文的问题
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
		// 1.获得下载的文件名称
		String fileName = request.getParameter("fileName");
		// 设置之后 fileName= 美女.jpg，可以正常下载，下载后的文件名为 jpg，加上前缀图片能正常显示
		fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8"); // 解决图片名称显示为
																		// "???.jpg",不能显示中文的问题

		// 9.获得请求头中的User-Agent
		String agent = request.getHeader("User-Agent"); // 解决的是下载后的文件名为"jpg"的问题
		// 根据不同浏览器进行不同的编码
		String filenameEncoder = "";
		if (agent.contains("MSIE")) {
			// IE浏览器
			filenameEncoder = URLEncoder.encode(fileName, "utf-8");
			filenameEncoder = filenameEncoder.replace("+", " ");
		} else if (agent.contains("Firefox")) {
			// 火狐浏览器
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filenameEncoder = "=?utf-8?B?" + base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";
		} else {
			// 其它浏览器
			filenameEncoder = URLEncoder.encode(fileName, "utf-8");
		}

		// 2.设置下载的文件的类型
		response.setContentType(this.getServletContext().getMimeType(fileName));
		// 3.告诉客户端（浏览器）改资源不是直接解析，显示下载 filename的值是已经处理过编码的文件名
		response.setHeader("Content-Disposition", "attachmnet;filename=" + filenameEncoder);
		// 4.获得下载的文件在服务器中的地址
		String realPath = this.getServletContext().getRealPath("download/" + fileName);
		// 5.创建写入流对象
		InputStream in = new FileInputStream(realPath);
		// 6.通过response对象获得文件输出流对象
		ServletOutputStream out = response.getOutputStream();
		// 7.创建缓冲区对象， 定义变量
		byte[] buf = new byte[1024];
		int len = 0;
		// 8.将下载的文件读取到输入流的缓冲区中
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		// 10.关闭流对象
		in.close();
		// out.close(); //可以不用关闭，服务器会自动检测，关闭该流对象
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}