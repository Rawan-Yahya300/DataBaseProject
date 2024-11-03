


package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

public class AddEmployeeController {
	@FXML
	private TextField ID;

	@FXML
	private TextField name;

	@FXML
	private TextField placeOfLiving;

	@FXML
	private TextField academicAchievement;

	@FXML
	private TextField phone;

	@FXML
	private Button btAddPhone;

	@FXML
	private Label lblPhone;
	@FXML
	private TextField experience;

	@FXML
	private TextField idDep;

	@FXML
	private DatePicker dateOfWork;

	@FXML
	private TextField salary;

	@FXML
	private Button create;

	@FXML
	private Button cancel;

	@FXML
	private Label warning;

	private ArrayList<String> phones = new ArrayList<>();

	@FXML
	void createButtonAction(ActionEvent event) throws SQLException {
		dateOfWork.setEditable(false);

		System.out.println();
		if (!ID.getText().isEmpty() && !name.getText().isEmpty() && !placeOfLiving.getText().isEmpty()
				&& !academicAchievement.getText().isEmpty() && !phone.getText().isEmpty() && !idDep.getText().isEmpty()
				&& dateOfWork.getValue() != null && !salary.getText().isEmpty()) {

			if (isEmployeeID(ID.getText())) {
				if (EmployeeIsExist(ID.getText()) == false) {
					if (idDep.getText().length() == 1 &&Main.isNumber(idDep.getText())) {
						if (departmentIsExist(idDep.getText())) {
							if (isCorrectDate(dateOfWork.getValue())) {
								try {
									double salaryAmount = Double.parseDouble(salary.getText());
									if (isCorrectPhone(phone.getText())) {
										LocalDate localDate = dateOfWork.getValue();
										Date date = new Date(localDate.getYear(), (localDate.getMonthValue()),
												localDate.getDayOfMonth());
										String strDate = "'" + localDate.getYear() + "-"
												+ (localDate.getMonthValue() - 1) + "-" + localDate.getDayOfMonth()
												+ "'";
										
                                          //insert a new  employee to employee table
										Statement stmt = Main.con.createStatement();
										stmt.executeUpdate("INSERT INTO employee values (\"" + ID.getText() + "\",\""
												+ name.getText() + "\",\"" + placeOfLiving.getText() + "\",\""
												+ academicAchievement.getText() + "\",\"" + experience.getText()
												+ "\",\"" + idDep.getText() + "\"," + strDate + "," + salaryAmount
												+ ",\"3245681\");");

										warning.setText("operation accomplished successfully");
										warning.setStyle("-fx-text-fill: darkgreen;");
									} else {
										warning.setText("please enter  a correct phone ");
										warning.setStyle("-fx-text-fill: red;");
									}
								} catch (Exception ex) {
									warning.setText("The salary must be number");
									System.out.println(ex.getMessage());
									warning.setStyle("-fx-text-fill: red;");
								}
							} else {
								warning.setText("Enter a correct date");
								warning.setStyle("-fx-text-fill: red;");
							}
						} else {
							warning.setText("The department does not exists");
							warning.setStyle("-fx-text-fill: red;");
						}
					} else {
						warning.setText("The id must be 1 digit and number");
						warning.setStyle("-fx-text-fill: red;");
					}
				} else {
					warning.setText("The id you are trying to enter already exists");
					warning.setStyle("-fx-text-fill: red;");
				}
			} else {
				warning.setText("The employee id must be 7 digits and number");
				warning.setStyle("-fx-text-fill: red;");
			}
		} else {
			warning.setText("please enter copmlete information");
			warning.setStyle("-fx-text-fill: red;");
		}
	}

	boolean isEmployeeID(String id) {
		if (id.length() != 7)
			return false;
		return Main.isNumber(id);
	}

	

	// Method for checking if the employee ID that the user is trying to enter
	// exists
	public boolean EmployeeIsExist(String id) throws SQLException {

		Statement stmt = Main.con.createStatement();
		//check if the employee already exist
		ResultSet rs = stmt.executeQuery("select emp_Id from employee where emp_Id ='" + id + "';");
		if (rs.next())
			return true;
		return false;

	}

	// Method for checking if the section the user is trying to enter exists
	public boolean departmentIsExist(String id) throws SQLException {
       
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("select id_dep from department where id_dep ='" + id + "';");
		if (rs.next())
			return true;
		return false;
	}

	public boolean isCorrectDate(LocalDate localDate) {
		// LocalDate localDate = datePicker.getValue();
		Date date = new Date(localDate.getYear(), (localDate.getMonthValue() - 1), localDate.getDayOfMonth());

		if (localDate.isAfter(LocalDate.now()))
			return false;
		return true;

	}

	public boolean isStrongPassword(String pass) {
		boolean isContainsSpecialCar = false;
		boolean isContainDigits = false;
		boolean isContainsCapitalLetter = false;
		boolean isContainsSmallLetter = false;

		for (int i = 0; i < pass.length(); i++) {
			if (!Character.isLetterOrDigit(pass.charAt(i))) {
				isContainsSpecialCar = true;
			}
			if (Character.isDigit(pass.charAt(i))) {
				isContainDigits = true;
			}
			if (Character.isUpperCase(pass.charAt(i))) {
				isContainsCapitalLetter = true;
			}
			if (Character.isLowerCase(pass.charAt(i))) {
				isContainsSmallLetter = true;
			}
		}
		if (isContainsSpecialCar && isContainDigits && isContainsCapitalLetter && isContainsSmallLetter)
			return true;
		return false;

	}
      //check if the entered phone is number of ten digits
	public boolean isCorrectPhone(String phone) {
		if (phone.length() != 10) {
			return false;
		}
		if (!Main.isNumber(phone)) {
			return false;
		}

		return true;
	}

	@FXML
	void cancelButtonAction(ActionEvent event) {
		System.out.println();
	}

	@FXML
	void btAddPhoneAction(ActionEvent event) {
		String strPhone = phone.getText();
		if (isCorrectPhone(strPhone)) {
			if (!phones.contains(strPhone)) {
				phones.add(strPhone);
				lblPhone.setText(strPhone);
				lblPhone.setStyle("-fx-text-fill: white;");
			} else {
				warning.setText("you have already added this number");
			}
		} else {
			warning.setText("please enter  a correct phone ");
		}
	}
}