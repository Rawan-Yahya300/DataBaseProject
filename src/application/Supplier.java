package application;

public class Supplier {
 
	private String licensed_operator;
	private String id_dep;
	private String supplier_Name;
	private String contact_address;
	
	
	public Supplier(String licensed_operator, String id_dep, String supplier_Name, String contact_address) {
		this.licensed_operator = licensed_operator;
		this.id_dep = id_dep;
		this.supplier_Name = supplier_Name;
		this.contact_address = contact_address;
	}
	
	public Supplier(String licensed_operator, String supplier_Name, String contact_address) {
		this.licensed_operator = licensed_operator;
		
		this.supplier_Name = supplier_Name;
		this.contact_address = contact_address;
	}
	public Supplier() {
		
	}
	
	public String getLicensed_operator() {
		return licensed_operator;
	}
	public void setLicensed_operator(String licensed_operator) {
		this.licensed_operator = licensed_operator;
	}
	public String getId_dep() {
		return id_dep;
	}
	public void setId_dep(String id_dep) {
		this.id_dep = id_dep;
	}
	public String getSupplier_Name() {
		return supplier_Name;
	}
	public void setSupplier_Name(String supplier_Name) {
		this.supplier_Name = supplier_Name;
	}
	public String getContact_address() {
		return contact_address;
	}
	public void setContact_address(String contact_address) {
		this.contact_address = contact_address;
	}
	
	
}