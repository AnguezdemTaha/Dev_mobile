package com.example.myapplication12.Services;

import com.example.myapplication12.Model.Personne;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Exm {
    private static final String COLLECTION_NAME = "utisateur";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static Task<Void> createUser(String nom) {
        //Personne userToCreate = new Personne(nom);
        //return Exm.getUsersCollection().document(nom).set(userToCreate);
        return null;
    }

    // --- GET ---

    public static Task<DocumentSnapshot> getUser(String nom){
        return Exm.getUsersCollection().document(nom).get();
    }

    // --- UPDATE ---

    public static Task<Void> updateUsername(String username, String uid) {
        return Exm.getUsersCollection().document(uid).update("username", username);
    }
    /*
    public static Task<Void> updateIsMentor(String uid, Boolean isMentor) {
        return Exm.getUsersCollection().document(uid).update("isMentor", isMentor);
    }
    */
    // --- DELETE ---

    public static Task<Void> deleteUser(String uid) {
        return Exm.getUsersCollection().document(uid).delete();
    }
}
