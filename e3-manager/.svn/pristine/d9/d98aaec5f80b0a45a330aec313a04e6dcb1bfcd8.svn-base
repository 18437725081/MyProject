/*package nyist.activemq.test;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ActiveMQTest {
	*//**
	 * 使用queue发送消息
	 *//*
	@Test
	public void testSpringActiveMQ() {
		// 加载spring容器
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-activemq.xml");
		// 获得jmstemplate对象
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		// 获得目的地对象
		// Queue queue = (Queue) context.getBean("queueDestination");
		// 获得topic目的地对象
		Topic topic = (Topic) context.getBean("topicDestination");
		// 发送消息
		jmsTemplate.send(topic, new MessageCreator() {

			// 创建消息对象，返回创建的消息内容
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage("activeMQ整合Spring的测试");
				return message;
			}
		});
	}

	*//**
	 * 接收消息
	 * 
	 * @throws JMSException
	 * @throws IOException
	 *//*

	@Test
	public void testActiveMQConsumer() throws JMSException, IOException {
		// 1.创建连接工厂
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.43.147:61616");
		// 2.创建连接对象
		Connection connection = connectionFactory.createConnection();
		// 3.开启连接
		connection.start();
		// 4.创建session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5.创建Destination对象,和消息生产者的一致
		Queue queue = session.createQueue("spring-queue");
		// 6.创建消息的消费者对象
		MessageConsumer consumer = session.createConsumer(queue);
		// 7.接收消息
		consumer.setMessageListener(new MessageListener() {

			// 接收消息的事件，消费者会一直处于监听状态，当Producer发送消息后，Consumer监听到，然后触发这个事件
			@Override
			public void onMessage(Message message) {
				System.out.println("消费者已经接收到生产者生产的消息....");
				try {
					// 将接收的消息转换成TextMessage对象，和消息的生产者保持一致
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					System.out.println("消费者消费的消息是：" + text);
				} catch (JMSException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		System.in.read();
		// 8.关闭连接
		consumer.close();
		session.close();
		connection.close();
	}

	*//**
	 * 测试主题的consumer
	 * 
	 * @throws JMSException
	 * @throws IOException
	 *//*
	@Test
	public void testActiveMQTopic() throws JMSException, IOException {
		// 1.创建连接工厂
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.43.147:61616");
		// 2.创建连接对象
		Connection connection = connectionFactory.createConnection();
		// 3.开启连接
		connection.start();
		// 4.创建session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5.创建Destination对象,和消息生产者的一致
		Queue queue = session.createQueue("spring-queue");
		// 6.创建消息的消费者对象
		MessageConsumer consumer = session.createConsumer(queue);
		// 7.接收消息
		consumer.setMessageListener(new MessageListener() {

			// 接收消息的事件，消费者会一直处于监听状态，当Producer发送消息后，Consumer监听到，然后触发这个事件
			@Override
			public void onMessage(Message message) {
				System.out.println("消费者已经接收到生产者生产的消息....");
				try {
					// 将接收的消息转换成TextMessage对象，和消息的生产者保持一致
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					System.out.println("消费者消费的消息是：" + text);
				} catch (JMSException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		System.in.read();
		// 8.关闭连接
		consumer.close();
		session.close();
		connection.close();
	}
}
*/