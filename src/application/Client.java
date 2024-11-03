package application;

public class Client {
    private String licensed_operator_client;
    private String client_name;
    private String contact_address;

    public Client() {
    }

    public Client(String licensed_operator_client, String client_name, String contact_address) {
        this.licensed_operator_client = licensed_operator_client;
        this.client_name = client_name;
        this.contact_address = contact_address;
    }

    public String getLicensed_operator_client() {
        return licensed_operator_client;
    }

    public void setLicensed_operator_client(String licensed_operator_client) {
        this.licensed_operator_client = licensed_operator_client;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getContact_address() {
        return contact_address;
    }

    public void setContact_address(String contact_address) {
        this.contact_address = contact_address;
    }
}