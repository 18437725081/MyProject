package nyist.jdbc.DataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

import nyist.jdbc.utils.JDBCUtils_v3;

public class MyDataSource implements DataSource {
	private static LinkedList<Connection> pool = new LinkedList<Connection>();
	private static Connection conn = null;
	//���徲̬����飬����3�����ӣ������õ����ӳ���
	static {
		for (int i = 0; i < 3; i++) {
			conn = JDBCUtils_v3.getConnection();
			//ʹ��װ�ι������Ӷ���
			MyConnection myconn = new MyConnection(conn, pool);
			//����pool�е����Ѿ���ǿ������
			pool.add(myconn);
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		if(pool.size()<=0) {
			for (int i = 0; i < 3; i++) {
				conn = JDBCUtils_v3.getConnection();
				MyConnection myconn = new MyConnection(conn, pool);
				pool.add(myconn);
			}
		}
		return pool.remove(0);
	}
	
	public void close(Connection conn) {
//		 pool.add(conn);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
