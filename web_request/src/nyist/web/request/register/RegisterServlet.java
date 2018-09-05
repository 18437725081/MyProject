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
 * 完成用户的注册功能，将数据插入数据库
 * 
 * @author Administrator
 *
 */
public class RegisterServlet extends HttpServlet {
	/**
	 * 需求分析： 1.获得表单中的请求参数（使用BeanUtils中的方法完成） 2.插入数据库（使用dbutils工具类中的方法完成）
	 * 3.完成注册功能
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1.获得请求参数(原始的方式)
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 如果为post请求的方式,可以使用下面的方法解决中文乱码的问题
		// request.setCharacterEncoding("UTF-8");

		// 如果为get请求，可以使用下面的方式解决
		// get请求乱码产生的原因 ：王帅------在xxxx.jsp页面使用utf-8编码--servlet中使用iso8859-1解码
		// 乱码的解决：iso8859-1进行重新编码 username.getBytes("iso8859-1")---使用utf-8解码new
		// String(bytes,"utf-8")---王帅

		// 下面的代码表示使用iso8859-1对得到的请求参数进行重编码，然后在使用utf-8解码
		username = new String(username.getBytes("iso8859-1"), "utf-8");

		System.out.println(username);
		System.out.println(password);
		// 1.1借助工具获取请求的参数
		// 1.2创建user对象
		User user = new User();
		// 1.3获得请求参数的map集合
		Map<String, String[]> properties = request.getParameterMap();
		try {
			// 1.4 使用该方法可以将map中的key（请求参数的name） 和User对象中的属性形成映射关系，自动匹配
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		// 1.5因为 uid的值是String类型的，非自动增长类型的，因此设置uuid的值为32位的UUID值
		user.setUid(UUID.randomUUID().toString());
		// 2.使用DBUtils完成插入数据库的功能
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		try {
			// 执行插入操作，如果没有值，用null替代
			qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), null,
					user.getBirthday(), user.getSex(), null, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 3.注册成功后跳转到登录界面 (使用的重定向，虽然转发的性能更好，但是转发的地址栏不会发生变化，如果刷新表单的话，会出现问题)
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}