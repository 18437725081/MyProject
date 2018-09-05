package nyist.e3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nyist.e3.content.service.ContentService;
import nyist.e3.pojo.TbContent;
import nyist.e3.pojo.mydefine.EasyUIResult;
import nyist.e3.utils.E3Result;

/**
 * 内容处理的controller
 * 
 * @author Administrator
 *
 */
@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;

	/**
	 * 根据内容分类的id值查询该内容分类下的所有的内容 分析：请求参数，categoryId 第一次categoryId=0
	 * 第二次将内容分类的id作为分类的父id进行 查询 返回结果：{total:xxx,rows[{},{}]},json格式
	 * 可以使用自定义的pojo进行包装
	 * 
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIResult getContentList(Integer page, Integer rows) {
		EasyUIResult result = contentService.queryContentList(page, rows);
		return result;
	}

	/**
	 * 添加内容
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public E3Result saveContent(TbContent content) {
		E3Result result = contentService.add(content);
		return result;
	}

	/**
	 * 更新内容 1.接收修改节点的分类id值 2.接收修改节点的id值 3.查询出修改对象 4.完成该节点的更新功能
	 */
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public E3Result updateContent(Long id, Long categoryId, TbContent content) {
		// TbContent oldContent =
		// contentService.findByCategoryAndContentId(id,categoryId);
		E3Result result = contentService.update(categoryId, id, content);
		return result;
	}
}
