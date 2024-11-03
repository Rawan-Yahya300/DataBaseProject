package application;

public class Sup_contactPerson {
  
	private String licensed_operator;
	private String contact_person;
	
	public Sup_contactPerson(String licensed_operator, String contact_person) {
		super();
		this.licensed_operator = licensed_operator;
		this.contact_person = contact_person;
	}
	 
	public Sup_contactPerson() {
		
		
	}

	public String getLicensed_operator() {
		return licensed_operator;
	}

	public void setLicensed_operator(String licensed_operator) {
		this.licensed_operator = licensed_operator;
	}

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	
	
}