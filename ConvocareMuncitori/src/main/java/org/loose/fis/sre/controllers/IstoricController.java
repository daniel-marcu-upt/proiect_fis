package org.loose.fis.sre.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;

import javafx.scene.paint.Color;
import org.loose.fis.sre.model.Interventie;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.InterventieService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.List;


public class IstoricController {

    @FXML
    private ListView listaIstoric;
    @FXML
    private Text err;
    @FXML
    private Text client_text;

    private List<Interventie> list;

    @FXML
    public void initialize() {
    }
    private void gotoRecenzie() throws IOException{
        Main.switchScene("recenzie.fxml", "Acordare recenzie");
    }

    @FXML
    private void gotoDashboard() throws IOException {
        Main.switchScene("dashboard_muncitor.fxml", "Dashboard Muncitor");
    }
}
