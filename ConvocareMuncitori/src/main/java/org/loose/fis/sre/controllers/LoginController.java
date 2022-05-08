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

import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class LoginController {
    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    public void initialize() {

    }
    @FXML
    public void checkCredentials(){

    }

    @FXML
    private void gotoRegister() throws IOException {
        Main.switchScene("register.fxml", "Register");
    }
}
