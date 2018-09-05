package nyist.e3.pojo.mydefine;

import java.io.Serializable;
import java.util.List;

/**
 * 响应的json数据格式： Easyui中datagrid控件要求的数据格式为：
 * {total:”2”,rows:[{“id”:”1”,”name”:”张三”},{“id”:”2”,”name”:”李四”}]}
 * 
 * @author Administrator
 *
 */
public class EasyUIResult implements Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<?> rows;
	private long total;
	
	public EasyUIResult() {
	}
	
	public EasyUIResult(Integer total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	public EasyUIResult(Long total, List<?> rows) {
		this.total = total.intValue();
		this.rows = rows;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}


}
