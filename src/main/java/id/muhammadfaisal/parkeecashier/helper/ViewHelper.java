package id.muhammadfaisal.parkeecashier.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class ViewHelper {
    public static Parent replaceContent(URL fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fxml);
        return loader.load();
    }
}
