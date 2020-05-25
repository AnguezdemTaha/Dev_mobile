package com.example.myapplication12.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.Emploit;
import com.example.myapplication12.Scolarité.Listcours;
import com.google.gson.Gson;

public class Menuscolarite extends AppCompatActivity {

    private Button button1,button2,button3,button4,button5;
    private TextView nomuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuscolarite);
        getSupportActionBar().hide();
        button1=(Button) findViewById(R.id.Cours_menu);
        button2=(Button) findViewById(R.id.Emploittemp);
        nomuser=(TextView) findViewById(R.id.nomcurentuser);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        final Personne p1 = gson.fromJson(json, Personne.class);

        nomuser.setText(p1.getNom());

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuscolarite.this, Listcours.class);
                startActivity(in);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuscolarite.this, Emploit.class);
                startActivity(in);
            }
        });
    }
}
