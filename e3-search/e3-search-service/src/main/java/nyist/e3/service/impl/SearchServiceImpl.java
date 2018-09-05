package nyist.e3.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import e3.nyist.search.SearchService;
import nyist.e3.search.SearchItem;
import nyist.e3.service.mapper.ItemMapper;
import nyist.e3.utils.E3Result;

/**
 * 向索引库中添加商品
 * 
 * @author Administrator
 *
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;

	/**
	 * 完成一键导入的功能，将所有的商品信息添加到索引库中
	 */
	@Override
	public E3Result importItems() {
		try {
			// 查询商品列表
			List<SearchItem> itemList = itemMapper.getItemList();
			// 导入索引库
			for (SearchItem searchItem : itemList) {
				// 创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				// 向文档中添加域
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				// 写入索引库
				solrServer.add(document);
			}
			// 提交
			solrServer.commit();
			// 返回成功
			return E3Result.ok();

		} catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500, "商品导入失败");
		}
	}

	/**
	 * 商品添加完成后，添加文档到索引库中
	 * 
	 * @throws IOException
	 * @throws SolrServerException
	 */
	@Override
	public E3Result addDocument(Long itemId) {
		try {
			// 根据商品的id获得商品信息
			SearchItem searchItem = itemMapper.getItemById(itemId);
			// 将该对象导入索引库中
			// 创建文档
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			// 将文档添加到索引库中
			solrServer.add(document);
			// 写入索引库
			solrServer.commit();
			return E3Result.ok();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return E3Result.build(500, "插入索引库失败");

	}

}
