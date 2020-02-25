package com.example.myapplication12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.Services.Methodes_event;
import com.example.myapplication12.Services.Methodes_personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

public class Listevent extends AppCompatActivity {

    private ImageView text,text2,text3;
    private LinearLayout t21;
    private TextView t211,text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event1);
        getSupportActionBar().hide();

        final Date[] date_event = new Date[1];
        final String[] description = new String[1];
        final int[] id = new int[1];
        final String[] nomevent = new String[1];
        //final String[] msguser = new String[1];

        //Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Methodes_event.GetAllevents().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

                    /*
                    @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Personne p = documentSnapshot.toObject(Personne.class);
                            String nom=p.getNom();
                            mytext=(TextView)findViewById(R.id.t);

                            mytext.setText(nom);


                        }*/

        });



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
        text3=(ImageView) findViewById(R.id.evenadd);
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listevent.this,Addevent.class);
                startActivity(in);
            }
        });
    }
}
