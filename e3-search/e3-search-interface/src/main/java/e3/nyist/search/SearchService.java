package e3.nyist.search;

import nyist.e3.utils.E3Result;

/**
 * 完成索引的一键导入功能
 * 将查询的索引商品信息添加到索引库中，完成全文检索功能
 * 全文检索也就是：将非结构化数据重新组织，创建索引，通过索引完成检索的过程
 * @author Administrator
 *
 */
public interface SearchService {
	E3Result importItems();
	E3Result addDocument(Long itemId);
}
