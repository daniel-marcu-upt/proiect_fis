package org.loose.fis.sre.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;

import javafx.scene.paint.Color;
import org.loose.fis.sre.model.Interventie;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.InterventieService;
import org.loose.fis.sre.services.RecenzieService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.List;


public class InterventiiController {

    @FXML
    private ListView listaInterventii;
    @FXML
    private Text err;
    @FXML
    private Text client_text;

    private List<Interventie> list;

    @FXML
    public void initialize() {
        list = InterventieService.getInterventii(UserService.get_logged_in().getEmail(), new Date());
        if (list.size() == 0) {
            err.setFill(Color.RED);
            err.setText("Nu exista interventii!");
        } else {
            for (Interventie i : list) {
                User client = UserService.findUser(i.getClient());
                String description = client.getDescription() + "  " + i.getData().getDate() + "." + (i.getData().getMonth()+1) + "." + (i.getData().getYear() + 1900);
                listaInterventii.getItems().addAll(description);
            }
            listaInterventii.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            listaInterventii.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    Interventie i = list.get((Integer) newValue);
                    User client = UserService.findUser(i.getClient());
                    String text = "Client: "+client.getName()+"\n";
                    text += "Adresa: "+client.getDescription()+"\n";
                    text += "Data: "+i.getData().getDate() + "." + (i.getData().getMonth()+1) + "." + (i.getData().getYear() + 1900)+"\n";
                    text += "Nota medie: " + RecenzieService.getMedia(client.getEmail());
                    client_text.setText(text);
                }
            });
        }
    }

    @FXML
    private void gotoDashboard() throws IOException {
        Main.switchScene("dashboard_muncitor.fxml", "Dashboard Muncitor");
    }
}
