package org.loose.fis.sre.exceptions;

public class BadCredentials extends Exception {
    public BadCredentials() {
        super(String.format("Parola sau utilizatorul sunt gresite!"));
    }
}
