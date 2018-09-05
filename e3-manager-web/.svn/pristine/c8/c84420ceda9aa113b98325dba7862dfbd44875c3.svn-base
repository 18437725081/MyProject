package nyist.e3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 显示后台首页的信息
 * 
 * @author Administrator
 *
 */
@Controller
public class PageController {

	@RequestMapping("/index")
	public String getIndex() {
		return "index";
	}

	@RequestMapping("/{page}")
	public String getPage(@PathVariable String page) {
		return page;
	}

}
