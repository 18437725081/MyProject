package nyist.e3.content.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import nyist.e3.mapper.TbContentMapper;
import nyist.e3.pojo.TbContent;
import nyist.e3.pojo.TbContentExample;
import nyist.e3.pojo.TbContentExample.Criteria;
import nyist.e3.pojo.mydefine.EasyUIResult;
import nyist.e3.utils.E3Result;
import nyist.e3.utils.JsonUtils;
import nyist.e3.utils.redis.JedisClient;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;

	/**
	 * 查询内容列表 实现分页使用PageHelper插件完成
	 */
	@Override
	public EasyUIResult queryContentList(Integer page, Integer rows) {
		// 设置分页开始的位置和每页的记录数
		PageHelper.startPage(page, rows);
		// 创建查询对象
		TbContentExample example = new TbContentExample();
		/*
		 * Criteria criteria = example.createCriteria(); // 为查询添加条件，根据分类的id进行查询
		 * criteria.andCategoryIdEqualTo(categoryId);
		 */
		List<TbContent> list = contentMapper.selectByExample(example);
		// 将查询结果封装成PageInfo对象
		PageInfo<TbContent> info = new PageInfo<>(list);
		// 返回结果
		EasyUIResult result = new EasyUIResult(info.getTotal(), list);
		return result;
	}

	/**
	 * 添加内容 分析： 1.创建添加的内容对象 2，补全该对象的属性（表单中没有的属性） 3.返回结果
	 */
	@Override
	public E3Result add(TbContent content) {
		content.setUpdated(new Date());
		content.setCreated(new Date());
		int i = contentMapper.insert(content);
		// 为了实现缓存同步，只需要将原来的缓存清楚即可，然后在第一次显示首页的时候，会查询数据库，添加缓存
		// 删除原来的缓存
		try {
			jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (i > 0) {
			return E3Result.ok();
		}
		return null;
	}

	/**
	 * 根据分类id值和当前节点的id值查询内容对象
	 */
	@Override
	public TbContent findByCategoryAndContentId(Long id, Long categoryId) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId).andIdEqualTo(id);
		List<TbContent> list = contentMapper.selectByExample(example);
		System.out.println(list);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 更新内容 1.根据内容分类的id值和id值查询出内容对象 2.将表单中该对象输入的属性值更新到数据库
	 */
	@Override
	public E3Result update(Long categoryId, Long id, TbContent content) {
		// 查询出要更新的内容对象
		TbContent old_content = findByCategoryAndContentId(id, categoryId);
		System.out.println(old_content);
		if (content.getContent() != null) {
			old_content.setContent(content.getContent());
		}
		System.out.println(content.getContent());
		old_content.setCreated(new Date());
		old_content.setUpdated(new Date());
		if (content.getPic() != null) {
			old_content.setPic(content.getPic());
		}
		if (content.getPic2() != null) {
			old_content.setPic2(content.getPic2());
		}
		if (content.getSubTitle() != null) {
			old_content.setSubTitle(content.getSubTitle());
		}
		if (content.getTitle() != null) {
			old_content.setTitle(content.getTitle());
		}
		if (content.getTitleDesc() != null) {
			old_content.setTitleDesc(content.getTitleDesc());
		}
		if (content.getUrl() != null) {
			old_content.setUrl(content.getUrl());
		}
		int i = contentMapper.updateByPrimaryKey(old_content);

		if (i > 0) {
			// 更新成功，给出响应
			return E3Result.ok();
		}
		// 更新失败，返回null
		return null;

	}

	/**
	 * 查询内容列表，完成首页轮播图的显示 查询条件：内容分类id 返回结果：内容列表集合
	 */
	@Override
	public List<TbContent> findContentByCategoryId(Long categoryId) {
		// 查询出的内容列表可以添加到缓存中，便于展示，为了保证添加缓存出现错误不影响程序的正常业务功能，可以使用try catch的方式加缓存
		try {
			String json = jedisClient.hget(CONTENT_LIST, categoryId + "");
			if (json != null) {
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		// 使用selectByExampleWithBLOBs方法会将content属性框中的内容也查询出来
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

		// 操作完成后需要将查询的内容添加到缓存中,因为添加缓存的过程可能出错，所以使用try catch将异常抛出即可
		// categoryId+""将Long类型的数据转换成String类型的
		try {
			jedisClient.hset(CONTENT_LIST, categoryId + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
