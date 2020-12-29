package com.example.quranproject.quran;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.quranproject.Base.BaseActivity;
import com.example.quranproject.R;
import com.example.quranproject.quran.API.Model.AzkarItem;
import com.example.quranproject.quran.Adapters.VersesAdapter;

import java.util.ArrayList;

public class AzkarContent extends BaseActivity {
    ArrayList<String> zekr=new ArrayList<String>();
    VersesAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    TextView Title;
    AzkarItem zekrItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azkar_content);
        Title=findViewById(R.id.zekrTitle);
        recyclerView=findViewById(R.id.azkarRecyclerView);
        zekrItem=(AzkarItem) getIntent().getSerializableExtra("item");
        Title.setText(zekrItem.title);
        zekr.add(zekrItem.description);
        adapter=new VersesAdapter(zekr);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
    }
}