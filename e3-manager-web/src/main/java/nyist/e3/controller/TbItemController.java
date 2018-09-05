package nyist.e3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nyist.e3.pojo.TbItem;
import nyist.e3.pojo.mydefine.EasyUIResult;
import nyist.e3.service.TbItemService;
import nyist.e3.utils.E3Result;

/**
 * 商品管理的controller
 * 
 * @author Administrator
 *
 */
@Controller
public class TbItemController {
	@Autowired
	private TbItemService tbItemService;

	// {itemId}表示动态获取url中的请求参数
	@RequestMapping("/item/{itemId}")
	// 加入词注解之后会将返回值直接写入http正文中，不会完成跳转，在异步请求时使用， 返回json数据
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		// String id = "536563";
		// Long itemId = Long.parseLong(id);
		TbItem tbItem = tbItemService.getItemById(itemId);
		System.out.println(tbItem);
		return tbItem;
	}

	/**
	 * 展示商品列表信息
	 */
	@RequestMapping("/item/list")
	// 将结果返回json数据
	@ResponseBody
	public EasyUIResult getItemList(Integer page, Integer rows) {
		EasyUIResult result = tbItemService.findItemList(page, rows);

		return result;
	}

	/**
	 * 完成添加商品的功能
	 * 1.封装属性
	 * 2.保存对象到数据库
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public E3Result save(TbItem item,String desc) {
		E3Result result = tbItemService.save(item,desc);
		return result;
		
	} 
	/**
	 * 删除商品的信息，接收页面传递的ids
	 */
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public E3Result delete(String ids) {
		E3Result result = tbItemService.delete(ids);
		return result;
	}
	
}
