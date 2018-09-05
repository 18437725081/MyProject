package nyist.web.servlet.test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;

public class TestSimulationIE {
	//8.����һ��map����
	private HashMap<String, String> data = new HashMap<String,String>();
	
	@Before
	public void testReadWEBXml(){
		try {
			//1.��������������
			SAXReader saxReader = new SAXReader();
			//2.ʹ�ý���������web.xml�ļ��õ�document����
			Document document = saxReader.read("src/nyist/web/servlet/web.xml");
			//3.��ȡ��Ԫ�ؽڵ�
			Element rootElement = document.getRootElement();
			//4.��ȡ�ӽڵ�(servlet��servlet-mapping)
			List<Element> childElements = rootElement.elements();
			//5.����
			for (Element element : childElements) {
				//6.�ж�Ԫ�ص�����Ϊservlet��Ԫ�ؽڵ�
				if("servlet".equals(element.getName())){
					//7.�ֱ��ȡservletԪ�ؽڵ��servlet-name��servlet-class��ֵ
					String servletName = element.element("servlet-name").getText();
					String servletClass = element.element("servlet-class").getText();
					/*System.out.println(servletName);
					System.out.println(servletClass);*/
					data.put(servletName, servletClass);
				}
				//9.�ж�Ԫ�ص�����Ϊservlet-mapping��Ԫ�ؽڵ�
				if("servlet-mapping".equals(element.getName())){
					//10.�ֱ��ȡservletԪ�ؽڵ��servlet-name��servlet-class��ֵ
					String servletName = element.element("servlet-name").getText();
					String urlPattern = element.element("url-pattern").getText();
					//11.��servletName��Ϊkey����ȡservletClass��ֵ
					String servletClass = data.get(servletName);
					//12.��url-pattern��Ϊkey,servletClass��Ϊvalue�浽map��ȥ
					data.put(urlPattern, servletClass);
					//13.�Ƴ�servletName
					data.remove(servletName);
				}
			}
			System.out.println(data);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMyServlet(){
		try {
			//1.ģ�������������һ��url
			String url1 = "/myServlet";
			//2.��urlPattern��Ϊkey����ȡservletClass
			String className = data.get(url1);
			//3.ͨ��servletClass��ȡ�ֽ����ļ�
			Class clazz = Class.forName(className);
			//4.ͨ���ֽ����ļ�����ʵ������
			Object obj = clazz.newInstance();
			//5.ͨ���ֽ����ļ���ȡ����(������������һ���Ƿ������ƣ��ڶ��������Ƿ����Ĳ���)
			Method method = clazz.getMethod("service", null);
			//6.����invoke����ִ��ʵ����������ķ���(ǰ��д�ķ���init)��������������һ���ǵ��÷�����ʵ�����󣬵ڶ����Ƿ�����ʵ�Ρ�
			method.invoke(obj, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
