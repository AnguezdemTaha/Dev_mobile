package com.example.myapplication12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mytext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String var="ahmed";
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //se connecter avec la base de donnée + getref(cree ou ecrire dans le child
        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("exemple").child("tese1");
        //reference.setValue("ok");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1816);
        /*db.collection("test2").document("testxd")
                .update(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });*/

        /*final DocumentReference sfDocRef = db.collection("test2").document("testxd");

        db.runTransaction(new Transaction.Function<Double>() {
            @Override
            public Double apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                double newPopulation = snapshot.getDouble("born") + 1;
                if (newPopulation <= 2000) {
                    transaction.update(sfDocRef, "born", newPopulation);
                    return newPopulation;
                } else {
                    throw new FirebaseFirestoreException("Population too high",
                            FirebaseFirestoreException.Code.ABORTED);
                }
            }
        }).addOnSuccessListener(new OnSuccessListener<Double>() {
            @Override
            public void onSuccess(Double result) {

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });*/





        /*
        //realtime database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        */

        mytext=(TextView)findViewById(R.id.t);

        mytext.setText(var);
        mytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");

                //se connecter avec la base de donnée + getref(cree ou ecrire dans le child
                //DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("tese1");
                //reference1.setValue("ok");
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference docRef = db.collection("Message");
                Methodes_msg_evt_.GetMessages(p1).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Message p = document.toObject(Message.class);
                                String nom=p.getPer_envoye().getEmail();
                                mytext=(TextView)findViewById(R.id.t);

                                mytext.setText(nom);
                            }
                        } else {

                        }
                    }

                    /*
                    @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Personne p = documentSnapshot.toObject(Personne.class);
                            String nom=p.getNom();
                            mytext=(TextView)findViewById(R.id.t);

                            mytext.setText(nom);


                        }*/
                    });



                FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                String var="test11";
                //Methodes_personne.createUser(var);
                //Personne user1 = new Personne(var);
                //db1.collection("utisateur").add(user1);

                Intent in=new Intent(MainActivity.this, Addmessage.class);
                startActivity(in);
            }
        });




    }
}