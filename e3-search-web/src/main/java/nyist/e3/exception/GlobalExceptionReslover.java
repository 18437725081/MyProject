package nyist.e3.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * springmvc提供的处理异常的接口
 * 1.实现HandlerExceptionReslover接口
 * 2.在spring中注册
 * 3.配置error的界面
 * @author Administrator
 *
 */
public class GlobalExceptionReslover implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		final Logger logger = LoggerFactory.getLogger(GlobalExceptionReslover.class);
		//异常处理的几种方式
		//1.直接打印控制台
		System.out.println(ex);
		//2.将错误信息写入日志
		logger.error(ex.toString());
		//展示用户错误的界面
		ModelAndView modelView =  new ModelAndView();
		modelView.addObject("message", "系统发生异常，请稍后再试...");
		modelView.setViewName("error/exception");
		
		//3.可以发送短信，写邮件
		return null;
	}

}
