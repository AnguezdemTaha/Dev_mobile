package com.example.myapplication12.Scolarité;

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
import android.widget.TextView;

import com.example.myapplication12.Model.Cours;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_cours;
import com.example.myapplication12.Services.MyAdapterCours;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.util.LinkedList;

public class Listcours extends AppCompatActivity {

    private ImageView text,text2,text3,telecharger;
    //private LinearLayout t21;
    //private TextView t211,text4;
    private TextView mytext;
    private ImageView addprof;
    public RecyclerView r;
    private Cours c=new Cours();
    private Object LayoutManager;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    private LinkedList<Personne> ps;
    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listecours);
        getSupportActionBar().hide();



        r = (RecyclerView) findViewById(R.id.listdescours);




        final LinkedList<Cours> cours = new LinkedList<Cours>();
        final Context context = this;
        //final MyAdapter.OnNoteListener note = this;
        Methodes_cours.GetAllcours().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //String idd= document.getId();

                        c = document.toObject(Cours.class);
                        cours.add(c);
                        //System.out.println("le nom ="+p.getNom());
                    }
                    /*SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(nome,n)
                    editor.commit();*/

                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapterCours myAdapter = new MyAdapterCours(cours, context);
                    r.setAdapter(myAdapter);
                    //r.setHasFixedSize(true);
                    //LayoutManager = new LinearLayoutManager(this);
                    //System.out.println("le nom ="+p.getNom());


                } else {

                }
            }


        });
        text3=(ImageView) findViewById(R.id.evenadd);

        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        Personne p = gson.fromJson(json, Personne.class);

        if(p.getType().equals("Etudiant") || p.getType().equals("Delegue")){
            //text3.setVisibility(View.INVISIBLE);

        }
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listcours.this, Addcours.class);
                startActivity(in);
            }
        });


    }

}
