package nyist.e3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nyist.e3.pojo.easyuitree.EasyuiTree;
import nyist.e3.service.ItemCatService;

@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyuiTree> getItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId) {
		List<EasyuiTree> list = itemCatService.getItemCatAll(parentId);
		return list;
	}
	
}
