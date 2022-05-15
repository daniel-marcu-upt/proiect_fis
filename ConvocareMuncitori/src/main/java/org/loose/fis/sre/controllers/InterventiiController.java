package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.loose.fis.sre.Main;

import javafx.scene.paint.Color;
import java.io.IOException;


public class InterventiiController {

    @FXML
    private ListView listView;

    @FXML
    public void initialize() {

    }
    @FXML
    private void gotoDashboard() throws IOException {
        Main.switchScene("dashboard_muncitor.fxml", "Dashboard Muncitor");
    }
}
