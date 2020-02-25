package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menuetudiant extends AppCompatActivity {

    private Button button1,button2,button3;
    private TextView nomuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        getSupportActionBar().hide();
        button1=(Button) findViewById(R.id.Messages);
        button2=(Button) findViewById(R.id.Evenements);


        nomuser=(TextView) findViewById(R.id.nomcurentuser);

        Intent in =getIntent();
        String nom=in.getStringExtra("nom_user");

        nomuser.setText(nom);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Listmessage.class);
                startActivity(in);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Menuetudiant.this, Menuevent.class);
                startActivity(in);
            }
        });
    }
}
