package com.example.myapplication12.Model;

public class Cours {
    private String Nom_cours;
    //+file

    //.

    private Matiere M;
    private Professeur Prf;

    public Cours(String nom_cours, Matiere m, Professeur prf) {
        Nom_cours = nom_cours;
        M = m;
        Prf = prf;
    }

    public String getNom_cours() {
        return Nom_cours;
    }

    public void setNom_cours(String nom_cours) {
        Nom_cours = nom_cours;
    }

    public Matiere getM() {
        return M;
    }

    public void setM(Matiere m) {
        M = m;
    }

    public Professeur getPrf() {
        return Prf;
    }

    public void setPrf(Professeur prf) {
        Prf = prf;
    }
}
