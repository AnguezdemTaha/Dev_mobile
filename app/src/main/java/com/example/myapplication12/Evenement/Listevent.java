package com.example.myapplication12.Evenement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_event;
import com.example.myapplication12.Services.Methodes_personne;
import com.example.myapplication12.Services.MyAdapter;
import com.example.myapplication12.Services.MyAdapterEven;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Listevent extends AppCompatActivity {

    private ImageView text,text2,text3;
    //private LinearLayout t21;
    //private TextView t211,text4;
    private TextView mytext;
    private ImageView addprof;
    public RecyclerView r;
    private Evenement e=new Evenement();
    private Object LayoutManager;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    private LinkedList<Personne> ps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event1);
        getSupportActionBar().hide();


        r = (RecyclerView) findViewById(R.id.listdesevents);



        final LinkedList<Evenement> evns = new LinkedList<Evenement>();
        final Context context = this;
        //final MyAdapter.OnNoteListener note = this;
        Methodes_event.GetAllevents().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //String idd= document.getId();

                        e = document.toObject(Evenement.class);
                        evns.add(e);
                        //System.out.println("le nom ="+p.getNom());
                    }
                    /*SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(nome,n)
                    editor.commit();*/

                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapterEven myAdapter = new MyAdapterEven(evns, context);
                    r.setAdapter(myAdapter);
                    //r.setHasFixedSize(true);
                    //LayoutManager = new LinearLayoutManager(this);
                    //System.out.println("le nom ="+p.getNom());


                } else {

                }
            }


        });

        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        Personne p = gson.fromJson(json, Personne.class);

        text3=(ImageView) findViewById(R.id.evenadd);

        if(p.getType().equals("Prof") || p.getType().equals("Etudiant")){
            text3.setVisibility(View.INVISIBLE);
        }
        //System.out.println("l'utisateur connecter est :" +p.getEmail());





        /*final Date[] date_event = new Date[1];
        final String[] description = new String[1];
        final int[] id = new int[1];
        final String[] nomevent = new String[1];*/
        //final String[] msguser = new String[1];

        //Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        /*Methodes_event.GetAllevents().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int i=0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Evenement e = document.toObject(Evenement.class);
                        //nom[0] =e.getNom_event();
                        //mytext7.setText(nom[0]);

                        nomevent[0] =e.getNom_event();
                        id[0] =e.getId_event();
                        //date_event[0] =e.getDate_event();
                        description[0] =e.getDescription_event();
                        //msguser[0] =m.getContenu_msg();



                        t21 = (LinearLayout) findViewById(R.id.listevent);
                        LayoutInflater v2 = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                        View v = v2.inflate(R.layout.unevent, null, false);
                        t21.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                        t211 = (TextView) findViewById(R.id.nomevent);
                        t211.setText(nomevent[0]);
                        t211.setId(id[0]);
                        //t212 = (TextView) findViewById(R.id.Msguser);
                        //t212.setText(msguser[0]);


                        //System.out.println("here is :"+mytext7.getText().toString());
                        t211.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in=new Intent(Listevent.this, Editevent.class);
                                in.putExtra("id_event",id[0]);
                                in.putExtra("nom_event",nomevent[0]);
                                in.putExtra("discription_event",description[0]);
                                in.putExtra("date_event",date_event[0]);

                                startActivity(in);

                            }
                        });
                    }

                } else {

                }
            }


        });*/



        /*text=(ImageView) findViewById(R.id.evenvoir);
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

         */

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listevent.this, Addevent.class);
                startActivity(in);
            }
        });
    }
}
