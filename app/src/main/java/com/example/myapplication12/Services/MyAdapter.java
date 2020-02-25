package com.example.myapplication12.Services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;

import java.util.LinkedList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private LinkedList<Personne> ps;
    private Context context;
    private TextView nom;

    public MyAdapter(LinkedList<Personne> ps , Context context){
        this.ps=new LinkedList<Personne>();

        //????
        this.ps.addAll(ps);
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemLayoutView=LayoutInflater.from(parent.getContext()).inflate(R.layout.unepersonne , parent,false);
        MyViewHolder vh = new MyViewHolder(itemLayoutView);
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
        public Context context;

        public MyViewHolder(View itemLayoutView){
            super(itemLayoutView);
            this.context=context;
            nom_e=itemLayoutView.findViewById(R.id.nompersonne);

        }
        @Override
        public void onClick(View v){

        }
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
