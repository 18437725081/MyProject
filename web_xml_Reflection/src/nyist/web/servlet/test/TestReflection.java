package nyist.web.servlet.test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import nyist.web.servlet.MyServlet;

/**
 * 通过反射和xml文件解析完成对servlet中方法的调用
 * 
 * @author Administrator
 *
 */
public class TestReflection {
	/**
	 * 分析： 1.获得xml文件解析对象SAXReader，并加载xml文件 2.通过文件解析对象得到xml文件中的root元素
	 * 3.通过root元素得到子元素 4.遍历子元素，得到servlet-class 5.通过反射代码得到测试的servlet的对象
	 * 6。通过对象调用方法
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		try {
			// 1.创建xml解析对象
			SAXReader saxReader = new SAXReader();
			// 2.加载xml文件
			Document document = saxReader.read("src/nyist/web/servlet/web.xml");
			//3.获得root元素
			Element rootElement = document.getRootElement();
			//4.获得servlet元素
			Element servlet = rootElement.element("servlet");
			//5.获得servlet-class的值，拿到运行的servlet类
			String servletClass = servlet.element("servlet-class").getText();
			//6.通过反射得到运行的servlet的class文件
			Class<MyServlet> clazz = (Class<MyServlet>) Class.forName(servletClass);
			//7.创建实例对象
			MyServlet myServlet = clazz.newInstance();
			myServlet.init();
			myServlet.service();
			myServlet.destroy();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
