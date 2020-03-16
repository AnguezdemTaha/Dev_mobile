package com.example.myapplication12.Services;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication12.Evenement.Editevent;
import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Gestion_etudiant_prof.Editprof;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


    public class MyAdapterEven extends RecyclerView.Adapter<MyAdapterEven.MyViewHolder> {
        private LinkedList<Evenement> evns;
        private Context context;
        private TextView nom;
        private ImageView i1;
        //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
        //private MyAdapterEven.OnNoteListener mOnNoteListener ;

        public MyAdapterEven(LinkedList<Evenement> evns , Context context ){
            this.evns=new LinkedList<Evenement>();

            //????
            this.evns.addAll(evns);
            this.context=context;
            //this.mOnNoteListener=onNoteListener;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View itemLayoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.unevent , parent,false);
            MyAdapterEven.MyViewHolder vh = new MyAdapterEven.MyViewHolder(itemLayoutView);
            return  vh;
        }

        @Override
        public void onBindViewHolder(final MyAdapterEven.MyViewHolder holder, int position){
            holder.nom_e.setText(evns.get(position).getNom_event());
            holder.description.setText(evns.get(position).getDescription_event());
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            StorageReference pathReference = storageReference.child("/events/"+holder.nom_e.getText().toString());
            File localFile = null;
            try {
                localFile = File.createTempFile("images", "jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }

            final File finalLocalFile = localFile;
            pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                    holder.i1.setImageBitmap(bitmap);
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
        }

        @Override
        public int getItemCount(){
            return evns.size();
        }

        public  static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public  TextView nom_e,description;
            public ImageView delete,i1;
            //public final ImageView i1;
            public ImageView edit;

            public Context context;
            com.example.myapplication12.Services.MyAdapter.OnNoteListener onNoteListener;



            public MyViewHolder(View itemLayoutView){
                super(itemLayoutView);
                this.context=context;
                nom_e=itemLayoutView.findViewById(R.id.even_nom);
                description=itemLayoutView.findViewById(R.id.even_description);
                i1=itemLayoutView.findViewById(R.id.imageevent1);
                delete=itemLayoutView.findViewById(R.id.delet_even);
                edit=itemLayoutView.findViewById(R.id.edit_even);
                this.onNoteListener = onNoteListener;
                itemView.setOnClickListener(this);

            }
            @Override
            public void onClick(View v){
                //onNoteListener.OnNoteClick(getAdapterPosition());

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(v.getContext(), Editevent.class);
                        String m=nom_e.getText().toString();
                        in.putExtra("nom_even", m);

                        Toast.makeText(v.getContext().getApplicationContext(),"L'even :"+m,Toast.LENGTH_SHORT).show();
                        v.getContext().startActivity(in);
                    }});
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        String nom11=nom_e.getText().toString();
                        Methodes_event.deleteEven(nom11).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                //Personne p = new Personne();
                                if (task.isSuccessful()) {

                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String idd= document.getId();
                                        Methodes_event.getUsersCollection().document(idd).delete();

                                        Intent in=new Intent(v.getContext(), Listevent.class);
                                        v.getContext().startActivity(in);

                                        Toast.makeText(v.getContext().getApplicationContext(),"Votre modification a été enregistré avec succe :",Toast.LENGTH_SHORT).show();
                                        //p = document.toObject(Personne.class);
                                        //ps.add(p);
                                        //System.out.println("le nom ="+p.getNom());
                                    }
                                }
                            }
                        });
                    }});
            /*int itemPosition = RecyclerView.getChildLayoutPosition(v);
            String item = (String) List.get(itemPosition);
            Toast.makeText(context, item, Toast.LENGTH_LONG).show();*/

            }
        }
        /*public interface OnNoteListener {
            //pour detecter click et position
            void OnNoteClick(int position);
        }*/
}
