/**
 * Sample Skeleton for 'incomerecordsView.fxml' Controller Class
 */

package incomerecords;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import billing.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;


public class incomerecordsViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="dtpdos"
    private DatePicker dtpdos; // Value injected by FXMLLoader

    @FXML // fx:id="dtpdoe"
    private DatePicker dtpdoe; // Value injected by FXMLLoader

    @FXML // fx:id="lblincome"
    private Label lblincome; // Value injected by FXMLLoader
    Connection con = null ;

    @FXML
    void doincome(ActionEvent event) 
    { LocalDate lwdos= dtpdos.getValue();
    LocalDate lwdoe= dtpdoe.getValue();
    java.sql.Date swdos= java.sql.Date.valueOf(lwdos);
    java.sql.Date swdoe= java.sql.Date.valueOf(lwdoe);
              try 
              {
				PreparedStatement pst =con.prepareStatement("select sum(amount) from bills where dos>=? and doe<= ?");
				pst.setDate(1, swdos);
				pst.setDate(2, swdoe);
			    ResultSet table =	pst.executeQuery();
			while(table.next())
			{
				Float total =table.getFloat("sum(amount)");
			     lblincome.setText(String.valueOf(total));
			}
			   } 
              catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              //lblincome.setText(String.valueOf(total));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
        con =DBConnection.doConnect();
    }
}
