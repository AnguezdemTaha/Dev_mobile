package com.example.myapplication12.Evenement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_event;
import com.example.myapplication12.Services.Methodes_personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

public class Editevent extends AppCompatActivity {
    private TextView t1,t2,t3,t4;
    private TextView nom2,date2,description2;
    private TextView nom1;
    private ImageView save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even2);
        getSupportActionBar().hide();

        nom1=(TextView) findViewById(R.id.even_nom1);
        nom2=(TextView) findViewById(R.id.even_nom2);
        date2=(EditText) findViewById(R.id.gmail_prof2);
        description2=(TextView) findViewById(R.id.even_description2);
        //save=(ImageView) findViewById(R.id.save_even);


        Bundle extras = getIntent().getExtras();
        String nom=extras.getString("nom_even");
        //String nom="test22";
        Methodes_event.Geteventbynom(nom).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    String Type=null;
                    String nomevn = null;
                    String dateevn=null;
                    String description =null;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        System.out.println("bonjour succe");
                        Evenement e = document.toObject(Evenement.class);
                        //Type = p.getType();
                        nomevn=e.getNom_event();
                        //dateevn=e.getDate_event();
                        description=e.getDescription_event();

                        nom1.setText(nomevn);
                        nom2.setText(nomevn);
                        description2.setText(description);



                    }

                }}

        });


    }
}
