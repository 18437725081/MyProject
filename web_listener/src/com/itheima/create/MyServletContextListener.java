package com.itheima.create;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener{

	@Override
	//����context�����Ĵ���
	public void contextInitialized(ServletContextEvent sce) {
		//���Ǳ������Ķ���---ServletContext
		//ServletContext servletContext = sce.getServletContext();
		//getSource���Ǳ������Ķ���  ��ͨ�õķ���
		//ServletContext source = (ServletContext) sce.getSource();
		//System.out.println("context������....");
		
		//����һ����Ϣ�������----ÿ������12�� ��Ϣһ��
		//Timer timer = new Timer();
		//task:����  firstTime����һ��ִ��ʱ��  period�����ִ��ʱ��
		//timer.scheduleAtFixedRate(task, firstTime, period);
		/*timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println("���м�Ϣ��.....");
			}
		} , new Date(), 5000);*/
		
		
		
		
		//�޸ĳ�������ʵ��Ϣҵ��
		//1����ʼʱ�䣺 ���������12��
		//2�����ʱ�䣺24Сʱ
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//String currentTime = "2016-08-19 00:00:00";
		String currentTime = "2016-08-18 09:34:00";
		Date parse = null;
		try {
			parse = format.parse(currentTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println("���м�Ϣ��.....");
			}
		} , parse, 24*60*60*1000);*/
		
	}

	//����context����������
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context������....");
		
	}

}
