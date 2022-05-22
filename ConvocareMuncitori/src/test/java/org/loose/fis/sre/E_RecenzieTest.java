package org.loose.fis.sre;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.controllers.IstoricMuncitorController;
import org.loose.fis.sre.services.InterventieService;
import org.loose.fis.sre.services.RecenzieService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class E_RecenzieTest {

    @BeforeAll
    static void setUp() throws Exception {
        RecenzieService.init();
        IstoricMuncitorController.selectat=InterventieService.getIstoric(UserService.get_logged_in().getEmail()).get(0);
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("recenzie.fxml"));
        primaryStage.setTitle("Acordare recenzie");
        primaryStage.setScene(new Scene(root, 500, 700));
        primaryStage.show();
    }

    @Test
    void testRecenzie(FxRobot robot)  {
        robot.clickOn("#nota");
        robot.write("abc");
        robot.clickOn("#submit_btn");
        assertThat(robot.lookup("#err").queryText()).hasText("Nu ati introdus o nota valida!");


        robot.doubleClickOn("#nota");
        robot.write("9");
        robot.clickOn("#submit_btn");

        assertThat(robot.lookup("#err").queryText()).hasText("Recenzie acordata cu success!");

    }
}