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
	 * ��ͨ�Ĳ���
	 */
	@Test
	public void testServlet() {
		MyServlet myServlet = new MyServletImpl();
		myServlet.init();
		myServlet.service();
		myServlet.destroy();

	}

	/**
	 * ʹ��dom4j����web.xml�ļ�
	 */
	@Test
	public void testDom4j() {
		try {
			// 1.����saxReader����
			SAXReader saxReader = new SAXReader();
			// 2.��ȡxml�ļ����õ�document����
			Document document = saxReader.read("src/nyist/web/servlet/web.xml");
			// 3.�õ�root�ڵ�
			Element rootElement = document.getRootElement();
			// 4.��ȡservlet��rootԪ��
			Element servletRoot = rootElement.element("servlet");
			// 5.��ȡServletElementԪ���е�servlet-name
			Element servletElement = servletRoot.element("servlet-name");
			// �õ�servlet-name��ֵ
			String servletName = servletElement.getText();
			// 6.�õ�servelt�ĵ�class ��servlet-class
			Element servletClass = servletRoot.element("servlet-class");
			// ��ӡservlet-class��ֵ
			String servletClassName = servletClass.getText();
			System.out.println(servletClassName);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}
}
