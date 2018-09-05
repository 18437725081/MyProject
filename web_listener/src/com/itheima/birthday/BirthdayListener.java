package com.itheima.birthday;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.mail.MailUtils;

public class BirthdayListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("hdlloe.....");
		
		//��webӦ������ �����������---�������û������յ�ǰ�����ʼ�
		//����һ����ʱ��
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				// Ϊ��ǰ�����յ��û����ʼ�
				//1����ý�������յ���
				//��ý��������
				SimpleDateFormat format = new SimpleDateFormat("MM-dd");
				String currentDate = format.format(new Date());
				System.out.println(currentDate);
				//���ݵ�ǰʱ������ݲ�ѯ��������յ���
				QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
				String sql = "select * from customer where birthday like ?";
				List<Customer> customerList = null;
				try {
					customerList = runner.query(sql, new BeanListHandler<Customer>(Customer.class) ,"%"+currentDate+"%");
				} catch (SQLException e) {
					e.printStackTrace();
				} //08-18
				//2�����ʼ�
				if(customerList!=null&&customerList.size()>0){
					for(Customer c : customerList){
						String emailMsg = "�װ��ģ�"+c.getRealname()+",���տ��֣�";
						try {
							MailUtils.sendMail(c.getEmail(), "����ף��", emailMsg);
							System.out.println(c.getRealname()+"�ʼ��������");
						} catch (MessagingException e) {
							e.printStackTrace();
						}
					}
				}
				
				
			}
		}, new Date(), 1000*10);
		//ʵ�ʿ�������ʼʱ����һ���̶���ʱ��
		//ʵ�ʿ����м��ʱ����1��
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
