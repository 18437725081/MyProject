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
	//filter�ڷ������رյ�ʱ������
	@Override
	public void destroy() {
		System.out.println("������������.....");
	}
	//��filter������������ִ��doFilter����
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("������ִ�в���.......");
		chain.doFilter(request, response); //���в���
	}
	//filter�����ڷ�����������ʱ�򴴽���init������filter���󴴽���ִ��
	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		String filterName = filterconfig.getFilterName();
		System.out.println(filterName);
		System.out.println(filterconfig.getServletContext());

	}
	
}
