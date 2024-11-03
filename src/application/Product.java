package application;


public class Product {

	 private String code;
	 private String pname;
	 private double price;
	 
	 public Product ( ) {


	 }

	 
	 public Product (  String code,  String pname,double price) {
		 this.code = code;
		 this.pname = pname;
		 this.price = price;


	 }
	 
	 public Product (  String code,  String pname) {
		 this.code = code;
		 this.pname = pname;
	
	 }


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	 @Override
	public String toString() {
		return "code: " + code + ", pname: " + pname + ", price: " + price ;
	}
	
	
}