package nyist.e3.content.service;

import java.util.List;

import nyist.e3.pojo.TbContent;
import nyist.e3.pojo.mydefine.EasyUIResult;
import nyist.e3.utils.E3Result;

public interface ContentService {

	EasyUIResult queryContentList(Integer page,Integer rows);

	E3Result add(TbContent content);

	TbContent findByCategoryAndContentId(Long id, Long categoryId);

	E3Result update(Long categoryId, Long id, TbContent content);

	List<TbContent> findContentByCategoryId(Long categoryId);

}
