package MKhanC482;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddPartController implements Initializable {
    @FXML private RadioButton InHouseRadioButton;
    @FXML private RadioButton OutsourcedRadioButton;
    private ToggleGroup SupplierInfoToggleGroup;
    
    @FXML private TextField IDTextField;
    @FXML private TextField NameTextField;
    @FXML private TextField InventoryLevelTextField;
    @FXML private TextField PriceTextField;
    @FXML private TextField MaxTextField;
    @FXML private TextField MinTextField;
    @FXML private Label CompanyNameorMachineIDLabel;
    @FXML private TextField CompanyNameorMachineIDField;
    
    public void SaveButtonClicked(ActionEvent e) throws IOException {
        
        if (this.SupplierInfoToggleGroup.getSelectedToggle().equals(this.InHouseRadioButton)) {
            
            Part newPart = new InHouse(Integer.parseInt(IDTextField.getText()), "", 0.0, 0,0,0,0);
            
            if (!NameTextField.getText().isEmpty()) {
                newPart.setName(NameTextField.getText());  
            }
            if (!PriceTextField.getText().isEmpty()) {
                newPart.setPrice(Double.parseDouble(PriceTextField.getText())); 
            }
            if (!InventoryLevelTextField.getText().isEmpty()) {
                newPart.setStock(Integer.parseInt(InventoryLevelTextField.getText()));
            }
            if (!MinTextField.getText().isEmpty()) {
                newPart.setMin(Integer.parseInt(MinTextField.getText()));
            }
            if (!MaxTextField.getText().isEmpty()) {
                newPart.setMax(Integer.parseInt(MaxTextField.getText()));
            }
            if (!CompanyNameorMachineIDField.getText().isEmpty()) {
                ((InHouse)newPart).setMachineId(Integer.parseInt(CompanyNameorMachineIDField.getText()));
            }
            Inventory.addPart(newPart);
        }
        
        if (this.SupplierInfoToggleGroup.getSelectedToggle().equals(this.OutsourcedRadioButton)) {
            
            Part newPart = new Outsourced(Integer.parseInt(IDTextField.getText()), "", 0.0,0,0,0,"");
            
            if (!NameTextField.getText().isEmpty()) {
                newPart.setName(NameTextField.getText());  
            }
            if (!PriceTextField.getText().isEmpty()) {
                newPart.setPrice(Double.parseDouble(PriceTextField.getText())); 
            }
            if (!InventoryLevelTextField.getText().isEmpty()) {
                newPart.setStock(Integer.parseInt(InventoryLevelTextField.getText()));
            }
            if (!MinTextField.getText().isEmpty()) {
                newPart.setMin(Integer.parseInt(MinTextField.getText()));
            }
            if (!MaxTextField.getText().isEmpty()) {
                newPart.setMax(Integer.parseInt(MaxTextField.getText()));
            }
            if (!CompanyNameorMachineIDField.getText().isEmpty()) {
                ((Outsourced)newPart).setCompanyName(CompanyNameorMachineIDField.getText());
            }            
            Inventory.addPart(newPart);
        }    
        ReturnToMainScreen(e);
    }
    
    public void CancelButtonClicked(ActionEvent e) throws IOException {
        if (DialogBoxes.confirmAction("Cancel")) {
            ReturnToMainScreen(e);
        }
    }
    
    public void radioButtonChanged() {
        
        if (this.SupplierInfoToggleGroup.getSelectedToggle().equals(this.InHouseRadioButton)) {
            CompanyNameorMachineIDLabel.setText("Machine ID");
            CompanyNameorMachineIDField.setPromptText("Mach ID");
        }
            
        if (this.SupplierInfoToggleGroup.getSelectedToggle().equals(this.OutsourcedRadioButton)) {
            CompanyNameorMachineIDLabel.setText("Company Name");
            CompanyNameorMachineIDField.setPromptText("Comp Nm");
        }
    }
    
    public void ReturnToMainScreen(ActionEvent e) throws IOException {
        
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);
        
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(mainScreenScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SupplierInfoToggleGroup = new ToggleGroup();
        this.InHouseRadioButton.setToggleGroup(SupplierInfoToggleGroup);
        this.OutsourcedRadioButton.setToggleGroup(SupplierInfoToggleGroup);
        this.IDTextField.setText(String.valueOf(Inventory.getAllParts().size()+1));
    }    
}