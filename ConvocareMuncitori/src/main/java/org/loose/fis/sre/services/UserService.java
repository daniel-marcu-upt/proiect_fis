package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.BadCredentials;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static User logged_in=null;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role, String name, String phone, String description) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role, name, phone, description));
    }

    private static void checkUserDoesNotAlreadyExist(String email) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(email, user.getEmail()))
                throw new UsernameAlreadyExistsException(email);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }


    public static User login(String username, String pass) throws BadCredentials {
        String encoded=encodePassword(username, pass);
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getEmail())){
                if(Objects.equals(encoded, user.getPassword())){
                    logged_in=user;
                    return user;
                }else{
                    throw new BadCredentials();
                }
            }
        }
        throw new BadCredentials();
    }

    public static User get_logged_in(){
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


}