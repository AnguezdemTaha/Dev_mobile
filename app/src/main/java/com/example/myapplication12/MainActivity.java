package com.example.myapplication12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.Services.Exm;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.proto.TargetGlobal;

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
        user.put("born", 1815);
        db.collection("test1")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


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

                //se connecter avec la base de donnée + getref(cree ou ecrire dans le child
                //DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("tese1");
                //reference1.setValue("ok");
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("utisateur").document("ahmed");
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Personne p = documentSnapshot.toObject(Personne.class);
                            String nom=p.getNom();
                            mytext=(TextView)findViewById(R.id.t);

                            mytext.setText(nom);
                        }
                    });



                FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                String var="test11";
                //Exm.createUser(var);
                //Personne user1 = new Personne(var);
                //db1.collection("utisateur").add(user1);

                Intent in=new Intent(MainActivity.this,login1.class);
                startActivity(in);
            }
        });




    }
}