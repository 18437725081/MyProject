package nyist.web.servlet.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import nyist.web.servlet.MyServlet;
import nyist.web.servlet.MyServletImpl;

public class TestDom4j {

	/**
	 * 普通的测试
	 */
	@Test
	public void testServlet() {
		MyServlet myServlet = new MyServletImpl();
		myServlet.init();
		myServlet.service();
		myServlet.destroy();

	}

	/**
	 * 使用dom4j解析web.xml文件
	 */
	@Test
	public void testDom4j() {
		try {
			// 1.创建saxReader对象
			SAXReader saxReader = new SAXReader();
			// 2.读取xml文件，得到document对象
			Document document = saxReader.read("src/nyist/web/servlet/web.xml");
			// 3.得到root节点
			Element rootElement = document.getRootElement();
			// 4.获取servlet的root元素
			Element servletRoot = rootElement.element("servlet");
			// 5.获取ServletElement元素中的servlet-name
			Element servletElement = servletRoot.element("servlet-name");
			// 得到servlet-name的值
			String servletName = servletElement.getText();
			// 6.得到servelt的的class 即servlet-class
			Element servletClass = servletRoot.element("servlet-class");
			// 打印servlet-class的值
			String servletClassName = servletClass.getText();
			System.out.println(servletClassName);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}
}
