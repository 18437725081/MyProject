package nyist.web.cookie;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import nyist.web.cookie.domain.User;
import nyist.web.cookie.utils.C3P0Utils;
/**
 * ��ɵ�¼������֤�����֤����
 * 1.���������֤��ͼƬ������
 * 2.����û��������֤���ֵ
 * 3.����һ���͵ڶ����е�ֵ���бȶԣ������ͬ������û�����������֤��¼���������ͬ����return����ֹ����
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ύ��ʽΪpost�ύ����������������ʹ������ķ�ʽ
		request.setCharacterEncoding("utf-8");
		//1.����û��������֤���ֵ
		String checkcode_input = request.getParameter("checkCode");
		//2.���������֤���ԭʼֵ������ʹ��session������
		String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
		request.getSession().removeAttribute(checkcode_session);
		//3.�����ε���֤����бȽ�
		if(!checkcode_input.equals(checkcode_session)) {
			//3.1��ʾ�û����������ֹ��¼���������Դ�����Ϣ
			request.setAttribute("info", "���������֤�벻��ȷ");
			//3.2��ҳ��ת������¼����
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			//3.3��ֹ��¼����
			return;
		}
		//4.��ʾ��֤����ȷ������û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//5.��ѯ���ݿ�
		QueryRunner qr  = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select username ,password from user where username=? and password=?";
		User user=null;
		try {
			 user = qr.query(sql, new BeanHandler<User>(User.class),username,password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//6.�Բ�ѯ���������֤
		if(user!=null) {
			//6.1��ʾ��¼�ɹ�
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else {
			//6.2��¼ʧ�ܣ����Դ�����Ϣ
			request.setAttribute("info", "�û��������������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}