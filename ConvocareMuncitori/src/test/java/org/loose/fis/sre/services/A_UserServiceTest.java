package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

public class A_UserServiceTest {
    @BeforeAll
    static void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-services-registration-example";
        FileSystemService.APPLICATION_HOME_PATH = Paths.get(FileSystemService.USER_FOLDER, FileSystemService.APPLICATION_FOLDER);
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        FileSystemService.initDatabase();
        UserService.init();
    }
    @Test
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getUsersCount()).isNotNull();
        assertThat(UserService.getUsersCount().equals(0));
    }
    @Test
    void testUserIsAddedToDatabase1() throws UsernameAlreadyExistsException {
        UserService.addUser("muncitor1@gmail.com", "password", "Muncitor", "muncitor test", "070000000", "instalator");
        assertThat(UserService.getUsersCount().equals(1));
        User user = UserService.findUser("muncitor1@gmail.com");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("muncitor1@gmail.com");
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword("muncitor1@gmail.com", "password"));
        assertThat(user.getRole()).isEqualTo("Muncitor");
        assertThat(user.getName()).isEqualTo("muncitor test");
        assertThat(user.getPhone()).isEqualTo("070000000");
        assertThat(user.getDescription()).isEqualTo("instalator");
    }
    @Test
    void testUserIsAddedToDatabase2() throws UsernameAlreadyExistsException {
        UserService.addUser("client@gmail.com", "password", "Client", "client test", "070000000", "martirilor 14");
        assertThat(UserService.getUsersCount().equals(2));
        User user = UserService.findUser("client@gmail.com");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("client@gmail.com");
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword("client@gmail.com", "password"));
        assertThat(user.getRole()).isEqualTo("Client");
        assertThat(user.getName()).isEqualTo("client test");
        assertThat(user.getPhone()).isEqualTo("070000000");
        assertThat(user.getDescription()).isEqualTo("martirilor 14");
    }

    @Test
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser("muncitor@gmail.com", "password", "Muncitor", "muncitor test", "070000000", "instalator");
            UserService.addUser("muncitor@gmail.com", "password", "Muncitor", "muncitor test", "070000000", "instalator");
        });
    }
}
