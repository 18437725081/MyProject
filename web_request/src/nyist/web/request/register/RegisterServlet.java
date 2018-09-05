package nyist.web.request.register;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import nyist.web.request.domain.User;
import nyist.web.request.utils.C3P0Utils;

/**
 * ����û���ע�Ṧ�ܣ������ݲ������ݿ�
 * 
 * @author Administrator
 *
 */
public class RegisterServlet extends HttpServlet {
	/**
	 * ��������� 1.��ñ��е����������ʹ��BeanUtils�еķ�����ɣ� 2.�������ݿ⣨ʹ��dbutils�������еķ�����ɣ�
	 * 3.���ע�Ṧ��
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1.����������(ԭʼ�ķ�ʽ)
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// ���Ϊpost����ķ�ʽ,����ʹ������ķ�������������������
		// request.setCharacterEncoding("UTF-8");

		// ���Ϊget���󣬿���ʹ������ķ�ʽ���
		// get�������������ԭ�� ����˧------��xxxx.jspҳ��ʹ��utf-8����--servlet��ʹ��iso8859-1����
		// ����Ľ����iso8859-1�������±��� username.getBytes("iso8859-1")---ʹ��utf-8����new
		// String(bytes,"utf-8")---��˧

		// ����Ĵ����ʾʹ��iso8859-1�Եõ���������������ر��룬Ȼ����ʹ��utf-8����
		username = new String(username.getBytes("iso8859-1"), "utf-8");

		System.out.println(username);
		System.out.println(password);
		// 1.1�������߻�ȡ����Ĳ���
		// 1.2����user����
		User user = new User();
		// 1.3������������map����
		Map<String, String[]> properties = request.getParameterMap();
		try {
			// 1.4 ʹ�ø÷������Խ�map�е�key�����������name�� ��User�����е������γ�ӳ���ϵ���Զ�ƥ��
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		// 1.5��Ϊ uid��ֵ��String���͵ģ����Զ��������͵ģ��������uuid��ֵΪ32λ��UUIDֵ
		user.setUid(UUID.randomUUID().toString());
		// 2.ʹ��DBUtils��ɲ������ݿ�Ĺ���
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		try {
			// ִ�в�����������û��ֵ����null���
			qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), null,
					user.getBirthday(), user.getSex(), null, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 3.ע��ɹ�����ת����¼���� (ʹ�õ��ض�����Ȼת�������ܸ��ã�����ת���ĵ�ַ�����ᷢ���仯�����ˢ�±��Ļ������������)
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}