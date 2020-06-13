package com.example.myapplication12.Services;

import com.example.myapplication12.Model.Emploi;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Methodes_emp {



    public static Task<DocumentReference> createmp(Emploi c) {
        //Personne userToCreate = new Personne(nom);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection("emp").add(c);

    }
    public static Task<DocumentReference> createmp2(Emploi c) {
        //Personne userToCreate = new Personne(nom);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
         return db.collection("emp2").add(c);

    }

    public static Task<DocumentReference> createmp3(Emploi c) {
        //Personne userToCreate = new Personne(nom);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db.collection("emp3").add(c);

    }
}
