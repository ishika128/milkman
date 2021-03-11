package milkmansignup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class milkmansignupViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtuser;

    @FXML
    private TextField txtpassword;

    @FXML
    private Button btnlogin;

    @FXML
    void dologin(ActionEvent event) {
    	String user=txtuser.getText();
        String password =txtpassword.getText();
        if(user.equals("user")& password.equals("123"))
        {
        	
        	try {
    			Parent root =FXMLLoader.load(getClass().getClassLoader().getResource("Dashboard/DashboardView.fxml"));
    			Scene scene = new Scene(root);
    			Stage stage = new Stage ();
    			stage.setScene(scene);
    			stage.show();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	showmsg("successfully login ");
        	
        }
        else
        {
        	showmsg("either username or paswword is incorrect ");
        }

    }
    
    void showmsg(String ss)
    {
    	Alert a = new Alert(AlertType.INFORMATION);
    	a.setTitle("information");
    	a.setContentText(ss);
    	a.show();
    }


    @FXML
    void initialize() {
        assert txtuser != null : "fx:id=\"txtuser\" was not injected: check your FXML file 'milkmansignupView.fxml'.";
        assert txtpassword != null : "fx:id=\"txtpassword\" was not injected: check your FXML file 'milkmansignupView.fxml'.";
        assert btnlogin != null : "fx:id=\"btnlogin\" was not injected: check your FXML file 'milkmansignupView.fxml'.";

    }
}
