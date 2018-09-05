package nyist.e3.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nyist.e3.pojo.TbUser;
import nyist.e3.service.TokenService;
import nyist.e3.utils.E3Result;
import nyist.e3.utils.JsonUtils;
import nyist.e3.utils.redis.JedisClient;

/**
 * 取出token的值
 * 
 * @author Administrator
 *
 */
@Service
public class TokenServiceImpl implements TokenService {
	@Autowired
	private JedisClient jedisClient;

	/**
	 * 从redis中取出token的值 1、取值,判断是否为空，为空，表示session已经过期，需要重新登录 2、如果不为空重新设置过期时间
	 * 3、将json数据转化为TbUser对象，返回
	 */
	@Override
	public E3Result getToken(String token) {
		try {
			// 从redis中取值
			String json = jedisClient.get("USER_INFO:" + token);
			if (StringUtils.isBlank(json)) {
				// json为空，表示已经过期
				return E3Result.build(400, "session已经过期，请重新登录");
			}
			//将json对象转化为pojo对象
			TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
			//重新设置用户登录信息的过期时间
			jedisClient.expire("USER_INFO:" + token, 1800);
			//将获取的user信息使用E3Result包装后返回
			return E3Result.ok(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
