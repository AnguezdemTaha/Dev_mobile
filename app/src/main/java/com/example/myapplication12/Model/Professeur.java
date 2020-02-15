package com.example.myapplication12.Model;

import java.util.ArrayList;

public class Professeur extends Personne {
    //.

    private ArrayList<Matiere> M=new ArrayList<Matiere>();

    public Professeur() {
    }

    public Professeur(String nom, String mot_de_passe, String email, String num_telephone) {
        super(nom, mot_de_passe, email, num_telephone);
    }
}
