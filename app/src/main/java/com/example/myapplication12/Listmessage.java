package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Listmessage extends AppCompatActivity {

    private LinearLayout l1,l2;
    private ImageView imageadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().hide();
        l1=(LinearLayout) findViewById(R.id.User1);
        l2=(LinearLayout) findViewById(R.id.User2);
        imageadd=(ImageView) findViewById(R.id.Add_msg);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listmessage.this, Discussion.class);
                startActivity(in);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listmessage.this, Discussion.class);
                startActivity(in);
            }
        });
        imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listmessage.this,Addmessage.class);
                startActivity(in);
            }
        });

    }
}
