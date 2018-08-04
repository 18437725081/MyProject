package nyist.web.jstl.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nyist.web.jstl.domain.Product;
import nyist.web.jstl.service.ProductListService;

/**
 * 首页显示所有的商品信息，使用javaee规范中的三层结构 MVC是web的设计模式 M:javabean V：jsp页面 C：servlet控制层
 * 1.web层--ProductListServlet 完成与客户端的交互
 * 2.service层---ProductListService--处理核心的业务逻辑 3.dao层--ProductListDao--与数据库交互
 * 
 * @author Administrator
 *
 */
public class ProductListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建service层的对象
		ProductListService productListService = new ProductListService();
		List<Product> productList = null;
		try {
			// 1.调用service层
			productList = productListService.findAllProduct();
			if (productList != null) {
				// 2.将查询的结果填充到request域中
				request.setAttribute("productList", productList);
				// 3.转发到商品列表页面
				request.getRequestDispatcher("/product_list.jsp").forward(request, response);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}