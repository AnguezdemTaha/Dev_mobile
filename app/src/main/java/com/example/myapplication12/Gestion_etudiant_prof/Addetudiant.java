package com.example.myapplication12.Gestion_etudiant_prof;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Messagerie.Addmessage;
import com.example.myapplication12.Messagerie.Listmessage;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.AddEmploit;
import com.example.myapplication12.Scolarité.Addcours;
import com.example.myapplication12.Scolarité.Emploit;
import com.example.myapplication12.Scolarité.Listcours;
import com.example.myapplication12.Services.Methodes_personne;
import com.google.gson.Gson;

public class Addetudiant extends AppCompatActivity {

    Menu menuitem;
    private TextView text,text2,text3,title_pe;
    private ImageView scolarete1, messages1, evenement1;
    private EditText nom1,email1,tele1,pass1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_prof);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Ajouter un etudiant");


        scolarete1 = (ImageView) findViewById(R.id.Scolarite7);
        messages1 = (ImageView) findViewById(R.id.messages7);
        evenement1 = (ImageView) findViewById(R.id.evenement7);
        scolarete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Addetudiant.this, Listcours.class);
                startActivity(in);
            }
        });
        messages1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Addetudiant.this, Listmessage.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Addetudiant.this, Listevent.class);
                startActivity(in);
            }
        });

        //text=(TextView) findViewById(R.id.Login1);
        //text2=(TextView) findViewById(R.id.signup);
        text3=(TextView) findViewById(R.id.ajouterprof);

        nom1=(EditText) findViewById(R.id.nom_proff);
        email1=(EditText) findViewById(R.id.email_prof);
        tele1=(EditText) findViewById(R.id.tele_prof);
        pass1=(EditText) findViewById(R.id.mot_de_passe11);
        //title_pe=(TextView) findViewById(R.id.title_pe);

        //title_pe.setText("Add Etudiant");

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exm_menu,menu);

        menuitem=menu;
        MenuItem itm1 = menuitem.findItem(R.id.item1);
        MenuItem itm2 = menuitem.findItem(R.id.item2);
        MenuItem itm3 = menuitem.findItem(R.id.item3);
        MenuItem itm4 = menuitem.findItem(R.id.item4);
        MenuItem itm5 = menuitem.findItem(R.id.item5);
        MenuItem itm6 = menuitem.findItem(R.id.item6);
        MenuItem itm7 = menuitem.findItem(R.id.item7);
        MenuItem itm8 = menuitem.findItem(R.id.item8);
        MenuItem itm9 = menuitem.findItem(R.id.item9);
        MenuItem itm10 = menuitem.findItem(R.id.item10);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        final Personne p1 = gson.fromJson(json, Personne.class);
        if(p1.getType().equals("Prof")){
            itm1.setVisible(false);
            itm2.setVisible(false);
            itm7.setVisible(false);
            itm8.setVisible(false);

            itm3.setVisible(false);
            itm5.setVisible(false);


        }
        else{
            if(p1.getType().equals("Etudiant")){
                itm1.setVisible(false);
                itm2.setVisible(false);
                itm7.setVisible(false);
                itm8.setVisible(false);

                itm3.setVisible(false);
                itm5.setVisible(false);

                itm4.setVisible(false);
            }
            else{
                if(p1.getType().equals("Delegue")){
                    itm1.setVisible(false);
                    itm2.setVisible(false);
                    itm7.setVisible(false);
                    itm8.setVisible(false);

                    //itm3.setVisible(false);
                    itm5.setVisible(false);

                    itm4.setVisible(false);
                }
            }
        }

        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Intent in = new Intent(Addetudiant.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(Addetudiant.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(Addetudiant.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(Addetudiant.this, Addcours.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(Addetudiant.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(Addetudiant.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(Addetudiant.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(Addetudiant.this, Addprof.class);
                startActivity(in8);
                break;
            case R.id.item9:
                Intent in9 = new Intent(Addetudiant.this, Emploit.class);
                startActivity(in9);
                break;
            case R.id.item10:
                Intent in10 = new Intent(Addetudiant.this, Login.class);
                startActivity(in10);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

