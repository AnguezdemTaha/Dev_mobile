package com.example.myapplication12.Messagerie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.example.myapplication12.Services.MyAdapterEven;
import com.example.myapplication12.Services.MyAdapterMessage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Listmessage extends AppCompatActivity {

    static ArrayList<Message> messages1 = new ArrayList<>();

    private LinearLayout l1, l2, t21;
    private ImageView imageadd;
    private TextView t1, mytext7, t211, t212;
    private ListView t2, t3;
    public RecyclerView r;
    final ArrayList<Message> messages = new ArrayList<>();
    private Object LayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().hide();
        //l1=(LinearLayout) findViewById(R.id.User1);
        //l2=(LinearLayout) findViewById(R.id.User2);
        imageadd = (ImageView) findViewById(R.id.Add_msg);
        t1 = (TextView) findViewById(R.id.Msguser);
        mytext7 = (TextView) findViewById(R.id.nom8);

        //t2 = (ListView) findViewById(R.id.allmessages);
        //Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        //Message m=new Message();
        //m.setPer_envoye(p1);

        final String[] nom = new String[1];
        final String[] nomuser = new String[1];
        final String[] msguser = new String[1];

        r = (RecyclerView) findViewById(R.id.listdesmessages);
        final LinkedList<Message> msgs = new LinkedList<Message>();
        final Context context = this;

        /*Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Methodes_msg_evt_.GetMessagesrecu(p1).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Message m = document.toObject(Message.class);

                        msgs.add(m);

                    }
                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapterMessage myAdapter = new MyAdapterMessage(msgs, context);
                    r.setAdapter(myAdapter);

                } else {

                }
            }



        });*/
        Personne p1 = new Personne("ahmed", "ahmed", "ahmed@gcom", "060666", "Prof");
        Task task1 = Methodes_msg_evt_.GetMessages(p1);
        Task task2 = Methodes_msg_evt_.GetMessagesrecu(p1);
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
                            return o1.getPer_envoye().getNom().compareTo(o2.getPer_envoye().getNom());
                        }


                    });
                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapterMessage myAdapter = new MyAdapterMessage(msgs, context);
                    r.setAdapter(myAdapter);
                } else {

                }
            }
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

                Intent in = new Intent(Listmessage.this, Addmessage.class);
                startActivity(in);
            }
        });


    }
}
