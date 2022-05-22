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
import java.util.List;


public class IstoricClientController {

    @FXML
    private ListView listaIstoric;
    @FXML
    private Text err;
    @FXML
    private Text muncitor_text;

    private List<Interventie> list;
    public static Interventie selectat;

    @FXML
    public void initialize() {
        list = InterventieService.getIstoricClient(UserService.get_logged_in().getEmail());
        if (list.size() == 0) {
            err.setFill(Color.RED);
            err.setText("Nu exista cereri!");
        } else {
            for (Interventie i : list) {
                User muncitor = UserService.findUser(i.getMuncitor());
                String description = muncitor.getName() + "  " + i.getData().getDate() + "." + (i.getData().getMonth()+1) + "." + (i.getData().getYear() + 1900);
                listaIstoric.getItems().addAll(description);
            }
            listaIstoric.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            listaIstoric.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    Interventie i = list.get((Integer) newValue);
                    selectat=i;
                    User muncitor = UserService.findUser(i.getMuncitor());
                    String text = "Muncitor: "+muncitor.getName()+"\n";
                    text += "Specializarea: "+muncitor.getDescription()+"\n";
                    text += "Data: "+i.getData().getDate() + "." + (i.getData().getMonth()+1) + "." + (i.getData().getYear() + 1900)+"\n";
                    text += "Nota medie: " + RecenzieService.getMedia(muncitor.getEmail());
                    muncitor_text.setText(text);

                }
            });
        }
    }
    public static Interventie getSelectat(){
        return selectat;
    }

    @FXML
    private void gotoRecenzie() throws  IOException{
        if(selectat == null){
            err.setFill(Color.RED);
            err.setText("Nu ati selectat o cerere!");
        }else{
            int r = RecenzieService.getRecenzie(selectat.getId(), selectat.getMuncitor());
            if(r == 0)
                Main.switchScene("recenzie.fxml", "Acordare recenzii");
            else {
                err.setFill(Color.RED);
                err.setText("Ati acordat deja recenzia!");
            }
        }
    }
    @FXML
    private void gotoDashboard() throws IOException {
        Main.switchScene("dashboard_client.fxml", "Dashboard Client");
    }
}