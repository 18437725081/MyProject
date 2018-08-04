package nyist.web.jstl.domain;

import java.util.Date;

public class Product {
	private Integer pid; //商品id值
	private String pname; //商品名称
	private String market_price; //市场价格
	private String shop_price;//商城价格
	private Integer is_hot;//是否热门
	private String pimage;//商品图片
	private String pdesc;//商品描述
	private Date date;//商品的日期
	private Integer pflag;//商品的标签
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getMarket_price() {
		return market_price;
	}
	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}
	public String getShop_price() {
		return shop_price;
	}
	public void setShop_price(String shop_price) {
		this.shop_price = shop_price;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getPflag() {
		return pflag;
	}
	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}
	
}

