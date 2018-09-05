package nyist.e3.service.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nyist.e3.result.SearchResult;
import nyist.e3.search.SearchItem;

/**
 * 通过索引完成查询
 * 
 * @author Administrator
 *
 */
@Repository
public class SearchItemListDao {
	/**
	 * 完成和索引库的交互，查询商品信息，封装到结果对象中
	 */
	@Autowired
	private SolrServer solrServer;

	public SearchResult getSearchItemList(SolrQuery query) {
		try {
			List<SearchItem> itemList = new ArrayList<>();
			// 创建结果对象
			SearchResult result = new SearchResult();
			// 执行查询
			QueryResponse response = solrServer.query(query);
			// 获得查询结果集
			SolrDocumentList results = response.getResults();
			// 获得总的记录数
			long numFound = results.getNumFound();
			result.setRecourdCount((int) numFound);

			// 取高亮显示的结果集
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

			// 处理结果集
			for (SolrDocument solrDocument : results) {
				// 获得文档的id值
				String id = (String) solrDocument.get("id");
//				System.out.println(id);

				List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
				String item_title = "";
				if (list != null && list.size() > 0) {
					item_title = list.get(0);
				} else {
					item_title = (String) solrDocument.get("item_title");
				}

				// 创建索引结果对象，封装对象
				SearchItem item = new SearchItem();
				item.setId(id);
				item.setImage((String) solrDocument.get("item_image"));
				item.setPrice((long) solrDocument.get("item_price"));
				item.setSell_point((String) solrDocument.get("item_sell_point"));
				item.setCategory_name((String) solrDocument.get("item_category_name"));

				item.setTitle(item_title);
				// 将对象添加到itemList集合中
				itemList.add(item);
			}
			result.setItemList(itemList);
			return result;
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return null;

	}
	
}
