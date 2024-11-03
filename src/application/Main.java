
package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main extends Application {
	static Connection con;
	static ResultSet rsPro;
	static ResultSet rsEmp;
	static ResultSet rsClient;
	static ResultSet rsSupplier;
	static ResultSet rsOrder;
	static ResultSet rsOrderLine;
	static ArrayList<Product> list;// = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {
		try {

			list = new ArrayList<>();
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "RawanLeen@123");
			Statement stmtPro = con.createStatement();

			Statement stmtEmp = con.createStatement();
			Statement stmtClient = con.createStatement();
			Statement stmtSup = con.createStatement();
			Statement stmtOrder = con.createStatement();
			Statement stmtPrderLine = con.createStatement();

			rsPro = stmtPro.executeQuery("select * from product");
			rsEmp = stmtEmp.executeQuery("select * from Employee");
			rsClient = stmtClient.executeQuery("select * from Client");
			rsSupplier = stmtSup.executeQuery("select * from Supplier");
			rsOrder = stmtOrder.executeQuery("select * from Client_Order");
			rsOrderLine = stmtPrderLine.executeQuery("select * from Order_Line");
			while (rsPro.next())
				list.add(new Product(rsPro.getString(1), rsPro.getString(2), rsPro.getDouble(3)));
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).toString());
			}

			if (isExist("3245") != null) {
				System.out.println(isExist("3245686").toString());
			} else {
				System.out.println("no");
			}
			// BorderPane root =
			// (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Parent par = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
			Scene scene = new Scene(par);
			// Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Account isExist(String emp_Id) throws SQLException {

		// ResultSet rsPro1= stmtPro1.executeQuery("select * from product");

		Statement stmtPro1 = con.createStatement();
		ResultSet rsPro1 = stmtPro1.executeQuery("select * from accounts where emp_Id ='" + emp_Id + "';"); // 3245686
		System.out.println(rsPro1);
		if (rsPro1.next())
			return new Account(rsPro1.getString(1), rsPro1.getString(2));
		return null;
	}

	public static boolean isNumber(String num) {
		for (int i = 0; i < num.length(); i++)
			if (!Character.isDigit(num.charAt(i)))
				return false;
		return true;
	}

	public static void main(String[] args) {
		launch(args);
	}
}