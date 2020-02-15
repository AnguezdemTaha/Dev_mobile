package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Listevent extends AppCompatActivity {

    private ImageView text,text2,text3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event1);
        getSupportActionBar().hide();
        text=(ImageView) findViewById(R.id.evenvoir);
        text2=(ImageView) findViewById(R.id.evenmodifier);
        text3=(ImageView) findViewById(R.id.evenadd);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listevent.this, Editevent.class);
                startActivity(in);
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listevent.this, Editevent.class);
                startActivity(in);
            }
        });
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listevent.this,Addevent.class);
                startActivity(in);
            }
        });
    }
}
