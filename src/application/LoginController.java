package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

	@FXML
	private TextField ID;

	@FXML
	private Button signInButton;

	@FXML
	private Button signUpButton;

	@FXML
	private PasswordField password;
	
	@FXML
	private Label warning;
	
	static Stage currentStage;

	@FXML
	void signInAction(ActionEvent event) throws SQLException {
    	System.out.println("testing");
//    	if(!ID.getText().isEmpty() && !password.getText().isEmpty() ) {
//    		Account acount = Main.isExist(ID.getText());
//    		if(acount != null) {
//    			if(acount.getPassword().equals(password.getText())) {
//    				System.out.println("true");
    				try {

    					FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
    					Pane menuPane = loader.load();
    					Scene menuScene = new Scene(menuPane);
    					Statement stmt = Main.con.createStatement();
    					ResultSet rs = stmt.executeQuery("SELECT e.ename from employee e , accounts a"
    							+ " where \""+ID.getText()+"\" =a.emp_id;");
    					if(rs.next()) {
    					MenuController controller = loader.getController();
    					
    					controller.getUserName().setText(rs.getString(1));
    					}
    					currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    					currentStage.setScene(menuScene);
    					currentStage.show();
    					
    					
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
//    			}else {
//    				warning.setText("incorrect password");
//    				//System.out.println("incorect password");
//    			}
//    		}else {
//    			warning.setText("incorrect id");
//    			//System.out.println("incorrect id");
//    		}
//    	}else {
//    		warning.setText("you don`t enter the id or the password");
//    		
//    	}
    }

	@FXML
	void SignUpButtonAction(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
			Pane signUpPane = loader.load();

			Scene signUpScene = new Scene(signUpPane);

			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currentStage.setScene(signUpScene);
			currentStage.show();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	//_________________________________
	//setters & getters

	public TextField getID() {
		return ID;
	}

	public void setID(TextField iD) {
		ID = iD;
	}
	

}