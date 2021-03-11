/**
 * Sample Skeleton for 'tablejdbcView.fxml' Controller Class
 */

package tablejdbc;

import java.net.URL;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;



import java.sql.Connection;
import java.sql.PreparedStatement;

import billing.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class tablejdbcViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tbl"
    private TableView<StudentBean>tbl; // Value injected by FXMLLoader
    

    @FXML
    private ComboBox<String> cmbcustomers;

    @FXML
    private RadioButton radcow;

    @FXML
    private RadioButton radbuffalo;

    @FXML
    void doradfetch(ActionEvent event) {
    	if (radcow.isSelected())
    	{
    		try {
				PreparedStatement pst=con.prepareStatement("select * from customerentry where bqty=0 and cqty > 0 ");
			ResultSet table=	pst.executeQuery();
			getAllRecordsFromTable(pst);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		tbl.setItems(list);
    	}
    	if(radbuffalo.isSelected())
    	{ 
    		try {
			PreparedStatement pst=	con.prepareStatement("select * from customerentry where bqty>0 and cqty=0 ");
			ResultSet table=pst.executeQuery();
			getAllRecordsFromTable(pst);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		tbl.setItems(list);
    		
    	}

    }

    @FXML
    void dofetch(ActionEvent event) 
    {      String dos=cmbcustomers.getSelectionModel().getSelectedItem();
          
         try {
			PreparedStatement pst= con.prepareStatement("select * from customerentry where dos= ?");
			pst.setString(1, dos);
			getAllRecordsFromTable(pst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         tbl.setItems(list);
         
    	

    }
    
    Connection con=null;

    @FXML
    void doshowall(ActionEvent event)
    {
    	
    cmbcustomers.getSelectionModel().select(-1);
    
    	try {
            PreparedStatement  pst=con.prepareStatement("select * from customerentry ");
            
            getAllRecordsFromTable(pst);
       } 
       catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
        
       tbl.setItems(list);//5
        

   }
   
   ResultSet table;
 
   ObservableList<StudentBean> list;
   void getAllRecordsFromTable(PreparedStatement pst)//2
   {
       list=FXCollections.observableArrayList();
       try {
            
            table= pst.executeQuery();
       while(table.next())
       {
    	   String name=table.getString("cname");
			String mobile=table.getString("mobile");
			String address=table.getString("address");
		     Float cqty=	table.getFloat("cqty");
			Float bqty=table.getFloat("bqty");
			Float cprice=table.getFloat("cprice");
			Float bprice=table.getFloat("bprice");
			java.sql.Date dos=table.getDate("dos");
			StudentBean obj=new StudentBean(name, mobile, address, cqty, bqty, cprice, bprice, dos);
			list.add(obj);
       }
       } 
       catch (SQLException e) 
       {
           e.printStackTrace();
       }
   }
    void addcolumns()
    {
    	TableColumn<StudentBean, String > cname =new TableColumn<StudentBean, String >("customer name");//Dikhava Title
        cname.setCellValueFactory(new PropertyValueFactory<>("cname"));//bean field name not table col. name
        
        TableColumn<StudentBean, String > mobile =new TableColumn<StudentBean, String >("phone no.");//Dikhava Title
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name not table col. name
        
        TableColumn<StudentBean, String > address=new TableColumn<StudentBean, String >("address");//Dikhava Title
        address.setCellValueFactory(new PropertyValueFactory<>("address"));//bean field name not table col. name
       
        TableColumn<StudentBean, Float > cqty=new TableColumn<StudentBean, Float >("cow qty ");//Dikhava Title
        cqty.setCellValueFactory(new PropertyValueFactory<>("cqty"));//bean field name not table col. name
        
        TableColumn<StudentBean, Float > bqty=new TableColumn<StudentBean, Float >("buffalo qty ");//Dikhava Title
        bqty.setCellValueFactory(new PropertyValueFactory<>("bqty"));//bean field name not table col. name
        
        TableColumn<StudentBean, Float > cprice=new TableColumn<StudentBean, Float >("cow price ");//Dikhava Title
        cprice.setCellValueFactory(new PropertyValueFactory<>("cprice"));//bean field name not table col. name
       
        TableColumn<StudentBean, Float > bprice=new TableColumn<StudentBean, Float >("buffalo price ");//Dikhava Title
        bprice.setCellValueFactory(new PropertyValueFactory<>("bprice"));//bean field name not table col. name
        
        TableColumn<StudentBean, Date > dos=new TableColumn<StudentBean, Date >("date of start ");//Dikhava Title
        dos.setCellValueFactory(new PropertyValueFactory<>("dos"));//bean field name not table col. name
        
  tbl.getColumns().clear();
  tbl.getColumns().addAll(cname,mobile,address,cqty,bqty,cprice,bprice,dos);
        
    }
    void fillcombo()
    {
    	ArrayList<String> arraylist = new ArrayList<>();
    	try {
			PreparedStatement pst =con.prepareStatement("select distinct dos from customerentry");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
		java.sql.Date swdos=table.getDate("dos");
		arraylist.add(swdos+"");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	cmbcustomers.getItems().addAll(arraylist);
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        //assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'tablejdbcView.fxml'.";
      con=  DBConnection.doConnect();
      fillcombo();
        addcolumns();
    }
}
