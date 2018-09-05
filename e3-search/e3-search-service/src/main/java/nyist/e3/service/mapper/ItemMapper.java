package nyist.e3.service.mapper;

import java.util.List;

import nyist.e3.search.SearchItem;

public interface ItemMapper {
	List<SearchItem> getItemList();
	SearchItem getItemById(Long itemId);
}
