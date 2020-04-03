package com.example.myapplication12.Messagerie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Gestion_etudiant_prof.Addetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Addprof;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.MainActivity;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.Model.Professeur;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.AddEmploit;
import com.example.myapplication12.Scolarité.Addcours;
import com.example.myapplication12.Scolarité.Emploit;
import com.example.myapplication12.Scolarité.Listcours;
import com.example.myapplication12.Services.GetAllContactsOnCompleteListener;
import com.example.myapplication12.Services.Methodes_cours;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.example.myapplication12.Services.Methodes_personne;
import com.example.myapplication12.Services.MyAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.*;

public class Addmessage extends AppCompatActivity implements MyAdapter.OnNoteListener {

    Menu menuitem;
    private LinearLayout t21;
    private TextView t211, t212, msg;
    public RecyclerView r;
    private Object LayoutManager;
    private ImageView envoyer_msg;
    private ImageView scolarete1, messages1, evenement1;


    private Personne p = new Personne();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmessage);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Ajouter un message");


        msg = (TextView) findViewById(R.id.msg);
        envoyer_msg = (ImageView) findViewById(R.id.envoyermsg2);

        r = (RecyclerView) findViewById(R.id.listdespersonnesmsg);

        final LinkedList<Personne> prs = new LinkedList<Personne>();
        final Context context = this;


        final String[] nom = new String[1];
        final String[] nomuser = new String[1];
        //final String[] msguser = new String[1];

        /*Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Personne p2 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Personne p3 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");

        prs.add(p1);prs.add(p2);prs.add(p3);
        r.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
        MyAdapter myAdapter =new MyAdapter(prs,this);
        r.setAdapter(myAdapter);*/

        final MyAdapter.OnNoteListener note = (MyAdapter.OnNoteListener) this;
        Methodes_personne.GetAllUsers().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        p = document.toObject(Personne.class);
                        prs.add(p);
                        //System.out.println("le nom ="+p.getNom());
                    }
                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapter myAdapter = new MyAdapter(prs, context, note);
                    r.setAdapter(myAdapter);


                    //del=(ImageView) findViewById(R.id.delet_personne);
                    //del.setVisibility(View.INVISIBLE);


                } else {

                }
            }


        });

        envoyer_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("test", MODE_PRIVATE);
                ArrayList<Personne> pers = new ArrayList<>();
                //5 nbr users in list checked
                /*for (int i = 0; i < pref.getAll().size(); i++) {
                    String nom = "nom" + i;
                    nom = pref.getString("nom" + i, String.valueOf(false));
                    String email=pref.getString("email" + i, String.valueOf(false));
                    String tele=pref.getString("tele" + i, String.valueOf(false));
                    String mot_de_pass=pref.getString("mot_de_pass" + i, String.valueOf(false));
                    String type=pref.getString("type" + i, String.valueOf(false));
                    Personne p=new Personne(nom,mot_de_pass,email,tele,type);

                    System.out.println("les utilisateur de la list :" + nom+mot_de_pass);
                    pers.add(p);

                }*/
                Gson gson = new Gson();
                String json = pref.getString("ok", "");
                //!!!! to get type(class) of list personne
                Type type = new TypeToken<ArrayList<Personne>>() {
                }.getType();
                ArrayList<Personne> obj = gson.fromJson(json, type);
                for (Personne p : obj) {
                    System.out.println("les utilisateur de la list :" + p.getEmail());
                }


                context.getSharedPreferences("test", 0).edit().clear().commit();


                String contenu = String.valueOf(msg.getText());

                //Professeur p2 = new Professeur("ahmedxxx", "ahmed", "ahmed@gcom", "060666", "Prof");
                SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                Gson gson1 = new Gson();
                String json1 = pref1.getString("personne_c", "");
                final Personne p1 = gson1.fromJson(json1, Personne.class);
                //Professeur p1 = new Professeur("tarik", "rachid", "tarik@gcom", "0606466", "Prof");

                //String tele = String.valueOf(tele1.getText());
                //String pass= String.valueOf(pass1.getText());
                //String type="Etudiant";
                Date currentTime = Calendar.getInstance().getTime();

                ArrayList<Personne> ps = new ArrayList<>();
                //ps.add(p2);
                for (Personne p : obj) {
                    ps.add(p);
                }

                Date date_msg = null;
                for (Personne p3 : ps) {
                    ArrayList<Personne> p4 = new ArrayList<>();
                    p4.add(p3);
                    Message m = new Message(currentTime, contenu, p1, p4);
                    Methodes_msg_evt_.creatMessage(m);
                }

                Toast.makeText(getApplicationContext(), "Votre message a été ajouter avec succe", Toast.LENGTH_LONG).show();
                Intent in = new Intent(Addmessage.this, Listmessage.class);
                startActivity(in);

            }
        });
        // Methodes_personne.GetAllUsers(new GetAllContactsOnCompleteListener());



        /*ArrayList<Personne> ps=new ArrayList<>();
        ps=Methodes_personne.getallUsers1();
        for(Personne p:ps){
            System.out.println("le nom ="+p.getNom());
        }*/


        //Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        /*Methodes_personne.GetAllUsers().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Personne p = document.toObject(Personne.class);
                        nom[0] =p.getNom();
                        //mytext7.setText(nom[0]);

                        nomuser[0] =p.getNom();
                        //msguser[0] =m.getContenu_msg();



                        t21 = (LinearLayout) findViewById(R.id.listdespersonnesmsg);
                        LayoutInflater v2 = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                        View v = v2.inflate(R.layout.unepersonnemsg, null, false);
                        t21.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                        t211 = (TextView) findViewById(R.id.nompersonnemsg);
                        t211.setText(nomuser[0]);
                        //t212 = (TextView) findViewById(R.id.Msguser);
                        //t212.setText(msguser[0]);

                        //System.out.println("here is :"+mytext7.getText().toString());
                    }

                } else {

                }
            }

       });*/

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
                Intent in = new Intent(Addmessage.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(Addmessage.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(Addmessage.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(Addmessage.this, Addcours.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(Addmessage.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(Addmessage.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(Addmessage.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(Addmessage.this, Addprof.class);
                startActivity(in8);
                break;
            case R.id.item9:
                Intent in9 = new Intent(Addmessage.this, Emploit.class);
                startActivity(in9);
                break;
            case R.id.item10:
                Intent in10 = new Intent(Addmessage.this, Login.class);
                startActivity(in10);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OnNoteClick(int position) {

    }
}
