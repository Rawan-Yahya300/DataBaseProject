package application;

public class Warehouse_product {
  
	private String id_warehouse;
	private String code;
	
	
	
	public Warehouse_product(String id_warehouse, String code) {
	
		this.id_warehouse = id_warehouse;
		this.code = code;
	}
	
	public Warehouse_product() {
		
	}
	
	public String getId_warehouse() {
		return id_warehouse;
	}
	public void setId_warehouse(String id_warehouse) {
		this.id_warehouse = id_warehouse;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}