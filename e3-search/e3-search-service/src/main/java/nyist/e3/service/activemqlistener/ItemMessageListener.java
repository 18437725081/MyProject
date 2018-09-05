package nyist.e3.service.activemqlistener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.annotation.Autowired;

import e3.nyist.search.SearchService;
import nyist.e3.service.impl.SearchServiceImpl;

/**
 * 用于监听商品添加完成后发送的信息，接收商品的id信息
 * 
 * @author Administrator
 *
 */
public class ItemMessageListener implements MessageListener {

	// 将添加索引库的service自动注入给消息监听器
	@Autowired
	private SearchService searchService;
	
	/**
	 * 接收商品添加后发送的消息 1.接收发送的消息 2.获得商品的id值 3.向索引库中添加文档
	 */

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("接收到消息....");
			TextMessage textMessage = (TextMessage) message;
			// 等待添加商品的事务提交，防止出现空指针
			String value = textMessage.getText();
			Long itemId = Long.parseLong(value);

			System.out.println("商品的id是:" + itemId);
			//等待事务添加商品的事务提交
			Thread.sleep(2000);
			// 将商品导入到索引库中
//			searchServiceImpl.addDocument(itemId);
			searchService.addDocument(itemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
