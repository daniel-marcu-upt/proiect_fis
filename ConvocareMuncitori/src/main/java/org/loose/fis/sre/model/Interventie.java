package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Date;

public class Interventie {
    @Id
    private Integer id;
    private String client;
    private String muncitor;
    private Date data;

    public Interventie(Integer id, String client, String muncitor, Date data){
        this.id=id;
        this.client=client;
        this.muncitor=muncitor;
        this.data=data;
    }
    public Interventie(){

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client;
    }

    public String getMuncitor() {
        return muncitor;
    }
    public void setMuncitor(String muncitor) {
        this.muncitor = muncitor;
    }

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interventie interventie = (Interventie) o;

        if (id != null ? !id.equals(interventie.id) : interventie.id != null) return false;
        if (client != null ? !client.equals(interventie.client) : interventie.client != null) return false;
        if (muncitor != null ? !muncitor.equals(interventie.muncitor) : interventie.muncitor != null) return false;
        return data != null ? data.equals(interventie.data) : interventie.data == null;
    }
}
