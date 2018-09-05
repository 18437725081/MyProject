package com.itheima.attribute;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener{

	@Override
	public void attributeAdded(ServletContextAttributeEvent scab) {
		//�ŵ����е�����
		System.out.println(scab.getName());//�ŵ����е�name
		System.out.println(scab.getValue());//�ŵ����е�value
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scab) {
		System.out.println(scab.getName());//ɾ�������е�name
		System.out.println(scab.getValue());//ɾ�������е�value
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		System.out.println(scab.getName());//����޸�ǰ��name
		System.out.println(scab.getValue());//����޸�ǰ��value
	}

}
