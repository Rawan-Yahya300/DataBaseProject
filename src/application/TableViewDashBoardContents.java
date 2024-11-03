package application;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableViewDashBoardContents implements Comparable<TableViewDashBoardContents> {
	private SimpleStringProperty code;
	private SimpleDoubleProperty price;
	private SimpleStringProperty date;
	 
	
	public TableViewDashBoardContents(String code,double price, String date) {
		super();
		this.code = new SimpleStringProperty(code);
		this.price = new SimpleDoubleProperty(price);
		this.date = new SimpleStringProperty(date);
	}
	public String getCode() {
		return code.get();
	}
	public void setCode(String code) {
		this.code = new SimpleStringProperty(code);
	}
	public String getDate() {
		return date.get();
	}
	public void setDate(String date) {
		this.date = new SimpleStringProperty(date);
	}
	@Override
	public int compareTo(TableViewDashBoardContents o) {
		
		return code.get().compareToIgnoreCase(o.getCode());
	}
	public Double getPrice() {
		return price.get();
	}
	public void setPrice(double price) {
		this.price = new SimpleDoubleProperty(price) ;
	}
	
	
	
}
