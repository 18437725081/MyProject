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

	// ʹ��װ����ģʽ��request���м�ǿ
	/**
	 * װ����ģʽ����Ҫ�� 1.Ҫ��װ���ߺͱ�װ����ʵ��ͳһ�ӿ� 2.��װ������Ϊ�������ݸ�װ����
	 * 3.ֻ����Ҫ��ǿ�ķ�������װ�Σ�����Ҫ��ǿ�ķ������ý���װ��
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request1 = (HttpServletRequest) req;

		// 1.����Ҫ��ǿ������Ϊ�������ݸ�װ������
		EnhanceRequest request = new EnhanceRequest(request1);
		chain.doFilter(request, resp);//����(����ǿ��request��Ϊ��������)

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

// 2.������װ����ģʽ�ĵڶ���Ҫ�أ�request����Ҳ�Ǽ̳е�HttpServletRequestWrapper�࣬���װ������ҲҪ�̳������
class EnhanceRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	public EnhanceRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	//3. ��д��Ҫ��ǿ�ķ�����װ�Σ�
	@Override
	public String getParameter(String name) {
		String parameter = request.getParameter(name);
		try {
			// ���get�������������
			parameter = new String(parameter.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return parameter;
	}
}
