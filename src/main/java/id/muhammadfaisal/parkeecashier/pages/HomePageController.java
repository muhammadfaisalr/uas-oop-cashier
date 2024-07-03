package id.muhammadfaisal.parkeecashier.pages;

import id.muhammadfaisal.parkeecashier.helper.ViewHelper;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    public BorderPane borderRoot;

    @FXML
    public ImageView imageProfile;

    @FXML
    public HBox hBoxTransaction;

    @FXML
    public HBox hBoxCheckout;

    @FXML
    public HBox hBoxHistory;

    @FXML
    public Label labelTransaction;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.hBoxTransaction.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @SneakyThrows
            @Override
            public void handle(MouseEvent mouseEvent) {
                createRipple(mouseEvent, hBoxTransaction);
                createTransactionPage();
            }
        });

        this.hBoxCheckout.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @SneakyThrows
            @Override
            public void handle(MouseEvent mouseEvent) {
                createRipple(mouseEvent, hBoxCheckout);
                createCheckoutPage();
            }
        });

        this.hBoxHistory.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @SneakyThrows
            @Override
            public void handle(MouseEvent mouseEvent) {
                createRipple(mouseEvent, hBoxHistory);
                createCheckoutPage();
            }
        });

        this.createTransactionPage();

    }

    private void createTransactionPage() throws IOException {
        Parent parent = ViewHelper.replaceContent(getClass().getResource("create-transaction-page.fxml"));
        this.borderRoot.setCenter(parent);

    }

    private void createCheckoutPage() throws IOException {
        Parent parent = ViewHelper.replaceContent(getClass().getResource("checkout-page.fxml"));
        this.borderRoot.setCenter(parent);
    }

    private void createRipple(MouseEvent event, Pane pane) {
        Circle ripple = new Circle(event.getSceneX(), event.getSceneY(), 0, Color.web("#2196F3"));
        ripple.setOpacity(0.4);
        pane.getChildren().add(ripple);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(ripple.radiusProperty(), 0)),
                new KeyFrame(Duration.seconds(0.1), new KeyValue(ripple.radiusProperty(), 20)),
                new KeyFrame(Duration.ZERO, new KeyValue(ripple.opacityProperty(), 0.4)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(ripple.opacityProperty(), 0))
        );

        timeline.setOnFinished(e -> pane.getChildren().remove(ripple));
        timeline.play();
        this.borderRoot.prefWidthProperty().bind(borderRoot.getScene().widthProperty());
    }

}
