package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.BadCredentials;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static User logged_in = null;

    public static void init() {

        userRepository = FileSystemService.getDatabase().getRepository(User.class);
    }

    public static void addUser(String username, String password, String role, String name, String phone, String description) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role, name, phone, description));
    }

    public static User findUser(String email) {
        for (User user : userRepository.find()) {
            if (Objects.equals(email, user.getEmail()))
                return user;
        }
        return null;
    }

    private static void checkUserDoesNotAlreadyExist(String email) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(email, user.getEmail()))
                throw new UsernameAlreadyExistsException(email);
        }
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }


    public static User login(String username, String pass) throws BadCredentials {
        String encoded = encodePassword(username, pass);
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getEmail())) {
                if (Objects.equals(encoded, user.getPassword())) {
                    logged_in = user;
                    return user;
                } else {
                    throw new BadCredentials();
                }
            }
        }
        throw new BadCredentials();
    }

    public static User get_logged_in() {
        return logged_in;
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


    public static Integer getUsersCount() {
        int n = 0;
        for (User u : userRepository.find())
            n++;
        return n;
    }
    public static List<User> cautaremuncitor(String specializare){
        List<User> l = new ArrayList<>();
        for (User u : userRepository.find())
            if(u.getDescription().contains(specializare) && u.getRole().equals("Muncitor"))
                l.add(u);

            return l;
    }

}
