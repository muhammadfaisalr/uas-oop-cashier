module id.muhammadfaisal.parkeecashier {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;

    opens id.muhammadfaisal.parkeecashier to javafx.fxml;
    exports id.muhammadfaisal.parkeecashier;
    exports id.muhammadfaisal.parkeecashier.pages;
}