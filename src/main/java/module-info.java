module id.muhammadfaisal.parkeecashier {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires MaterialFX;
    requires retrofit2;
    requires rxjava;
    requires retrofit2.converter.gson;
    requires adapter.rxjava;
    requires java.sql;
    requires kotlinx.coroutines.javafx;
    requires org.pdfsam.rxjavafx;
    requires retrofit2.adapter.rxjava3;
    requires gson;

    opens id.muhammadfaisal.parkeecashier to javafx.fxml;
    exports id.muhammadfaisal.parkeecashier;
    exports id.muhammadfaisal.parkeecashier.pages;
    exports id.muhammadfaisal.parkeecashier.api.request;
    exports id.muhammadfaisal.parkeecashier.api.response;
    exports id.muhammadfaisal.parkeecashier.enumeration;
}