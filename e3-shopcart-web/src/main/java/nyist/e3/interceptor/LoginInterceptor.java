package nyist.e3.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import nyist.e3.cookie.CookieUtils;
import nyist.e3.pojo.TbUser;
import nyist.e3.service.TokenService;
import nyist.e3.utils.E3Result;

/**
 * 登录功能拦截器
 * 
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	// token接口用来从redis中获取用户信息
	@Autowired
	private TokenService tokenService;
	/**
	 * handler执行之前，进行前处理，判断用户是否登录，将结过封装到request域中，然后放行
	 * 在controller中只需要判断request中是否有登录的用户信息，就可以判断用户是否登录状态
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//在方法执行之前拦截
		
		
		// 1、从token中取出用户信息，判断用户是否登录状态
		String token = CookieUtils.getCookieValue(request, "COOKIE_TOKEN_KEY");
		// 2、判断cookie是否过期
		if (StringUtils.isBlank(token)) {
			// 表示保存用户登录信息的session的key已经过期
			// 放行操作
			return true;
		}
		// 调用tokenService中的方法从redis中获取登录用户的信息
		E3Result e3result = tokenService.getToken(token);
		if (e3result.getStatus() != 200) {
			//表示session已经过期，放行操作
			return true;
		}
		//接收获取的登录用户的信息
		TbUser user = (TbUser) e3result.getData();
		//将登录用户的信息存入request域中，用来判断是否已经登录
		request.setAttribute("user", user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//方法（handler）执行之后，但是没有返回ModelAndView之前执行
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//在方法执行之后，返回返回值之后执行
		//可以再次处理异常
	}

}
