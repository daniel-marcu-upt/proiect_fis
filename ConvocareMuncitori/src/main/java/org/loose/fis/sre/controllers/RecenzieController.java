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
import org.loose.fis.sre.services.RecenzieService;
import org.loose.fis.sre.services.UserService;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class RecenzieController {
    @FXML
    private Text title_text;
    @FXML
    private Text err;
    @FXML
    private TextField nota;
    @FXML
    public void initialize() {
        User u = UserService.findUser(IstoricController.getSelectat().getClient());
        title_text.setText("Acordare recenzie pentru " + u.getName());
    }
    @FXML
    public void submitRecenzie(){
        try {
            User u = UserService.findUser(IstoricController.getSelectat().getClient());
            Integer n = Integer.parseInt(nota.getText());
            RecenzieService.addRecenzie(IstoricController.getSelectat().getId(), n, u.getEmail());
            err.setFill(Color.GREEN);
            err.setText("Recenzie acordata cu success!");
        }catch (NumberFormatException e){
            err.setFill(Color.RED);
            err.setText("Nu ati introdus o nota valida!");
        }
    }


    @FXML
    private void gotoIstoric() throws IOException {
        if(UserService.get_logged_in().getRole().equals("Muncitor"))
            Main.switchScene("istoric_muncitor.fxml", "Istoric Muncitor");
        else
            Main.switchScene("istoric_client.fxml", "Istoric Client");
    }
}
