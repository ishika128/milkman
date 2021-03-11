/**
 * Sample Skeleton for 'DashboardView.fxml' Controller Class
 */

package Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DashboardViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void opencustomerentry(MouseEvent event) 
    { 
    	/*System.out.println("hello");
    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("combos/comboView.fxml")); 
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
*/
    	try {
			Parent root =FXMLLoader.load(getClass().getClassLoader().getResource("CustomerEntry/CustomerEntryView.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage ();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void dobillingconsole(MouseEvent event) {

    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billingconsole/billingconsoleView.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
					stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doincomerecord(MouseEvent event) 
    {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("incomerecords/incomerecordsView.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
					stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

    @FXML
    void dopaymentcollection(MouseEvent event) {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("paymentcollection/paymentcollectionView.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
					stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

    @FXML
    void doroutineentry(MouseEvent event) {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("routineentry/routineentryView.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
					stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void dovariationlog(MouseEvent event) {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Variationslog/VariationslogView.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
					stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void openbilling(MouseEvent event) {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billing/billingView.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
					stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

  

    @FXML
    void openroutineentry(MouseEvent event) {
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("routineentry/routineentryView.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
					stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
