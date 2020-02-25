package com.example.myapplication12.Services;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Methodes_personne {
    private static final String COLLECTION_NAME = "Personne";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static Task<DocumentReference> createUser(Personne p) {
        //Personne userToCreate = new Personne(nom);
        return Methodes_personne.getUsersCollection().add(p);

    }

    // --- GET ---

    public static Task<DocumentSnapshot> getUser(String nom){
        return Methodes_personne.getUsersCollection().document(nom).get();
    }

    // --- UPDATE ---

    public static Task<Void> updateUsername(String username, String uid) {
        return Methodes_personne.getUsersCollection().document(uid).update("username", username);
    }
    /*
    public static Task<Void> updateIsMentor(String uid, Boolean isMentor) {
        return Methodes_personne.getUsersCollection().document(uid).update("isMentor", isMentor);
    }
    */
    // --- DELETE ---

    public static Task<Void> deleteUser(String uid) {
        return Methodes_personne.getUsersCollection().document(uid).delete();
    }

    public static Task<QuerySnapshot> GetUsers(String nom, String mot_de_passe) {


        return getUsersCollection().whereEqualTo("Nom", nom).whereEqualTo("Mot_de_passe",mot_de_passe).get();

    }
    public static Task<QuerySnapshot> GetAllUsers() {


        return getUsersCollection().get();

    }

    public static ArrayList<Personne> getallUsers1(String Nom) {
        final ArrayList<Personne> ps = new ArrayList<>();
        Task<QuerySnapshot> task = Methodes_personne.getUsersCollection().whereEqualTo("Nom", Nom).get();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Personne p = document.toObject(Personne.class);
                        //String nom = p.getEmail();

                        //Personne p = new Personne();
                        //p = document.toObject(Personne.class);
                        ps.add(p);
                    }

            

     return ps;
    }


    /*public static void main(String[] args) {
        String nom="ahmed";
        String mot_de_passe="";
        String Type=null;
        ArrayList<Personne> task =getallUsers1(nom);
                            for (Personne p :task) {
                                //Personne p = document.toObject(Personne.class);
                                System.out.println("une personne :"+p.getNom());

                            }

    }*/


}
