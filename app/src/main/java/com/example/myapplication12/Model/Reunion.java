package com.example.myapplication12.Model;

import java.util.ArrayList;
import java.util.Date;

public class Reunion extends Evenement {
    public Reunion(int Id_event,String nom_event, Date date_event, String description_event, ArrayList<Personne> per_participes) {
        super(Id_event,nom_event, date_event, description_event, per_participes);
    }

    public Reunion() {
        super();
    }
}
