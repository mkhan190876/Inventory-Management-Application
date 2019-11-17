package MKhanC482;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class DialogBoxes {
    
    public static boolean confirmAction(String buttonPressed) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm " + buttonPressed);
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to " + buttonPressed.toLowerCase() + "?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> result = alert.showAndWait();
         
        return (result.get() == yesButton);
    }
    
    public static void warnAboutPartRequirement() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Products must have associated parts");
        alert.setHeaderText(null);
        alert.setContentText("Products must have at least 1 associated part.\nPlease add a part and try again.");
        alert.showAndWait();
    }
    
    public static void warnAboutNoSelection(String partOrProduct) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No " + partOrProduct + " Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a " + partOrProduct.toLowerCase() + " to modify first");
            alert.showAndWait();
    }
}