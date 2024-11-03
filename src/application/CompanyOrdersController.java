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
import java.util.LinkedList;

public class CompanyOrdersController {
	
	@FXML
	private Label lblTotalPrice;
	@FXML
	private TableView companyOrderTable ;
	
	
	
	
  public void companyOrderTable() throws SQLException {
	  
	  
	//A query to fetch all the products in the product table and the date is Null (meaning they will not reach the company yet)
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("select s.licensed_operator,p.code,p.pname, s.supplier_Name,purchasingPrice "
				+ "   from supplier s, product p "
				+ "   where s.licensed_operator = p.sup_licensed_operator and p.purchasingDate is null;");
		LinkedList<TableViewCompanyOrderContents> list = new LinkedList<>();
		while(rs.next()) {
			
			list.add(new TableViewCompanyOrderContents(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5)));
		}
		TableViewCompanyOrder tt = new TableViewCompanyOrder(list, companyOrderTable);
  }
  
  public void totalPrice() throws SQLException {
	  double total = 0;
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(" select sum(p.purchasingPrice) as total"
				+ "				from supplier s, product p"
				+ "				  where s.licensed_operator = p.sup_licensed_operator and p.purchasingDate is null;");
		if (rs.next()) {
			total = rs.getDouble("total");
			lblTotalPrice.setText("" + total);
		}
  }
	

	
	
	
	
	
}