package com.example.quranproject.quran.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranproject.R;


public class QuranSuraAdapter  extends RecyclerView.Adapter<QuranSuraAdapter.ViewHolder>{

    String [] names;
    OnItemClickListener onItemClickListener;


    public QuranSuraAdapter(String[] names) {
        this.names = names;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_sura,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final String name=names[position];
        viewHolder.name.setText(name);
        if(onItemClickListener!=null){
            viewHolder.itemView
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onItemClick(position,name);
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolder(View view){
            super(view);
            name=view.findViewById(R.id.name);

        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos,String name);
    }
}
