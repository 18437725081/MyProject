
package nyist.e3.search;

import java.io.Serializable;
/**
 * 查询索引库，需要在页面展示的数据属性
 * @author Administrator
 *
 */
public class SearchItem implements Serializable {
	private String id;
	private String title;
	private String sell_point;
	private long price;
	private String image;
	private String category_name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSell_point() {
		return sell_point;
	}

	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	/**
	 * 最终获得的图片是图片的url数据，通过逗号隔开，所以可以进行切割遍历
	 * 
	 * @return
	 */
	public String[] getImages() {
		String image1 = this.getImage();
		if (image1 != null && !"".equals(image1)) {
			String[] strings = image1.split(","); // 获得每一个图片的url地址
			return strings;
		}
		return null;
	}
}