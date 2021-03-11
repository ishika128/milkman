/**
 * Sample Skeleton for 'CustomerEntryView.fxml' Controller Class
 */
//upload pic problem
//in new combotems names problem new does not happen there
//

package CustomerEntry;

import java.awt.TextArea;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class CustomerEntryViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtmobile"
    private TextField txtmobile; // Value injected by FXMLLoader

    @FXML // fx:id="txtaddress"
    private TextField txtaddress; // Value injected by FXMLLoader

    @FXML // fx:id="txtcqty"
    private TextField txtcqty; // Value injected by FXMLLoader

    @FXML // fx:id="txtcprice"
    private TextField txtcprice; // Value injected by FXMLLoader

    @FXML // fx:id="txtbqty"
    private TextField txtbqty; // Value injected by FXMLLoader

    @FXML // fx:id="txtbprice"
    private TextField txtbprice; // Value injected by FXMLLoader

    @FXML // fx:id="dtpdos"
    private DatePicker dtpdos; // Value injected by FXMLLoader
    
    @FXML
    private Label lblpath;
    @FXML
    private ImageView imgchange;




    @FXML // fx:id="cmbname"
    private ComboBox<String> cmbname; // Value injected by FXMLLoader
     Connection con=null;
     String imagepath;
     Image img ;
     Image image;

    @FXML
    void doSave(ActionEvent event) 
    {      String name=cmbname.getSelectionModel().getSelectedItem();
           String phone=txtmobile.getText();
           String address  = txtaddress.getText();
           float cowqty =Float.parseFloat(txtcqty.getText());
           float cowprice =Float.parseFloat(txtcprice.getText());
           float bfqty =Float.parseFloat(txtbqty.getText());
           float bfprice =Float.parseFloat(txtbprice.getText());
             LocalDate lwdos=dtpdos.getValue();
             java.sql.Date swdos=java.sql.Date.valueOf(lwdos);
           
       
           
    	 try {
    		 
			PreparedStatement pst =con.prepareStatement("insert into customerentry values(?,?,?,?,?,?,?,?,? )");
			   pst.setString(1, name);
			   pst.setString(2, phone);
			   pst.setString(3, address);
			   pst.setFloat(4, cowqty);
			   pst.setFloat(5, cowprice);
			   pst.setFloat(6, bfqty);
			   pst.setFloat(7, bfprice);
			   pst.setDate(8, swdos);
			   pst.setString(9, imagepath);
			   pst.executeUpdate();
			   showmsg(" ishika ");
			   
			   
			   
                    			
			
		   } 
    	 catch (Exception e)
    	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    }

    @FXML
    void dofetch(ActionEvent event)
    {    String name=cmbname.getSelectionModel().getSelectedItem();
             try {
				PreparedStatement pst =con.prepareStatement("select * from customerEntry where cname =?");
				pst.setString(1, name);
			     ResultSet table=	pst.executeQuery();
			    boolean jasus=false;
			    while(table.next())
			    {
			    	jasus=true;
			    	
			    
			    String mobile = table.getString("mobile");
			    String address= table.getString("address");
			    Float cqty = table.getFloat("cqty");
			    Float cprice =  table.getFloat("cprice");
			    Float bqty =table.getFloat("bqty");
			    Float bprice =table.getFloat("bprice");
			    java.sql.Date swdos=table.getDate("dos");
			    String path=table.getString("path");
			    File file = new File(path);
			     
				try {
					img = new Image(new FileInputStream(file));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    txtmobile.setText(mobile);
			    txtaddress.setText(address);
			    txtcqty.setText(String.valueOf(cqty));
			    txtcprice.setText(String.valueOf(cprice));
			    txtbqty.setText(String.valueOf(bqty));
			    txtbprice.setText(String.valueOf(bprice));
			    dtpdos.getEditor().setText(swdos+"");
			    imgchange.setImage(img);
			    }
			    if(jasus==false)
			    {
			    	showinformation("This customer not available");
			    }
			     
			
				
			    }   
             
             catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	

    }

    @FXML
    void doleft(ActionEvent event) 
    {   String cname = cmbname.getSelectionModel().getSelectedItem();
    if(cmbname.getSelectionModel().isEmpty())
    {
    	showinformation("Select customer name ");
    }
    else{
          try {
			PreparedStatement pst= con.prepareStatement("delete  from customerentry where cname =?");
			        pst.setString(1, cname);
                  pst.executeUpdate();
                  showconfirmation("confirmation");
              	cmbname.getEditor().setText("");
            	txtmobile.setText("");
            	txtaddress.setText("");
            	txtcqty.setText("");
            	txtcprice.setText("");
            	txtbqty.setText("");
            	txtbprice.setText("");
            	dtpdos.getEditor().setText("");}
            	
            	
	         
	          
				    
			        	 
			        	
			        
			
		     
           
          
          catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    }

    @FXML
    void donew(ActionEvent event) {
    	cmbname.getEditor().setText("");
    	txtmobile.setText("");
    	txtaddress.setText("");
    	txtcqty.setText("");
    	txtcprice.setText("");
    	txtbqty.setText("");
    	txtbprice.setText("");
    	dtpdos.getEditor().setText("");
    	//imgchange.setImage(new Image("/"));// to remove the image which is already there
    	// imgchange.setImage(new Image("");
    	
    	String image;
    	try
    	{
    		image=new File("F:\\workspace\\milkman\\src\\CustomerEntry\\images (1).jpg").toURI().toURL().toString();
    		Image im = new Image (image);
    		imgchange.setImage(im);
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	

    }
    @FXML
    void doupload(ActionEvent event)
    {
    	FileChooser chooser=new FileChooser();
    	chooser.setTitle("open file");
    	File file = chooser.showOpenDialog(null);
    	if(file!=null)
    	{
    		imagepath=file.getPath();
    		System.out.println("file:"+imagepath);
    		lblpath.setText(imagepath);
    		String imagefile ;
    		
    		
    		try {
				imagefile=file.toURI().toURL().toString();
				Image image = new Image(imagefile);
				imgchange.setImage(image);
				
				
				
			   } 
    		
    		
    		catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    	}

    }
    
    @FXML
    void doupdate(ActionEvent event) 
   {
   String name=cmbname.getSelectionModel().getSelectedItem();
   String phone=txtmobile.getText();
   String address  = txtaddress.getText();
   float cowqty =Float.parseFloat(txtcqty.getText());
   float cowprice =Float.parseFloat(txtcprice.getText());
   float bfqty =Float.parseFloat(txtbqty.getText());
   float bfprice =Float.parseFloat(txtbprice.getText());
   LocalDate lwdos = dtpdos.getValue();
   String path=lblpath.getText();
   if(lwdos==null)
   {
             String stdos= dtpdos.getEditor().getText();
	            lwdos=LocalDate.parse(stdos);
   }
   java.sql.Date swdos= java.sql.Date.valueOf(lwdos); 
     try {
		PreparedStatement pst= con.prepareStatement("update customerentry set mobile=?,address=?,cqty=?,cprice=?,bqty=?,bprice=?,dos=?,path=? where cname=?   ");
		pst.setString(9, name);
		   pst.setString(1, phone);
		   pst.setString(2, address);
		   pst.setFloat(3, cowqty);
		   pst.setFloat(4, cowprice);
		   pst.setFloat(5, bfqty);
		   pst.setFloat(6, bfprice);
		   pst.setDate(7, swdos);
		   pst.setString(8, path);
		 //  pst.executeUpdate();
		   
		   File file=new File(path);
		   ;
		try {
			image = new Image(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   imgchange.setImage(image);
		   pst.executeUpdate();
		   showinformation("data has being updated successfully");
		   
		   
		   
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}      
   
   
    	

    }
     void fillcombo()
     {
    	 ArrayList<String>arraycname=new ArrayList<>();
    try
    {
    	
		PreparedStatement pst=con.prepareStatement("select distinct cname from customerentry ");
	           ResultSet  table  =	pst.executeQuery();
	           while(table.next())
	           {
	        	   String ss =table.getString("cname");
	        	   arraycname.add(ss);
	        	   
	           }
	           cmbname.getItems().addAll(arraycname);
	      
		
	} 
    catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	
    	
     }
     void showmsg(String ss)
    		 {
    			 Alert a= new Alert(AlertType.INFORMATION);
    		     a.setTitle("SAVED");
    		     a.setContentText(" Data has being saved successfuly!!!");
    		     a.show();
    		 }
     void showconfirmation(String sp )
     {
    	 
    		Alert a2=new Alert(AlertType.CONFIRMATION);
            a2.setTitle("confirm");
            a2.setContentText("are u sure u want to delete ");
            Optional<ButtonType>result=a2.showAndWait();
            if(result.get()==ButtonType.OK)
            {
            //int count=	Integer.parseInt(sp);
           // showdetails("String ");
//            txtmobile.setText("");
//         	txtaddress.setText("");
//         	txtcqty.setText("");
//         	txtcprice.setText("");
//         	txtbqty.setText("");
//         	txtbprice.setText("");
//         	dtpdos.getEditor().setText("");
         	System.out.println("bbb");
         	
         	showdetails();
         	System.out.println("aaaa");
         	
            }
            /*else
            {
          	  showinformation("customer details not found ");
            }*/
            
           
     }
     void showdetails()
     { 
    	 
    	 Alert ap=new Alert(AlertType.INFORMATION);
    	 ap.setTitle("information");
    	 ap.setContentText("Customer details removed !!" );
    	 ap.show();
//    	 txtmobile.setText("");
//     	txtaddress.setText("");
//     	txtcqty.setText("");
//     	txtcprice.setText("");
//     	txtbqty.setText("");
//     	txtbprice.setText("");
//     	dtpdos.getEditor().setText("");
//     	

    	 ap.show();
    	 
     }
     void showinformation(String  details)
     { 
    	 Alert ap = new Alert(AlertType.INFORMATION);
    	 ap.setTitle("imformation");
    	 ap.setContentText(details);
    	 ap.show();
          
    	 
    	 
     }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
        con =DBConnection.doConnect();
        fillcombo();
        
    }
}
