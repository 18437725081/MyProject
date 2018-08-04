package nyist.web.jstl.service;

import java.sql.SQLException;

import nyist.web.jstl.dao.ProductDao;
import nyist.web.jstl.domain.Product;

public class ProductService {
	ProductDao productDao = new ProductDao();

	public Product findProductById(String pid) throws SQLException {
		return productDao.findProductById(pid);
	}
}
