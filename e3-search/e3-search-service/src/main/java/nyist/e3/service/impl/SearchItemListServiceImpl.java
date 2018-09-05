package nyist.e3.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import e3.nyist.search.SearchItemListService;
import nyist.e3.result.SearchResult;
import nyist.e3.service.dao.SearchItemListDao;

@Service
public class SearchItemListServiceImpl implements SearchItemListService {
	/**
	 * 查询索引的业务层，完成正常的业务逻辑
	 */
	@Autowired
	private SearchItemListDao searchItemdao;

	@Value("${DEFAULT_FIELD}")
	private String DEFAULT_FIELD;

	@Override
	public SearchResult getSearchItemList(String keyword, int page, int rows) {
		// 创建查询对象
		SolrQuery query = new SolrQuery();
		// 设置过滤条件
		query.setQuery(keyword);
		// 设置分页条件
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		// 设置默认搜索域
		query.set("df", DEFAULT_FIELD);
		// 开启高亮显示
		// 设置高亮显示的域
		query.addHighlightField("item_title");
		query.setHighlight(true);
		// 设置高亮显示的样式
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");

		// 调用dao层的方法完成查询操作
		SearchResult result = searchItemdao.getSearchItemList(query);

		// 计算中的页数
		// 计算公式 总记录数对每页记录数进行向上取整
		Integer count = result.getRecourdCount();
		Integer totalPages = (int) Math.ceil(count / rows);
		result.setTotalPages(totalPages);
		return result;
	}

}
