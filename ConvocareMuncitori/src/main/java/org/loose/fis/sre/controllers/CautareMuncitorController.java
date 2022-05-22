package org.loose.fis.sre.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Interventie;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.InterventieService;
import org.loose.fis.sre.services.RecenzieService;
import org.loose.fis.sre.services.UserService;

import javax.xml.namespace.QName;
import java.util.jar.Attributes;

public class CautareMuncitorController{
        @FXML
        private Text err;
        @FXML
        private TextField tfCautare;
        @FXML
        private List<User> list;
        @FXML
        private ListView listaCautare;
        @FXML
        private static User selectat;
        public static User getSelectat(){
                return selectat;
        }

        @FXML
        void btnInapoiClicked() {
                try {
                        Main.switchScene("dashboard_client.fxml", "Dashboard Client");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @FXML
        void btnRezervareClicked() {
                try {
                        Main.switchScene("rezervare_muncitor.fxml", "Rezervare muncitor");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        @FXML
        void btnCautareClicked() {
                        list = UserService.cautaremuncitor(tfCautare.getText());
                        if (list.size() == 0) {
                                err.setFill(Color.RED);
                                err.setText("Nu exista muncitori!");
                        } else {
                                for (User i : list) {
                                        String text = "Muncitor: " + i.getName() + "\n";
                                        text += "Specializarea: " + i.getDescription() + "\n";
                                        text += "Telefon: " + i.getPhone() + "\n";
                                        text += "Nota medie: " + RecenzieService.getMedia(i.getEmail());
                                        listaCautare.getItems().addAll(text);
                                }
                                listaCautare.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                                listaCautare.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                                        @Override
                                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                                User i = list.get((Integer) newValue);
                                                selectat = i;

                                        }
                                });


                        }
                }


}

