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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddProductController implements Initializable {
    
    @FXML private TextField IDTextField;
    @FXML private TextField NameTextField;
    @FXML private TextField InventoryTextField;
    @FXML private TextField PriceTextField;
    @FXML private TextField MaxTextField;
    @FXML private TextField MinTextField;
    @FXML private TextField SearchTextField;
    
    @FXML private TableView<Part> PartLookupTable;
    @FXML private TableColumn<Part, Integer> PartLookupIdColumn;
    @FXML private TableColumn<Part, String> PartLookupNameColumn;
    @FXML private TableColumn<Part, Integer> PartLookupInventoryColumn;
    @FXML private TableColumn<Part, Double> PartLookupPriceColumn;
   
    
    @FXML private TableView<Part> ProductPartTable;
    @FXML private TableColumn<Part, Integer> ProductPartIdColumn;
    @FXML private TableColumn<Part, String> ProductPartNameColumn;
    @FXML private TableColumn<Part, Integer> ProductPartInventoryColumn;
    @FXML private TableColumn<Part, Double> ProductPartPriceColumn;
    
    Product newProduct = new Product(0, "", 0.0, 0, 0, 0);
    
    public void SaveButtonClicked(ActionEvent e) throws IOException {
                
        if (ProductPartTable.getItems().isEmpty()) {
            DialogBoxes.warnAboutPartRequirement();
        }
        
        else {
            newProduct.setId(Integer.parseInt(IDTextField.getText()));
            if (!NameTextField.getText().isEmpty()) {
                newProduct.setName(NameTextField.getText());  
            }
            if (!PriceTextField.getText().isEmpty()) {
                newProduct.setPrice(Double.parseDouble(PriceTextField.getText()));  
            }
            if (!InventoryTextField.getText().isEmpty()) {
                newProduct.setStock(Integer.parseInt(InventoryTextField.getText()));
            }
            if (!MinTextField.getText().isEmpty()) {
                newProduct.setMin(Integer.parseInt(MinTextField.getText()));
            }  
            if (!MaxTextField.getText().isEmpty()) {
                newProduct.setMax(Integer.parseInt(MaxTextField.getText()));
            }  
             
            Inventory.addProduct(newProduct);
            ReturnToMainScreen(e);     
        }
    }
    
    public void CancelButtonClicked(ActionEvent e) throws IOException {
        if (DialogBoxes.confirmAction("Cancel")) {
            ReturnToMainScreen(e);
        }
    }
    
    public void AddButtonClicked() {
        
        Part selectedPart = PartLookupTable.getSelectionModel().getSelectedItem();
        
        if (selectedPart != null) {
            newProduct.addAssociatedPart(selectedPart);
            ProductPartTable.setItems(newProduct.getAllAssociatedParts());
        }
        
    }
    
    public void DeleteButtonClicked() {
        Part selectedPart = ProductPartTable.getSelectionModel().getSelectedItem();
        
        if (selectedPart != null) {
            if (DialogBoxes.confirmAction("Delete")) {
                newProduct.deleteAssociatedPart(selectedPart);
            }
        }
    }
    
    public void SearchButtonClicked() {
        
        if (!SearchTextField.getText().trim().isEmpty()) {
           PartLookupTable.setItems(Inventory.lookupPart(SearchTextField.getText().trim()));
       }
       
       else {
           PartLookupTable.setItems(Inventory.getAllParts());
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
        this.IDTextField.setText(String.valueOf(Inventory.getAllProducts().size()+1));
        
        PartLookupIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartLookupNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartLookupInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartLookupPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        PartLookupTable.setItems(Inventory.getAllParts());
        PartLookupTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ProductPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductPartTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }    
}