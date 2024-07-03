package id.muhammadfaisal.parkeecashier.pages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTransactionPageController implements Initializable {

    @FXML
    public AnchorPane anchorRoot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("createTransactionPage() called.");
        anchorRoot.setMaxWidth(Double.MAX_VALUE);
    }
}
