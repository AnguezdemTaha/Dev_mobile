package com.example.myapplication12.Messagerie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication12.Model.Message;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.Model.Professeur;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_msg_evt_;
import com.example.myapplication12.Services.MyAdapterMessage;
import com.example.myapplication12.Services.MyAdapterMessageDiscussion;
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

public class Discussion extends AppCompatActivity {

    static ArrayList<Message> messages1 =new ArrayList<>();

    private LinearLayout l1,l2,t21;
    private ImageView imageadd;
    private TextView t1,mytext7,t211,t212;
    private ListView t2,t3;
    public RecyclerView r;
    final  ArrayList<Message> messages =new ArrayList<>();
    private Object LayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        getSupportActionBar().hide();


        r = (RecyclerView) findViewById(R.id.listdesmessagesd);
        final LinkedList<Message> msgs = new LinkedList<Message>();
        final Context context = this;

        Personne p1 =new Personne("ahmed","ahmed","ahmed@gcom","060666","Prof");
        Personne p2 =new Personne("rachid", "rachid", "rachid@gcom", "060666", "Prof");
        Task task1 = Methodes_msg_evt_.GetMessages1(p1,p2);
        Task task2 = Methodes_msg_evt_.GetMessages1(p2,p1);
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


    }
}
