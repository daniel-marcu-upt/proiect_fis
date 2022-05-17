package org.loose.fis.sre;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class RegistrationTest {

    public static final String EMAIL = "user@gmail.com";
    public static final String PASSWORD = "password";
    public static final String NAME = "password";
    public static final String PHONE = "0700000000";
    public static final String DESCRIPTION = "specializare";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        FileSystemService.initDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Registration Example");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    @Test
    void testRegistration(FxRobot robot) {
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
//        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

//        robot.clickOn("#registerButton");
//        assertThat(robot.lookup("#registrationMessage").queryText()).hasText(
//                String.format("An account with the username %s already exists!", USERNAME)
//        );

//        robot.clickOn("#username");
//        robot.write("1");
//        robot.clickOn("#registerButton");

//        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
//        assertThat(UserService.getAllUsers()).size().isEqualTo(2);
    }
}