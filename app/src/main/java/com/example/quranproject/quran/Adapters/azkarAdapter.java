package com.example.quranproject.quran.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranproject.R;
import com.example.quranproject.quran.API.Model.AzkarItem;

import java.util.ArrayList;

public class azkarAdapter extends RecyclerView.Adapter<azkarAdapter.ViewHolder> {

    ArrayList<AzkarItem> list;

    public azkarAdapter(ArrayList<AzkarItem> list) {
        this.list = list;
    }

    OnItemClickListner onItemClickListner = null;

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.azkar_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final AzkarItem azkarItem = list.get(position);
        holder.Title.setText(azkarItem.title);
       if(onItemClickListner!=null){
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   onItemClickListner.onItemClickListner(azkarItem,position);
               }
           });
       }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public interface OnItemClickListner {
        void onItemClickListner(AzkarItem azkarItem, int position);

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title, Description;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.azkarTitle);

        }
    }
}
