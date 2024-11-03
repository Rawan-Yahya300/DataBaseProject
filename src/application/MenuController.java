package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

public class MenuController {
	@FXML
	private Label lbTitle;
	
	@FXML
	private Label userName;
	@FXML
	private Button btDashBoard ;
	
	@FXML
	private StackPane pane ;     //
	
	@FXML
	private Button btNewSell ;
	@FXML
	private Button btAddEmployee;
	
	@FXML
	private Button btOrders ;
	@FXML
	private Button btWarehouses ;
	@FXML
	private Button btCustomers ;
	
	@FXML
	private Button btEmployee ;
	@FXML
	private Button btSuppliers ;
	@FXML
	private Button btReport ;
	
	
	@FXML
	public void initialize() throws SQLException {
		loadDashBoard();
		ClientController.menuController = this;
		
	}

	

	private void loadDashBoard() throws SQLException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DashBoard1.fxml"));
			Pane dashBoard = loader.load();
			DashBorder1Controller controller = loader.getController();
			controller.totalProductts();
			controller.totalEmployee();
			controller.totalSuppliers();
			controller.totalClients();
			controller.salesLastMonth();
			controller.purchasesLastMonth();
			controller.grossProfit();
			
			pane.getChildren().setAll(dashBoard);
			lbTitle.setText("DashBoard");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@FXML
	void btDashBoardAction(ActionEvent event) throws SQLException {
		loadDashBoard();
	}
	
	@FXML
	void btNewSellAction(ActionEvent event) {

	}
	@FXML
	void btAddEmployeeAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmployee.fxml"));
			Pane addEmployee = loader.load();
			AddEmployeeController controller = loader.getController();
			
			pane.getChildren().setAll(addEmployee);
			lbTitle.setText("Add Employee");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	

	
	@FXML
	void btOrdersAction(ActionEvent event) throws SQLException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CompanyOrders.fxml"));
			Pane companyOrder = loader.load();
			CompanyOrdersController controller = loader.getController();
			controller.companyOrderTable();
			controller.totalPrice();
			pane.getChildren().setAll(companyOrder);
			lbTitle.setText("Company Orders");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	@FXML
	void btWarehousesAction(ActionEvent event) throws SQLException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WareHouses.fxml"));
			Pane wareHouser = loader.load();
			WareHousesController controller = loader.getController();
			controller.displayTableWareHouse1();
			controller.displayTableWareHouse2();
			pane.getChildren().setAll(wareHouser);
			lbTitle.setText("WareHouses");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	@FXML
	void btCustomersAction(ActionEvent event) throws SQLException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Client.fxml"));
			Pane client = loader.load();
			ClientController controller = loader.getController();
			controller.addToClientTable();
			controller.addToCotactPersonTable();
			pane.getChildren().setAll(client);
			lbTitle.setText("Client");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	@FXML
	void btEmployeeAction(ActionEvent event) throws SQLException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Employee.fxml"));
			Pane employee = loader.load();
			EmployeeController controller = loader.getController();
			controller.displayEmployeeTable();
			controller.displayToCotactPersonTable();
			pane.getChildren().setAll(employee);
			lbTitle.setText("Employee");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	@FXML
	void btSuppliersAction(ActionEvent event) throws SQLException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Suppliers.fxml"));
			Pane supplier = loader.load();
			SupplierController controller = loader.getController();
			controller.displayTableSupplier();
			controller.displayToCotactPersonTable();
			pane.getChildren().setAll(supplier);
			lbTitle.setText("Supplier");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@FXML
	void btReportAction(ActionEvent event) {

	}
	//__________________________________________________



	public StackPane getPane() {
		return pane;
	}



	public void setPane(StackPane pane) {
		this.pane = pane;
	}



	public Label getUserName() {
		return userName;
	}



	public void setUserName(Label userName) {
		this.userName = userName;
	}
	
	
	
}