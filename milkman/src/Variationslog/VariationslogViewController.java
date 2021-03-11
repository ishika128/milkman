/**
 * Sample Skeleton for 'VariationslogView.fxml' Controller Class
 */

package Variationslog;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import billing.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tablejdbc.StudentBean;

public class VariationslogViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbcustomer"
    private ComboBox<String> cmbcustomer; // Value injected by FXMLLoader

    @FXML // fx:id="dtpdos"
    private DatePicker dtpdos; // Value injected by FXMLLoader

    @FXML // fx:id="dtpdoe"
    private DatePicker dtpdoe; // Value injected by FXMLLoader

    @FXML // fx:id="tblvariations"
    private TableView<VariationsBean>tblvariations; // Value injected by FXMLLoader
    Connection con=null ;
   
    @FXML
    void getvariations(ActionEvent event)
    { 
    	//System.out.println("***********");
    	LocalDate lwdos=dtpdos.getValue();
  java.sql.Date swdos= java.sql.Date.valueOf(lwdos);

  LocalDate lwdoe=dtpdoe.getValue();
    java.sql.Date swdoe= java.sql.Date.valueOf(lwdoe);
    	try {
			PreparedStatement pst =con.prepareStatement("select * from routineentry where date >=? and date<=?");
				pst.setDate(1, swdos);
			pst.setDate(2, swdoe);
			getallrecordsfromtable(pst);
			tblvariations.setItems(ll);
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doFind(ActionEvent event) {
    	String name=cmbcustomer.getSelectionModel().getSelectedItem();
    try {
		PreparedStatement pst =	con.prepareStatement("select * from routineentry where cname = ?");
		pst.setString(1, name);
		getallrecordsfromtable(pst);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    tblvariations.setItems(ll);
    	

    }

    @FXML
    void doshowallvariations(ActionEvent event)
    {
    try {
		PreparedStatement pst=	con.prepareStatement("select * from routineentry");
		getallrecordsfromtable(pst );
		
	    }
    catch (SQLException e) 
    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
         tblvariations.setItems(ll);
    	

    }
    ResultSet table;
    ObservableList<VariationsBean>ll;
    void getallrecordsfromtable(PreparedStatement pst)
    
    {
    	ll=FXCollections.observableArrayList();
    	try {
			 table=pst.executeQuery();
			while(table.next())
			{
				String name=table.getString("cname");
				java.sql.Date date=table.getDate("date");
				Float cowqty=table.getFloat("cowqty");
				Float buffaloqty=table.getFloat("buffaloqty");
				VariationsBean obj = new VariationsBean(name,date,cowqty,buffaloqty);
				ll.add(obj);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    void fillcombo()
    {
    	ArrayList<String>list=new ArrayList<>();
    	try {
			PreparedStatement pst =con.prepareStatement("select distinct cname from routineentry ");
			ResultSet table =pst.executeQuery();
			while(table.next())
			{
			String name=	table.getString("cname");
			list.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//cmbcustomer.setItems().add(list));
    	cmbcustomer.getItems().addAll(list);
    	
    	
    }
    void addcolumns()
    {
    
    	TableColumn<VariationsBean, String > cname =new TableColumn<VariationsBean, String >("customer name");//Dikhava Title
        cname.setCellValueFactory(new PropertyValueFactory<>("cname"));//bean field name not table col. name
        
        TableColumn<VariationsBean, Date> dos =new TableColumn<VariationsBean, Date>("date of Start");//Dikhava Title
        dos.setCellValueFactory(new PropertyValueFactory<>("date"));//bean field name not table col. name
        
        TableColumn<VariationsBean, Float > cqty =new TableColumn<VariationsBean, Float >("cow qty");//Dikhava Title
        cqty.setCellValueFactory(new PropertyValueFactory<>("cowqty"));//bean field name not table col. name
        
        
        TableColumn<VariationsBean, Float > bqty =new TableColumn<VariationsBean, Float >("buffalo qty");//Dikhava Title
        bqty.setCellValueFactory(new PropertyValueFactory<>("buffaloqty"));//bean field name not table col. name
        
        tblvariations.getColumns().clear();
        tblvariations.getColumns().addAll(cname,dos,cqty,bqty);
              
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
       
        con=DBConnection.doConnect();
        addcolumns();
        fillcombo();

    }
}
