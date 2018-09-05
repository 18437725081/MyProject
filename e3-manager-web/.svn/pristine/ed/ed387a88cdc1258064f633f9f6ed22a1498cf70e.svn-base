package nyist.e3.controller;
/**
 * 
 * @author Administrator
 * 管理索引库
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import e3.nyist.search.SearchService;
import nyist.e3.utils.E3Result;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3Result importItemsToSolr() {
		E3Result result = searchService.importItems();
		return result;
	}
}
