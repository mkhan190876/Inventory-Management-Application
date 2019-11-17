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

public class MainScreenController implements Initializable {
    
    @FXML private TextField PartSearchField;
    @FXML private TableView<Part> PartTable;
    @FXML private TableColumn<Part, Integer> PartIdColumn;
    @FXML private TableColumn<Part, String> PartNameColumn;
    @FXML private TableColumn<Part, Integer> PartInventoryColumn;
    @FXML private TableColumn<Part, Double> PartPriceColumn;
   
    @FXML private TextField ProductSearchField;
    @FXML private TableView<Product> ProductTable;
    @FXML private TableColumn<Product, Integer> ProductIdColumn;
    @FXML private TableColumn<Product, String> ProductNameColumn;
    @FXML private TableColumn<Product, Integer> ProductInventoryColumn;
    @FXML private TableColumn<Product, Double> ProductPriceColumn;
    
    public void addPartButtonPushed (ActionEvent e) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(addPartScene);
        window.show();
    }
    
    public void modifyPartButtonPushed (ActionEvent e) throws IOException {
        
        if (PartTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyPart.fxml"));
            Parent modifyPartParent = loader.load();
            Scene modifyPartScene = new Scene(modifyPartParent);
        
            ModifyPartController controller = loader.getController();
            controller.partToModify(PartTable.getSelectionModel().getSelectedItem());
        
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            window.setScene(modifyPartScene);
            window.show();
        }
        
        else {
            DialogBoxes.warnAboutNoSelection("Part");
        }
    }
    
    public void deletePartButtonPushed (ActionEvent e) {
        
        Part selectedPart = PartTable.getSelectionModel().getSelectedItem();
        
        if (selectedPart != null) {
            if (DialogBoxes.confirmAction("Delete")) {
                Inventory.deletePart(selectedPart);
            }
        }
    }
    
    public void searchPartButtonPushed () {
        
       if (!PartSearchField.getText().trim().isEmpty()) {
           PartTable.setItems(Inventory.lookupPart(PartSearchField.getText().trim()));
       }
       
       else {
           PartTable.setItems(Inventory.getAllParts());
       }    
    }
    
    public void addProductButtonPushed (ActionEvent e) throws IOException {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);
        
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(addProductScene);
        window.show();
    }
    
    public void modifyProductButtonPushed (ActionEvent e) throws IOException {
        
        if (ProductTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
            Parent modifyProductParent = loader.load();
        
            Scene modifyProductScene = new Scene(modifyProductParent);
        
            ModifyProductController controller = loader.getController();
            controller.productToModify(ProductTable.getSelectionModel().getSelectedItem());
        
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            window.setScene(modifyProductScene);
            window.show();
        }
        
        else {
            DialogBoxes.warnAboutNoSelection("Product");
        } 
    }
    
    public void deleteProductButtonPushed (ActionEvent e) {
        
        Product selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
        
        if (selectedProduct != null){
            if (DialogBoxes.confirmAction("Delete")) {
                Inventory.deleteProduct(selectedProduct);
            }
        }   
    }
    
    public void searchProductButtonPushed () {
        
       if (!ProductSearchField.getText().trim().isEmpty()) {
           ProductTable.setItems(Inventory.lookupProduct(ProductSearchField.getText().trim()));
       }
       
       else {
           ProductTable.setItems(Inventory.getAllProducts());
       }    
    }
    
    public void exitButtonPushed(ActionEvent e) {
        if (DialogBoxes.confirmAction("Exit")) {
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            stage.close();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        PartTable.setItems(Inventory.getAllParts());
        PartTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ProductIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductTable.setItems(Inventory.getAllProducts());
        ProductTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }    
}