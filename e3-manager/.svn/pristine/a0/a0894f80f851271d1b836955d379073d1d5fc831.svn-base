package nyist.e3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nyist.e3.mapper.TbItemCatMapper;
import nyist.e3.pojo.TbItemCat;
import nyist.e3.pojo.TbItemCatExample;
import nyist.e3.pojo.TbItemCatExample.Criteria;
import nyist.e3.pojo.easyuitree.EasyuiTree;
import nyist.e3.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 根据父id值查询所有的节点
	 */
	@Override
	public List<EasyuiTree> getItemCatAll(Long parentId) {
		// 创建查询对象
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		// 设置查询条件
		criteria.andParentIdEqualTo(parentId);
		// 查询的结果
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 对查询的结果集进行处理，转换成需要的类型
		List<EasyuiTree> resultList = new ArrayList<>();
		// 遍历每一个对象，转换成easyUITree对象，添加到需要的结果集集合中，返回结果
		for (TbItemCat tbItemCat : list) {
			EasyuiTree e = new EasyuiTree();
			e.setId(tbItemCat.getId());
			e.setText(tbItemCat.getName());
			// 采用的是异步加载，需要进行判断，如果当前节点是子节点，显示关闭，当点击的时候，在加载子节点
			e.setState(tbItemCat.getIsParent() ? "closed" : "open");
			resultList.add(e);
		}
		return resultList;
	}
}
