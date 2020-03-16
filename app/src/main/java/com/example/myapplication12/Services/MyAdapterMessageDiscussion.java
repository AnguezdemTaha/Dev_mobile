package com.example.myapplication12.Services;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication12.Messagerie.Discussion;
import com.example.myapplication12.Model.Message;
import com.example.myapplication12.R;

import java.util.LinkedList;

public class MyAdapterMessageDiscussion extends  RecyclerView.Adapter<MyAdapterMessageDiscussion.MyViewHolder>{
    private LinkedList<Message> msgs;
    private Context context;
    private TextView nom;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    //private MyAdapterEven.OnNoteListener mOnNoteListener ;

    public MyAdapterMessageDiscussion(LinkedList<Message> msgs , Context context ){
        this.msgs=new LinkedList<Message>();

        //????
        this.msgs.addAll(msgs);
        this.context=context;
        //this.mOnNoteListener=onNoteListener;
    }
    @Override
    public MyAdapterMessageDiscussion.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemLayoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.unmsgd , parent,false);
        MyAdapterMessageDiscussion.MyViewHolder vh = new MyAdapterMessageDiscussion.MyViewHolder(itemLayoutView);
        return  vh;
    }

    @Override
    public void onBindViewHolder(MyAdapterMessageDiscussion.MyViewHolder holder, int position){
        //holder.nom_e.setText(msgs.get(position).getPer_envoye().getNom());
        holder.contenu.setText(msgs.get(position).getContenu_msg());
        //holder.
        //...
        // StorageReference storageReference = FirebaseStorage.getInstance().getReference(ps.get(position).getIma);
        //Glifrapp.with(context.l)
    }

    @Override
    public int getItemCount(){
        return msgs.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public  TextView nom_e,contenu;



        public Context context;
        com.example.myapplication12.Services.MyAdapter.OnNoteListener onNoteListener;



        public MyViewHolder(View itemLayoutView){
            super(itemLayoutView);
            this.context=context;
            nom_e=itemLayoutView.findViewById(R.id.Nomuser);
            contenu=itemLayoutView.findViewById(R.id.Msguser);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){
            //onNoteListener.OnNoteClick(getAdapterPosition());

            contenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* Intent in=new Intent(v.getContext(), Discussion.class);
                    String m=nom_e.getText().toString();
                    in.putExtra("nom_per_envoye", m);*/


                }});

            /*int itemPosition = RecyclerView.getChildLayoutPosition(v);
            String item = (String) List.get(itemPosition);
            Toast.makeText(context, item, Toast.LENGTH_LONG).show();*/

        }
    }
}
