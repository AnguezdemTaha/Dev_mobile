package com.example.myapplication12.Gestion_etudiant_prof;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_personne;

public class Addetudiant extends AppCompatActivity {

    private TextView text,text2,text3,title_pe;
    private EditText nom1,email1,tele1,pass1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_prof);
        getSupportActionBar().hide();

        //text=(TextView) findViewById(R.id.Login1);
        //text2=(TextView) findViewById(R.id.signup);
        text3=(TextView) findViewById(R.id.ajouterprof);

        nom1=(EditText) findViewById(R.id.nom_proff);
        email1=(EditText) findViewById(R.id.email_prof);
        tele1=(EditText) findViewById(R.id.tele_prof);
        pass1=(EditText) findViewById(R.id.mot_de_passe11);
        title_pe=(TextView) findViewById(R.id.title_pe);

        title_pe.setText("Add Etudiant");

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nom = String.valueOf(nom1.getText());
                String email = String.valueOf(email1.getText());
                String tele = String.valueOf(tele1.getText());
                String pass= String.valueOf(pass1.getText());

                String type="Etudiant";

                Personne p=new Personne(nom,pass,email,tele,type);
                Methodes_personne.createUser(p);
                Toast.makeText(getApplicationContext(), "Vous avez ajouter un etudaint avec succe", Toast.LENGTH_LONG).show();

                Intent in=new Intent(Addetudiant.this, Listetudiant.class);
                startActivity(in);
            }
        });
    }
}

