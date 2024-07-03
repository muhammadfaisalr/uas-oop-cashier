package id.muhammadfaisal.parkeecashier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pages/home-page.fxml"));
        stage.setMaximized(true);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        String css = (Objects.requireNonNull(getClass().getResource("style/styles.css")).toExternalForm());
        Font.loadFont(Objects.requireNonNull(getClass().getResource("fonts/PlusJakartaSansSemiBold.ttf")).toExternalForm(), 10);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.getScene().getStylesheets().addFirst(css);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}