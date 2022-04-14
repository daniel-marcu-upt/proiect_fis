package org.loose.fis.sre.exceptions;

public class UsernameAlreadyExistsException extends Exception {

    private String username;

    public UsernameAlreadyExistsException(String username) {
        super(String.format("Adresa '%s' e folosita deja!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
