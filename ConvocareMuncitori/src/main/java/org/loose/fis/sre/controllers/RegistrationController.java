package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private ChoiceBox role;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField descriptionField;

    public static boolean not_testing=true;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Muncitor");
    }

    @FXML
    public void handleRegisterAction() {
        registrationMessage.setFill(Color.RED);
        if(emailField.getText().length()==0 || !emailField.getText().contains("@")){
            registrationMessage.setText("Adresa de email invalida!");
            return;
        }
        if(passwordField.getText().length()<8){
            registrationMessage.setText("Parola trebuie sa contina minim 8 caractere!");
            return;
        }
        if(role.getValue() == null){
            registrationMessage.setText("Nu ati selectat rolul!");
            return;
        }
        //se mai poate verifica numele sa nu contina cifre
        if(nameField.getText().length()==0){
            registrationMessage.setText("Nu ati introdus numele!");
            return;
        }
        //se mai poate verifica nr de tel sa nu contina litere
        if(phoneField.getText().length()<10){
            registrationMessage.setText("Numar de telefon invalid!");
            return;
        }
        if(descriptionField.getText().length()==0){
            if(role.getValue().toString().equals("Client"))
                registrationMessage.setText("Nu ati introdus adresa!");
            if(role.getValue().toString().equals("Muncitor"))
                registrationMessage.setText("Nu ati introdus specializarile!");
            return;
        }
        try {
            UserService.addUser(
                    emailField.getText(), passwordField.getText(), role.getValue().toString(),
                    nameField.getText(), phoneField.getText(), descriptionField.getText()
            );
            registrationMessage.setFill(Color.GREEN);
            registrationMessage.setText("Cont creat cu succes!\nVa puteti autentifica acum.");
            if(not_testing) {
                gotoLogin();
            }
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void gotoLogin() throws IOException {
        Main.switchScene("login.fxml", "Login");
    }
}
