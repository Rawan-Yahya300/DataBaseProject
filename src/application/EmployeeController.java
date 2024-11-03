package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class EmployeeController {
	@FXML
	TableView<Employee> employeeTable;

	@FXML
	TableView<EmployeePhone> CotactPersonTable;

	@FXML
	private TextField txtSearchName;

	ObservableList<Employee> dataEmployee = FXCollections.observableArrayList();

	

	

	public void displayEmployeeTable() throws SQLException {

		TableColumn<Employee, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("emp_Id"));
		idColumn.setMinWidth(40);

		TableColumn<Employee, String> nameColumn = new TableColumn<>("Employee Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("ename"));
		nameColumn.setMinWidth(140);

		TableColumn<Employee, String> placeOfLivingColumn = new TableColumn<>("place Of living");
		placeOfLivingColumn.setCellValueFactory(new PropertyValueFactory<>("place_Of_living"));
		placeOfLivingColumn.setMinWidth(30);

		TableColumn<Employee, String> academic_achievementColumn = new TableColumn<>("academic achievement");
		academic_achievementColumn.setCellValueFactory(new PropertyValueFactory<>("academic_achievement"));
		academic_achievementColumn.setMinWidth(30);

		TableColumn<Employee, String> experienceColumn = new TableColumn<>("experience");
		experienceColumn.setCellValueFactory(new PropertyValueFactory<>("experience"));
		experienceColumn.setMinWidth(30);

		TableColumn<Employee, String> id_depColumn = new TableColumn<>("id_department");
		id_depColumn.setCellValueFactory(new PropertyValueFactory<>("id_dep"));
		id_depColumn.setMinWidth(30);

		TableColumn<Employee, String> date_of_workColumn = new TableColumn<>("date of work");
		date_of_workColumn.setCellValueFactory(new PropertyValueFactory<>("date_of_work"));
		date_of_workColumn.setMinWidth(30);

		TableColumn<Employee, Double> salaryColumn = new TableColumn<>("salary");
		salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
		salaryColumn.setMinWidth(30);

		employeeTable.getColumns().addAll(idColumn, nameColumn, placeOfLivingColumn, academic_achievementColumn,
				experienceColumn, id_depColumn, date_of_workColumn, salaryColumn);

		employeeTable.getItems().clear();

		dataEmployee.clear();
		addToObservableListEmployee(dataEmployee);

		employeeTable.setItems(dataEmployee);
	}

	public void addToObservableListEmployee(ObservableList<Employee> data) throws SQLException {
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from Employee;");
		while (rs.next()) {
			data.add(new Employee(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDouble(8)));
		}

	}
	
	public void displayToCotactPersonTable() throws SQLException {
		ObservableList<EmployeePhone> dataContactPerson = FXCollections.observableArrayList();
		TableColumn<EmployeePhone, String> idColumn = new TableColumn<>("Employee id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("idEmp"));
		idColumn.setMinWidth(70);
		
		TableColumn<EmployeePhone, String> contactPersonColumn = new TableColumn<>("Contact Person");
		contactPersonColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
		contactPersonColumn.setMinWidth(170);

		CotactPersonTable.getColumns().add(idColumn);
		CotactPersonTable.getColumns().add(contactPersonColumn);

		CotactPersonTable.getItems().clear();

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM phone_emp;");
		dataContactPerson.clear();
		addToObservableListContact(dataContactPerson, rs);

		CotactPersonTable.setItems(dataContactPerson);

	}

	public void addToObservableListContact(ObservableList<EmployeePhone> data, ResultSet rs) throws SQLException {

		while (rs.next()) {
			data.add(new EmployeePhone(rs.getString(1), rs.getString(2)));
		}
	}
	public void updateEmployeeTable() throws SQLException {

		ObservableList<Employee> dataEmployee = FXCollections.observableArrayList();
		employeeTable.getItems().clear();
		employeeTable.getColumns().clear();
		dataEmployee.clear();


		TableColumn<Employee, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("emp_Id"));
		idColumn.setMinWidth(40);

		TableColumn<Employee, String> nameColumn = new TableColumn<>("Employee Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("ename"));
		nameColumn.setMinWidth(140);

		TableColumn<Employee, String> placeOfLivingColumn = new TableColumn<>("place Of living");
		placeOfLivingColumn.setCellValueFactory(new PropertyValueFactory<>("place_Of_living"));
		placeOfLivingColumn.setMinWidth(30);

		TableColumn<Employee, String> academic_achievementColumn = new TableColumn<>("academic achievement");
		academic_achievementColumn.setCellValueFactory(new PropertyValueFactory<>("academic_achievement"));
		academic_achievementColumn.setMinWidth(30);

		TableColumn<Employee, String> experienceColumn = new TableColumn<>("experience");
		experienceColumn.setCellValueFactory(new PropertyValueFactory<>("experience"));
		experienceColumn.setMinWidth(30);

		TableColumn<Employee, String> id_depColumn = new TableColumn<>("id_department");
		id_depColumn.setCellValueFactory(new PropertyValueFactory<>("id_dep"));
		id_depColumn.setMinWidth(30);

		TableColumn<Employee, String> date_of_workColumn = new TableColumn<>("date of work");
		date_of_workColumn.setCellValueFactory(new PropertyValueFactory<>("date_of_work"));
		date_of_workColumn.setMinWidth(30);

		TableColumn<Employee, Double> salaryColumn = new TableColumn<>("salary");
		salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
		salaryColumn.setMinWidth(30);

		employeeTable.getColumns().addAll(idColumn, nameColumn, placeOfLivingColumn, academic_achievementColumn,
				experienceColumn, id_depColumn, date_of_workColumn, salaryColumn);

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT * FROM employee WHERE ename LIKE '%" + txtSearchName.getText() + "%';");
		while (rs.next()) {
			
			dataEmployee.add(new Employee(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getDouble(8)));
		}

		employeeTable.setItems(dataEmployee);
	}

	
	public void updateContactPersonTable() throws SQLException {

		ObservableList<EmployeePhone> dataContactPerson = FXCollections.observableArrayList();
		CotactPersonTable.getItems().clear();
		CotactPersonTable.getColumns().clear();
		dataContactPerson.clear();
		
		TableColumn<EmployeePhone, String> idColumn = new TableColumn<>("Employee id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("idEmp"));
		idColumn.setMinWidth(70);
		
		TableColumn<EmployeePhone, String> contactPersonColumn = new TableColumn<>("Contact Person");
		contactPersonColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
		contactPersonColumn.setMinWidth(170);

		CotactPersonTable.getColumns().add(idColumn);
		CotactPersonTable.getColumns().add(contactPersonColumn);
		

		CotactPersonTable.getItems().clear();

		Statement stmt = Main.con.createStatement();
		// select * from client_contactperson where licensed_operator_client in(
		// SELECT licensed_operator_client FROM client WHERE client_name LIKE '%an%');

		ResultSet rs = stmt.executeQuery(
				"select * from phone_emp where eId in(SELECT emp_Id FROM employee WHERE ename LIKE '%"
						+ txtSearchName.getText() + "%');");
		dataContactPerson.clear();
		while (rs.next()) {
			dataContactPerson.add(new EmployeePhone(rs.getString(1), rs.getString(2)));
		}

		CotactPersonTable.setItems(dataContactPerson);

	}
	@FXML
	void updatTableAfterSearch() throws SQLException {

		updateEmployeeTable();
		updateContactPersonTable();
	}
}
