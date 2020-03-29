package com.example.myapplication12.Gestion_etudiant_prof;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.MainActivity;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.example.myapplication12.Services.Methodes_personne;
import com.example.myapplication12.Services.MyAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.LinkedList;

public class Listprof extends AppCompatActivity implements MyAdapter.OnNoteListener {

    private TextView mytext;
    private ImageView addprof;
    public RecyclerView r;
    private  Personne p=new Personne();
    private Object LayoutManager;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    private LinkedList<Personne> ps;
    public static final String MyPREFERENCES = "MyPrefs" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeprofs);
        getSupportActionBar().hide();

        addprof = (ImageView) findViewById(R.id.addprof);
        r = (RecyclerView) findViewById(R.id.listdesprofs);

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final LinkedList<Personne> ps = new LinkedList<Personne>();
        final Context context = this;
        final MyAdapter.OnNoteListener note = this;
        Methodes_personne.GetAllProfs().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //String idd= document.getId();

                        p = document.toObject(Personne.class);
                        ps.add(p);
                        //System.out.println("le nom ="+p.getNom());
                    }
                    /*SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(nome,n)
                    editor.commit();*/

                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapter myAdapter = new MyAdapter(ps, context, note);
                    r.setAdapter(myAdapter);
                    //r.setHasFixedSize(true);
                    //LayoutManager = new LinearLayoutManager(this);
                    //System.out.println("le nom ="+p.getNom());


                } else {

                }
            }


        });

        addprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Listprof.this, Addprof.class);
                startActivity(in);
            }
        });
    }


    @Override
    public void OnNoteClick(int position) {



        /*mytext=(TextView)findViewById(R.id.nompersonne);
        String m= mytext.getText().toString();

        mytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
                Toast.makeText(getApplicationContext(),"Hello je suis le nom",Toast.LENGTH_SHORT).show();

                    }

                    /*
                    @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Personne p = documentSnapshot.toObject(Personne.class);
                            String nom=p.getNom();
                            mytext=(TextView)findViewById(R.id.t);

                            mytext.setText(nom);


                        }*/
                //});




        //String nom= mytext.getText();
        //Toast.makeText(getApplicationContext(),"Hello"+m+position,Toast.LENGTH_SHORT).show();
        //ps.get(position);

        //Intent in=new Intent(Listprof.this, Editprof.class);
        //in.putExtra("t1",nom);
        //startActivity(in);
    }
}
