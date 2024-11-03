package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

public class ClientOrderController {
	@FXML
	private TextField txtSearch;

	@FXML
	private TableView<Product> productTable;

	@FXML
	private TableView<Product> clientOrderBillTable;

	@FXML
	private Button btAddToOrder;

	@FXML
	private Button btFinish;

	@FXML
	private Label lblOrderNum;

	@FXML
	private Label lblClintName;

	@FXML
	private Label lblClientId;

	@FXML
	private Label lblTotalPrice;

	@FXML
	private RadioButton rdBuying;

	@FXML
	private RadioButton rdReservation;

	@FXML
	private Label lblWarning = new Label(" ");

	ToggleGroup toggleGroup = new ToggleGroup();

	ObservableList<Product> dataProduct = FXCollections.observableArrayList();
	ObservableList<Product> dataOrder = FXCollections.observableArrayList();

	Product selectedProduct;

	// initialize the order table and add radio buttons to the toggle Group
	@FXML
	public void initialize() throws SQLException {
		TableColumn<Product, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		idColumn.setMinWidth(40);

		TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("pname"));
		nameColumn.setMinWidth(140);

		TableColumn<Product, Double> ContactADdresColumn = new TableColumn<>("Price");
		ContactADdresColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		ContactADdresColumn.setMinWidth(30);

		clientOrderBillTable.getColumns().add(idColumn);
		clientOrderBillTable.getColumns().add(nameColumn);
		clientOrderBillTable.getColumns().add(ContactADdresColumn);

		// add radio buttons to the toggle Group
		rdBuying.setToggleGroup(toggleGroup);
		rdReservation.setToggleGroup(toggleGroup);

		//set the order number on the label
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"Select order_num from  client_order where order_num =  (SELECT MAX(order_num) FROM client_order);");
		int lastOrderNum=1;
		if (rs.next()) {
			 lastOrderNum = rs.getInt(1)+1;// store the result
		}
		lblOrderNum.setText(lastOrderNum+"");
		//******************************************************
		//*******************************************
		displayTable();

		//chooseProdact();
		//updateProdectTableAfterSearch();
		
		
		btAddToOrder.setOnAction(e->{
			try {
				chooseProdact();
				addToOrderAction();
				

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btFinish.setOnAction(e->{
			try {
				finishAction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}

	/**
	 * Method to display the items in the product table
	 * 
	 * @throws SQLException
	 */
	public void displayTable() throws SQLException {

		TableColumn<Product, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		idColumn.setMinWidth(40);

		TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("pname"));
		nameColumn.setMinWidth(140);

		TableColumn<Product, Double> ContactADdresColumn = new TableColumn<>("Price");
		ContactADdresColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		ContactADdresColumn.setMinWidth(30);

		productTable.getColumns().add(idColumn);
		productTable.getColumns().add(nameColumn);
		productTable.getColumns().add(ContactADdresColumn);

		productTable.getItems().clear();

		dataProduct.clear();
		addToObservableListPrpduct(dataProduct);

		productTable.setItems(dataProduct);
	}

	public void addToObservableListPrpduct(ObservableList<Product> data) throws SQLException {
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("Select p.code,p.pname,p.price from "
				+ " warehouse_product w , product p where " + " p.code=w.code And " + " w.id_warehouse=\"" + 1 + "\";");
		while (rs.next()) {
			data.add(new Product(rs.getString(1), rs.getString(2), rs.getDouble(3)));
		}

	}

//_____________________________________________________________________________________

	/**
	 * Update the product table according to the result of search
	 * 
	 * @throws SQLException
	 */
	@FXML
	public void updateProdectTableAfterSearch() throws SQLException {
		ObservableList<Product> dataProduct = FXCollections.observableArrayList();
		productTable.getItems().clear();
		productTable.getColumns().clear();
		dataProduct.clear();

		TableColumn<Product, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		idColumn.setMinWidth(40);

		TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("pname"));
		nameColumn.setMinWidth(140);

		TableColumn<Product, Double> ContactADdresColumn = new TableColumn<>("Price");
		ContactADdresColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		ContactADdresColumn.setMinWidth(30);

		productTable.getColumns().add(idColumn);
		productTable.getColumns().add(nameColumn);
		productTable.getColumns().add(ContactADdresColumn);

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE pname LIKE '%" + txtSearch.getText() + "%';");
		while (rs.next()) {
			dataProduct.add(new Product(rs.getString(1), rs.getString(2), rs.getDouble(3)));
		}

		productTable.setItems(dataProduct);
	}

	/**
	 * to select the product from the product table
	 */
	@FXML
	void chooseProdact() {
		selectedProduct = productTable.getSelectionModel().getSelectedItem();
	}

	/**
	 * to add the product to the order Table
	 * 
	 * @param event
	 * @throws SQLException
	 */


	
	@FXML
	void addToOrderAction() throws SQLException {
		if (productTable.getSelectionModel().getSelectedItem() != null) {
			dataOrder.add(selectedProduct);
			
			clientOrderBillTable.setItems(dataOrder);

			dataProduct.remove(selectedProduct);
			
			productTable.setItems(dataProduct);
			calculatePrice();
			
		}

	}

	@FXML
	void finishAction() throws SQLException {

		Date curDate = new Date();
		int day = curDate.getDate();
		int month = curDate.getMonth();
		int year = curDate.getYear() + 1900;
		String dateString = year + "-" + month + "-" + day;
		if (rdReservation.isSelected()) {

			// create an order in the sql
			Statement stmt = Main.con.createStatement();
			stmt.executeUpdate("Insert into client_order (licensed_operator_client ,dateOfOrder) values(\""
					+ lblClientId.getText() + "\",\"" + dateString + "\");");

			// return the number(order_num) of the last order
			ResultSet rs = stmt.executeQuery(
					"Select order_num from  client_order where order_num = (SELECT MAX(order_num) FROM client_order);");
			int lastOrderNum=1;
			if (rs.next()) {
				 lastOrderNum = rs.getInt(1);// store the result
			}

			for (int i = 0; i < dataOrder.size(); i++) {
				// add all the products that client reserved it in the order to the order line
				stmt.executeUpdate("Insert into order_line (order_num ,code)" + "values(\"" + lastOrderNum + "\",\""
						+dataOrder.get(i).getCode() + "\");");
				// update the product to the warehouse 2
				stmt.executeUpdate("update warehouse_product set id_warehouse=\"2\"" + "where code =\""
						+ dataOrder.get(i).getCode() +"\";");

			}
			lblWarning.setText("");
			rdBuying.setDisable(true);
			rdReservation.setDisable(true);
			lblWarning.setText("The reservation was completed successfully");
			lblWarning.setStyle("-fx-text-fill:darkgreen;");
		} else if (rdBuying.isSelected()) {
			// create an order in the sql
			Statement stmt = Main.con.createStatement();
			stmt.executeUpdate("Insert into client_order (licensed_operator_client ,dateOfOrder) " + "values(\""
					+ lblClientId.getText() + "\",\"" + dateString + " \");");

			// return the number(order_num) of the last order
			ResultSet rs = stmt.executeQuery(
					"Select order_num from  client_order where order_num =  (SELECT MAX(order_num) FROM client_order);");
			int lastOrderNum=1;
			if (rs.next()) {
				 lastOrderNum = rs.getInt(1);// store the result
			}
			for (int i = 0; i < dataOrder.size(); i++) {
				// add all the products that client reserved it in the order to the order line
				System.out.println("code: "+dataOrder.get(i).getCode() );
				stmt.executeUpdate("Insert into order_line (order_num ,code) " + "values(" + lastOrderNum + ",\""
						+dataOrder.get(i).getCode() + "\");");

				// delete the product from the warehouse
				stmt.executeUpdate(
						"delete from  warehouse_product " + "where code =\"" + dataOrder.get(i).getCode() + "\";");
				
			}
			lblWarning.setText("");
			rdBuying.setDisable(true);
			rdReservation.setDisable(true);
			lblWarning.setText("The sale was completed successfully");
			lblWarning.setStyle("-fx-text-fill:darkgreen;");
		} else {
			lblWarning.setText("Please select one of the two options(Buying/Reservation)");
			lblWarning.setStyle("-fx-text-fill: red;");
		}

	}
	//____________________________________________________________________________________________________________
	void calculatePrice() {
		double totalPrice = 0;
		for (int i =0 ; i< dataOrder.size() ; i++) {
			totalPrice+=dataOrder.get(i).getPrice();
		}
		lblTotalPrice.setText(totalPrice+"");
	}

	// _________________________________________________________________________________________________________
	// Setter & Getter

	public Product getSelectedClient() {
		return selectedProduct;
	}

	public void setSelectedClient(Product selectedClient) {
		this.selectedProduct = selectedClient;
	}

	public TextField getTxtSearch() {
		return txtSearch;
	}

	public void setTxtSearch(TextField txtSearch) {
		this.txtSearch = txtSearch;
	}

	public TableView<Product> getProductTable() {
		return productTable;
	}

	public void setProductTable(TableView<Product> productTable) {
		this.productTable = productTable;
	}

	public TableView<Product> getClientOrderBillTable() {
		return clientOrderBillTable;
	}

	public void setClientOrderBillTable(TableView<Product> clientOrderBillTable) {
		this.clientOrderBillTable = clientOrderBillTable;
	}

	public Button getBtAddToOrder() {
		return btAddToOrder;
	}

	public void setBtAddToOrder(Button btAddToOrder) {
		this.btAddToOrder = btAddToOrder;
	}

	public Button getBtFinish() {
		return btFinish;
	}

	public void setBtFinish(Button btFinish) {
		this.btFinish = btFinish;
	}

	public Label getLblOrderNum() {
		return lblOrderNum;
	}

	public void setLblOrderNum(Label lblOrderNum) {
		this.lblOrderNum = lblOrderNum;
	}

	public Label getLblClintName() {
		return lblClintName;
	}

	public void setLblClintName(Label lblClintName) {
		this.lblClintName = lblClintName;
	}

	public Label getLblClientId() {
		return lblClientId;
	}

	public void setLblClientId(Label lblClientId) {
		this.lblClientId = lblClientId;
	}

	public Label getLblTotalPrice() {
		return lblTotalPrice;
	}

	public void setLblTotalPrice(Label lblTotalPrice) {
		this.lblTotalPrice = lblTotalPrice;
	}

	public RadioButton getRdBuying() {
		return rdBuying;
	}

	public void setRdBuying(RadioButton rdBuying) {
		this.rdBuying = rdBuying;
	}

	public RadioButton getRdReservation() {
		return rdReservation;
	}

	public void setRdReservation(RadioButton rdReservation) {
		this.rdReservation = rdReservation;
	}

}