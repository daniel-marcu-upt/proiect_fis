package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Date;

public class Recenzie {
    @Id
    private String id;
    private Integer interventie;
    private Integer nota;
    private String user;

    public Recenzie(Integer interventie, Integer nota, String user){
        this.id=interventie+user;
        this.interventie=interventie;
        this.nota=nota;
        this.user=user;
    }
    public Recenzie(){

    }
    public String getId() {
        return id;
    }
    public Integer getInterventie() {return interventie;}
    public Integer getNota() {return nota;}
    public String getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recenzie recenzie = (Recenzie) o;

        if (id != null ? !id.equals(recenzie.id) : recenzie.id != null) return false;
        if (interventie != null ? !interventie.equals(recenzie.interventie) : recenzie.interventie != null) return false;
        if (user != null ? !user.equals(recenzie.user) : recenzie.user != null) return false;
        return nota != null ? nota.equals(recenzie.nota) : recenzie.nota == null;
    }
}
