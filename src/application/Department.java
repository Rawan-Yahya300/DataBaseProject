package application;

public class Department {
	private String id_dep;
	private String dep_name;

	public Department(String id_dep, String dep_name) {

		this.id_dep = id_dep;
		this.dep_name = dep_name;
	}

	public Department() {

	}

	public String getId_dep() {
		return id_dep;
	}

	public void setId_dep(String id_dep) {
		this.id_dep = id_dep;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

}