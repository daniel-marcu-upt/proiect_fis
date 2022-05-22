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
import org.loose.fis.sre.controllers.RegistrationController;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.nio.file.Paths;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class A_RegistrationTest {

    public static final String EMAIL = "muncitor@gmail.com";
    public static final String PASSWORD = "password";
    public static final String NAME = "Nume Muncitor";
    public static final String PHONE = "0700000000";
    public static final String DESCRIPTION = "specializare";

    public static final String EMAIL1 = "client@gmail.com";
    public static final String PASSWORD1 = "password";
    public static final String NAME1 = "Nume Client";
    public static final String PHONE1 = "0712213142";
    public static final String DESCRIPTION1 = "Strada Martirilor, nr 21";

    @BeforeAll
    static void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.APPLICATION_HOME_PATH = Paths.get(FileSystemService.USER_FOLDER, FileSystemService.APPLICATION_FOLDER);
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        FileSystemService.initDatabase();
        UserService.init();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Registration");
        primaryStage.setScene(new Scene(root, 500, 700));
        primaryStage.show();
        RegistrationController.not_testing=false;
    }

    @Test
    void testRegistration1(FxRobot robot)  {

        robot.clickOn("#emailField");
        robot.write(EMAIL);
        robot.clickOn("#passwordField");
        robot.write(PASSWORD);
//        robot.clickOn("#role");

        robot.clickOn("#nameField");
        robot.write(NAME);
        robot.clickOn("#phoneField");
        robot.write(PHONE);
        robot.clickOn("#descriptionField");
        robot.write(DESCRIPTION);
        robot.clickOn("#register");

        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Nu ati selectat rolul!");
        assertThat(UserService.getUsersCount().equals(0));

        robot.clickOn("#role");
        robot.press(KeyCode.DOWN);
        robot.press(KeyCode.ENTER);
        robot.clickOn("#register");

        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Cont creat cu succes!\nVa puteti autentifica acum.");
        assertThat(UserService.getUsersCount().equals(1));

    }
    @Test
    void testRegistration2(FxRobot robot)  {

        robot.clickOn("#emailField");
        robot.write(EMAIL1);
        robot.clickOn("#passwordField");
        robot.write(PASSWORD1);
//        robot.clickOn("#role");

        robot.clickOn("#nameField");
        robot.write(NAME1);
        robot.clickOn("#phoneField");
        robot.write(PHONE1);
        robot.clickOn("#descriptionField");
        robot.write(DESCRIPTION1);


        robot.clickOn("#role");
        robot.press(KeyCode.ENTER);
        robot.clickOn("#register");

        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Cont creat cu succes!\nVa puteti autentifica acum.");
        assertThat(UserService.getUsersCount().equals(2));

    }
}