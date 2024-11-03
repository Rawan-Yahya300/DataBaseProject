package application;

public class Account {
 
	private String emp_id;
	private String password;
	
	public Account(String emp_id, String passward) {
		this.emp_id = emp_id;
		this.password = passward;
	}
	public Account() {
		
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}