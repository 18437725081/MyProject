package nyist.web.jstl.service;

import java.sql.SQLException;
import java.util.List;

import nyist.web.jstl.dao.ProductListDao;
import nyist.web.jstl.domain.Product;

public class ProductListService {

	public List<Product> findAllProduct() throws SQLException {
		ProductListDao productListDao = new ProductListDao();
		return productListDao.findAllProduct();
	}

}
