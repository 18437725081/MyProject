package nyist.e3.content.service;

import java.util.List;

import nyist.e3.pojo.easyuitree.EasyuiTree;
import nyist.e3.utils.E3Result;

public interface ContentCatagoryService {
	List<EasyuiTree> getContentCatagory(Long parentId);

	E3Result create(Long parentId, String name);

	E3Result update(Long id, String name);

	E3Result delete(Long id);
}
