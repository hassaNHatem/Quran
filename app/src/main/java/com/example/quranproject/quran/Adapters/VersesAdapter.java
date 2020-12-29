package com.example.quranproject.quran.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranproject.R;

import java.util.List;


public class VersesAdapter extends RecyclerView.Adapter<VersesAdapter.ViewHolder>{

    List<String> verses;
    OnItemClickListener onItemClickListener;


    public VersesAdapter(List<String> verses) {
        this.verses = verses;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_verse,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final String name=verses.get(position);
        viewHolder.text.setText(name+"{"+(position+1)+"}");
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
        return verses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public ViewHolder(View view){
            super(view);
            text=view.findViewById(R.id.text);

        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos, String name);
    }
}
