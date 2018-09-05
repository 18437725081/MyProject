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
	 * 1.ʹ��QueryRunner������ɲ�ѯ���еĹ���ʹ��MapListHandler��������
	 */
	@Test
	public void testQueryAll() {
		try {
			// 1.����QueryROunner����
			QueryRunner qr = new QueryRunner(C3p0Utils.getDataSource());
			// 2.ִ�ж����еĲ�ѯ����
			String sql = "select * from t_user";
			// 3.ʹ��MapListHandler()������н����
			List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
			// 4.���������
			for (Map<String, Object> map : list) {
				// 5.map���ϰ���key--value�ķ�ʽ���
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
	 * ��ѯһ����¼��ʹ��MapHandler��������
	 */
	@Test
	public void testQueryOne() {
		// 1.����QueryRunner����
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
	 * ��ѯ���У�ʹ��BeanListHandler��������
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
	 * ��ѯ�������У�ʹ��ColumnListHandler��������
	 */
	@Test
	public void testQueryAll3() {
		try {
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_user";
			// ʹ��columnListHandler����õ�ָ���ĵ��н���� �����磺����username
			// ���õ��Ľ����ֻ��username��һ��
			List<Object> list = qr.query(C3p0Utils.getConn(), sql, new ColumnListHandler("username"));
			for (Object user : list) {
				System.out.println(user);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * ��ѯָ��idֵ��user����,ʹ��beanHandlee��������
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
	 * ʹ��scalaHandler����ۺϺ����Ľ��
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
	 * ʹ��queryRunner�����ݿ�����¼
	 */
	@Test
	public void testInsert1() {
		try {
			QueryRunner qr = new QueryRunner();
			String sql = "insert into t_user values(null,?,?)";
			Object[] params = { "������", "1234" };
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
