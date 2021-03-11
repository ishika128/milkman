/**
 * Sample Skeleton for 'billingconsoleView.fxml' Controller Class
 */

package billingconsole;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import billing.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import tablejdbc.StudentBean;

public class billingconsoleViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;


    @FXML
    private ToggleGroup grp;
    
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbnames"
    private ComboBox<String> cmbnames; // Value injected by FXMLLoader

    @FXML // fx:id="radpaid"
    private RadioButton radpaid; // Value injected by FXMLLoader

    @FXML // fx:id="radunpaid"
    private RadioButton radunpaid; // Value injected by FXMLLoader

    @FXML // fx:id="tbl"
    private TableView<billingbean> tbl; // Value injected by FXMLLoader
    
    Connection con = null ;
    String name ;

    @FXML
    void completehistory(ActionEvent event) {
    	try {
			PreparedStatement pst =con.prepareStatement("select name, dos,doe,cowqty,bfqty,amount from bills");
			getdata(pst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	tbl.setItems(lll);
    

    }

    @FXML
    void doshow(ActionEvent event) {
    	if(radpaid.isSelected())
    	{
    		try {
					PreparedStatement pst =con.prepareStatement("select name, dos,doe,cowqty,bfqty,amount from bills where status = 1");
						getdata(pst);
						tbl.setItems(lll);
		       }
					  
        catch (SQLException e) 
    		{
	
                	e.printStackTrace();
            }
    	}
  if(radunpaid.isSelected())
  {
	  try {
			PreparedStatement pst =con.prepareStatement("select name, dos,doe,cowqty,bfqty,amount from bills where status = 0");
			getdata(pst);
			//System.out.println("11");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
  }
  tbl.setItems(lll);
    	}

    
    ObservableList<billingbean>lll ;
    void getdata(PreparedStatement pst )
    {
     try {
    	 lll=FXCollections.observableArrayList();
		ResultSet table=pst.executeQuery();
		while(table.next())
		{
			String n = table .getString("name");
	String dos=table.getString("dos");
	String doe=table.getString("dos");
	Float cowqty=table.getFloat("cowqty");
	Float bfqty=table.getFloat("bfqty");
	Float amount=table.getFloat("amount");
	billingbean obj = new billingbean(n, dos,doe, cowqty,bfqty,amount);
	lll.add(obj);
		}
		
	} 
     catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  	
    }

    @FXML
    void showhistory(ActionEvent event) {
    	System.out.println("1");
    	try {
    		System.out.println("2");
    		 name= cmbnames.getSelectionModel().getSelectedItem();
			PreparedStatement pst =con.prepareStatement("select  dos,doe,cowqty,bfqty,amount from bills where name = ?");
			pst.setString(1, name);
		//ResultSet table =	pst.executeQuery();
			getdataintotable(pst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	tbl.setItems(ll);

    }
    ObservableList<billingbean>ll ;
    void getdataintotable(PreparedStatement pst )
    {
     try {
    	 ll=FXCollections.observableArrayList();
		ResultSet table=pst.executeQuery();
		while(table.next())
		{
			
	String dos=table.getString("dos");
	String doe=table.getString("dos");
	Float cowqty=table.getFloat("cowqty");
	Float bfqty=table.getFloat("bfqty");
	Float amount=table.getFloat("amount");
	billingbean obj = new billingbean(name, dos,doe, cowqty,bfqty,amount);
	ll.add(obj);
		}
		
	} 
     catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  	
    }
    void fillcombo()
    {
    	ArrayList<String>list=new ArrayList<>();
    	try {
			PreparedStatement pst=con.prepareStatement("select distinct name from bills ");
			ResultSet table = pst.executeQuery();
			while(table.next())
			{
				String name = table.getString("name");
				list.add(name);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	cmbnames.getItems().addAll(list);
    	
    }
    void addcolumns()
    {
    	TableColumn<billingbean, String > cname =new TableColumn<billingbean, String >("customer name");//Dikhava Title
        cname.setCellValueFactory(new PropertyValueFactory<>("name"));//bean field name not table col. name
        
        TableColumn<billingbean, String > dos =new TableColumn<billingbean, String >("date of start");//Dikhava Title
        dos.setCellValueFactory(new PropertyValueFactory<>("dos"));//bean field name not table col. name
        
        TableColumn<billingbean, String > doe =new TableColumn<billingbean, String >("date of end ");//Dikhava Title
        doe.setCellValueFactory(new PropertyValueFactory<>("doe"));//bean field name not table col. name
        
        TableColumn<billingbean, Float > cowqty =new TableColumn<billingbean, Float >("cow qty ");//Dikhava Title
        cowqty.setCellValueFactory(new PropertyValueFactory<>("cowqty"));//bean field name not table col. name
        
        TableColumn<billingbean, Float > bfqty =new TableColumn<billingbean, Float >("buffalo qty ");//Dikhava Title
        bfqty.setCellValueFactory(new PropertyValueFactory<>("bfqty"));//bean field name not table col. name
    
        TableColumn<billingbean, Float > amt =new TableColumn<billingbean, Float >("amount ");//Dikhava Title
        amt.setCellValueFactory(new PropertyValueFactory<>("amount"));//bean field name not table col. name
        
        tbl.getColumns().clear();
        tbl.getColumns().addAll(cname,dos,doe,cowqty,bfqty,amt);
 
    }
    @FXML
    void doExcel(ActionEvent event) {
    	Writer writer = null;
        try {
            FileChooser chooser=new FileChooser();
             
            chooser.setTitle("Select Path:");
             
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.*")
                     
                );
             File file=chooser.showSaveDialog(null);
            String filePath=file.getAbsolutePath();
            if(!(filePath.endsWith(".csv")||filePath.endsWith(".CSV")))
            {
                filePath=filePath+".csv";
            }
             file = new File(filePath);
              
              
              
            writer = new BufferedWriter(new FileWriter(file));
            String text="Rollno ,Name,Per,date of birth\n";
            writer.write(text);
            for (billingbean p : ll)
            {
                text = p.getName()+ "," + p.getDos()+ "," + p.getDoe()+ "," + p.getBfqty()+","+p.bfqty+","+p.getAmount()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            
            try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
        }
    }

    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con =DBConnection.doConnect();
    	addcolumns();
    	fillcombo();
        
    }
}
