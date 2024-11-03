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

public class WareHousesController {

	@FXML
	private TextField tfWareHouseSearch1;

	@FXML
	private TextField tfWareHouseSearch2;

	@FXML
	TableView<Product> wareHouse1Table;

	@FXML
	TableView<Product> wareHouse2Table;

	ObservableList<Product> dataWareHouse1 = FXCollections.observableArrayList();
	ObservableList<Product> dataWareHouse2 = FXCollections.observableArrayList();

	
	@FXML
	public void updateWareHouse1TableAfterSearch() throws SQLException {
		ObservableList<Product> dataProduct = FXCollections.observableArrayList();
		wareHouse1Table.getItems().clear();
		wareHouse1Table.getColumns().clear();
		dataProduct.clear();

		TableColumn<Product, String> codeCol = new TableColumn<>("product_code");
		codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
		codeCol.setMinWidth(40);

		TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("pname"));
		nameColumn.setMinWidth(300);

		wareHouse1Table.getColumns().add(codeCol);
		wareHouse1Table.getColumns().add(nameColumn);

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT p.code,p.pname FROM wareHouse_product w, product p WHERE  p.code = w.code and w.id_warehouse = \"1\"and pname LIKE '%"
						+ tfWareHouseSearch1.getText() + "%';");
		while (rs.next()) {
			dataProduct.add(new Product(rs.getString(1), rs.getString(2)));
		}

		wareHouse1Table.setItems(dataProduct);
	}
	//_____________________________________________________________________________
	public void displayTableWareHouse1() throws SQLException {

		TableColumn<Product, String> codeCol = new TableColumn<>("product_code");
		codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
		codeCol.setMinWidth(40);

		TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("pname"));
		nameColumn.setMinWidth(300);

		wareHouse1Table.getColumns().add(codeCol);
		wareHouse1Table.getColumns().add(nameColumn);

		wareHouse1Table.getItems().clear();

		dataWareHouse1.clear();
		addToObservableListWareHouse1(dataWareHouse1);

		wareHouse1Table.setItems(dataWareHouse1);
	}
	
	
	
	//---------------------------------------------------------------------------------------
	public void addToObservableListWareHouse1(ObservableList<Product> data) throws SQLException {
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT p.code,p.pname FROM wareHouse_product w, product p WHERE p.code=w.code and w.id_warehouse = \"1\";");
		while (rs.next()) {
			data.add(new Product(rs.getString(1), rs.getString(2)));
		}

	}
	//_____________________________________________________________________________
	public void displayTableWareHouse2() throws SQLException {

		TableColumn<Product, String> codeCol = new TableColumn<>("product_code");
		codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
		codeCol.setMinWidth(40);

		TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("pname"));
		nameColumn.setMinWidth(300);

		wareHouse2Table.getColumns().add(codeCol);
		wareHouse2Table.getColumns().add(nameColumn);

		wareHouse2Table.getItems().clear();

		dataWareHouse2.clear();
		addToObservableListWareHouse2(dataWareHouse2);

		wareHouse2Table.setItems(dataWareHouse2);
	}
	//---------------------------------------------------------------------------------------
		public void addToObservableListWareHouse2(ObservableList<Product> data) throws SQLException {
			Statement stmt = Main.con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT p.code,p.pname FROM wareHouse_product w, product p WHERE p.code=w.code and w.id_warehouse = \"2\";");
			while (rs.next()) {
				data.add(new Product(rs.getString(1), rs.getString(2)));
			}

		}
		//_____________________________________________________________________________
	@FXML
	public void updateWareHouse2TableAfterSearch() throws SQLException {
		ObservableList<Product> dataProduct = FXCollections.observableArrayList();
		wareHouse2Table.getItems().clear();
		wareHouse2Table.getColumns().clear();
		dataProduct.clear();

		TableColumn<Product, String> codeCol = new TableColumn<>("product_code");
		codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
		codeCol.setMinWidth(40);

		TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("pname"));
		nameColumn.setMinWidth(300);

		wareHouse2Table.getColumns().add(codeCol);
		wareHouse2Table.getColumns().add(nameColumn);

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT p.code,p.pname FROM wareHouse_product w, product p WHERE p.code = w.code and w.id_warehouse = \"2\" and pname LIKE '%"
						+ tfWareHouseSearch2.getText() + "%';");
		while (rs.next()) {
			dataProduct.add(new Product(rs.getString(1), rs.getString(2)));
		}

		wareHouse2Table.setItems(dataProduct);
	}

}