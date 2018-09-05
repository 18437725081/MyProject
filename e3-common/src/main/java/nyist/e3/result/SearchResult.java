package nyist.e3.result;

import java.io.Serializable;
/**
 * 封装索引查询的结果对象
 * @author Administrator
 *
 */
import java.util.List;

import nyist.e3.search.SearchItem;

public class SearchResult implements Serializable {

	private List<SearchItem> itemList; // 结果集
	private Integer totalPages; // 总页数
	private Integer recourdCount; // 总的记录数

	public List<SearchItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getRecourdCount() {
		return recourdCount;
	}

	public void setRecourdCount(Integer recourdCount) {
		this.recourdCount = recourdCount;
	}

}
