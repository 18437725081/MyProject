package nyist.e3.pojo.easyuitree;

import java.io.Serializable;

public class EasyuiTree implements Serializable{
	private String text;
	private long id;
	private String state;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
