package com.example.quranproject.quran;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.example.quranproject.Base.BaseFragment;
import com.example.quranproject.R;
import com.example.quranproject.quran.Adapters.QuranSuraAdapter;


public class QuranFragment extends BaseFragment {


    protected View rootView;
    protected RecyclerView recyclerView;

    public QuranFragment() {
        // Required empty public constructor
    }


    QuranSuraAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_quran, container, false);
        initView(rootView);
        adapter = new QuranSuraAdapter(DataHolder.ArSuras);
        layoutManager = new GridLayoutManager(activity,3, LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        SnapHelper snapHelper= new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new QuranSuraAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, String name) {
//                SuraContent.name=name;
//                SuraContent.pos=pos;
                Intent intent = new Intent(activity,SuraContent.class);
                intent.putExtra("sura_name",name);
                intent.putExtra("file_name",(pos+1)+".txt");
                startActivity(intent);
            }
        });


        return rootView;
    }

    private void initView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
    }
}
