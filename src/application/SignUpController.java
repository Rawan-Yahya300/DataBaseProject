package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUpController {

	@FXML
	private TextField ID;

	@FXML
	private Button creatButton;

	@FXML
	private Button cancelButton;

	@FXML
	private PasswordField password;

	@FXML
	private Label warning;

	@FXML
	void createAction(ActionEvent event) throws SQLException {
		if (!ID.getText().isEmpty() && !password.getText().isEmpty()) {
			if (isEmployeeID(ID.getText())) {

				Statement stmt = Main.con.createStatement();
				ResultSet rs = stmt.executeQuery("select emp_Id from accounts where emp_Id ='" + ID.getText() + "';");
				if (rs.next()) {
					warning.setText("This id already has an account");
					warning.setStyle("-fx-text-fill: red;");
				} else {
					Statement stmt2 = Main.con.createStatement();
					ResultSet rs2 = stmt2
							.executeQuery("select emp_Id from employee where emp_Id ='" + ID.getText() + "';");
					if (rs2.next()) {
						if (isStrongPassword(password.getText())) {
							Statement stmtAdd = Main.con.createStatement();
							stmtAdd.executeUpdate("INSERT INTO accounts values (\"" + ID.getText() + "\",\""
									+ password.getText() + "\");");
							warning.setText("operation accomplished successfully");
							warning.setStyle("-fx-text-fill: lightgreen;");
						} else {
							warning.setText("The password must contains numbers ,capital letter ,small letter and special character ");
							warning.setStyle("-fx-text-fill: red;");
						}
					} else {
						warning.setText("You are trying to create an account for an employee that does not exist");
						warning.setStyle("-fx-text-fill: red;");
					}
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

	@FXML
	void cancelButtonAction(ActionEvent event) throws IOException {
		Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currentStage.close();
		Parent par = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
		Scene scene = new Scene(par);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		System.out.println("cancel");
	}

	boolean isEmployeeID(String id) {
		if (id.length() != 7)
			return false;
		return Main.isNumber(id);
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

}