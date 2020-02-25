package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class Editevent extends AppCompatActivity {
    private TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even2);
        getSupportActionBar().hide();

        t1 =(TextView) findViewById(R.id.nomevent);
        t2 =(TextView) findViewById(R.id.dateevent);
        t3 =(TextView) findViewById(R.id.descriptionevent);
        //t4 =(TextView) findViewById(R.id.nomevent);

        Intent in =getIntent();

        String nom=in.getStringExtra("nom_event");
        String discription=in.getStringExtra("discription_event");
        //Date date=in.getStringExtra("date_event");

        t1.setText("Nom de l'evenement"+nom);
        t3.setText("discription de l'evenemtent"+discription);
        //t2.setText(date);


    }
}
