package e3.nyist.search;

import nyist.e3.result.SearchResult;

public interface SearchItemListService {
	//传递查询额关键字和当前页数
	SearchResult getSearchItemList(String keyword,int page,int rows);
}
