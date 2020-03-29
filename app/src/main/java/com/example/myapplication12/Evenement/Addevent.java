package com.example.myapplication12.Evenement;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Scolarité.Addcours;
import com.example.myapplication12.Services.Methodes_event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.SystemParameterOrBuilder;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Addevent extends AppCompatActivity {

    private EditText nom1,discription1,date1,per1,id1;
    private TextView text3;
    private ImageView i1,b1;

    private DatePicker p1;
    private TimePicker t1;

    private Uri filePath;
    FirebaseStorage storage;
    StorageReference storageReference;

    private final int PICK_IMAGE_REQUEST = 71;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);
        getSupportActionBar().hide();

        i1=(ImageView) findViewById(R.id.imageevent2);
        b1=(ImageView) findViewById(R.id.imageajouterevn);

        p1=(DatePicker) findViewById(R.id.datePicker1);
        t1=(TimePicker) findViewById(R.id.datePicker2);

        nom1=(EditText) findViewById(R.id.nomeventajout);
        discription1=(EditText) findViewById(R.id.discriptionevenajout);
        //tele1=(EditText) findViewById(R.id.phone);
        //pass1=(EditText) findViewById(R.id.password);
        text3=(TextView) findViewById(R.id.ajoutevent);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        storageReference =  FirebaseStorage.getInstance().getReference();




        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Calendar calendarA = Calendar.getInstance();
                //calendarA.setTime();
                //Calendar calendarB = Calendar.getInstance();
                //calendarB.setTime();
                calendarA.set(Calendar.YEAR, p1.getYear());
                calendarA.set(Calendar.MONTH, p1.getMonth());
                calendarA.set(Calendar.DAY_OF_MONTH, p1.getDayOfMonth());

                calendarA.set(Calendar.HOUR_OF_DAY, t1.getHour());
                calendarA.set(Calendar.MINUTE, t1.getMinute());
                //calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));
                //calendarA.set(Calendar.MILLISECOND, calendarB.get(Calendar.MILLISECOND));

                Date result = calendarA.getTime();
                System.out.print("bonojout voila la date :"+result.getTime());

                String nom = String.valueOf(nom1.getText());
                String discription_event = String.valueOf(discription1.getText());
                //String tele = String.valueOf(tele1.getText());
                //String pass= String.valueOf(pass1.getText());
                //String type="Etudiant";
                int id_event=2;
                Date date_event=result;
                String image =nom;
                ArrayList<Personne> per_participes= new ArrayList<>();
                Personne p1 = new Personne("ahmedxxx", "ahmed", "ahmed@gcom", "060666", "Prof");
                per_participes.add(p1);


                Evenement e=new Evenement(nom,date_event,discription_event,image,per_participes);
                Methodes_event.creatEvent(e);
                uploadImage(nom);
                Toast.makeText(getApplicationContext(), "Votre evenemenet a été ajouter avec succe", Toast.LENGTH_LONG).show();

                Intent in=new Intent(Addevent.this, Listevent.class);
                startActivity(in);
            }
        });
    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        //intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                i1.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage(String contenu) {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("events/"+ contenu);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Addevent.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Addevent.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}
