package nyist.e3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nyist.e3.cookie.CookieUtils;
import nyist.e3.pojo.TbItem;
import nyist.e3.pojo.TbUser;
import nyist.e3.service.TbItemService;
import nyist.e3.shopcart.service.ShopCartService;
import nyist.e3.utils.E3Result;
import nyist.e3.utils.JsonUtils;

@Controller
public class ShopCartController {

	@Autowired
	private TbItemService tbItemService;

	@Autowired
	private ShopCartService shopCartService;
	// 获取过期时间
	@Value("${EXPIRE_KEY}")
	private Integer EXPIRE_KEY;

	@Value("${CART_COOKIE}")
	private String CART_COOKIE;

	/**
	 * 需求：将商品加入购物车中未登录状态下，将购物超过添加到cookie中
	 * 
	 * 分析：1、从cookie中获取购物车信息
	 * 2、判断购物车中的商品，如果添加的商品存在，数量相加，不存在，根据商品id查询商品信息，添加到cookie中
	 * 3、将购物车列表信息写入cookie中
	 * 
	 * 
	 * @param itemId
	 * @param num
	 * @return
	 */
	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		// 添加购物车之前，判断用户是否是登陆状态
		// 在拦截器中已经将登录用户的信息存入request域中。因此可以判断request域是否为空， 就可以判断用户是否登录
		TbUser user = (TbUser) request.getAttribute("user");
		// 判断user是否为空
		if (user != null) {
			// 表示用户已经的登录
			// 进行购物车的服务端业务逻辑处理
			Long userId = user.getId();
			shopCartService.addCart(userId, itemId, num);
			//返回逻辑视图
			return "cartSuccess";
		}

		// 用户没有登录，将购物车信息写入cookie中
		// 1.获得购物车列表
		List<TbItem> itemList = getCartItemList(request);
		// 用来判断商品是否存在的标志
		boolean flag = false;
		// 2、循环遍列表中的商品信息
		for (TbItem tbItem : itemList) {
			// 3、判断添加的商品是否存在
			if (tbItem.getId() == itemId.longValue()) {
				// 4、添加的商品在cookie中存在，将数量相加
				tbItem.setNum(tbItem.getNum() + num);
				// 重置标签
				flag = true;
				// 跳出循环
				break;
			}
		}
		if (!flag) {
			// cookie中没有添加的商品信息
			// 通过商品id查询商品信息
			TbItem item = tbItemService.getItemById(itemId);
			item.setNum(num);
			if (StringUtils.isNotBlank(item.getImage())) {
				// 取一张图片用于展示使用
				item.setImage(item.getImage().split(",")[0]);
			}
			// 将商品添加购物车
			itemList.add(item);
		}
		//将购物车写入cookie中
		
		CookieUtils.setCookie(request, response, CART_COOKIE, JsonUtils.objectToJson(itemList),EXPIRE_KEY,true);
		
		return "cartSuccess";

	}

	/**
	 * 1、从cookie中取商品列表 2、进行非空的判断,如果为空，返回一个空的集合对象 3、如果不为空，将查询的结果返回
	 * 
	 * @param request
	 * @return
	 */
	private List<TbItem> getCartItemList(HttpServletRequest request) {
		// 使用utf-8，需要设置第三个参数为true
		String json = CookieUtils.getCookieValue(request, CART_COOKIE, true);
		if (StringUtils.isNotBlank(json)) {
			// 返回cookie中取出的数据集合
			return JsonUtils.jsonToList(json, TbItem.class);
		}
		// 返回空集合对象
		return new ArrayList<>();
	}

	/**
	 * 从cookie中取出购物车列表，展示给客户端 返回逻辑视图：cart.jsp
	 * 取购物车时需要将cookie中的购物车和redis中的购物车同步，进行合并操作
	 * 
	 * @return
	 */
	@RequestMapping("/cart/cart")
	public String getCartList(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 展示购车信息时，需要将redis中的购物车和cookie中的购物车整合
		// 从cookie中取出商品信息，
		List<TbItem> itemList = getCartItemList(request);
		// 判断用户是否登录
		TbUser user = (TbUser) request.getAttribute("user");
		if (user != null) {
			// 表示用户已经登录
			Long userId = user.getId();
			// 将cookie中的购物车和redis中的购物车进行合并
			shopCartService.mergeCart(userId, itemList);
			// 合并之后需要清空cookie中的购物车
			CookieUtils.deleteCookie(request, response, CART_COOKIE);
			// 这次从redis中取出的购物车是合并之后的购物车
			itemList = shopCartService.getRedisCartList(userId);
		}
		
		// 将购物车信息返回给页面中
		model.addAttribute("cartList", itemList);
		// 跳转逻辑视图
		return "cart";
	}

	/**
	 * 修改商品数量 请求参数：商品id和商品的数量 返回值：E3result 返回数据类型，json格式
	 */
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public E3Result updateNum(@PathVariable Long itemId, @PathVariable Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		// 判断用户是否是登陆装态
		TbUser user = (TbUser) request.getAttribute("user");
		if (user != null) {
			// 表示用户已经登录
			shopCartService.updateRedisNum(user.getId(), itemId, num);
			return E3Result.ok(tbItemService.getItemById(itemId));
		}
		// 1.从cookie中取出商品列表
		List<TbItem> list = getCartItemList(request);

		for (TbItem tbItem : list) {
			if (tbItem.getId() == itemId.longValue()) {
				tbItem.setNum(num);
				break;
			}
		}
		// 将更新后的数据列表写入cookie中
		CookieUtils.setCookie(request, response, CART_COOKIE, JsonUtils.objectToJson(list), EXPIRE_KEY, true);

		return E3Result.ok();
	}

	/**
	 * 删除购物车商品
	 */

	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 删除购物车，判断是否登录
		TbUser user = (TbUser) request.getAttribute("user");
		if (user != null) {
			// 用户已经登录
			shopCartService.deleteCartItem(user.getId(), itemId);
			return "redirect:/cart/cart.html";
		}

		List<TbItem> list = getCartItemList(request);
		for (TbItem tbItem : list) {
			if (tbItem.getId() == itemId.longValue()) {
				list.remove(tbItem);
				break;
			}
		}
		// 删除成功后，将购物车列表写入cookie中
		CookieUtils.setCookie(request, response, CART_COOKIE, JsonUtils.objectToJson(list), EXPIRE_KEY, true);

		// 删除成功后，重定向到购物车列表页面
		return "redirect:/cart/cart.html";

	}
}
