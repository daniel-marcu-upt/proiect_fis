package org.loose.fis.sre;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.controllers.LoginController;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.nio.file.Paths;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class B_LoginTest {

    public static final String EMAIL = "muncitor@gmail.com";
    public static final String PASSWORD0 = "password0";
    public static final String PASSWORD = "password";

    public static final String EMAIL1 = "client@gmail.com";
    public static final String PASSWORD1 = "password";

    @BeforeAll
    static void setUp() throws Exception {
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 500, 700));
        primaryStage.show();
        LoginController.not_testing=false;
    }

    @Test
    void testLogin1(FxRobot robot)  {

        robot.clickOn("#emailField");
        robot.write(EMAIL);
        robot.clickOn("#passwordField");
        robot.write(PASSWORD0);
        robot.clickOn("#login_btn");

        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Parola sau utilizatorul sunt gresite!");


        robot.doubleClickOn("#passwordField");
//        for(int i=0;i<8;i++)
//        robot.press(KeyCode.BACK_SPACE);
        robot.write(PASSWORD);
        robot.clickOn("#login_btn");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Ok");

    }
    @Test
    void testLogin2(FxRobot robot)  {

        robot.clickOn("#emailField");
        robot.write(EMAIL1);
        robot.clickOn("#passwordField");
        robot.write(PASSWORD1);
        robot.clickOn("#login_btn");

        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Ok");

    }
}