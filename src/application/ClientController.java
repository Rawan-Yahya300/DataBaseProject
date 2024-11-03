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

public class ClientController {
	@FXML
	TableView<Client> clintTable;

	@FXML
	TableView<ClientContactPerson> CotactPersonTable;

	@FXML
	public Button btSearch;
	@FXML
	private TextField txtSearchID;

	@FXML
	private TextField txtClientName;

	@FXML
	private TextField txtClienID;

	@FXML
	private TextField txtClienCntactAddress;

	@FXML
	private TextField txtClienAContactPerson;

	@FXML
	private Button btAdditionalContactPerson;

	@FXML
	private Button btAdd;

	@FXML
	private Button btChooseProduct;

	@FXML
	private Label lblWarning;

	private ArrayList<String> contactPerson = new ArrayList<>();

	private Client selectedClient;
	
	static MenuController menuController;

	// *********************************************************************************************************
	@FXML
	void btSearchAction(ActionEvent event) throws SQLException {

	}
	// _____________________________________________________________________________________

	@FXML
	void updatTableAfterSearch() throws SQLException {

		ubdateClientTable();
		ubdateContactPersonTable();
	}

	// ----------------------------------------------------------------------
	
	/**
	 * update the client table after search
	 * @throws SQLException
	 */
	public void ubdateClientTable() throws SQLException {

		ObservableList<Client> dataClient = FXCollections.observableArrayList();
		clintTable.getItems().clear();
		clintTable.getColumns().clear();
		dataClient.clear();

		TableColumn<Client, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("licensed_operator_client"));
		TableColumn<Client, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("client_name"));
		TableColumn<Client, String> ContactADdresColumn = new TableColumn<>("Contact Address");
		ContactADdresColumn.setCellValueFactory(new PropertyValueFactory<>("contact_address"));

		clintTable.getColumns().add(idColumn);
		clintTable.getColumns().add(nameColumn);
		clintTable.getColumns().add(ContactADdresColumn);

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT * FROM client WHERE client_name LIKE '%" + txtSearchID.getText() + "%';");
		while (rs.next()) {
			dataClient.add(new Client(rs.getString(1), rs.getString(2), rs.getString(3)));
		}

		clintTable.setItems(dataClient);
	}

	// ----------------------------------------------------------------------
	/**
	 * update the contact person table after search 
	 * @throws SQLException
	 */
	public void ubdateContactPersonTable() throws SQLException {

		ObservableList<ClientContactPerson> dataContactPerson = FXCollections.observableArrayList();
		CotactPersonTable.getItems().clear();
		CotactPersonTable.getColumns().clear();
		dataContactPerson.clear();
		TableColumn<ClientContactPerson, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("licensed_operator_client"));
		TableColumn<ClientContactPerson, String> contactPersonColumn = new TableColumn<>("Contact Person");
		contactPersonColumn.setCellValueFactory(new PropertyValueFactory<>("contact_person"));

		CotactPersonTable.getColumns().add(idColumn);
		CotactPersonTable.getColumns().add(contactPersonColumn);

		CotactPersonTable.getItems().clear();

		Statement stmt = Main.con.createStatement();
		// select * from client_contactperson where licensed_operator_client in(
		// SELECT licensed_operator_client FROM client WHERE client_name LIKE '%an%');

		ResultSet rs = stmt.executeQuery(
				"select * from client_contactperson where licensed_operator_client in(SELECT licensed_operator_client FROM client WHERE client_name LIKE '%"
						+ txtSearchID.getText() + "%');");
		dataContactPerson.clear();
		while (rs.next()) {
			dataContactPerson.add(new ClientContactPerson(rs.getString(1), rs.getString(2)));
		}

		CotactPersonTable.setItems(dataContactPerson);

	}
//_______________________________________________________________________________________

	@FXML
	void txtAdditionalContactPersonAction(ActionEvent event) {

	}

	@FXML
	/**
	 * to choose client from tableView to add his info to the client_order(to buy products)
	 * @throws SQLException
	 */
	void chooseClient() throws SQLException {
		selectedClient = clintTable.getSelectionModel().getSelectedItem();
		
		String id;
		String name;
		String contact_address;
		id = selectedClient.getLicensed_operator_client();
		name = selectedClient.getClient_name();
		contact_address = selectedClient.getContact_address();
		System.out.println(id + " " + name + " " + contact_address);
		
		

	}

	@FXML
	/**
	 *   go to the client_Order screen
	 * @param event
	 * @throws IOException
	 * @throws SQLException
	 */
	void btChooseProductAction(ActionEvent event) throws IOException, SQLException {
		if (clintTable.getSelectionModel().getSelectedItem() != null) {
			try {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientOrder.fxml"));
				Pane addEmployee = loader.load();
				ClientOrderController controller = loader.getController();
				controller.getLblClientId().setText(selectedClient.getLicensed_operator_client());
				controller.getLblClintName().setText(selectedClient.getClient_name());
				menuController.getPane().getChildren().setAll(addEmployee);
//				lbTitle.setText("Add Employee");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}
		
		
	}
	

	@FXML
	/**
	 * method to add client to the client table
	 * @param event
	 * @throws SQLException
	 */
	void btAddAction(ActionEvent event) throws SQLException {
		if (!txtClientName.getText().isEmpty() && !txtClienID.getText().isEmpty()
				&& !txtClienCntactAddress.getText().isEmpty() && !txtClienAContactPerson.getText().isEmpty()) {
			if (Main.isNumber(txtClienID.getText()) && txtClienID.getText().length() <= 7
					&& !clientIsExist(txtClienID.getText())) {
				Statement stmt = Main.con.createStatement();
				stmt.executeUpdate("INSERT INTO client values (\"" + txtClienID.getText() + "\",\""
						+ txtClientName.getText() + "\",\"" + txtClienCntactAddress.getText() + "\");");
				stmt.executeUpdate("INSERT INTO client_contactperson values (\"" + txtClienID.getText() + "\",\""
						+ txtClienAContactPerson.getText() + "\");");
				//for loop to add all the contact person in the arrayList
				for (int i = 0; i < contactPerson.size(); i++) {
					//Method to prevent adding the negation of the Person contract twice
					ResultSet rs = stmt
							.executeQuery("select * from client_contactperson where licensed_operator_client =\""
									+ txtClienID.getText() + "\" AND " + " contact_person=\"" + contactPerson.get(i)
									+ "\";");
					if (!rs.next()) {
						System.out.println("in for");
						stmt.executeUpdate("INSERT INTO client_contactperson values (\"" + txtClienID.getText()
								+ "\",\"" + contactPerson.get(i) + "\");");

					}

				}
                //clear the tables
				clintTable.getItems().clear();
				clintTable.getColumns().clear();
				CotactPersonTable.getItems().clear();
				CotactPersonTable.getColumns().clear();
				contactPerson.clear();

				addToClientTable();
				addToCotactPersonTable();
			} else {
				lblWarning.setText("Please ensure that the entered data is correct");
				lblWarning.setStyle("-fx-text-fill: red;");
			}

		} else {
			lblWarning.setText("Please ensure that the entered data is correct");
			lblWarning.setStyle("-fx-text-fill: red;");
		}
	}

	@FXML
	/**
	 * method to add an additional contact person
	 * @param event
	 * @throws SQLException
	 */
	void btAddPhoneAction(ActionEvent event) throws SQLException {
		String strPhone = txtClienAContactPerson.getText();

		if (!contactPerson.contains(strPhone)) {
			System.out.println("in if");
			contactPerson.add(strPhone);

		}

	}

	public boolean clientIsExist(String id) throws SQLException {

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select licensed_operator_client from client where licensed_operator_client ='" + id + "';");
		if (rs.next())
			return true;
		return false;

	}

	public void addToClientTable() throws SQLException {
		ObservableList<Client> dataClient = FXCollections.observableArrayList();
		TableColumn<Client, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("licensed_operator_client"));
		TableColumn<Client, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("client_name"));
		TableColumn<Client, String> ContactADdresColumn = new TableColumn<>("Contact Address");
		ContactADdresColumn.setCellValueFactory(new PropertyValueFactory<>("contact_address"));

		clintTable.getColumns().add(idColumn);
		clintTable.getColumns().add(nameColumn);
		clintTable.getColumns().add(ContactADdresColumn);

		clintTable.getItems().clear();

		dataClient.clear();
		addToObservableListClient(dataClient);

		clintTable.setItems(dataClient);

	}

	public void addToObservableListClient(ObservableList<Client> data) throws SQLException {
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM client;");
		while (rs.next()) {
			data.add(new Client(rs.getString(1), rs.getString(2), rs.getString(3)));
		}

	}

	// ______________________________________________________________________________________________

	public void addToCotactPersonTable() throws SQLException {
		ObservableList<ClientContactPerson> dataContactPerson = FXCollections.observableArrayList();
		TableColumn<ClientContactPerson, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("licensed_operator_client"));
		TableColumn<ClientContactPerson, String> contactPersonColumn = new TableColumn<>("Contact Person");
		contactPersonColumn.setCellValueFactory(new PropertyValueFactory<>("contact_person"));

		CotactPersonTable.getColumns().add(idColumn);
		CotactPersonTable.getColumns().add(contactPersonColumn);

		CotactPersonTable.getItems().clear();

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM client_contactperson;");
		dataContactPerson.clear();
		addToObservableListContact(dataContactPerson, rs);

		CotactPersonTable.setItems(dataContactPerson);

	}

	public void addToObservableListContact(ObservableList<ClientContactPerson> data, ResultSet rs) throws SQLException {

		while (rs.next()) {
			data.add(new ClientContactPerson(rs.getString(1), rs.getString(2)));
		}
	}

	// _________________________________________________________________________________________________________
//Setter & Getter
	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

}