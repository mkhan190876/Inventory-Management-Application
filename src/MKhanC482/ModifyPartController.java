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

public class ModifyPartController implements Initializable {
    
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
    
    private Part selectedPart;

    public void SaveButtonClicked(ActionEvent e) throws IOException {
        
         if (this.SupplierInfoToggleGroup.getSelectedToggle().equals(this.InHouseRadioButton)) {
            Part newPart = new InHouse(
                    Integer.parseInt(IDTextField.getText()), 
                    NameTextField.getText(), 
                    Double.parseDouble(PriceTextField.getText()), 
                    Integer.parseInt(InventoryLevelTextField.getText()), 
                    Integer.parseInt(MinTextField.getText()), 
                    Integer.parseInt(MaxTextField.getText()), 
                    Integer.parseInt(CompanyNameorMachineIDField.getText())
            );
            Inventory.updatePart(Integer.parseInt(IDTextField.getText()), newPart);
        }
         
        if (this.SupplierInfoToggleGroup.getSelectedToggle().equals(this.OutsourcedRadioButton)) {
            Part newPart = new Outsourced(
                    Integer.parseInt(IDTextField.getText()), 
                    NameTextField.getText(), 
                    Double.parseDouble(PriceTextField.getText()), 
                    Integer.parseInt(InventoryLevelTextField.getText()), 
                    Integer.parseInt(MinTextField.getText()), 
                    Integer.parseInt(MaxTextField.getText()), 
                    CompanyNameorMachineIDField.getText()
            );
            Inventory.updatePart(Integer.parseInt(IDTextField.getText()), newPart);
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
    
    public void partToModify(Part part){
        
        selectedPart = part;
        IDTextField.setText(String.valueOf(selectedPart.getId()));
        NameTextField.setText(selectedPart.getName());
        InventoryLevelTextField.setText(String.valueOf(selectedPart.getStock()));
        PriceTextField.setText(String.valueOf(selectedPart.getPrice()));
        MaxTextField.setText(String.valueOf(selectedPart.getMax()));
        MinTextField.setText(String.valueOf(selectedPart.getMin()));
        
        if (selectedPart instanceof InHouse){        
            InHouseRadioButton.setSelected(true);
            CompanyNameorMachineIDLabel.setText("Machine ID");
            CompanyNameorMachineIDField.setText(String.valueOf(((InHouse)selectedPart).getMachineId()));
        }
        
        else if (selectedPart instanceof Outsourced){
            OutsourcedRadioButton.setSelected(true);
            CompanyNameorMachineIDLabel.setText("Company Name");
            CompanyNameorMachineIDField.setText(((Outsourced)selectedPart).getCompanyName());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SupplierInfoToggleGroup = new ToggleGroup();
        this.InHouseRadioButton.setToggleGroup(SupplierInfoToggleGroup);
        this.OutsourcedRadioButton.setToggleGroup(SupplierInfoToggleGroup);
    }    
}