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
 * ��ҳ��ʾ���е���Ʒ��Ϣ��ʹ��javaee�淶�е�����ṹ MVC��web�����ģʽ M:javabean V��jspҳ�� C��servlet���Ʋ�
 * 1.web��--ProductListServlet �����ͻ��˵Ľ���
 * 2.service��---ProductListService--������ĵ�ҵ���߼� 3.dao��--ProductListDao--�����ݿ⽻��
 * 
 * @author Administrator
 *
 */
public class ProductListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ����service��Ķ���
		ProductListService productListService = new ProductListService();
		List<Product> productList = null;
		try {
			// 1.����service��
			productList = productListService.findAllProduct();
			if (productList != null) {
				// 2.����ѯ�Ľ����䵽request����
				request.setAttribute("productList", productList);
				// 3.ת������Ʒ�б�ҳ��
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