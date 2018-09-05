/*package nyist.e3.test.activemq;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
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

*//**
 * 测试activeMQ的两种传递消息的方式 点对点：PTP 发布订阅：Publish/Subscribe
 * 
 * @author Administrator
 *
 *//*
public class ActiveMQTest {
	*//**
	 * 创建PTP的消息生产者 1.创建connectionFactory工厂对象 2.创建connection对象,并开启连接
	 * 3.创建session对象 4.创建Destination对象（topic，quene），这里使用quene方式创建
	 * 5.创建消息的生产者对象Producer对象 6，创建Message对象，这里使用的是TextMessage对象 7.发送消息 8.关闭连接
	 * 
	 * @throws JMSException
	 *//*
	@Test
	public void test_PTP_Producer() throws JMSException {
		// 1.创建工厂对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.43.147:61616");
		// 2.通过工厂创建connection对象
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// 3.创建session对象
		// 两个参数表示的意思：第一个参数表示是否使用事务：开启：true，关闭：false，如果第一个参数设置为true，开启事务，第二个参自动忽略。
		// 如果第一个参数的是为false，第二个参数有意义，表示消息应答的模式，（这里设置为自动应答）
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 4.创建Destination对象,这里使用的是Quene方式创建Destination对象
		Queue queue = session.createQueue("queue对象");
		// 5.创建消息的生产者Producer
		MessageProducer producer = session.createProducer(queue);
		// 6.创建Message对象
		// TextMessage message = session.createTextMessage();
		// message.setText("这是PTP生产的第一个消息......");
		TextMessage textMessage = session.createTextMessage("这是PTP生产的第一个消息......");
		// 7.发送消息
		producer.send(textMessage);
		// 8.关闭连接
		producer.close();
		session.close();
		connection.close();
	}

	@Test
	public void test_PTP_Consumer() throws JMSException, IOException {
		// 1.创建连接工厂
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.43.147:61616");
		// 2.创建连接对象
		Connection connection = connectionFactory.createConnection();
		// 3.开启连接
		connection.start();
		// 4.创建session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5.创建Destination对象,和消息生产者的一致
		Queue queue = session.createQueue("queue对象");
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
	 * 发布订阅模式的消息传递方式 步骤基本和PTP的方式一样
	 * 
	 * @throws JMSException
	 *//*

	@Test
	public void testPublishSubscribe_Producer() throws JMSException {
		// 创建工厂对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.43.147:61616");
		// 创建连接对象
		Connection connection = connectionFactory.createConnection();
		// 开启连接对象
		connection.start();
		// 创建session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建Destination对象
		Topic topic = session.createTopic("topic_02");
		// 创建消息的生产者对象Producer
		MessageProducer producer = session.createProducer(topic);
		// 创建消息对象TextMessage
		TextMessage message = session.createTextMessage("这是Topic生产的第一个消息...");
		// 发送消息
		producer.send(message);
		// 关闭资源
		producer.close();
		session.close();
		connection.close();

	}

	*//**
	 * Topic传递消息的Consumer方 要一直处于开启装态，才可以接收到生产者生产的消息
	 * 
	 * @throws JMSException
	 * @throws IOException
	 * 
	 *//*
	@Test
	public void testPublishSubcribe_Consumer() throws JMSException, IOException {
		// 创建工厂对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.43.147:61616");
		// 创建连接对象
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// 创建session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建Destination对象
		Topic topic = session.createTopic("topic_02");
		// 创建消费者对象
		MessageConsumer consumer = session.createConsumer(topic);
		// 通过监听器,获得message信息，打印控制台
		consumer.setMessageListener(new MessageListener() {

			// 消费者一直处于监听状态，当有消息生产时，会被监听到，触发这个事件
			@Override
			public void onMessage(Message message) {
				try {
					System.out.println("消费者开始消费...");
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					System.out.println("被消费的消息是:" + text);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("这是第一个小消费者。。。。。。");
		System.in.read();
		consumer.close();
		session.close();
		connection.close();

	}

	*//**
	 * 测试使用spring和activemq整合发送消息
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

	
	
	
	@Test
	public void testActiveMQSpring() throws IOException {
		// 加载spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		//保持监听状态即可
		System.in.read();
	}

}
*/