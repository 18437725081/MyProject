package nyist.web.servlet;

public class MyServletImpl  implements MyServlet{

	@Override
	public void init() {
		System.out.println("初始化servlet......");
	}

	@Override
	public void destroy() {
		System.out.println("启动servlet服务......");
	}

	@Override
	public void service() {
		System.out.println("销毁servlet......");
	}
	
}
