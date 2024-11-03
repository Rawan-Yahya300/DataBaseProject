package application;

public class Warehouse {
  
	private String id_warehouse;
	private String type_of_product;
	
	
	public Warehouse(String id_warehouse, String type_of_product) {
		
		this.id_warehouse = id_warehouse;
		this.type_of_product = type_of_product;
	}
    public Warehouse() {
		
		
		this.type_of_product = type_of_product;
	}
	public String getId_warehouse() {
		return id_warehouse;
	}
	public void setId_warehouse(String id_warehouse) {
		this.id_warehouse = id_warehouse;
	}
	public String getType_of_product() {
		return type_of_product;
	}
	public void setType_of_product(String type_of_product) {
		this.type_of_product = type_of_product;
	}
	
	
}
