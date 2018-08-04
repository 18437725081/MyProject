package nyist.web.jstl.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import nyist.web.jstl.domain.Product;
import nyist.web.jstl.utils.C3P0Utils;

public class ProductDao {

	public Product findProductById(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		Product product = qr.query("select * from product where pid=?", new BeanHandler<Product>(Product.class),pid);
		if(product!=null) {
			return product;
		}
		
		return null;
	}
	
}
