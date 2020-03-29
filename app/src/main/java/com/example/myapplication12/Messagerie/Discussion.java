package com.example.myapplication12.Messagerie;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication12.MainActivity;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.Model.Professeur;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.example.myapplication12.Services.Methodes_personne;
import com.example.myapplication12.Services.MyAdapterMessage;
import com.example.myapplication12.Services.MyAdapterMessageDiscussion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Discussion extends AppCompatActivity {

    static ArrayList<Message> messages1 = new ArrayList<>();

    private LinearLayout l1, l2, t21;
    private ImageView imageadd;
    private TextView t1, nom_recu,mytext7, t211, t212;
    private ListView t2, t3;
    public RecyclerView r;
    final ArrayList<Message> messages = new ArrayList<>();
    private Object LayoutManager;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        getSupportActionBar().hide();

        imageadd = (ImageView) findViewById(R.id.envoyermsg);
        t1 = (TextView) findViewById(R.id.messageaenvoye);


        r = (RecyclerView) findViewById(R.id.listdesmessagesd);
        final LinkedList<Message> msgs = new LinkedList<Message>();
        final Context context = this;

        SharedPreferences pref3 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson3 = new Gson();
        String json3 = pref3.getString("personne_c", null);
        Personne pc = gson3.fromJson(json3, Personne.class);

        /*SharedPreferences pref4 = getApplicationContext().getSharedPreferences("personne_recu", MODE_PRIVATE);
        Gson gson4 = new Gson();
        String json4 = pref3.getString("personne_re", null);
        Personne p4 = gson3.fromJson(json3, Personne.class);*/

        String nomuser = null;


        Bundle extras = getIntent().getExtras();
        String nomuser1 = extras.getString("nom_per_envoye");
        String nomuser2 = extras.getString("nom_per_recu");

        if (nomuser1.equals(pc.getNom())) {
            nomuser = nomuser2;
        } else {
            nomuser = nomuser1;
            System.out.println("wtfffff :");
        }
        /* else {
             nomuser =p4.getNom();
        }*/

        //}
        //System.out.println("..............this......." + nomuser);
        nom_recu=(TextView) findViewById(R.id.nom_recu);
        nom_recu.setText(nomuser);


        Methodes_personne.Getuserbynom(nomuser).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Personne p2 = document.toObject(Personne.class);

                        pref = getApplicationContext().getSharedPreferences("personne_recu", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(p2);
                        editor.putString("personne_re", json);
                        editor.commit();

                        SharedPreferences pref1 = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                        Gson gson1 = new Gson();
                        String json1 = pref1.getString("personne_c", "");
                        final Personne p1 = gson1.fromJson(json1, Personne.class);


                        //Personne p1 = new Personne("rachid", "rachid", "rachid@gcom", "060666", "Prof");
                        Task task1 = Methodes_msg_evt_.GetMessages1(p1, p2);
                        Task task2 = Methodes_msg_evt_.GetMessages1(p2, p1);
                        Task<List<QuerySnapshot>> alltask = Tasks.whenAllSuccess(task1, task2);
                        alltask.addOnCompleteListener(new OnCompleteListener<List<QuerySnapshot>>() {
                            @Override
                            public void onComplete(@NonNull Task<List<QuerySnapshot>> task) {
                                if (task.isSuccessful()) {
                                    for (QuerySnapshot document : task.getResult()) {
                                        for (QueryDocumentSnapshot document1 : document) {
                                            Message m = document1.toObject(Message.class);

                                            msgs.add(m);

                                        }


                                    }
                                    Collections.sort(msgs, new Comparator<Message>() {
                                        @Override
                                        public int compare(Message o1, Message o2) {
                                            return o1.getDate_msg().compareTo(o2.getDate_msg());
                                        }


                                    });
                                    r.setHasFixedSize(true);
                                    LayoutManager = new LinearLayoutManager(context);
                                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                                    MyAdapterMessageDiscussion myAdapter = new MyAdapterMessageDiscussion(msgs, context);
                                    r.setAdapter(myAdapter);
                                } else {

                                }
                            }
                        });


                    }
                } else {

                }


            }
        });

        imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Professeur p2 = new Professeur("ahmedxxx", "ahmed", "ahmed@gcom", "060666", "Prof");

                SharedPreferences pref3 = getApplicationContext().getSharedPreferences("personne_recu", MODE_PRIVATE);
                Gson gson3 = new Gson();
                String json3 = pref3.getString("personne_re", "");
                Personne p2 = gson3.fromJson(json3, Personne.class);


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
                ps.add(p2);


                Date date_msg = null;
                Message m = new Message(currentTime, t1.getText().toString(), p1, ps);
                Methodes_msg_evt_.creatMessage(m);
                Toast.makeText(getApplicationContext(), "Votre message a été ajouter avec succe", Toast.LENGTH_LONG).show();
                Intent in = new Intent(Discussion.this, Discussion.class);
                String m1 = p1.getNom();
                String m2 =p2.getNom();
                in.putExtra("nom_per_envoye", m1);
                in.putExtra("nom_per_recu", m2);
                startActivity(in);
            }
        });


    }
}
