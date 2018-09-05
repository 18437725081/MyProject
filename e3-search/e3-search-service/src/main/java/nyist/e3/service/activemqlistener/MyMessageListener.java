package nyist.e3.service.activemqlistener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 配置监听器，接收消息
 * 1.实现MessageListener接口
 * 2.在spring中注册自定义的监听器对象
 * 
 * @author Administrator
 *
 */
public class MyMessageListener implements MessageListener {

	/**
	 * 监听接收的消息
	 */
	@Override
	public void onMessage(Message message) {

		try {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			System.out.println("接收到的消息是:" + text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
