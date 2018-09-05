package nyist.e3.service;

import java.util.List;

import nyist.e3.pojo.easyuitree.EasyuiTree;

public interface ItemCatService {

	List<EasyuiTree> getItemCatAll(Long parentId);

}
