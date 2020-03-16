package com.example.myapplication12.Services;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Messagerie.Discussion;
import com.example.myapplication12.Model.Evenement;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.LinkedList;

public class MyAdapterMessage extends RecyclerView.Adapter<MyAdapterMessage.MyViewHolder> {
    private LinkedList<Message> msgs;
    private Context context;
    private TextView nom;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    //private MyAdapterEven.OnNoteListener mOnNoteListener ;

    public MyAdapterMessage(LinkedList<Message> msgs, Context context) {
        this.msgs = new LinkedList<Message>();

        //????
        this.msgs.addAll(msgs);
        this.context = context;
        //this.mOnNoteListener=onNoteListener;
    }

    @Override
    public MyAdapterMessage.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.unmsg, parent, false);
        MyAdapterMessage.MyViewHolder vh = new MyAdapterMessage.MyViewHolder(itemLayoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapterMessage.MyViewHolder holder, int position) {
        holder.nom_e.setText(msgs.get(position).getPer_envoye().getNom());
        holder.contenu.setText(msgs.get(position).getContenu_msg());

        //ahmed c'est le partenaire du session
        if (msgs.get(position).getPer_envoye().getNom().equals("ahmed") && (context instanceof Discussion)) {
            holder.contenu.setBackgroundColor(Color.rgb(200, 200, 200));
        }
        //holder.
        //...
        // StorageReference storageReference = FirebaseStorage.getInstance().getReference(ps.get(position).getIma);
        //Glifrapp.with(context.l)
    }

    @Override
    public int getItemCount() {
        return msgs.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nom_e, contenu;


        public Context context;
        com.example.myapplication12.Services.MyAdapter.OnNoteListener onNoteListener;


        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            this.context = context;
            nom_e = itemLayoutView.findViewById(R.id.Nomuser);
            contenu = itemLayoutView.findViewById(R.id.Msguser);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //onNoteListener.OnNoteClick(getAdapterPosition());

            contenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(v.getContext(), Discussion.class);
                    String m = nom_e.getText().toString();
                    in.putExtra("nom_per_envoye", m);
                    v.getContext().startActivity(in);

                }
            });
            nom_e.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(v.getContext(), Discussion.class);
                    String m = nom_e.getText().toString();
                    in.putExtra("nom_per_envoye", m);
                    v.getContext().startActivity(in);

                }
            });

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
