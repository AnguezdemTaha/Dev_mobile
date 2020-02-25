package com.example.myapplication12.Model;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Message;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Personne {
    private String Nom;
    private String Mot_de_passe;
    private String Email;
    private String Num_telephone;
    private String Type;

    //attribus

    //private ArrayList<Message> Msg_envoyes=new ArrayList<Message>();
    //private ArrayList<Message> Msg_recus=new ArrayList<Message>();
    //private ArrayList<Evenement> Evn_participes=new ArrayList<Evenement>();

    public Personne() {
    }

    public Personne(String nom, String mot_de_passe, String email, String num_telephone, String Type) {
        Nom = nom;
        Mot_de_passe = mot_de_passe;
        Email = email;
        Num_telephone = num_telephone;
        this.Type = Type;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getMot_de_passe() {
        return Mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        Mot_de_passe = mot_de_passe;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNum_telephone() {
        return Num_telephone;
    }

    public void setNum_telephone(String num_telephone) {
        Num_telephone = num_telephone;
    }
}
