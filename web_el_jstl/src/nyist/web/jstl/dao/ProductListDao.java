package nyist.web.jstl.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import nyist.web.jstl.domain.Product;
import nyist.web.jstl.utils.C3P0Utils;

/**
 * dao层 与数据库交互，查询所有的商品
 * 
 * @author Administrator
 *
 */
public class ProductListDao {
	/**
	 * 查询所有的商品
	 * 
	 * @throws SQLException
	 */
	public List<Product> findAllProduct() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		List<Product> productList = qr.query("select * from product", new BeanListHandler<Product>(Product.class));
		if (productList != null) {
			return productList;
		}
		return null;
	}

}
