package nyist.e3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nyist.e3.content.service.ContentCatagoryService;
import nyist.e3.pojo.easyuitree.EasyuiTree;
import nyist.e3.utils.E3Result;

/**
 * 内容分类的controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCatagoryService contentCatagoryService;

	/**
	 * 查询所有的内容分类 请求参数：当前节点的id值 第一次请求id=0，查询子节点的时候会将子节点的id值作为父节点的id值查询
	 * 需要进行参数绑定，使用注解@requestparam 保证请求的参数的一致性
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyuiTree> getContentCategory(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
		List<EasyuiTree> list = contentCatagoryService.getContentCatagory(parentId);
		return list;
	}
	/**
	 * 完成内容分类的添加功能
	 * 分析：
	 * 1.接收添加的节点的id值和内容
	 * 2.返回的是E3Result对象，json数据类型
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public E3Result addContentCategory(Long parentId,String name) {
		E3Result result = contentCatagoryService.create(parentId,name);
		return result;
	}
	
	/**
	 * 对接点进行重命名
	 * 1.如果插入的节点的id值不为零，表示重命名原来的节点，为零，表示插入新的节点
	 * 2.根据节点的id值进行更新
	 */
	@RequestMapping("/update")
	@ResponseBody
	public E3Result update(Long id,String name) {
		E3Result result = contentCatagoryService.update(id,name);
		return result;
	}
	/**
	 * 根据id值删除当前节点
	 * 1.需要对要删除的节点做判断，如果是父节点的话，禁止删除操作，如果是子节点的话，可以执行删除操作
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public E3Result delete(Long id) {
		E3Result result = contentCatagoryService.delete(id);
		return result;
	}
	
}
