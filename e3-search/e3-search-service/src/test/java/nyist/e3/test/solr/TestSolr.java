package nyist.e3.test.solr;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.v2.sql.filter.SynchronizedFilterCallableStatement;

/**
 * 测试solr全文检索功能实现
 * 
 * @author Administrator
 *
 */
public class TestSolr {
	/**
	 * 查询索引库中的所用索引
	 * 
	 * @throws SolrServerException
	 */
	@Test
	public void test() throws SolrServerException {
		// 通过spring工程获得solr连接对象
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-solr.xml");
		SolrServer solrServer = context.getBean(SolrServer.class);
		// 创建查询对象
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		QueryResponse response = solrServer.query(solrQuery);
		SolrDocumentList results = response.getResults();
		long num = results.getNumFound();
		System.out.println("总的记录数为：" + num);
		for (SolrDocument solrDocument : results) {
			System.out.println("商品标题:" + solrDocument.get("item_title"));

		}
	}

	/**
	 * 高亮显示
	 * 
	 * @throws SolrServerException
	 */
	@Test
	public void testHighlighting() throws SolrServerException {
		// 通过spring工程获得solr连接对象
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-solr.xml");
		SolrServer solrServer = context.getBean(SolrServer.class);
		// 创建查询对象
		SolrQuery solrQuery = new SolrQuery();
		// 设置查询条件，查询标题中有手机的内容
		// solrQuery.set("q","手机");
		solrQuery.set("q", "阿尔卡特");
		// 开启高亮显示
		solrQuery.setHighlight(true);
		// 设置高亮显示的域
		solrQuery.addHighlightField("item_title");
		// 设置高亮显示的样式
		solrQuery.setHighlightSimplePre("<em>");
		solrQuery.setHighlightSimplePost("</em>");

		// 设置默认搜索域
		solrQuery.set("df", "item_title");
		/*
		 * // 设置分页信息 solrQuery.setStart(1); solrQuery.setRows(10);
		 */
		// 执行查询操作
		QueryResponse response = solrServer.query(solrQuery);
		SolrDocumentList results = response.getResults();
		System.out.println("总的记录数为:" + results.getNumFound());
		for (SolrDocument solrDocument : results) {
			// 获得文档的id值
			String id = (String) solrDocument.get("id");
			System.out.println(id);
			// 获取高亮现实的部分
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

			// 通过id值一层一层遍历，取值
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String item_title = "";
			if (list != null && list.size() > 0) {
				item_title = list.get(0);
			} else {
				item_title = (String) solrDocument.get("item_title");
			}
			System.out.println(item_title);
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_sell_point"));

		}
	}

	// 使用solrJ实现查询
	@Test
	public void queryDocument() throws Exception {
		// 创建一个SolrServer对象
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.133:8989/solr");
		// 创建一个查询对象，可以参考solr的后台的查询功能设置条件
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		// query.setQuery("阿尔卡特");
		query.set("q", "阿尔卡特");
		// 设置分页条件
		query.setStart(1);
		query.setRows(2);
		// 开启高亮
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		// 设置默认搜索域
		query.set("df", "item_title");
		// 执行查询，得到一个QueryResponse对象。
		QueryResponse queryResponse = solrServer.query(query);
		// 取查询结果总记录数
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());
		// 取查询结果
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			// 取高亮后的结果
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size() > 0) {
				// 取高亮后的结果
				title = list.get(0);
			} else {
				title = (String) solrDocument.get("item_title");
			}
			System.out.println(title);
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}

	}

}
