package application;

import java.util.Date;

public class ClientOrder {
	
	private String order_num ;
	private String licensed_operator_client ;
	private Date dateOfOrder ;
	
	
	public ClientOrder(String order_num, String licensed_operator_client, Date dateOfOrder) {
		super();
		this.order_num = order_num;
		this.licensed_operator_client = licensed_operator_client;
		this.dateOfOrder = dateOfOrder;
	}
	
	
	public ClientOrder() {
		
	}


	public String getOrder_num() {
		return order_num;
	}


	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}


	public String getLicensed_operator_client() {
		return licensed_operator_client;
	}


	public void setLicensed_operator_client(String licensed_operator_client) {
		this.licensed_operator_client = licensed_operator_client;
	}


	public Date getDateOfOrder() {
		return dateOfOrder;
	}


	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	
	

}