package id.muhammadfaisal.parkeecashier.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SideNavigationBarController {
    @FXML
    public VBox sideNavigationBar;

    @FXML
    public Button homeButton;

    @FXML
    public Button aboutButton;

    @FXML
    public Button contactButton;

    @FXML
    public void handleHomeButtonAction() {
        System.out.println("Home button clicked");
    }

    @FXML
    public void handleAboutButtonAction() {
        System.out.println("About button clicked");
    }

    @FXML
    public void handleContactButtonAction() {
        System.out.println("Contact button clicked");
    }
}
