package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;

import javafx.scene.paint.Color;
import java.io.IOException;

import org.loose.fis.sre.exceptions.BadCredentials;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class RecenzieController {
    @FXML
    private Text title_text;
    @FXML
    private TextField nota;
    @FXML
    public void initialize() {
    }
    @FXML
    public void submitRecenzie(){
    }


    @FXML
    private void gotoIstoric() throws IOException {
        if(UserService.get_logged_in().getRole().equals("Muncitor"))
            Main.switchScene("istoric_muncitor.fxml", "Istoric Muncitor");
        else
            Main.switchScene("istoric_client.fxml", "Istoric Client");
    }
}
