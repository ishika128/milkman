/**
 * Sample Skeleton for 'billingView.fxml' Controller Class

 */
//use jasus method if u want the names not to be displayed on the list once the bill is done 
package billing;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.lang.Math;


public class billingViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lstnames"
    private ListView<String> lstnames; // Value injected by FXMLLoader

    @FXML // fx:id="dtpdos"
    private DatePicker dtpdos; // Value injected by FXMLLoader

    @FXML // fx:id="dtpdoe"
    private DatePicker dtpdoe; // Value injected by FXMLLoader

    @FXML // fx:id="lbldays"
    private Label lbldays; // Value injected by FXMLLoader

    @FXML // fx:id="txtcqty"
    private TextField txtcqty; // Value injected by FXMLLoader

    @FXML // fx:id="txtbfqty"
    private TextField txtbfqty; // Value injected by FXMLLoader

    @FXML // fx:id="txtcprice"
    private TextField txtcprice; // Value injected by FXMLLoader

    @FXML // fx:id="txtbfprice"
    private TextField txtbfprice; // Value injected by FXMLLoader

    @FXML // fx:id="txtmobile"
    private TextField txtmobile; // Value injected by FXMLLoader

    @FXML // fx:id="txtvcqty"
    private TextField txtvcqty; // Value injected by FXMLLoader

    @FXML // fx:id="txtvbfqty"
    private TextField txtvbfqty; // Value injected by FXMLLoader
    
    @FXML
    void docheck(MouseEvent event) {

    }


    @FXML // fx:id="lblbill"
    private Label lblbill; // Value injected by FXMLLoader
    Connection con ;
    int days;
    Float totalcowqty;
    Float totalbuffaloqty;
    Float bill ;

    @FXML
    void dogeneratebill(ActionEvent event) 
    {
       /*Float cqty=Float.parseFloat(txtcqty.getText());
       Float bfqty=Float.parseFloat(txtbfqty.getText());
       Float vcqty=Float.parseFloat(txtvcqty.getText());//here problem of numerException comes why ??????
       Float vbfqty=Float.parseFloat(txtvbfqty.getText());
       Float tcqty=days*(cqty+vcqty);
       Float tbfqty=days*(bfqty+vbfqty);
       Float cprice=Float.parseFloat(txtcprice.getText());
       Float bfprice=Float.parseFloat(txtbfprice.getText());
       Float bill=(cprice*tcqty)+(bfprice*tbfqty);
       lblbill.setText(String.valueOf(bill));*/
    	 Float cprice=Float.parseFloat(txtcprice.getText());
    	 Float bfprice=Float.parseFloat(txtbfprice.getText());
    	 bill=(cprice*totalcowqty)+(bfprice*totalbuffaloqty);
    	 lblbill.setText(String.valueOf(bill));
    	 
    	 String name=lstnames.getSelectionModel().getSelectedItem();
    	 LocalDate los=dtpdos.getValue();
    	 java.sql.Date swdos=java.sql.Date.valueOf(los);
    	    
    	
    	
       
       
    	
    }

    @FXML
    void dogetdays(ActionEvent event)
    {
   LocalDate ldos= 	dtpdos.getValue();
   LocalDate ldoe = dtpdoe.getValue();
   //Period intervalPeriod = Period.between(ldos, ldoe);
   long Days=ChronoUnit.DAYS.between(ldos, ldoe);
   
  // days= intervalPeriod.getDays();
   
   lbldays.setText(String.valueOf(Days)+"days");
   
  /* String name=lstnames.getSelectionModel().getSelectedItem();
   
     try {
		PreparedStatement pst= con.prepareStatement("select * from bills where name=?");
		pst.setString(1,name);
		ResultSet table=pst.executeQuery();
		while(table.next())
		{
		java.sql.Date swdos=	table.getDate("dos");
		swdos.toString();
		}
		
		
	   }  
	   
	     catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
   */
  
    }
    String totalvcowqty;
    String totalvbuffaloqty;

    @FXML
    void dogetvariations(ActionEvent event)
    {

    	String cname=lstnames.getSelectionModel().getSelectedItem();
                LocalDate lwdos=  dtpdos.getValue();
                java.sql.Date swdos=java.sql.Date.valueOf(lwdos);
                LocalDate lwdoe=  dtpdoe.getValue();
                java.sql.Date swdoe=java.sql.Date.valueOf(lwdoe);
    	
    	
    	try {
		PreparedStatement pst=	con.prepareStatement("select sum(cowqty) as 'scqty',sum(buffaloqty) from routineentry where date>=? and date<=? and cname=?");
	    System.out.println(swdos+" "+swdoe+" "+cname);
		pst.setDate(1, swdos);
	    pst.setDate(2, swdoe);
	    pst.setString(3, cname);
	    ResultSet table=pst.executeQuery();
	    while(table.next())
	   
	    {
	    
	   totalvcowqty=table.getString("scqty"); 
	   System.out.println(totalvcowqty);
	   totalvbuffaloqty=table.getString("sum(buffaloqty)"); 
	    }
	   Float cqtyy= Float.parseFloat(txtcqty.getText());
	   
	  Float cowqty= Float.parseFloat(totalvcowqty);
	    totalcowqty = cqtyy+cowqty;
	 totalbuffaloqty=    Float.parseFloat(txtbfqty.getText())+Float.parseFloat(totalvbuffaloqty);
	txtvcqty.setText(String.valueOf(totalcowqty));
	txtvbfqty.setText(String.valueOf(totalbuffaloqty));
	    
	    
	    
			
			
			
			
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	
    	
    	
    	
    }
    @FXML
    void dosaveandSms(ActionEvent event) 
    {
    String name=lstnames.getSelectionModel().getSelectedItem();
     LocalDate lwdos=dtpdos.getValue();
     java.sql.Date swdos=java.sql.Date.valueOf(lwdos);
     LocalDate lwdoe=dtpdoe.getValue();
     java.sql.Date swdoe=java.sql.Date.valueOf(lwdoe);
    
    		 String amount= lblbill.getText();
      try 
      {
    	  
		PreparedStatement pst=con.prepareStatement("INSERT INTO bills (name, dos, doe,amount,cowqty,bfqty) VALUES (?,?,?,?,?,?)");
       pst.setString(1, name);
       pst.setDate(2, swdos);
       pst.setDate(3,swdoe) ;
       pst.setFloat(4, bill);
       pst.setFloat(5, totalcowqty);
       
       pst.setFloat(6,totalbuffaloqty );
       pst.executeUpdate();
      lstnames.getItems().remove(name);
      dtpdos.getEditor().setText("");
      dtpdoe.getEditor().setText("");
      txtcqty.setText("");
      txtbfqty.setText("");
      txtcprice.setText("");
      txtbfprice.setText("");
      txtmobile.setText("");
      txtvcqty.setText("");
      txtvbfqty.setText("");
      showmsg("ur data is saved");
      
      
     
      
      } 
      catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      
     

    }

    @FXML
    void doentervalues(MouseEvent event) 
    {
    	String cname=lstnames.getSelectionModel().getSelectedItem();
    	 try {
			PreparedStatement pst=con.prepareStatement(" select * from customerentry where cname=?");
			pst.setString(1, cname);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
			Float cqty=	table.getFloat("cqty");
			Float bqty=table.getFloat("bqty");
			Float cprice=table.getFloat("cprice");
			Float  bprice=table.getFloat("bprice");
			String mobile=table.getString("mobile");
			txtcqty.setText(String.valueOf(cqty));
			txtbfqty.setText(String.valueOf(bqty));
			txtcprice.setText(String.valueOf(cprice));
			txtbfprice.setText(String.valueOf(bprice));
			txtmobile.setText(mobile);
			}
		
    	     } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    void showmsg(String ss)
    {
    	Alert aa= new Alert(AlertType.INFORMATION);
    	aa.setTitle("Saved!!");
    	aa.setContentText(ss);
    	aa.show();
    }

    void dofilllist()
    {
    	try 
    	{   
			PreparedStatement pst=con.prepareStatement("select * from customerentry");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{ 
				String sname=table.getString("cname");
				lstnames.getItems().addAll(sname);
//				String ary[]={"100","200","500","1000"};
//		         lstItems.getItems().addAll(ary);
//				
			}
			
		} 
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    	
    	
    	
    }
    void showwarning(String ss)
    {
    	Alert a = new Alert (AlertType.WARNING);
    	a.setTitle("warning");
    	a.setContentText(ss);
    	a.show();
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
    	con=DBConnection.doConnect();
    	dofilllist();
       

    }
}
