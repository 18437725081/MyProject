package nyist.jdbc.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import nyist.jdbc.domain.User;
import nyist.jdbc.utils.C3p0Utils;
import nyist.jdbc.utils.DBCPUtils;

public class TestDBUtils {
	/**
	 * 1.使用QueryRunner对象完成查询所有的功能使用MapListHandler处理结果集
	 */
	@Test
	public void testQueryAll() {
		try {
			// 1.创建QueryROunner对象
			QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
			// 2.执行对象中的查询方法
			String sql = "select * from t_user";
			// 3.使用MapListHandler()处理多行结果集
			List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
			// 4.遍历结果集
			for (Map<String, Object> map : list) {
				// 5.map集合按照key--value的方式输出
				System.out.println(map);
				Set<Entry<String,Object>> entrySet = map.entrySet();
				for (Entry<String, Object> entry : entrySet) {
					String key = entry.getKey();
					Object value = entry.getValue();
					System.out.println(key+"==="+value);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 查询一条记录，使用MapHandler处理结果集
	 */
	@Test
	public void testQueryOne() {
		// 1.创建QueryRunner对象
		QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from t_user where uid=?";
		Object[] params = { 1 };
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), params);
			System.out.println(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 查询所有，使用BeanListHandler处理结果集
	 */
	@Test
	public void testQueryAll2() {
		try {
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_user";
			List<User> list = qr.query(C3p0Utils.getConn(), sql, new BeanListHandler<User>(User.class));
			for (User user : list) {
				System.out.println(user);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 查询单列所有，使用ColumnListHandler处理结果集
	 */
	@Test
	public void testQueryAll3() {
		try {
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_user";
			// 使用columnListHandler对象得到指定的单列结果集 ：例如：传入username
			// ，得到的结果中只有username这一列
			List<Object> list = qr.query(C3p0Utils.getConn(), sql, new ColumnListHandler("username"));
			for (Object user : list) {
				System.out.println(user);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 查询指定id值的user对象,使用beanHandlee处理结果集
	 */
	@Test
	public void testQueryOne1() {
		try {
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_user where uid=?";
			@SuppressWarnings("deprecation")
			Object u = qr.query(C3p0Utils.getConn(), sql, "1", new BeanHandler<User>(User.class));
			System.out.println(u);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 使用scalaHandler处理聚合函数的结果
	 */
	@Test
	public void testQueryCount() {
		try {
			QueryRunner qr = new QueryRunner();
			String sql = "select count(*) from t_user";
			Long count = (Long) qr.query(DBCPUtils.getConn(), sql, new ScalarHandler());
			System.out.println(count.intValue());

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 使用queryRunner向数据库插入记录
	 */
	@Test
	public void testInsert1() {
		try {
			QueryRunner qr = new QueryRunner();
			String sql = "insert into t_user values(null,?,?)";
			Object[] params = { "景德镇", "1234" };
			int row = qr.update(DBCPUtils.getConn(), sql, params);
			if (row > 0) {
				System.out.println("success");
			} else {
				System.out.println("failure");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
