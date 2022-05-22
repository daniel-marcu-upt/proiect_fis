package org.loose.fis.sre.services;

import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Recenzie;

public class RecenzieService {

    private static ObjectRepository<Recenzie> repository;

    public static void init() {

        repository = FileSystemService.getDatabase().getRepository(Recenzie.class);
    }

    public static void addRecenzie(Integer interventie, Integer nota, String user){
        repository.insert(new Recenzie(interventie, nota, user));
    }
    public static Recenzie getRecenzie(String id){
        for(Recenzie r: repository.find())
            if(r.getId().equals(id))
                return r;
        return null;
    }
    public static int getRecenzie(Integer interventie, String user){
        String id=interventie+user;
//        System.out.println("cautam: "+id);
        //for(Recenzie r: repository.find())
        //    System.out.println(r.getId());
        for(Recenzie r: repository.find())
            if(r.getId().equals(id))
                return 1;
        return 0;
    }
    public static float getMedia(String user){
        float s=0;
        int n=0;
        for(Recenzie r: repository.find())
            if(r.getUser().equals(user)) {
                n++;
                s = s + r.getNota();
            }
        if(n==0)
            return 0;
        return s/n;
    }
    public static int getSuma(){
        int n=0;
        for(Recenzie r: repository.find())
            n = n + r.getNota();
        return n;
    }
    public static Integer getRecenziiCount(){
        int n=0;
        for(Recenzie r: repository.find())
            n++;
        return n;
    }


}
