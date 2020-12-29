package com.example.quranproject.quran.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranproject.R;
import com.example.quranproject.quran.API.Model.RadiosItem;


import java.util.List;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 2/4/2019.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */
public class RadiosAdapter extends RecyclerView.Adapter<RadiosAdapter.ViewHolder>{

    List<RadiosItem> channels;
    OnItemClickListener onPlayClickListener;
    OnItemClickListener onStopClickListener;


    public RadiosAdapter(List<RadiosItem> channels) {
        this.channels = channels;
    }

    public void setOnPlayClickListener(OnItemClickListener onPlayClickListener) {
        this.onPlayClickListener = onPlayClickListener;
    }

    public void setOnStopClickListener(OnItemClickListener onStopClickListener) {
        this.onStopClickListener = onStopClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_radio_channel,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final RadiosItem channel=channels.get(position);
        viewHolder.name.setText(channel.getName());
        if(onPlayClickListener!=null){
            viewHolder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPlayClickListener.onItemClick(position,channel);
                }
            });
        }
        if(onStopClickListener!=null){
            viewHolder.stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStopClickListener.onItemClick(position,channel);
                }
            });
        }

    }

  public void changeData(List<RadiosItem> items){
        this.channels = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
       if(channels==null)return 0;
        return channels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView play,stop;
        public ViewHolder(View view){
            super(view);
            name=view.findViewById(R.id.name);
            play=view.findViewById(R.id.play);
            stop=view.findViewById(R.id.stop);

        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos, RadiosItem channel);
    }
}
