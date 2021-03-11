/**
 * Sample Skeleton for 'routineentryView.fxml' Controller Class

 */
//multipleSelection problem????????????

// solution= 

package routineentry;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.collections.ObservableSetWrapper;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class routineentryViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lstCname"
    private ListView<String> lstCname; // Value injected by FXMLLoader

    @FXML // fx:id="txtcqty"
    private TextArea txtcqty; // Value injected by FXMLLoader

    @FXML // fx:id="txtbqty"
    private TextArea txtbqty; // Value injected by FXMLLoader

    @FXML // fx:id="lblcqty"
    private Label lblcqty; // Value injected by FXMLLoader

    @FXML // fx:id="lblbqty"
    private Label lblbqty; // Value injected by FXMLLoader

    @FXML // fx:id="dtpday"
    private DatePicker dtpday; // Value injected by FXMLLoader
    Connection con=null;

    @FXML
    void doFetch(ActionEvent event) {
    	String name=lstCname.getSelectionModel().getSelectedItem();
    	
    	            try 
    	      {
    	            System.out.println("1");	
				PreparedStatement pst=con.prepareStatement("Select cqty,bqty from customerentry where cname=?");
				System.out.println("2");
				pst.setString(1, name);
				System.out.println("3");
				ResultSet table=pst.executeQuery();
				while(table.next())
				{
				
				Float cowqty=table.getFloat("cqty");
				System.out.println("5");
				Float bfqty=table.getFloat("bqty");
				System.out.println("6");
				lblcqty.setText(String.valueOf(cowqty));
				System.out.println("7");
				lblbqty.setText(String.valueOf(bfqty));
				System.out.println("8");
				}
						
		      }  
    	            catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	
    	
    	

    }
    @FXML
    void dodeleteothers(ActionEvent event)
//    { lstCname.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//      ObservableList<String>names= lstCname.getSelectionModel().getSelectedItems();
//      System.out.println("ishika");
//     names.
//     System.out.println("gupta");
    {
    	lstCname.getItems().retainAll(lstCname.getSelectionModel().getSelectedItems());//best way to remove the items which are not selected 
    	
      /*  ObservableList<String>ol=lstCname.getSelectionModel().getSelectedItems();//selected items are selected 
        ArrayList<String>al=new ArrayList<String>(ol);//in observable list references are stored
        lsrCname.getItems.clear();//here complete list is removed
        lstCname.getItems().addAll(ol);//here we have add those elements which are selected 
   */
    	
    }

    @FXML
    void dosave(ActionEvent event)
    {
    String name = lstCname.getSelectionModel().getSelectedItem();
     String cqty=txtcqty.getText();
      Float cowqty=Float.parseFloat(cqty);
     
     String bqty=txtbqty.getText();
     Float bfqty=Float.parseFloat(bqty);
     LocalDate lwday=dtpday.getValue();
     java.sql.Date swday=java.sql.Date.valueOf(lwday);
     
     
     try {
    	 System.out.println("9");
    	 //PreparedStatement pst=con.prepareStatement("insert into customerentry values(?,?,?,?)");
    	 PreparedStatement pst=con.prepareStatement("INSERT INTO routineentry (cname, date, cowqty,buffaloqty) VALUES (?, ?, ?,?)");

    	 pst.setString(1, name);
    	 pst.setDate(2, swday);
    	 pst.setFloat(3, cowqty);
    	 pst.setFloat(4, bfqty);
         pst.executeUpdate();
//         String done=lstCname.getSelectionModel().getSelectedItem();
//         String cname =lstCname.getSelectionModel().getSelectedItem();
//         
    	 
    	 
    	 
		
	     }
     catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

    @FXML
    void doselection(MouseEvent event) {

    }
    void fillList()
    {
    	try {
    		ArrayList<String>as=new ArrayList<String>();
			PreparedStatement pst=con.prepareStatement("select cname from customerentry");
			ResultSet table=pst.executeQuery();
			while(table.next())
					{
				String s=table.getString("cname");
				   as.add(s);
				
					}
			lstCname.getItems().addAll(as);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
        con=DBConnection.doConnect();
        fillList();
        lstCname.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
   
        
    }
}
