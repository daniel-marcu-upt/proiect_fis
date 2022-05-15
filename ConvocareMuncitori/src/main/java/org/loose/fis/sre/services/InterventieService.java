package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.BadCredentials;
import org.loose.fis.sre.exceptions.DateNotAvailable;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Interventie;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class InterventieService {

    private static ObjectRepository<Interventie> repository;
    private static Integer last_id;

    public static void init() {

        repository = FileSystemService.getDatabase().getRepository(Interventie.class);
        for(Interventie interventie : repository.find())
            last_id = interventie.getId();
    }

    public static void addInterventie(String client, String muncitor, Date data) throws DateNotAvailable {
        checkAvailability(muncitor, data);
        last_id++;
        repository.insert(new Interventie(last_id, client, muncitor, data ));
    }

    private static void checkAvailability(String muncitor, Date data) throws DateNotAvailable {
        for (Interventie interventie : repository.find()) {
            if (interventie.getMuncitor().equals(muncitor) && interventie.getData().equals(data)) {
                throw new DateNotAvailable();
            }
        }
    }



}
