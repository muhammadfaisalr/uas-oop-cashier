package id.muhammadfaisal.parkeecashier.pages;

import com.google.gson.internal.LinkedTreeMap;
import id.muhammadfaisal.parkeecashier.api.response.CheckInResponse;
import id.muhammadfaisal.parkeecashier.enumeration.PaymentMethod;
import id.muhammadfaisal.parkeecashier.helper.ApiHelper;
import id.muhammadfaisal.parkeecashier.helper.DataHelper;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.pdfsam.rxjavafx.schedulers.JavaFxScheduler;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class CheckoutPageController implements Initializable {

    @FXML
    public TextField inputLicense;

    @FXML
    public MFXProgressBar mfxProgressBar;

    @FXML
    public MFXButton btnSubmit;

    @FXML
    public Label labelInfo;

    @FXML
    public VBox vBoxTransaction;

    @FXML
    public ComboBox<PaymentMethod> comboBox;

    @FXML
    public MFXButton btnPayment;

    private ApiHelper apiHelper;

    private DataHelper<CheckInResponse> dataHelper;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("checkoutPage() called.");
        this.apiHelper = new ApiHelper();
        this.dataHelper = new DataHelper<>();
        this.comboBox.setItems(FXCollections.observableArrayList(PaymentMethod.values()));
        this.mfxProgressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        this.btnSubmit.setOnAction(_ -> processSubmit());
    }

    private void processSubmit() {
        String license = this.inputLicense.getText().toUpperCase(Locale.ROOT).trim();

        if (license.isBlank()) {
            this.labelInfo.setText("Pastikan anda mengisi data dengan benar");
            return;
        }
        this.mfxProgressBar.setVisible(true);
        this.apiHelper
                .inquiryTransaction(license)
                .map(data -> data)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(
                        data -> {
                            if (data.getCode() == 200) {
                                this.successInquiry(this.dataHelper.convertLinkedTreeMap((LinkedTreeMap<?, ?>) data.getData(), CheckInResponse.class));
                                return;
                            }
                            System.out.println(data.getMessage());
                            this.labelInfo.setText(data.getMessage());
                            this.vBoxTransaction.setVisible(false);
                        },
                        throwable -> {
                            // handle error here, e.g., log the error or show an error message
                            System.out.println("Error: " + throwable.getMessage());
                            this.labelInfo.setText("ERROR: \n" + throwable.getMessage());
                            this.vBoxTransaction.setVisible(false);
                            this.mfxProgressBar.setVisible(false);
                        },
                        () -> {
                            this.mfxProgressBar.setVisible(false);
                            this.inputLicense.setText("");
                        }
                );
    }

    private void successInquiry(CheckInResponse checkInResponse) {
        System.out.println("Success: " + checkInResponse.getCheckInTime());
        this.labelInfo.setText("Data Ditemukan " + checkInResponse.getVehicleNumber() + " [Rp" + checkInResponse.getTotalAmount() + "]");
        this.vBoxTransaction.setVisible(true);
        this.btnPayment.setOnAction(_ -> processPayment(checkInResponse));
    }

    private void processPayment(CheckInResponse checkInResponse) {
        if (comboBox.getSelectionModel().getSelectedItem() == null) {

            String labelBefore = labelInfo.getText();

            labelInfo.setText("Belum Memilih Pembayaran");
            btnPayment.setDisable(true);

            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
            pauseTransition.setOnFinished(_ -> {
                labelInfo.setText(labelBefore);
                btnPayment.setDisable(false);
            });
            pauseTransition.play();
            return;
        }

        this.btnPayment.setDisable(true);
        this.btnSubmit.setDisable(true);
        this.comboBox.setDisable(true);

        int paymentMethod = comboBox.getSelectionModel().getSelectedItem().getCode();

        this.mfxProgressBar.setVisible(true);
        this.apiHelper
                .paymentTransaction(checkInResponse.getVehicleNumber(), paymentMethod)
                .map(data -> data)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(
                        data -> {
                            if (data.getCode() == 200) {
                                this.labelInfo.setText("Pembayaran Berhasil, Terima Kasih! Hati Hati Di Jalan.");
                                this.vBoxTransaction.setVisible(false);
                                return;
                            }
                            System.out.println(data.getMessage());
                            this.labelInfo.setText(data.getMessage());
                            this.vBoxTransaction.setVisible(false);
                        },
                        throwable -> {
                            // handle error here, e.g., log the error or show an error message
                            System.out.println("Error: " + throwable.getMessage());
                            this.labelInfo.setText("ERROR: \n" + throwable.getMessage());
                            this.vBoxTransaction.setVisible(false);
                            this.mfxProgressBar.setVisible(false);
                        },
                        () -> {
                            this.mfxProgressBar.setVisible(false);
                            this.inputLicense.setText("");
                            this.btnSubmit.setDisable(false);
                            this.btnPayment.setDisable(false);
                            this.comboBox.setDisable(false);
                        }
                );
    }
}
