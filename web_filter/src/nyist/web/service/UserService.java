package nyist.web.service;

import java.sql.SQLException;

import nyist.web.dao.UserDao;
import nyist.web.domain.User;

public class UserService {

	public User login(String username, String password) {
		UserDao dao = new UserDao();
		try {
			return dao.login(username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
