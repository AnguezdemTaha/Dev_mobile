package com.example.myapplication12.Messagerie;

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
import android.widget.Toast;

import com.example.myapplication12.MainActivity;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.Model.Professeur;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.GetAllContactsOnCompleteListener;
import com.example.myapplication12.Services.Methodes_cours;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.example.myapplication12.Services.Methodes_personne;
import com.example.myapplication12.Services.MyAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import static androidx.recyclerview.widget.RecyclerView.*;

public class Addmessage extends AppCompatActivity implements MyAdapter.OnNoteListener {

    private LinearLayout t21;
    private TextView t211, t212, msg;
    public RecyclerView r;
    private Object LayoutManager;
    private ImageView envoyer_msg;


    private Personne p = new Personne();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmessage);
        getSupportActionBar().hide();

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
                ArrayList<Personne> pers=new ArrayList<>();
                //5 nbr users in list checked
                for (int i = 0; i <5 ; i++) {
                    String nom="nom"+i;
                    nom =pref.getString("nom"+i, String.valueOf(false));
                    System.out.println("les utilisateur de la list :"+nom);
                }
                String contenu = String.valueOf(msg.getText());

                Professeur p2 = new Professeur("ahmedxxx", "ahmed", "ahmed@gcom", "060666", "Prof");
                Professeur p1 = new Professeur("tarik", "rachid", "tarik@gcom", "0606466", "Prof");
                //String tele = String.valueOf(tele1.getText());
                //String pass= String.valueOf(pass1.getText());
                //String type="Etudiant";
                Date currentTime = Calendar.getInstance().getTime();

                ArrayList<Personne> ps = new ArrayList<>();
                ps.add(p2);

                Date date_msg = null;
                Message m = new Message(currentTime, contenu, p1, ps);
                Methodes_msg_evt_.creatMessage(m);
                Toast.makeText(getApplicationContext(), "Votre message a été ajouter avec succe", Toast.LENGTH_LONG).show();
                Intent in = new Intent(Addmessage.this, Addmessage.class);
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

    public void OnNoteClick(int position) {

    }
}
