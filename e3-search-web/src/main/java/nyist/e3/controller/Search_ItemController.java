package nyist.e3.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import e3.nyist.search.SearchItemListService;
import nyist.e3.result.SearchResult;

@Controller
public class Search_ItemController {
	@Autowired
	private SearchItemListService service;

	@Value("${SEARCH_ROWS}")
	private Integer SEARCH_ROWS;

	@RequestMapping("/search")
	public String getSearchItemList(String keyword, @RequestParam(defaultValue = "1") int page, Model model)
			throws UnsupportedEncodingException {
		// 解决get请求的乱码
		keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
		SearchResult result = service.getSearchItemList(keyword, page, SEARCH_ROWS);
		model.addAttribute("itemList", result.getItemList());
		model.addAttribute("query", keyword);
		model.addAttribute("totalPages", result.getTotalPages());
		model.addAttribute("recourdCount", result.getRecourdCount());
		model.addAttribute("page", page);
		return "search";
	}
}
