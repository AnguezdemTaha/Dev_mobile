package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class Listetudiant extends AppCompatActivity {
    private LinearLayout t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listetudiant);

        getSupportActionBar().hide();

        /*for(int i=0;i<10;i++) {
            t2 = (LinearLayout) findViewById(R.id.etudiant);
            LayoutInflater v2 = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
            View v = v2.inflate(R.layout.unepersonne, null, false);
            t2.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //LinearLayout y2 = (LinearLayout) findViewById(R.id.allmessages);
            //LayoutInflater x2 = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
            //View x = x2.inflate(R.layout.unmsg, null, false);
            //y2.addView(x, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }*/
    }
}
