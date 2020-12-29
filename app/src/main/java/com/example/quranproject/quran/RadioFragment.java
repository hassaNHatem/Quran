package com.example.quranproject.quran;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.quranproject.Base.BaseFragment;
import com.example.quranproject.R;
import com.example.quranproject.quran.API.APIManager;
import com.example.quranproject.quran.API.Model.RadiosItem;
import com.example.quranproject.quran.API.Model.RadiosResponse;
import com.example.quranproject.quran.Adapters.RadiosAdapter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RadioFragment extends BaseFragment {


    public RadioFragment() {
        // Required empty public constructor
    }


    View view;
    RadiosAdapter adapter;
    RecyclerView channelsRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_radio, container, false);
        channelsRecyclerView= view.findViewById(R.id.radios_recycler_view);
        layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false);
        channelsRecyclerView.setLayoutManager(layoutManager);
        adapter = new RadiosAdapter(null);
        channelsRecyclerView.setAdapter(adapter);
        channelsRecyclerView.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(channelsRecyclerView);
        adapter.setOnPlayClickListener(new RadiosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem channel) {
                playChannel(channel.getURL());
            }
        });
        adapter.setOnStopClickListener(new RadiosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem channel) {
                StopPlaying();
            }
        });
        getRadioChannels();


        return view;
    }

    MediaPlayer mediaPlayer;
    public void playChannel(String URL){
        StopPlaying();
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(URL);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });


        }catch (IOException e){
            showMessage(R.string.error,R.string.cannot_play_channel,R.string.ok);
        }


    }
    public void StopPlaying(){
        if(mediaPlayer!=null)
            mediaPlayer.stop();
    }
    void getRadioChannels(){
       showProgressBar();
       APIManager.getApis()
               .getAllRadioChannels()
               .enqueue(new Callback<RadiosResponse>() {
                   @Override
                   public void onResponse(Call<RadiosResponse> call,
                                          Response<RadiosResponse> response) {
                       hideProgressBar();
                       if(response.isSuccessful()){
                           List<RadiosItem> channels= response.body().getRadios();
                           adapter.changeData(response.body().getRadios());
                           adapter = new RadiosAdapter(response.body().getRadios());
                       }

                   }

                   @Override
                   public void onFailure(Call<RadiosResponse> call, Throwable t) {
                       hideProgressBar();
                       showMessage("error",t.getLocalizedMessage(),"ok");
                   }
               });

   }

}
