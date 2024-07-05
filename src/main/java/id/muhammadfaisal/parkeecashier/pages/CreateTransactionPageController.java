package id.muhammadfaisal.parkeecashier.pages;

import com.google.gson.internal.LinkedTreeMap;
import id.muhammadfaisal.parkeecashier.api.response.CheckInResponse;
import id.muhammadfaisal.parkeecashier.enumeration.VehicleType;
import id.muhammadfaisal.parkeecashier.helper.ApiHelper;
import id.muhammadfaisal.parkeecashier.helper.DataHelper;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.pdfsam.rxjavafx.schedulers.JavaFxScheduler;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateTransactionPageController<T> implements Initializable {

    @FXML
    public AnchorPane anchorRoot;

    @FXML
    public MFXProgressBar mfxProgressBar;

    @FXML
    public MFXButton btnSubmit;

    @FXML
    public ComboBox<VehicleType> comboBox;

    @FXML
    public TextField inputLicense;

    @FXML
    public Label labelInfo;

    private ApiHelper apiHelper;

    private DataHelper<CheckInResponse> dataHelper;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("createTransactionPage() called.");
        this.apiHelper = new ApiHelper();
        this.dataHelper = new DataHelper<>();
        this.mfxProgressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        this.comboBox.setItems(FXCollections.observableArrayList(VehicleType.values()));
        this.btnSubmit.setOnAction(_ -> processSubmit());
    }

    private void processSubmit() {
        String license = this.inputLicense.getText().toUpperCase(Locale.ROOT).trim();

        if (license.isBlank() || this.comboBox.getSelectionModel().getSelectedItem() == null) {
            this.labelInfo.setText("Pastikan anda mengisi data dengan benar");
            return;
        }
        int vehicle = this.comboBox.getSelectionModel().getSelectedItem().getCode();

        this.mfxProgressBar.setVisible(true);
        this.apiHelper
                .newTransaction(license, vehicle)
                .map(data -> data)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(
                        data -> {
                            if (data.getCode() == 200) {
                                this.successCheckIn(this.dataHelper.convertLinkedTreeMap((LinkedTreeMap<?, ?>) data.getData(), CheckInResponse.class));
                                return;
                            }
                            System.out.println(data.getMessage());
                            this.labelInfo.setText(data.getMessage());
                        },
                        throwable -> {
                            // handle error here, e.g., log the error or show an error message
                            System.out.println("Error: " + throwable.getMessage());
                            this.labelInfo.setText("ERROR: \n" + throwable.getMessage());
                        },
                        () -> {
                            this.mfxProgressBar.setVisible(false);
                            this.inputLicense.setText("");
                            this.comboBox.getSelectionModel().clearSelection();
                        }
                );
    }

    private void successCheckIn(CheckInResponse response) {
        System.out.println("SUCCESS");
        this.labelInfo.setText("SUCCESS CHECKIN\n" + response.getParkingSlip());
    }
}
