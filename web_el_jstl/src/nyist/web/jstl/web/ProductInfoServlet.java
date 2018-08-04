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
 * 点击商品的图片完成商品信息的展示
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
	 * 点击链接，完成商品信息的展示 1.获得商品的id值 2.根据id值查询数据库 3.对返回的结果进行非空的判断
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.获取页面中商品的id值
		response.setContentType("text/html;charset=utf-8");
		String pid = request.getParameter("pid");
		// 2.创建service层
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