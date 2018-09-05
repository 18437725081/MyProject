package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloFilter implements Filter{
	//filter在服务器关闭的时候销毁
	@Override
	public void destroy() {
		System.out.println("过滤器销毁了.....");
	}
	//在filter的声明周期中执行doFilter方法
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("过滤器执行操作.......");
		chain.doFilter(request, response); //放行操作
	}
	//filter对象在服务器启动的时候创建，init方法在filter对象创建后执行
	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		String filterName = filterconfig.getFilterName();
		System.out.println(filterName);
		System.out.println(filterconfig.getServletContext());

	}
	
}
