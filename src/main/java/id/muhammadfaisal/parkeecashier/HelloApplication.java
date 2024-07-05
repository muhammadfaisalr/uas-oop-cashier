package id.muhammadfaisal.parkeecashier;

import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
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
        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();
        String css = (Objects.requireNonNull(getClass().getResource("style/styles.css")).toExternalForm());
        Font.loadFont(Objects.requireNonNull(getClass().getResource("fonts/PlusJakartaSansSemiBold.ttf")).toExternalForm(), 10);
        stage.setTitle("SOLID Parking!");
        stage.setScene(scene);
        stage.getScene().getStylesheets().addFirst(css);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}