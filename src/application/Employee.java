package application;
import java.util.Date;



public class Employee {
	private String emp_Id ;
	private String ename ;
	private String place_Of_living ;
	private String academic_achievement ;
	private String experience ;
	private String id_dep ;
	private Date date_of_work ;
	private double salary; 
	private String manager_id ;
	
	
	
	
	public Employee() {
		
	}


	public Employee(String emp_Id, String ename, String place_Of_living, String academic_achievement, String experience,
			String id_dep, Date date_of_work, double salary, String manager_id) {
		super();
		this.emp_Id = emp_Id;
		this.ename = ename;
		this.place_Of_living = place_Of_living;
		this.academic_achievement = academic_achievement;
		this.experience = experience;
		this.id_dep = id_dep;
		this.date_of_work = date_of_work;
		this.salary = salary;
		this.manager_id = manager_id;
		
	}
	public Employee(String emp_Id, String ename, String place_Of_living, String academic_achievement, String experience,
			String id_dep, Date date_of_work, double salary) {
		super();
		this.emp_Id = emp_Id;
		this.ename = ename;
		this.place_Of_living = place_Of_living;
		this.academic_achievement = academic_achievement;
		this.experience = experience;
		this.id_dep = id_dep;
		this.date_of_work = date_of_work;
		this.salary = salary;
		
		
	}

	public String getEmp_Id() {
		return emp_Id;
	}


	public void setEmp_Id(String emp_Id) {
		this.emp_Id = emp_Id;
	}


	public String getEname() {
		return ename;
	}


	public void setEname(String ename) {
		this.ename = ename;
	}


	public String getPlace_Of_living() {
		return place_Of_living;
	}


	public void setPlace_Of_living(String place_Of_living) {
		this.place_Of_living = place_Of_living;
	}


	public String getAcademic_achievement() {
		return academic_achievement;
	}


	public void setAcademic_achievement(String academic_achievement) {
		this.academic_achievement = academic_achievement;
	}


	public String getExperience() {
		return experience;
	}


	public void setExperience(String experience) {
		this.experience = experience;
	}


	public String getId_dep() {
		return id_dep;
	}


	public void setId_dep(String id_dep) {
		this.id_dep = id_dep;
	}


	public Date getDate_of_work() {
		return date_of_work;
	}


	public void setDate_of_work(Date date_of_work) {
		this.date_of_work = date_of_work;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public String getManager_id() {
		return manager_id;
	}


	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}


	

	@Override
	public String toString() {
		return "emp_Id=" + emp_Id + ", ename=" + ename + ", place_Of_living=" + place_Of_living
				+ ", academic_achievement=" + academic_achievement + ", experience=" + experience + ", id_dep=" + id_dep
				+ ", date_of_work=" + date_of_work + ", salary=" + salary + ", manager_id=" + manager_id ;
				
	}
	
	
	
	
	
}