package nyist.web.request.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import nyist.web.request.domain.User;
import nyist.web.request.utils.C3P0Utils;

/**
 * ʵ���û���¼�Ĺ���
 * 
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.�������Ĳ���
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 2.��ѯ���ݿ����Ƿ���ڴ�����¼
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select username,password from user where username=? and password=?";
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class), username, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user != null) {
			// ��ʾ��¼�ɹ����ض�����ת����ҳ����ַ���ĵ�ַ��Ҫ�仯������ʹ���ض���
			request.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			// ��ʾ��¼ʧ�ܣ�����ת������¼ҳ�棬���Դ�����Ϣ
			request.setAttribute("info", "�û��������������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}