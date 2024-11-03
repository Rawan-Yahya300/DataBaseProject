package application;

public class EmployeePhone {

	private String idEmp;
	private String phoneNum;

	public EmployeePhone(String idEmp, String phoneNum) {
		super();
		this.idEmp = idEmp;
		this.phoneNum = phoneNum;
	}

	public String getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(String idEmp) {
		this.idEmp = idEmp;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}