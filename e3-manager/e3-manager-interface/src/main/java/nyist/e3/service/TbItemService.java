package nyist.e3.service;

import nyist.e3.pojo.TbItem;
import nyist.e3.pojo.TbItemDesc;
import nyist.e3.pojo.mydefine.EasyUIResult;
import nyist.e3.utils.E3Result;

public interface TbItemService {

	TbItem getItemById(Long itemId);

	EasyUIResult findItemList(Integer page, Integer rows);

	E3Result save(TbItem item, String desc);

	E3Result delete(String ids);

	TbItemDesc getItemDescById(Long itemId);
}
