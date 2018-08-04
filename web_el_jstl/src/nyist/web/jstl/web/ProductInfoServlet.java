package nyist.web.jstl.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyist.web.jstl.domain.Product;
import nyist.web.jstl.service.ProductService;

/**
 * �����Ʒ��ͼƬ�����Ʒ��Ϣ��չʾ
 * 
 * @author Administrator
 *
 */
public class ProductInfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ������ӣ������Ʒ��Ϣ��չʾ 1.�����Ʒ��idֵ 2.����idֵ��ѯ���ݿ� 3.�Է��صĽ�����зǿյ��ж�
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.��ȡҳ������Ʒ��idֵ
		response.setContentType("text/html;charset=utf-8");
		String pid = request.getParameter("pid");
		// 2.����service��
		ProductService productService = new ProductService();
		try {
			Product product = productService.findProductById(pid);
			request.setAttribute("product", product);
			request.getRequestDispatcher("/product_info.jsp").forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}