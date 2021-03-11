/**
 * Sample Skeleton for 'paymentcollectionView.fxml' Controller Class
 */

package paymentcollection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import CustomerEntry.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class paymentcollectionViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lblcow"
    private Label lblcow; // Value injected by FXMLLoader

    @FXML // fx:id="lblbf"
    private Label lblbf; // Value injected by FXMLLoader

    @FXML // fx:id="lblamount"
    private Label lblamount; // Value injected by FXMLLoader

    @FXML // fx:id="lblto"
    private Label lblto; // Value injected by FXMLLoader

    @FXML // fx:id="lblfrom"
    private Label lblfrom; // Value injected by FXMLLoader

    @FXML // fx:id="chkrecieved"
    private CheckBox chkrecieved; // Value injected by FXMLLoader

    @FXML // fx:id="cmbnames"
    private ComboBox<String> cmbnames; // Value injected by FXMLLoader
    
    
    String name ;
    Float amount ;
    @FXML
    void dopayment(ActionEvent event) 
    
    {
    	System.out.println("ishika");
    	/* name = cmbnames.getSelectionModel().getSelectedItem();*/
    	try {
    		  name = cmbnames.getSelectionModel().getSelectedItem();
			PreparedStatement pst= con.prepareStatement("select dos,doe,amount,cowqty,bfqty from bills where name =? ");
			pst.setString(1, name);
			ResultSet table = pst.executeQuery();
			while(table.next())
			{
			Float cowqty = 	table.getFloat("cowqty");
			Float bfqty = table.getFloat("bfqty");
			 amount = table.getFloat("amount");
			java.sql.Date swdos= table.getDate("dos");
			java.sql.Date swdoe= table.getDate("doe");
			
			lblcow.setText(String.valueOf(cowqty));
			lblbf.setText(String.valueOf(bfqty));
			lblamount.setText(String.valueOf(amount));
			lblfrom.setText(swdos+"");
			lblto.setText(swdoe+"");
			
			
			
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    


    @FXML
    void dosaveandsms(ActionEvent event) {
          System.out.println("1");
          if(chkrecieved.isSelected())
          {
        	  try {
        		 String name = cmbnames.getSelectionModel().getSelectedItem();
				PreparedStatement pst =con.prepareStatement("update bills set Status = 1 where name =? " );
				pst.setString(1, name);
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }
    
    	
    }
    String m ;
    @FXML
    void dosms(ActionEvent event) 
    {
    	try {
			PreparedStatement pst= con.prepareStatement("select * from customerentry where cname =?");
			pst.setString(1, name);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
		 m=	table.getString("mobile");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String msg=" ur payment has been deposited ";
        String resp=SST_SMS.bceSunSoftSend(m, msg); 

    } 
    void fillcombo()
    {
    	ArrayList<String>list=new ArrayList<>();
    	try {
			PreparedStatement pst =con.prepareStatement("select distinct name from bills");
		ResultSet table =	pst.executeQuery();
		while(table.next())
		{
			String name= table.getString("name");
			list.add(name);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	cmbnames.getItems().addAll(list);
    }
    Connection con=null;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
       con=DBConnection.doConnect();
       fillcombo();
    }
}
