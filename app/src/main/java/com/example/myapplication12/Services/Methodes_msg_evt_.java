package com.example.myapplication12.Services;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Methodes_msg_evt_ {



    public static CollectionReference getUsersCollection(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Message");
    }

    public static Task<DocumentReference> creatMessage(Message p) {
        //Personne userToCreate = new Personne(nom);
        return getUsersCollection().add(p);

    }


    public static Task<QuerySnapshot> GetMessages(Personne p) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getNom());
        p1.put("mot_de_passe", p.getMot_de_passe());
        p1.put("email",p.getEmail());
        p1.put("num_telephone",p.getNum_telephone());
        p1.put("type",p.getType());

        Task<QuerySnapshot> r1 = getUsersCollection().whereEqualTo("per_envoye", p1).get();
        Task<QuerySnapshot> r2 = getUsersCollection().whereEqualTo("per_envoye", p1).get();

        return r1;



                   /* for (QueryDocumentSnapshot document : task.getResult()) {
                        Message m = document.toObject(Message.class);
                        //String nom = p.getEmail();

                        //Personne p = new Personne();
                        //p = document.toObject(Personne.class);
                        Ms.add(m);

                    }*/


    }

    public static Task<QuerySnapshot> GetMessagesrecu(Personne p) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getNom());
        p1.put("mot_de_passe", p.getMot_de_passe());
        p1.put("email", p.getEmail());
        p1.put("num_telephone", p.getNum_telephone());
        p1.put("type", p.getType());

        Task<QuerySnapshot> r1 = getUsersCollection().whereEqualTo("per_envoye", p1).get();
        Task<QuerySnapshot> r2 = getUsersCollection().whereArrayContains("per_recus", p1).get();

        return r2;
    }

    public static Task<QuerySnapshot> GetMessages1(Personne p,Personne p2) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getNom());
        p1.put("mot_de_passe", p.getMot_de_passe());
        p1.put("email",p.getEmail());
        p1.put("num_telephone",p.getNum_telephone());
        p1.put("type",p.getType());

        Map<String, Object> p21 = new HashMap<>();
        p21.put("nom", p2.getNom());
        p21.put("mot_de_passe", p2.getMot_de_passe());
        p21.put("email",p2.getEmail());
        p21.put("num_telephone",p2.getNum_telephone());
        p21.put("type",p2.getType());

        Task<QuerySnapshot> r1 = getUsersCollection().whereEqualTo("per_envoye", p1).whereArrayContains("per_recus",p21).get();


        return r1;
    }

    public static Task<QuerySnapshot> Getwhenobjattr(Personne p) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("nom", p.getNom());
        p1.put("mot_de_passe", p.getMot_de_passe());
        p1.put("email", p.getEmail());
        p1.put("num_telephone", p.getNum_telephone());
        p1.put("type", p.getType());


        Task<QuerySnapshot> r2 = getUsersCollection().whereArrayContains("per_recus", p1).get();

        return r2;
    }

    public static Task<QuerySnapshot> GetMessagesrecus(Personne p) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("Nom", p.getNom());
        p1.put("Mot_de_passe", p.getMot_de_passe());
        p1.put("Email",p.getEmail());
        p1.put("Num_telephone",p.getNum_telephone());
        p1.put("Type",p.getType());

        return getUsersCollection().whereArrayContains("Per_recus",p1).get();





    }

    public static Task<QuerySnapshot> GetMessagesdiscution(Personne p,Personne p2) {


        Map<String, Object> p1 = new HashMap<>();
        p1.put("Nom", p.getNom());
        p1.put("Mot_de_passe", p.getMot_de_passe());
        p1.put("Email",p.getEmail());
        p1.put("Num_telephone",p.getNum_telephone());
        p1.put("Type",p.getType());

        Map<String, Object> p21 = new HashMap<>();
        p21.put("Nom", p2.getNom());
        p21.put("Mot_de_passe", p2.getMot_de_passe());
        p21.put("Email",p2.getEmail());
        p21.put("Num_telephone",p2.getNum_telephone());
        p21.put("Type",p2.getType());

        return getUsersCollection().whereEqualTo("Per_envoye",p1).whereArrayContains("Per_recus",p21).get();





    }


}
