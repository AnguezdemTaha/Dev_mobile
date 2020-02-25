package com.example.myapplication12.Model;

import java.util.ArrayList;
import java.util.Date;

public class Evenement {
    private int Id_event;
    private String Nom_event;
    private Date Date_event;
    private String Description_event;
    //.

    private ArrayList<Personne> per_participes=new ArrayList<Personne>();

    public Evenement() {

    }

    public Evenement(int id_event, String nom_event, Date date_event, String description_event, ArrayList<Personne> per_participes) {
        Id_event =id_event;
        Nom_event = nom_event;
        Date_event = date_event;
        Description_event = description_event;
        this.per_participes = per_participes;
    }

    public int getId_event() {
        return Id_event;
    }

    public void setId_event(int id_event) {
        Id_event = id_event;
    }

    public String getNom_event() {
        return Nom_event;
    }

    public void setNom_event(String nom_event) {
        Nom_event = nom_event;
    }

    public Date getDate_event() {
        return Date_event;
    }

    public void setDate_event(Date date_event) {
        Date_event = date_event;
    }

    public String getDescription_event() {
        return Description_event;
    }

    public void setDescription_event(String description_event) {
        Description_event = description_event;
    }

    public ArrayList<Personne> getPer_participes() {
        return per_participes;
    }

    public void setPer_participes(ArrayList<Personne> per_participes) {
        this.per_participes = per_participes;
    }
}
