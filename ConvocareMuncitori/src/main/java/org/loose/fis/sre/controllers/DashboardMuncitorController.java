package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class DashboardMuncitorController {
    @FXML
    private Text profileText;

    public void initialize() {
        profileText.setText("Buna ziua, "+ UserService.get_logged_in().getName()+"!");
    }
    @FXML
    private void gotoInterventii() throws IOException {
        Main.switchScene("interventii.fxml", "Intervenii");
    }
    @FXML
    private void gotoIstoric() throws IOException {
        Main.switchScene("istoric_muncitor.fxml", "Istoric interventii");
    }
    @FXML
    private void gotoProfil() throws IOException {
        Main.switchScene("profil.fxml", "Vizualizare profil");
    }
}
