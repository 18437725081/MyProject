package nyist.web.jstl.domain;

import java.util.Date;

public class Product {
	private Integer pid; //��Ʒidֵ
	private String pname; //��Ʒ����
	private String market_price; //�г��۸�
	private String shop_price;//�̳Ǽ۸�
	private Integer is_hot;//�Ƿ�����
	private String pimage;//��ƷͼƬ
	private String pdesc;//��Ʒ����
	private Date date;//��Ʒ������
	private Integer pflag;//��Ʒ�ı�ǩ
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

