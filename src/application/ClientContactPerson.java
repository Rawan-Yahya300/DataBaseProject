package application;

public class ClientContactPerson {

	
	private String licensed_operator_client ;
	private String contact_person ;
	
	public ClientContactPerson() {
		
	}
	
	public ClientContactPerson(String licensed_operator_client, String contact_person) {
		super();
		this.licensed_operator_client = licensed_operator_client;
		this.contact_person = contact_person;
	}

	public String getLicensed_operator_client() {
		return licensed_operator_client;
	}

	public void setLicensed_operator_client(String licensed_operator_client) {
		this.licensed_operator_client = licensed_operator_client;
	}

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	
	

}