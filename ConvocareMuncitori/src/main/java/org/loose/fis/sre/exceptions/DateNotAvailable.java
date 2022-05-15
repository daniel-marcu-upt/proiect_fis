package org.loose.fis.sre.exceptions;

public class DateNotAvailable extends Exception {
    public DateNotAvailable() {
        super(String.format("Data selectata nu este disponibila!"));
    }
}