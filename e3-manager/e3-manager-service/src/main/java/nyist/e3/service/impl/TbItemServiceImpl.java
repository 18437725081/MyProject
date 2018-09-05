package nyist.e3.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import nyist.e3.mapper.TbItemDescMapper;
import nyist.e3.mapper.TbItemMapper;
import nyist.e3.pojo.TbItem;
import nyist.e3.pojo.TbItemDesc;
import nyist.e3.pojo.TbItemExample;
import nyist.e3.pojo.TbItemExample.Criteria;
import nyist.e3.pojo.mydefine.EasyUIResult;
import nyist.e3.service.TbItemService;
import nyist.e3.utils.E3Result;
import nyist.e3.utils.IDUtils;
import nyist.e3.utils.JsonUtils;
import nyist.e3.utils.redis.JedisClient;

@Service
public class TbItemServiceImpl implements TbItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private JedisClient jedisClient;

	// 注入JMS对象，完成消息的发送
	@Autowired
	private JmsTemplate jmsTemplate;
	// 注入目的地对象,使用主题方式传递消息
	// resource是根据spring容器中bean的id完成注入
	@Resource
	private Destination topicDestination;

	public TbItem getItemById(Long id) {
		try {
			// 查询缓存
			String json = jedisClient.get("ITEM_INFO" + id + "BASE");
			if (json != null) {
				TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
				return tbItem;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		TbItem tbItem = itemMapper.selectByPrimaryKey(id);
		// 添加缓存
		jedisClient.set("ITEM_INFO" + id + "BASE", JsonUtils.objectToJson(tbItem));
		// 设置缓存的过期时间为一小时
		jedisClient.expire("ITEM_INFO" + id + "BASE", 3600);

		return tbItem;

		/*
		 * TbItemExample example = new TbItemExample(); Criteria criteria =
		 * example.createCriteria(); criteria.andCidEqualTo(id); List<TbItem>
		 * list = itemMapper.selectByExample(example); if (list != null &&
		 * list.size() > 0) { return list.get(0); } return null;
		 */
	}

	/**
	 * 完成商品信息的分页查询
	 */
	@Override
	public EasyUIResult findItemList(Integer page, Integer rows) {
		// 1.设置分页信息
		PageHelper.startPage(page, rows);
		// 2.执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		// 3.取分页信息
		PageInfo<TbItem> info = new PageInfo<>(list);
		// 4.处理返回结果对象，返回json数据
		EasyUIResult result = new EasyUIResult(info.getTotal(), list);
		return result;
	}

	/**
	 * 完成商品上传的功能 1.生成商品的id值 2.补全商品的属性 3.添加商品 4.添加商品描述
	 * 5，完成商品添加后，使用ActiveMQ发送消息，然后在消费者一方接收消息，实现同步索引库（当然，也可以使用dubbo，通过rpc实现通信、缺点是：如果service和web工程之间存在双向调用，造成服务之间调用关系混乱）
	 */
	@Override
	public E3Result save(TbItem item, String desc) {
		// 使用工具类生成商品的id值
		final long id = IDUtils.genItemId();
		item.setId(id);
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 向商品表中添加商品信息
		itemMapper.insert(item);

		// 向商品描述表中封装数据
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemDesc(desc);
		itemDescMapper.insert(itemDesc);

		// 商品添加成功后，在返回结果之前，发送消息通知，将商品的id作为消息发送即可
		// 但是存在一个问题：有可能商品还没有添加成功，消息已经发送，然后消费者又不能接收到信息，有可能造成空指针异常
		// 解决办法：在调用的时候，调用线程中的sleep方法即可，等待插入商品的事务提交后再完成同步索引库的操作
		jmsTemplate.send(topicDestination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				//发送商品id的消息
				TextMessage textMessage = session.createTextMessage(id + "");
				System.out.println("发送的消息为："+textMessage);
				return textMessage;
			}
		});
		// 返回状态码200
		return E3Result.ok();
	}

	/**
	 * 删除商品的功能
	 */
	@Override
	public E3Result delete(String ids) {
		int i = 0;
		if (StringUtils.isNotBlank(ids)) {
			String[] split = ids.split(",");
			for (String id : split) {
				TbItemExample example = new TbItemExample();
				Criteria criteria = example.createCriteria();
				criteria.andIdEqualTo(Long.parseLong(id));
				i = itemMapper.deleteByExample(example);
			}
		}
		if (i > 0) {
			return E3Result.ok();
		}
		return E3Result.ok("数据删除失败");
	}

	/**
	 * 根据商品的id查询商品的详细信息
	 */
	@Override
	public TbItemDesc getItemDescById(Long itemId) {

		// 取商品描述前先查询缓存，如果缓存中没有，在查询数据库 key==>ITEM_INFO:123456:DESC
		try {
			// 防止key重复，加上前缀后缀
			String json = jedisClient.get("ITEM_INFO" + itemId + "DESC");
			if (json != null) {
				// 缓存中有直接返回
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return itemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		if (itemDesc != null) {
			// 添加缓存
			jedisClient.set("ITEM_INFO" + itemId + "DESC", JsonUtils.objectToJson(itemDesc));
			// 同时设置缓存的有效期
			jedisClient.expire("ITEM_INFO" + itemId + "DESC", 3600);
			return itemDesc;
		}
		return null;
	}
}
