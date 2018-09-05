package nyist.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequestFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	// 使用装饰者模式对request进行加强
	/**
	 * 装饰者模式的三要素 1.要求装饰者和被装饰者实现统一接口 2.被装饰者最为参数传递给装饰者
	 * 3.只对需要加强的方法进行装饰，不需要加强的方法不用进行装饰
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request1 = (HttpServletRequest) req;

		// 1.将需要增强的类作为参数传递给装饰者类
		EnhanceRequest request = new EnhanceRequest(request1);
		chain.doFilter(request, resp);//放行(将增强的request最为参数传递)

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

// 2.满足了装饰者模式的第二个要素：request本身也是继承的HttpServletRequestWrapper类，因此装饰者类也要继承这个类
class EnhanceRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	public EnhanceRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	//3. 重写需要增强的方法（装饰）
	@Override
	public String getParameter(String name) {
		String parameter = request.getParameter(name);
		try {
			// 解决get请求的乱码问题
			parameter = new String(parameter.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return parameter;
	}
}
