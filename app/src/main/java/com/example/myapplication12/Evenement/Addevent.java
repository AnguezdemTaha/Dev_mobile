package com.example.myapplication12.Evenement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_event;

import java.util.ArrayList;
import java.util.Date;

public class Addevent extends AppCompatActivity {

    private EditText nom1,discription1,date1,per1,id1;
    private TextView text3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);
        getSupportActionBar().hide();


        nom1=(EditText) findViewById(R.id.nomeventajout);
        discription1=(EditText) findViewById(R.id.discriptionevenajout);
        //tele1=(EditText) findViewById(R.id.phone);
        //pass1=(EditText) findViewById(R.id.password);
        text3=(TextView) findViewById(R.id.ajoutevent);


        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nom = String.valueOf(nom1.getText());
                String discription_event = String.valueOf(discription1.getText());
                //String tele = String.valueOf(tele1.getText());
                //String pass= String.valueOf(pass1.getText());
                //String type="Etudiant";
                int id_event=2;
                String date_event=null;
                ArrayList<Personne> per_participes= null;


                Evenement e=new Evenement(nom,date_event,discription_event,per_participes);
                Methodes_event.creatEvent(e);
                Toast.makeText(getApplicationContext(), "Votre evenemenet a été ajouter avec succe", Toast.LENGTH_LONG).show();

                Intent in=new Intent(Addevent.this, Listevent.class);
                startActivity(in);
            }
        });
    }
}
