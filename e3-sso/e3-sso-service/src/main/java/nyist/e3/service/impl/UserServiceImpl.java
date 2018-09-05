package nyist.e3.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nyist.e3.mapper.TbUserMapper;
import nyist.e3.pojo.TbUser;
import nyist.e3.pojo.TbUserExample;
import nyist.e3.pojo.TbUserExample.Criteria;
import nyist.e3.service.UserService;
import nyist.e3.utils.E3Result;
import nyist.e3.utils.JsonUtils;
import nyist.e3.utils.redis.JedisClient;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;

	/**
	 * 根据传入参数的不同，完成对应的数据库查询操作 分析： 传递的参数 1.param=username type=1 param=phone
	 * type=2 方法的返回值类型 E3Result ,返回的结果为true或者false
	 */
	@Override
	public E3Result check(String param, Integer type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		// type的取值为1,2 分别表示用户名和手机号
		// 如果type=1，表示检验用户名是否存在
		if (type == 1) {
			// 表示校验用户名是否存在
			criteria.andUsernameEqualTo(param);
		}
		// 如果type=2，表示校验手机号
		else if (type == 2) {
			// 添加查询条件
			criteria.andPhoneEqualTo(param);
		} else {
			return E3Result.build(400, "非法参数");
		}
		List<TbUser> listUser = userMapper.selectByExample(example);
		if (listUser == null || listUser.size() == 0) {
			// 表示可以使用
			return E3Result.ok(true);
		}
		return E3Result.ok(false);

	}

	/**
	 * 完成用户注册的功能 1.对表单中的数据进行非空的校验 2.补全注册用户的属性 3.完成用户信息查询数据库的操作 4.返回值类型为 E3Result
	 */
	@Override
	public E3Result register(TbUser user) {
		try {
			// 对用户名和密码进行非空的校验
			if (StringUtils.isBlank(user.getUsername())) {
				// 用户名不能为空
				return E3Result.build(400, "用户名不能为空");
			}
			if (StringUtils.isBlank(user.getUsername())) {
				return E3Result.build(400, "用户密码不能为空");
			}

			// 非空进行用户名是否 被占用的校验
			E3Result result = check(user.getUsername(), 1);
			if (!(boolean) result.getData()) {
				return E3Result.build(400, "用户名已经被占用");
			}
			// 校验手机号
			result = check(user.getPhone(), 2);
			if (!(boolean) result.getData()) {
				return E3Result.build(400, "手机号已经注册过用户");
			}
			user.setCreated(new Date());
			user.setUpdated(new Date());
			// 使用md5加密密码

			String userPass = DigestUtils.md5Hex((user.getPassword().getBytes()));
			user.setPassword(userPass);
			userMapper.insert(user);

			return E3Result.ok();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 用户登录的流程 1.接收传递的用户名和密码 2.登录成功后，创建token对象，Token相当于返回的jsessionid字符串，可以使用uuid
	 * 3.将用户信息保存至redis中，使用string类型保存 将token最为key，将TbUser对象转换为json保存
	 * 4.设置key的过期时间，模拟正常的session 5.将token写入cookie中 6.解决cookie的跨域问题
	 * 7，cookie的有效期，关闭浏览器无效 8.返回登录成功
	 * 
	 */
	@Override
	public E3Result login(String username, String password) {
		try {
			TbUserExample example = new TbUserExample();
			Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(username);
			List<TbUser> list = userMapper.selectByExample(example);
			if (list == null || list.size() == 0) {
				// 登录失败
				return E3Result.build(400, "用户名或密码错误");
			}
			//获得用户对象
			TbUser user = list.get(0);
			//校验用户密码
			if(!(user.getPassword()).equals(DigestUtils.md5Hex(password.getBytes()))) {
				//校验失败
				return E3Result.build(400, "用户名或者密码错误");
			}

			//登录成功
			//1.创建token对象，使用uuid
			String token = UUID.randomUUID().toString();
			//2.将uuid作为key，用户信息作为value值存入redis中
			jedisClient.set("USER_INFO:"+token, JsonUtils.objectToJson(user));
			//3.设置过期时间,半小时
			jedisClient.expire("USER_INFO:"+token, 1800);
			
			//4.返回登录成功的信息
			
			return E3Result.ok(token);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
