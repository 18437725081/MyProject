package nyist.web.servlet.test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import nyist.web.servlet.MyServlet;

/**
 * ͨ�������xml�ļ�������ɶ�servlet�з����ĵ���
 * 
 * @author Administrator
 *
 */
public class TestReflection {
	/**
	 * ������ 1.���xml�ļ���������SAXReader��������xml�ļ� 2.ͨ���ļ���������õ�xml�ļ��е�rootԪ��
	 * 3.ͨ��rootԪ�صõ���Ԫ�� 4.������Ԫ�أ��õ�servlet-class 5.ͨ���������õ����Ե�servlet�Ķ���
	 * 6��ͨ��������÷���
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		try {
			// 1.����xml��������
			SAXReader saxReader = new SAXReader();
			// 2.����xml�ļ�
			Document document = saxReader.read("src/nyist/web/servlet/web.xml");
			//3.���rootԪ��
			Element rootElement = document.getRootElement();
			//4.���servletԪ��
			Element servlet = rootElement.element("servlet");
			//5.���servlet-class��ֵ���õ����е�servlet��
			String servletClass = servlet.element("servlet-class").getText();
			//6.ͨ������õ����е�servlet��class�ļ�
			Class<MyServlet> clazz = (Class<MyServlet>) Class.forName(servletClass);
			//7.����ʵ������
			MyServlet myServlet = clazz.newInstance();
			myServlet.init();
			myServlet.service();
			myServlet.destroy();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
