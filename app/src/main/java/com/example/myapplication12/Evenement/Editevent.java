package com.example.myapplication12.Evenement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.Model.Professeur;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_event;
import com.example.myapplication12.Services.Methodes_personne;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Editevent extends AppCompatActivity {
    private TextView t1, t2, t3, t4;
    private TextView nom2, date2, description2;
    private TextView nom1, nombreparticipants;
    private ImageView save, imageeven;
    private DatePicker date22;
    private TimePicker time22;
    private CheckBox interese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even2);
        getSupportActionBar().hide();

        nom1 = (TextView) findViewById(R.id.even_nom1);
        nom2 = (TextView) findViewById(R.id.even_nom2);
        date2 = (TextView) findViewById(R.id.dateeven2);
        description2 = (TextView) findViewById(R.id.even_description2);
        imageeven = (ImageView) findViewById(R.id.imageevent);
        save=(ImageView) findViewById(R.id.save_even);



        interese = (CheckBox) findViewById(R.id.interise);

        nombreparticipants = (TextView) findViewById(R.id.nombreparticipant);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        Personne p1 = gson.fromJson(json, Personne.class);

        if(p1.getType().equals("Prof") || p1.getType().equals("Etudiant")){
            save.setVisibility(View.INVISIBLE);
        }


        Bundle extras = getIntent().getExtras();
        String nom = extras.getString("nom_even");
        //String nom="test22";
        Methodes_event.Geteventbynom(nom).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    String Type = null;
                    String nomevn = null;
                    Date dateevn = null;
                    String description = null;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        System.out.println("bonjour succe");
                        final Evenement e = document.toObject(Evenement.class);
                        //Type = p.getType();
                        nomevn = e.getNom_event();
                        //dateevn=e.getDate_event();
                        description = e.getDescription_event();
                        dateevn = e.getDate_event();
                        int i = e.getPer_participes().size();

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                        String date_evn = dateFormat.format(dateevn);

                        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                        StorageReference pathReference = storageReference.child("/events/" + e.getNom_event());
                        File localFile = null;
                        try {
                            localFile = File.createTempFile("images", "jpg");
                        } catch (IOException d) {
                            d.printStackTrace();
                        }

                        final File finalLocalFile = localFile;
                        pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                                imageeven.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                            }
                        });
                        //holder.
                        //...
                        // StorageReference storageReference = FirebaseStorage.getInstance().getReference(ps.get(position).getIma);
                        //Glifrapp.with(context.l)


                        nom1.setText(nomevn);
                        nom2.setText("Nom :" + nomevn);
                        description2.setText("Descriotion :" + description);
                        date2.setText(date_evn);
                        nombreparticipants.setText("nombre de participant:" + i);

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
                        Gson gson = new Gson();
                        String json = pref.getString("personne_c", "");
                        final Personne p1 = gson.fromJson(json, Personne.class);

                        // a voir apres
                        /*if(e.getPer_participes().contains(p1)){
                            interese.setChecked(true);
                            System.out.println("you are interesed xd :");
                        }*/
                        for(Personne p:e.getPer_participes()){
                            if(p.getNom().equals(p1.getNom())){
                                interese.setChecked(true);

                            }
                        }


                        interese.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View arg0) {
                                boolean isChecked = interese.isChecked();


                                final ArrayList<Personne> pers_par = e.getPer_participes();
                                //ArrayList<Personne> pers_par1 =new ArrayList<>();
                                //Personne p1 = new Personne("ahmedxxxyyy", "ahmed", "ahmed@gcom", "060666", "Prof");
                                //for (int i = 0; i < ps.size(); i++) {


                                //System.out.println("voila size malist:"+pers_par1.size()+"....."+pers_par.size());

                                if (isChecked) {

                                    pers_par.add(p1);
                                    Methodes_event.updateevent(e.getNom_event()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            //Personne p = new Personne();
                                            if (task.isSuccessful()) {

                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    String idd = document.getId();
                                                    ArrayList<Map<String, Object>> ps = new ArrayList<Map<String, Object>>();
                                                    for (Personne p :pers_par) {
                                                        Map<String, Object> p1 = new HashMap<>();
                                                        p1.put("nom", p.getNom());
                                                        p1.put("mot_de_passe", p.getMot_de_passe());
                                                        p1.put("email", p.getEmail());
                                                        p1.put("num_telephone", p.getNum_telephone());
                                                        p1.put("type", p.getType());

                                                        ps.add(p1);
                                                    }

                                                    Methodes_event.getUsersCollection().document(idd).update("per_participes", ps);

                                                    Toast.makeText(getApplicationContext(), "Vous etes interisse :", Toast.LENGTH_SHORT).show();
                                                    //p = document.toObject(Personne.class);
                                                    //ps.add(p);
                                                    //System.out.println("le nom ="+p.getNom());
                                                }
                                            }
                                        }
                                    });

                                } else {
                                    pers_par.remove(p1);
                                    Methodes_event.updateevent(e.getNom_event()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            //Personne p = new Personne();
                                            if (task.isSuccessful()) {

                                                ArrayList<Map<String, Object>> ps = new ArrayList<Map<String, Object>>();
                                                for (Personne p :pers_par) {
                                                    Map<String, Object> p1 = new HashMap<>();
                                                    p1.put("nom", p.getNom());
                                                    p1.put("mot_de_passe", p.getMot_de_passe());
                                                    p1.put("email", p.getEmail());
                                                    p1.put("num_telephone", p.getNum_telephone());
                                                    p1.put("type", p.getType());

                                                    ps.add(p1);
                                                }
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    String idd = document.getId();
                                                    Methodes_event.getUsersCollection().document(idd).update("per_participes", ps);

                                                    Toast.makeText(getApplicationContext(), "Vous etes non interesse :", Toast.LENGTH_SHORT).show();
                                                    //p = document.toObject(Personne.class);
                                                    //ps.add(p);
                                                    //System.out.println("le nom ="+p.getNom());
                                                }
                                            }
                                        }
                                    });

                                }


                            }
                        });


                    }

                }
            }

        });


    }
}
