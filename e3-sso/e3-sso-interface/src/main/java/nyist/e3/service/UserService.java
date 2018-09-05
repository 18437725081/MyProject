package nyist.e3.service;

import nyist.e3.pojo.TbUser;
import nyist.e3.utils.E3Result;

public interface UserService {
	E3Result check(String param,Integer type);

	E3Result register(TbUser user);

	E3Result login(String username, String password);
}
