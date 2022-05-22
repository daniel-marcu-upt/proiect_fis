package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.DateNotAvailable;
import org.loose.fis.sre.model.Interventie;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.InterventieService;
import org.loose.fis.sre.services.RecenzieService;
import org.loose.fis.sre.services.UserService;

import javax.xml.namespace.QName;
import java.util.jar.Attributes;

public class RezervareMuncitorController {
    @FXML
    private TextField zi,luna,an;
    @FXML
    private  Text err;
    @FXML
    void btnRezervareMuncitor() {
        int zi1,luna1,an1;
        zi1=Integer.parseInt(zi.getText());
        luna1=Integer.parseInt(luna.getText());
        an1=Integer.parseInt(an.getText());
        Date date = new Date(an1-1900,luna1-1,zi1);
        try {
            InterventieService.addInterventie(UserService.get_logged_in().getEmail(),CautareMuncitorController.getSelectat().getEmail(),date);
            err.setFill(Color.GREEN);
            err.setText("Rezervare cu succes!");
        }
        catch (DateNotAvailable e){
            err.setFill(Color.RED);
            err.setText(e.getMessage());
        }

    }
    @FXML
    void btnInapoi(){
        try {
            Main.switchScene("dashboard_client.fxml", "Dashboard Client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
