package nyist.e3.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nyist.e3.service.TokenService;
import nyist.e3.utils.E3Result;
import nyist.e3.utils.JsonUtils;

@Controller
public class TokenController {
	@Autowired
	private TokenService tokenService;

	/**
	 * 获取jedis中key为token的value值（登录用户的信息） 参数：token 使用jsonp实现跨域请求
	 * 跨域：端口号不同或者域名不同都成为跨域 解决跨域请求的问题 1.在使用ajax请求中dataType的值为jsonp
	 * 2.设置响应的类型为type/json格式 3.拼接成mycall({id:1,name:z});格式的数据返回
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/user/token/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getTokenName(@PathVariable String token, String callback) {
		E3Result result = tokenService.getToken(token);
		if (StringUtils.isNotBlank(callback)) {
			// 拼接成页面需要的数据给事
			// mycall({id:1,name:z});
			String json = callback + "(" + JsonUtils.objectToJson(result) + ");";
			System.out.println(json);
			return json;
		}
		return JsonUtils.objectToJson(result);
	}
}
