package nyist.e3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nyist.e3.content.service.ContentService;
import nyist.e3.pojo.TbContent;

/**
 * 配置显示首页信息
 * 
 * @author Administrator
 *
 */
@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;

	/**
	 * 显示首页功能 加载首页时候实现轮播图的效果 参数：分类id（可以硬编码） 返回值：TbContent对象集合
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) {
		// 查询内容列表，实现轮播图的效果
		Long categoryId = 89L;
		List<TbContent> ad1List = contentService.findContentByCategoryId(categoryId);
		model.addAttribute("ad1List", ad1List);
		return "index";
	}

}
