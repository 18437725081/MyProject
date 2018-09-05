package nyist.e3.content.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nyist.e3.mapper.TbContentCategoryMapper;
import nyist.e3.pojo.TbContentCategory;
import nyist.e3.pojo.TbContentCategoryExample;
import nyist.e3.pojo.TbContentCategoryExample.Criteria;
import nyist.e3.pojo.easyuitree.EasyuiTree;
import nyist.e3.utils.E3Result;

@Service
public class ContentCatagoryServiceImpl implements ContentCatagoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	/**
	 * 查询内容分类列表 1.创建查询对象 2.添加按照父id进行查询操作 3.将返回的结果进行包装，返回相要的结果
	 */
	@Override
	public List<EasyuiTree> getContentCatagory(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EasyuiTree> resultList = new ArrayList<>();
		for (TbContentCategory contentCategory : list) {
			// 将没有子节点的节点改成子节点，将isParent属性设置为false
			// 获得当前节点的id值，
			Long id = contentCategory.getId();
			TbContentCategory contentCategory2 = contentCategoryMapper.selectByPrimaryKey(id);
			if (contentCategory2.getParentId() == null) {
				// 如果当前节点的父id为空，表示为空的父节点，改变节点状态为叶子节点
				contentCategory.setIsParent(false);
			}
			EasyuiTree result = new EasyuiTree();
			result.setId(contentCategory.getId());
			result.setText(contentCategory.getName());
			// 如果当前节点是父节点，不加载叶子节点，标签显示关闭状态，如果不是父节点，直接加载
			result.setState(contentCategory.getIsParent() ? "closed" : "open");
			resultList.add(result);
		}
		return resultList;
	}

	/**
	 * 完成添加功能， 1.补全内容分类对象的属性 2.创建数据路操作对象 3，返回结果
	 */
	@Override
	public E3Result create(Long parentId, String name) {
		// 创建内容分类对象
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		// 设置不是父节点
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategory.setSortOrder(1);
		contentCategory.setStatus(1);
		contentCategoryMapper.insert(contentCategory);
		// 因为有可能是在非父节点下面插入的新节点，因此需要将新节点的parentId值作为主键查询出被插入节点的对象，
		// 判断该对象是否为父节点，更新isparent的属性为true
		// 判断父节点的isparent是否为true，如果不为true，需要设置为true
		TbContentCategory content = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!content.getIsParent()) {
			content.setIsParent(true);
			//
			contentCategoryMapper.updateByPrimaryKey(content);
		}

		return E3Result.ok();

	}

	/**
	 * 根据id更新当前节点的name值 1.根据id查询出当前节点 2.更新数据即可
	 */
	@Override
	public E3Result update(Long id, String name) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		return E3Result.ok();
	}

	/**
	 * 删除选中的节点。需要判断是否有叶子节点，如果有叶子节点不让执行删除操作
	 */
	@Override
	public E3Result delete(Long id) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		Boolean isParent = contentCategory.getIsParent();
		if (!isParent) {
			// 如果当前节点不是父节点，
			int i = contentCategoryMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				return E3Result.ok();
			}
		}
		return null;
	}

}
