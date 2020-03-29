package com.example.myapplication12.Gestion_etudiant_prof;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_personne;
import com.example.myapplication12.Services.MyAdapter;
import com.example.myapplication12.Services.MyAdapterE;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.LinkedList;

public class Listetudiant extends AppCompatActivity implements MyAdapterE.OnNoteListener{
    private LinearLayout t2;

    private TextView mytext;
    public RecyclerView r;
    private Personne p=new Personne();
    private Object LayoutManager;
    private ImageView addetudiant;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    private LinkedList<Personne> ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listetudiant);
        getSupportActionBar().hide();
        addetudiant= (ImageView) findViewById(R.id.add_etudiant);


        r=(RecyclerView) findViewById(R.id.listdesetudiants);



        final LinkedList<Personne> ps =new LinkedList<Personne>();
        final Context context=this;

        final MyAdapterE.OnNoteListener note = (MyAdapterE.OnNoteListener) this;
        Methodes_personne.GetAllEtudiants().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                    MyAdapterE myAdapterE =new MyAdapterE(ps,context,note);
                    r.setAdapter(myAdapterE);
                    //r.setHasFixedSize(true);
                    //LayoutManager = new LinearLayoutManager(this);
                    //System.out.println("le nom ="+p.getNom());


                } else {

                }
            }


        });
        addetudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Listetudiant.this, Addetudiant.class);
                startActivity(in);
            }
        });
    }

    @Override
    public void OnNoteClick(int position) {

    }
}
