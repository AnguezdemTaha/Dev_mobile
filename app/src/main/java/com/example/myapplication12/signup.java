package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class signup extends AppCompatActivity {

    private  TextView text,text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        text=(TextView) findViewById(R.id.Login1);
        text2=(TextView) findViewById(R.id.signup);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(signup.this, Login.class);
                startActivity(in);
            }
        });
        /*text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                String Nom="test1";
                String Email="test1@g.com";
                String Num_telephone="0654575847";
                String Mot_de_passe="test1";
                //Exm.createUser(var);
                Personne test1 = new Personne(Nom,Email,Num_telephone,Mot_de_passe);
                db1.collection("Personne").add(test1);
                Intent in=new Intent(signup.this,Login.class);
                startActivity(in);
            }
        });*/

    }
}
