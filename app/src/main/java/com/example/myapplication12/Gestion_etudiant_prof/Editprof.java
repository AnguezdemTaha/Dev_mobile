package com.example.myapplication12.Gestion_etudiant_prof;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.MainActivity;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Menu.Menuetudiant;
import com.example.myapplication12.Menu.Menuprof;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Editprof extends AppCompatActivity {

    private EditText nom2,email2,tele2;
    private TextView nom1;
    private ImageView save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulerprof);
        getSupportActionBar().hide();
        nom1=(TextView) findViewById(R.id.nom_prof);
        nom2=(EditText) findViewById(R.id.nom_prof2);
        email2=(EditText) findViewById(R.id.gmail_prof2);
        tele2=(EditText) findViewById(R.id.telephone_prof2);
        save=(ImageView) findViewById(R.id.save_prof);


        Bundle extras = getIntent().getExtras();
        String nom=extras.getString("nom_prof");
        Methodes_personne.Getuserbynom(nom).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    String Type=null;
                    String nomuser = null;
                    String email=null;
                    String tele =null;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Personne p = document.toObject(Personne.class);
                        //Type = p.getType();
                        nomuser=p.getNom();
                        email=p.getEmail();
                        tele=p.getNum_telephone();

                        nom1.setText(nomuser);
                        nom2.setText(nomuser);
                        email2.setText(email);
                        tele2.setText(tele);

                    }

                }}

            });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom11 =nom1.getText().toString();
                String nom22 =nom2.getText().toString();
                String email22 =email2.getText().toString();
                String tele22 =tele2.getText().toString();

                final Personne p= new Personne(nom22,"t1",email22,tele22,"Prof");
                //Methodes_personne.updateUser(nom11,p);
                Methodes_personne.updateUser(nom11,p).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //Personne p = new Personne();
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String idd= document.getId();
                                Methodes_personne.getUsersCollection().document(idd).update("Nom",p.getNom(),"Email",p.getEmail(),"Num_telephone",p.getNum_telephone());
                                Intent in=new Intent(Editprof.this, Listprof.class);
                                startActivity(in);
                                Toast.makeText(getApplicationContext(),"Votre modification a été enregistré avec succe :",Toast.LENGTH_SHORT).show();
                                //p = document.toObject(Personne.class);
                                //ps.add(p);
                                //System.out.println("le nom ="+p.getNom());
                            }
                        }
                    }
                });
            }
        });



    }
}
