package org.loose.fis.sre;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.controllers.CautareMuncitorController;
import org.loose.fis.sre.controllers.InterventiiController;
import org.loose.fis.sre.controllers.LoginController;
import org.loose.fis.sre.model.Interventie;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.InterventieService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.awt.*;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class H_RezervareTest {

    @BeforeAll
    static void setUp() throws Exception {
        CautareMuncitorController.selectat=UserService.findUser("muncitor@gmail.com");
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("rezervare_muncitor.fxml"));
        primaryStage.setTitle("Rezervare Muncitor");
        primaryStage.setScene(new Scene(root, 500, 700));
        primaryStage.show();
    }

    @Test
    void testRezervare(FxRobot robot)  {
        robot.clickOn("#zi");
        robot.write("10");
        robot.clickOn("#luna");
        robot.write("3");
        robot.clickOn("#an");
        robot.write("2022");
        robot.clickOn("#btnRezervare");
        assertThat(robot.lookup("#err").queryText()).hasText("Data selectata nu este disponibila!");
        robot.doubleClickOn("#zi");
        robot.write("10");
        robot.doubleClickOn("#luna");
        robot.write("7");
        robot.doubleClickOn("#an");
        robot.write("2022");
        robot.clickOn("#btnRezervare");
        assertThat(robot.lookup("#err").queryText()).doesNotHaveText("Data selectata nu este disponibila!");
    }
}