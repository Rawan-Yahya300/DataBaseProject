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

public class TableViewDahshBoard {
	LinkedList<TableViewDashBoardContents> list = new LinkedList<>();
	private ObservableList<TableViewDashBoardContents> obs;
	private BorderPane root = new BorderPane();
	//private TableViewContants[] list;
	 TableView<TableViewDashBoardContents> table = new TableView<>();

	public TableViewDahshBoard(LinkedList<TableViewDashBoardContents> list,  TableView<TableViewDashBoardContents> table){
		this.list = list;
		this.table = table;

         TableColumn<TableViewDashBoardContents, String> codeCol = new TableColumn<>("Code");
         codeCol.setMinWidth(81);
         codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));

        
         
         TableColumn<TableViewDashBoardContents, Integer> priceCol = new TableColumn<>("price");
         priceCol.setMinWidth(81);
         priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

         TableColumn<TableViewDashBoardContents, String> dateCol = new TableColumn<>("Date");
         dateCol.setMinWidth(81);
         dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

         table.setItems(addItemsToObservableList());
         table.getColumns().addAll(codeCol,priceCol, dateCol);
        // root.setCenter(table);
	}
	
	 private ObservableList<TableViewDashBoardContents> addItemsToObservableList() {
	        obs = FXCollections.observableArrayList();

	        Iterator<TableViewDashBoardContents> iterator = list.iterator();
	        while (iterator.hasNext()) {
	        	
	            obs.add(iterator.next());
	        }
	        return obs;
	    }
	public BorderPane getRoot() {
		return root;
	}
	public LinkedList<TableViewDashBoardContents> getList() {
		return list;
	}
	 public TableView<TableViewDashBoardContents> table() {
		 return table;
	 }
}

