package com.source.LOLSN.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hsoumare.learnig_android.R;

import java.util.List;

/**
 * Created by soums on 12/04/2018.
 */

public class MyAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<String> data;
    public MyAdapter(Context context, List<String> data){
        this.context = context;
        this.data = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from ( context ).inflate ( R.layout.example,parent,false );
        return new MyViewHolder ( itemView );

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        String item = data.get ( position );

        ((MyViewHolder) holder).textView.setText ( item );


    }

    @Override
    public int getItemCount() {
        return data.size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        protected TextView textView;
        public MyViewHolder(View itemView) {
            super ( itemView );
            textView= (TextView)itemView.findViewById ( R.id.tv_example_data );
        }
    }
}