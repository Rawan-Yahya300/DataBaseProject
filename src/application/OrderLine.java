package application;

public class OrderLine {

	private String order_num ;
	private String code ;
	
	public OrderLine() {
		
	}
	
	public OrderLine(String order_num, String code) {
		super();
		this.order_num = order_num;
		this.code = code;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
}