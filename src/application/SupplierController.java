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

public class SupplierController {

	@FXML
	private TextField txtSearchName;
	@FXML
	private TextField tfSuppId;

	@FXML
	private TextField tfSuppName;
	
	@FXML
	private TextField tfContactAddress;
	
	@FXML
	private TextField tfContactPerson;
	@FXML
	private Button btCreateSupplier;
	@FXML
	private Button btAddPhone;
	@FXML
	private TableView<Sup_contactPerson> tvsuppContactPerson;

	@FXML
	private TableView<Supplier> tvsuppliers;
     
	private ArrayList<String> contactPerson = new ArrayList<>();
	
	ObservableList<Supplier> dataSupplier = FXCollections.observableArrayList();
	ObservableList<Sup_contactPerson> dataWareHouse2 = FXCollections.observableArrayList();

	@FXML
	void CreateSupplierAction(ActionEvent event) throws SQLException {
	        if(!tfSuppId.getText().isEmpty() && !tfSuppName.getText().isEmpty() && !tfContactPerson.getText().isEmpty() 
	        		&& !tfContactAddress.getText().isEmpty()) {
	        	if (Main.isNumber(tfSuppId.getText()) && tfSuppId.getText().length() == 6 && !supplierIsExist(tfSuppId.getText())) {
					Statement stmt = Main.con.createStatement();
					stmt.executeUpdate("insert into supplier (licensed_operator,supplier_Name,contact_address) values(\""+tfSuppId.getText()+"\",\""+tfSuppName.getText()+"\",\""+tfContactAddress.getText()+"\");");
					stmt.executeUpdate("INSERT INTO sup_contactPerson values (\"" + tfSuppId.getText() + "\",\""
							+ tfContactPerson.getText() + "\");");
					for (int i = 0; i < contactPerson.size(); i++) {
						ResultSet rs = stmt
								.executeQuery("select * from sup_contactPerson where licensed_operator =\""
										+ tfSuppId.getText() + "\" AND " + " contact_person=\"" + contactPerson.get(i)
										+ "\";");
						if (!rs.next()) {
							System.out.println("in for");
							stmt.executeUpdate("INSERT INTO sup_contactPerson values (\"" + tfSuppId.getText()
									+ "\",\"" + contactPerson.get(i) + "\");");

						}

					}

					tvsuppliers.getItems().clear();
					tvsuppliers.getColumns().clear();
					tvsuppContactPerson.getItems().clear();
					tvsuppContactPerson.getColumns().clear();
					contactPerson.clear();

					displayTableSupplier();
					displayToCotactPersonTable();
				} 
	        	//else {
//					lblWarning.setText("Please ensure that the entered data is correct");
//					lblWarning.setStyle("-fx-text-fill: red;");
//				}

	        }
	}
	public boolean supplierIsExist(String id) throws SQLException {

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select licensed_operator from supplier where licensed_operator ='" + id + "';");
		if (rs.next())
			return true;
		return false;

	}
	
	public boolean depIdIsExist(String id) throws SQLException {

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select id_dep from department where id_dep ='" + id + "';");
		if (rs.next())
			return true;
		return false;

	}
	@FXML
	void AddPhoneAcction(ActionEvent event) throws SQLException {
		String strPhone = tfContactPerson.getText();

		if (!contactPerson.contains(strPhone)) {
			System.out.println("in if");
			contactPerson.add(strPhone);


		}
	}
	
	public void displayTableSupplier() throws SQLException {

		TableColumn<Supplier, String> idColumn = new TableColumn<>("supplier ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("licensed_operator"));
		idColumn.setMinWidth(40);

		TableColumn<Supplier, String> nameColumn = new TableColumn<>("Supplier Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("supplier_Name"));
		nameColumn.setMinWidth(340);

		TableColumn<Supplier, String> ContactAddresColumn = new TableColumn<>("Contact Address");
		ContactAddresColumn.setCellValueFactory(new PropertyValueFactory<>("contact_address"));
		ContactAddresColumn.setMinWidth(50);

		tvsuppliers.getColumns().add(idColumn);
		tvsuppliers.getColumns().add(nameColumn);
		tvsuppliers.getColumns().add(ContactAddresColumn);

		tvsuppliers.getItems().clear();

		dataSupplier.clear();
		addToObservableListSupplier(dataSupplier);

		tvsuppliers.setItems(dataSupplier);
	}
	
	public void addToObservableListSupplier(ObservableList<Supplier> data) throws SQLException {
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM supplier;");
		while (rs.next()) {
			data.add(new Supplier(rs.getString(1), rs.getString(3), rs.getString(4)));
		}

	}
	
	public void displayToCotactPersonTable() throws SQLException {
		ObservableList<Sup_contactPerson> dataContactPerson = FXCollections.observableArrayList();
		TableColumn<Sup_contactPerson, String> idColumn = new TableColumn<>("supplier ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("licensed_operator"));
		idColumn.setMinWidth(40);
		
		TableColumn<Sup_contactPerson, String> contactPersonColumn = new TableColumn<>("Contact Person");
		contactPersonColumn.setCellValueFactory(new PropertyValueFactory<>("contact_person"));
		contactPersonColumn.setMinWidth(170);

		tvsuppContactPerson.getColumns().add(idColumn);
		tvsuppContactPerson.getColumns().add(contactPersonColumn);

		tvsuppContactPerson.getItems().clear();

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM sup_contactPerson;;");
		dataContactPerson.clear();
		addToObservableListContact(dataContactPerson, rs);

		tvsuppContactPerson.setItems(dataContactPerson);

	}

	public void addToObservableListContact(ObservableList<Sup_contactPerson> data, ResultSet rs) throws SQLException {

		while (rs.next()) {
			data.add(new Sup_contactPerson(rs.getString(1), rs.getString(2)));
		}
	}
	
	@FXML
	void updatTableAfterSearch() throws SQLException {

		updateSupplierTable();
		updateContactPersonTable();
	}
	public void updateSupplierTable() throws SQLException {

		ObservableList<Supplier> dataClient = FXCollections.observableArrayList();
		tvsuppliers.getItems().clear();
		tvsuppliers.getColumns().clear();
		dataClient.clear();


		TableColumn<Supplier, String> idColumn = new TableColumn<>("supplier ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("licensed_operator"));
		idColumn.setMinWidth(40);

		TableColumn<Supplier, String> nameColumn = new TableColumn<>("Supplier Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("supplier_Name"));
		nameColumn.setMinWidth(340);

		TableColumn<Supplier, String> ContactAddresColumn = new TableColumn<>("Contact Address");
		ContactAddresColumn.setCellValueFactory(new PropertyValueFactory<>("contact_address"));
		ContactAddresColumn.setMinWidth(50);

		tvsuppliers.getColumns().add(idColumn);
		tvsuppliers.getColumns().add(nameColumn);
		tvsuppliers.getColumns().add(ContactAddresColumn);

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT * FROM supplier WHERE supplier_Name LIKE '%" + txtSearchName.getText() + "%';");
		while (rs.next()) {
			dataClient.add(new Supplier(rs.getString(1), rs.getString(3), rs.getString(4)));
		}

		tvsuppliers.setItems(dataClient);
	}

	// ----------------------------------------------------------------------
	public void updateContactPersonTable() throws SQLException {

		ObservableList<Sup_contactPerson> dataContactPerson = FXCollections.observableArrayList();
		tvsuppContactPerson.getItems().clear();
		tvsuppContactPerson.getColumns().clear();
		dataContactPerson.clear();
		TableColumn<Sup_contactPerson, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("licensed_operator"));
		TableColumn<Sup_contactPerson, String> contactPersonColumn = new TableColumn<>("Contact Person");
		contactPersonColumn.setCellValueFactory(new PropertyValueFactory<>("contact_person"));

		tvsuppContactPerson.getColumns().add(idColumn);
		tvsuppContactPerson.getColumns().add(contactPersonColumn);

		tvsuppContactPerson.getItems().clear();

		Statement stmt = Main.con.createStatement();
		// select * from client_contactperson where licensed_operator_client in(
		// SELECT licensed_operator_client FROM client WHERE client_name LIKE '%an%');

		ResultSet rs = stmt.executeQuery(
				"select * from sup_contactPerson where licensed_operator in(SELECT licensed_operator FROM supplier WHERE supplier_Name LIKE '%"
						+ txtSearchName.getText() + "%');");
		dataContactPerson.clear();
		while (rs.next()) {
			dataContactPerson.add(new Sup_contactPerson(rs.getString(1), rs.getString(2)));
		}

		tvsuppContactPerson.setItems(dataContactPerson);

	}
}