package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.BadCredentials;
import org.loose.fis.sre.exceptions.DateNotAvailable;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Interventie;
import org.loose.fis.sre.model.Recenzie;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class RecenzieService {

    private static ObjectRepository<Recenzie> repository;

    public static void init() {

        repository = FileSystemService.getDatabase().getRepository(Recenzie.class);
    }

    public static void addRecenzie(Integer interventie, Integer nota, String user){
        repository.insert(new Recenzie(interventie, nota, user));
    }
    public static int getRecenzie(Integer interventie, String user){
        String id=interventie+user;
        System.out.println("cautam: "+id);
        for(Recenzie r: repository.find())
            System.out.println(r.getId());
        for(Recenzie r: repository.find())
            if(r.getId().equals(id))
                return 1;
        return 0;
    }
    public static float getAverage(String user){
        float s=0;
        int n=0;
        for(Recenzie r: repository.find()){
            if(r.getUser().equals(user)){
                s=s+r.getNota();
                n++;
            }
        }
        return s/n;
    }

    public static void removeAll(){
        for(Recenzie r: repository.find())
            repository.remove(r);
    }


}
