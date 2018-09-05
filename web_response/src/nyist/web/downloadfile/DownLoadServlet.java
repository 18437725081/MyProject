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
 * 完成文件下载的功能 1.文件何时下载？ 浏览器自身对文件有解析功能，如果浏览器能解析的文件，将会直接打开；如果不能解析的文件，显示下载选择项
 * 因此在浏览器不能够解析的时候下载文件 2.何时需要编写文件下载的代码 浏览器自身对需要下载的文件能够进行解析时，需要编写下载文件的代码，阻止浏览器解析
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
	 * 文件下载的步骤 1.获得下载的文件的文件名 2.获得文件在服务器中的位置 3.设置Content-type的值，显示下载的文件名
	 * 4.创建缓冲区，创建输入流对象 5.通过response对象得到输出流对象 6.将输入流对象中读取到缓冲区中的内如写入到输出流对象中
	 * 7.关闭通过new创建的输入流对象(通过response获得的输出流对象可以不用关闭，服务器会通过检测自动关闭)
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.获得下载的文件名
		String fileName = request.getParameter("fileName");
		// 8.获取下载的文件名称 ；通过设置MIME的值
		// 8.1获得下载文件的类型
		response.setContentType(this.getServletContext().getMimeType(fileName));
		// 8.2 告诉浏览器打开的方式是下载；防止浏览器解析
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		// 2.获得要下载的文件在服务器中的位置
		String path = this.getServletContext().getRealPath("download/" + fileName);
		// 3.创建缓冲区对象
		byte[] buf = new byte[1024];
		int len = 0;
		// 4.创建输入流对象
		InputStream in = new FileInputStream(path);
		// 5.通过response获得输出流对象
		ServletOutputStream out = response.getOutputStream();
		// 6.将文件读取到输入流中
		while ((len = in.read(buf)) > 0) {
			// 7.将文件通过输出流写到response缓冲区中
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
