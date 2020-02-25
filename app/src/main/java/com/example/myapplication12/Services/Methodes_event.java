package com.example.myapplication12.Services;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Personne;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Methodes_event {
    public static CollectionReference getUsersCollection(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Evenement");
    }

    public static Task<QuerySnapshot> GetAllevents() {


        return getUsersCollection().get();

    }

    public static Task<DocumentReference> creatEvent(Evenement p) {
        //Personne userToCreate = new Personne(nom);
        return getUsersCollection().add(p);

    }
}
