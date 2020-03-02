package com.example.myapplication12.Services;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication12.Gestion_etudiant_prof.Editprof;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.MainActivity;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private LinkedList<Personne> ps;
    private Context context;
    private TextView nom;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    private OnNoteListener mOnNoteListener ;

    public MyAdapter(LinkedList<Personne> ps , Context context , OnNoteListener onNoteListener){
        this.ps=new LinkedList<Personne>();

        //????
        this.ps.addAll(ps);
        this.context=context;
        this.mOnNoteListener=onNoteListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemLayoutView=LayoutInflater.from(parent.getContext()).inflate(R.layout.unepersonne , parent,false);
        MyViewHolder vh = new MyViewHolder(itemLayoutView, mOnNoteListener);
        return  vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.nom_e.setText(ps.get(position).getNom());
        //holder.
        //...
       // StorageReference storageReference = FirebaseStorage.getInstance().getReference(ps.get(position).getIma);
        //Glifrapp.with(context.l)
    }

    @Override
    public int getItemCount(){
        return ps.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public  TextView nom_e;
        public ImageView delete;
        public ImageView edit;

        public Context context;
        OnNoteListener onNoteListener;



        public MyViewHolder(View itemLayoutView ,OnNoteListener onNoteListener){
            super(itemLayoutView);
            this.context=context;
            nom_e=itemLayoutView.findViewById(R.id.nompersonne);
            delete=itemLayoutView.findViewById(R.id.delet_personne);
            edit=itemLayoutView.findViewById(R.id.edit_personne);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){
            onNoteListener.OnNoteClick(getAdapterPosition());

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(v.getContext(), Editprof.class);
                    String m=nom_e.getText().toString();
                    in.putExtra("nom_prof", m);

                    Toast.makeText(v.getContext().getApplicationContext(),"Le professeur :"+m,Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(in);
                }});
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    String nom11=nom_e.getText().toString();
                    Methodes_personne.deleteUser(nom11).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            //Personne p = new Personne();
                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String idd= document.getId();
                                    Methodes_personne.getUsersCollection().document(idd).delete();

                                    Intent in=new Intent(v.getContext(), Listprof.class);
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
    public interface OnNoteListener {
        //pour detecter click et position
        void OnNoteClick(int position);
    }

    /*public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.unepersonne, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //...
        ViewHolder vh = new ViewHolder((TextView) v);
        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }*/
}
