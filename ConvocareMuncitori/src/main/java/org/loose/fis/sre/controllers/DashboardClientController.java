package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class DashboardClientController {
    @FXML
    private Text profileText;
    public void initialize() {
        profileText.setText("Buna ziua, " + UserService.get_logged_in().getName()+"!");
    }
    @FXML
    private void gotoCautare() throws IOException {
        Main.switchScene("cautare_muncitor.fxml", "Cautare");
    }
    @FXML
    private void gotoIstoric() throws IOException {
        Main.switchScene("istoric_client.fxml", "Istoric lucrari");
    }


}