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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class InterventieService {

    private static ObjectRepository<Interventie> repository;
    private static Integer last_id;

    public static void init() {

        repository = FileSystemService.getDatabase().getRepository(Interventie.class);
    }

    public static void addInterventie(String client, String muncitor, Date data) throws DateNotAvailable {
        checkAvailability(muncitor, data);
        last_id=-1;
        for(Interventie interventie : repository.find())
            last_id = interventie.getId();
        last_id++;
        repository.insert(new Interventie(last_id, client, muncitor, data ));
    }
    public static List<Interventie> getInterventii(String muncitor){
        List<Interventie> list = new ArrayList<Interventie>();
        for(Interventie interventie: repository.find())
            if(interventie.getMuncitor().equals(muncitor))
                list.add(interventie);
        return list;
    }
    private static int cmp(Date a, Date b){
        if(a.getYear()<b.getYear())
            return -1;
        if(b.getYear()<a.getYear())
            return 1;
        if(a.getMonth()<b.getMonth())
            return -1;
        if(b.getMonth()<a.getMonth())
            return 1;
        if(a.getDay()<b.getDay())
            return -1;
        if(b.getDay()<a.getDay())
            return 1;
        return 0;
    }
    public static List<Interventie> getInterventii(String muncitor, Date data){
        List<Interventie> list = new ArrayList<Interventie>();
        for(Interventie interventie: repository.find())
            if(interventie.getMuncitor().equals(muncitor) && cmp(data, interventie.getData()) <= 0)
                list.add(interventie);
        return list;
    }
    public static List<Interventie> getIstoric(String muncitor){
        List<Interventie> list = new ArrayList<Interventie>();
        Date now = new Date();
        for(Interventie interventie: repository.find())
            if(interventie.getMuncitor().equals(muncitor) && cmp(now, interventie.getData()) > 0)
                list.add(interventie);
        return list;
    }

    private static void checkAvailability(String muncitor, Date data) throws DateNotAvailable {
        for (Interventie interventie : repository.find()) {
            if (interventie.getMuncitor().equals(muncitor) && interventie.getData().equals(data)) {
                throw new DateNotAvailable();
            }
        }
    }



}
