package nyist.e3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nyist.e3.pojo.Item;
import nyist.e3.pojo.TbItem;
import nyist.e3.pojo.TbItemDesc;
import nyist.e3.service.TbItemService;

/**
 * 显示商品详情的控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class ItemController {
	@Autowired
	private TbItemService tbItemService;

	/**
	 * 根据商品的id查询商品的详细信息
	 * 
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	public String showItemDetailPage(@PathVariable Long itemId, Model model) {
		TbItem tbItem = tbItemService.getItemById(itemId);
		// 根据商品的id查询商品描述信息
		TbItemDesc itemDesc = tbItemService.getItemDescById(itemId);
		// 将item对象转换成结果页面需要的对象
		Item item = new Item(tbItem);
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		return "item";
	}
}
