package application;



import java.util.Iterator;
import java.util.LinkedList;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class TableViewCompanyOrder {
	LinkedList<TableViewCompanyOrderContents> list = new LinkedList<>();
	private ObservableList<TableViewCompanyOrderContents> obs;
	private BorderPane root = new BorderPane();
	//private TableViewContants[] list;
	 TableView<TableViewCompanyOrderContents> table = new TableView<>();

	public TableViewCompanyOrder(LinkedList<TableViewCompanyOrderContents> list,  TableView<TableViewCompanyOrderContents> table){
		this.list = list;
		this.table = table;

         TableColumn<TableViewCompanyOrderContents, String> supIdCol = new TableColumn<>("Supplier_id");
         supIdCol.setMinWidth(50);
         supIdCol.setCellValueFactory(new PropertyValueFactory<>("supp_id"));

        
         
         TableColumn<TableViewCompanyOrderContents, String> supNameCol = new TableColumn<>("Supplier_name");
         supNameCol.setMinWidth(215);
         supNameCol.setCellValueFactory(new PropertyValueFactory<>("sup_name"));

         TableColumn<TableViewCompanyOrderContents, String> proCodeCol = new TableColumn<>("Product_code");
         proCodeCol.setMinWidth(50);
         proCodeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
         
         TableColumn<TableViewCompanyOrderContents, String> proNameCol = new TableColumn<>("Product_name");
         proNameCol.setMinWidth(430);
         proNameCol.setCellValueFactory(new PropertyValueFactory<>("pro_name"));
         
         TableColumn<TableViewCompanyOrderContents, String> priceCol = new TableColumn<>("Price");
         priceCol.setMinWidth(50);
         priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

         table.setItems(addItemsToObservableList());
         table.getColumns().addAll(supIdCol,proCodeCol,proNameCol,supNameCol,priceCol);
        // root.setCenter(table);
	}
	
	 private ObservableList<TableViewCompanyOrderContents> addItemsToObservableList() {
	        obs = FXCollections.observableArrayList();

	        Iterator<TableViewCompanyOrderContents> iterator = list.iterator();
	        while (iterator.hasNext()) {
	        	
	            obs.add(iterator.next());
	        }
	        return obs;
	    }
	public BorderPane getRoot() {
		return root;
	}
	public LinkedList<TableViewCompanyOrderContents> getList() {
		return list;
	}
	 public TableView<TableViewCompanyOrderContents> table() {
		 return table;
	 }
}

