package nyist.web.servlet;

public class MyServletImpl  implements MyServlet{

	@Override
	public void init() {
		System.out.println("��ʼ��servlet......");
	}

	@Override
	public void destroy() {
		System.out.println("����servlet����......");
	}

	@Override
	public void service() {
		System.out.println("����servlet......");
	}
	
}
