package com.example.myapplication12.Messagerie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Listmessage extends AppCompatActivity {

     static ArrayList<Message> messages1 =new ArrayList<>();

    private LinearLayout l1,l2,t21;
    private ImageView imageadd;
    private TextView t1,mytext7,t211,t212;
    private ListView t2,t3;
    final  ArrayList<Message> messages =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().hide();
        //l1=(LinearLayout) findViewById(R.id.User1);
        //l2=(LinearLayout) findViewById(R.id.User2);
        imageadd=(ImageView) findViewById(R.id.Add_msg);
        t1=(TextView) findViewById(R.id.Msguser);
        mytext7 = (TextView) findViewById(R.id.nom8);
        /*for(int i=0;i<10;i++) {
            t21 = (ListView) findViewById(R.id.allmessages);
            LayoutInflater v2 = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
            View v = v2.inflate(R.layout.unmsg, null, false);
            t21.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //LinearLayout y2 = (LinearLayout) findViewById(R.id.allmessages);
            //LayoutInflater x2 = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
            //View x = x2.inflate(R.layout.unmsg, null, false);
            //y2.addView(x, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }*/
        //t2 = (ListView) findViewById(R.id.allmessages);
        //Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        //Message m=new Message();
        //m.setPer_envoye(p1);

        final String[] nom = new String[1];
        final String[] nomuser = new String[1];
        final String[] msguser = new String[1];

        Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Methodes_msg_evt_.GetMessages(p1).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Message m = document.toObject(Message.class);
                        nom[0] =m.getPer_envoye().getEmail();
                        //mytext7.setText(nom[0]);

                        nomuser[0] =m.getPer_envoye().getNom();
                        msguser[0] =m.getContenu_msg();



                        t21 = (LinearLayout) findViewById(R.id.allmessages);
                        LayoutInflater v2 = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
                        View v = v2.inflate(R.layout.unmsg, null, false);
                        t21.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                        t211 = (TextView) findViewById(R.id.Nomuser);
                        t211.setText(nomuser[0]);
                        t212 = (TextView) findViewById(R.id.Msguser);
                        t212.setText(msguser[0]);

                        //System.out.println("here is :"+mytext7.getText().toString());
                        /*t212.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in=new Intent(Listmessage.this, Discussion.class);
                                in.putExtra("nom_user",nomuser[0]);
                                in.putExtra("msg_user",msguser[0]);
                                startActivity(in);
                            }
                        });*/
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

        /*System.out.println("here is :"+ nom[0]);

        Message m1=new Message();
        m1.setContenu_msg("msg here");
        m1.setPer_envoye(p1);
        messages.add(m1);
        for (int i = 0; i <messages.size() ; i++) {
            System.out.println("èçèç(çè"+messages.get(i).getContenu_msg());
        }*/


        /*ArrayAdapter<Message> arrayAdapter
                = new ArrayAdapter<Message>(this, android.R.layout.simple_list_item_1 , messages);
        t2.setAdapter(arrayAdapter);*/

        //ArrayList<Message> MS= Methodes_msg_evt_.GetMessages();
        /*Methodes_msg_evt_.GetMessages("ok").addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Message p = document.toObject(Message.class);
                        String nom=p.getContenu_msg();
                        //t1=(TextView)findViewById(R.id.Msg1);

                        //t1.setText(nom);
                    }
                } else {

                }
            }});*/






       /* l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listmessage.this, Discussion.class);
                startActivity(in);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Listmessage.this, Discussion.class);
                startActivity(in);
            }
        });*/
        imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(Listmessage.this, Addmessage.class);
                startActivity(in);
            }
        });



    }
}
