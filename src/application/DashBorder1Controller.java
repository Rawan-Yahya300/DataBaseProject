package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class DashBorder1Controller implements Initializable {
	@FXML
	private TableView<TableViewDashBoardContents> salesTable;
	@FXML
	private TableView<TableViewDashBoardContents> purchasisTable;
	@FXML
	private Pane DashPane;
	@FXML
	private Label lblTotalEmp;

	@FXML
	private Label lblTotalClients;

	@FXML
	private Label lblTotalProducts;

	@FXML
	private Label lblTotalSuppliers;
	@FXML
	private Label lblPriceSales;
	@FXML
	private Label lblPricePurchases;

	@FXML
	private Label lblGrossProfit;

	@FXML
	private TableView<Product> tblSales;

	@FXML
	private TableView<Product> tblPurchases;

	
	@FXML
	private PieChart pieChart = new PieChart();
	
	@FXML
	void mainAction(ActionEvent event) {

	}

	public void totalEmployee() throws SQLException {
		int total = 0;
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM employee;");
		if (rs.next()) {
			total = rs.getInt("total");
			lblTotalEmp.setText("" + total);
		}

	}

	public void totalClients() throws SQLException {
		int total = 0;
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM client;");
		if (rs.next()) {
			total = rs.getInt("total");
			lblTotalClients.setText("" + total);
		}

	}

	public void totalProductts() throws SQLException {
		int total = 0;
		// SQL query to count products with certain conditions
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM product WHERE "
				+ "code NOT IN (SELECT code FROM order_Line) AND purchasingDate IS NOT NULL;");
		if (rs.next()) {
			total = rs.getInt("total");
			lblTotalProducts.setText("" + total);
		}
	}

	public void totalSuppliers() throws SQLException {
		int total = 0;
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM supplier;");
		if (rs.next()) {
			total = rs.getInt("total");
			lblTotalSuppliers.setText("" + total);
		}

	}

	public void salesLastMonth() throws SQLException {
		Date curDate = new Date();
		int day = curDate.getDate();
		int month = curDate.getMonth();
		int year = curDate.getYear() + 1900;
		String dateString = year + "-" + month + "-" + day;

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select o.code,p.price,c.dateOfOrder from order_Line o, client_order c,product p where o.order_num = c.order_num and p.code = o.code and c.dateOfOrder > \" "
						+ dateString + "\";");
		LinkedList<TableViewDashBoardContents> list = new LinkedList<>();
		while (rs.next()) {
			Date date = rs.getDate(3);
			int day2 = date.getDate();
			int month2 = date.getMonth() + 1;
			int year2 = date.getYear() + 1900;
			String dateStr = year2 + "-" + month2 + "-" + day2;
			list.add(new TableViewDashBoardContents(rs.getString(1), rs.getDouble(2), dateStr));
		}
		TableViewDahshBoard tt = new TableViewDahshBoard(list, salesTable);

	}

	public void purchasesLastMonth() throws SQLException {
		Date curDate = new Date();
		int day = curDate.getDate();
		int month = curDate.getMonth();
		int year = curDate.getYear() + 1900;
		String dateString = year + "-" + month + "-" + day;

		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select p.code,p.purchasingPrice,p.purchasingDate from product p where  p.purchasingDate > \" "
						+ dateString + "\";");
		LinkedList<TableViewDashBoardContents> list = new LinkedList<>();
		while (rs.next()) {
			Date date = rs.getDate(3);
			int day2 = date.getDate();
			int month2 = date.getMonth() + 1;
			int year2 = date.getYear() + 1900;
			String dateStr = year2 + "-" + month2 + "-" + day2;
			list.add(new TableViewDashBoardContents(rs.getString(1), rs.getDouble(2), dateStr));
		}
		TableViewDahshBoard tt = new TableViewDahshBoard(list, purchasisTable);

	}

	public void grossProfit() throws SQLException {
		Date curDate = new Date();
		int day = curDate.getDate();
		int month = curDate.getMonth();
		int year = curDate.getYear() + 1900;
		String dateString = year + "-" + month + "-" + day;
		int salesTotal = 0;
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select sum(p.price) as total from order_Line o, client_order c,product p where o.order_num = c.order_num and p.code = o.code and c.dateOfOrder > \" "
						+ dateString + "\";");
		if (rs.next()) {
			salesTotal = rs.getInt("total");
			lblPriceSales.setText("Total Price: " + salesTotal);
			System.out.println(salesTotal);

		}
		int purchasertotal = 0;
		Statement stmt2 = Main.con.createStatement();
		ResultSet rs2 = stmt2.executeQuery(
				"select sum(p.purchasingPrice) as total from product p where p.purchasingDate > \" " + dateString + "\";");
		if (rs2.next()) {
			purchasertotal = rs2.getInt("total");
			lblPricePurchases.setText("Total Price: " + purchasertotal);
			System.out.println(purchasertotal);

		}
		lblGrossProfit.setText((salesTotal - purchasertotal) + "");
	}

	public Pane getDashPane() {
		return DashPane;
	}

	public void setDashPane(Pane dashPane) {
		DashPane = dashPane;
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Data> data = FXCollections.observableArrayList(
				
				);
		Date date = new Date();
		int month = date.getMonth();
		int year = date.getYear() + 1900;
		
		// TODO Auto-generated method stub
		try {
			for(int i = 1; i <= 3; i++) {
			Statement stmt2 = Main.con.createStatement();
			ResultSet rs2 = stmt2.executeQuery(
					"select sum(p.price) from Order_Line o,client_Order c,product p where p.code = o.code and c.order_num = o.order_num and c.dateOfOrder >= \""
							+ year + "-" + (month) + "-1\" and c.dateOfOrder <= \"" + year + "-" + (month) + "-30\";");
			double price = 1;
			if(rs2.next()) {
				System.out.println("iffffffffffff");
			 price = rs2.getDouble(1);
			 System.out.println("price"+price+" month:"+month);
			}
			
			data.add(new PieChart.Data(""+month, price));
			//ser.getData().add(new XYChart.Data("1", month));
			month--;
			
			}
			
		} catch (Exception ex) {
            ex.getStackTrace();
            System.out.println(ex.getMessage());
		}
		pieChart.setData(data);
	  
		

	}

}
