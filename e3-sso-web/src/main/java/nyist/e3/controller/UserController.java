package nyist.e3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nyist.e3.cookie.CookieUtils;
import nyist.e3.pojo.TbUser;
import nyist.e3.service.UserService;
import nyist.e3.utils.E3Result;

/**
 * 单点登录的controller
 * 
 * @author Administrator
 *
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	// 跳转到注册页面
	@RequestMapping("/page/register")
	public String showRegister() {
		return "register";
	}

	/**
	 * 进行ajax的校验 1.校验用户名是否被占用 2，校验手机号码是否正确
	 */

	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public E3Result check(@PathVariable String param, @PathVariable Integer type) {
		E3Result result = userService.check(param, type);
		return result;
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	@ResponseBody
	public E3Result register(TbUser user) {
		E3Result result = userService.register(user);
		return result;
	}

	/**
	 * 显示用户登录的页面
	 * 
	 * @return
	 */
	@RequestMapping("/page/login")

	public String loginPage() {
		return "login";
	}

	/**
	 * 实现用户登录的功能 1.调用service的登录方法完成登录的功能 2.从session中取出token，写入cookie中 3.然后结果
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public E3Result login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		E3Result result = userService.login(username, password);
		if (result.getStatus() == 200) {
			String token = result.getData().toString();
			CookieUtils.setCookie(request, response, "COOKIE_TOKEN_KEY", token);
		}
		// 响应数据，
		return result;
	}

}
