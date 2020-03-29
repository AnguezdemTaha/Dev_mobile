package com.example.myapplication12.Model;

import java.util.ArrayList;
import java.util.Date;

public class Emploi {
    //.

    private String annee;
    private String contenu;
    private Date date_semaine;
    //private ArrayList<Crenau> Crn=new ArrayList<Crenau>();


    public Emploi() {
    }

    public Emploi(String annee, String contenu, Date date_semaine) {
        this.annee = annee;
        this.contenu = contenu;
        this.date_semaine = date_semaine;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate_semaine() {
        return date_semaine;
    }

    public void setDate_semaine(Date date_semaine) {
        this.date_semaine = date_semaine;
    }
}
