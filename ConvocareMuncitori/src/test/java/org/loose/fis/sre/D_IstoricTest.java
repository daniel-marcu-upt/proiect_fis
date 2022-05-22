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

import java.nio.file.Paths;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class D_IstoricTest {

    @BeforeAll
    static void setUp() throws Exception {
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("istoric_muncitor.fxml"));
        primaryStage.setTitle("Interventii");
        primaryStage.setScene(new Scene(root, 500, 700));
        primaryStage.show();
    }

    @Test
    void testIstoric(FxRobot robot)  {
        assertThat(robot.lookup("#err").queryText()).doesNotHaveText("Nu exista interventii!");
        List<Interventie> list = InterventieService.getInterventii(UserService.get_logged_in().getEmail(), new Date());
        assertThat(list.size() != 1);
        robot.clickOn("#recenzie");
        assertThat(robot.lookup("#err").queryText()).hasText("Nu ati selectat o interventie!");

        robot.clickOn("#listaIstoric");
        robot.press(KeyCode.DOWN);

//        assertThat(robot.lookup("#client_text").queryText()).hasText("!");
    }
}