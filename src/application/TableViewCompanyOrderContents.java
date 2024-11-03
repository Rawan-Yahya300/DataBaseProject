package application;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableViewCompanyOrderContents{// implements Comparable<TableViewCompanyOrderContents> {
	private SimpleStringProperty supp_id;
	private SimpleStringProperty code;
	private SimpleStringProperty pro_name;
	private SimpleStringProperty sup_name;
	private SimpleDoubleProperty price;
	
	 
	
	
	public TableViewCompanyOrderContents(String supp_id, String code,String pro_name, String sup_name, Double price) {
		
		this.supp_id = new SimpleStringProperty(supp_id) ;
		this.code = new SimpleStringProperty(code) ;
		this.pro_name = new SimpleStringProperty(pro_name);
		this.sup_name = new SimpleStringProperty(sup_name);
		this.price = new SimpleDoubleProperty(price);
	}
	
	
//	@Override
//	public int compareTo(TableViewCompanyOrderContents o) {
//		
//		return supp_id.get().compareToIgnoreCase(o.getCode());
//	}


	public String getSupp_id() {
		return supp_id.get();
	}


	public void setSupp_id(String supp_id) {
		this.supp_id = new SimpleStringProperty(supp_id) ;
	}


	public String getCode() {
		return code.get();
	}


	public void setCode(String code) {
		this.code = new SimpleStringProperty(code) ;
	}


	public String getPro_name() {
		return pro_name.get();
	}


	public void setPro_name(String pro_name) {
		this.pro_name = new SimpleStringProperty(pro_name) ;
	}


	public String getSup_name() {
		return sup_name.get();
	}


	public void setSup_name(String sup_name) {
		this.sup_name = new SimpleStringProperty(sup_name) ;
	}


	public Double getPrice() {
		return price.get();
	}


	public void setPrice(Double price) {
		this.price = new SimpleDoubleProperty(price) ;
	}
	
	
	
	
}
