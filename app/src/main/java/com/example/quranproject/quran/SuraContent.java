package com.example.quranproject.quran;

import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranproject.Base.BaseActivity;
import com.example.quranproject.R;
import com.example.quranproject.quran.Adapters.VersesAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SuraContent extends BaseActivity {
    protected RecyclerView recyclerView;

    /*public static int pos;
    public static String name;*/

    ArrayList<String> AllLines;
    VersesAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sura_content);
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        String suraName = getIntent().getStringExtra("sura_name");
        String fileName = getIntent().getStringExtra("file_name");
        ((TextView) findViewById(R.id.sura_name))
                .setText(suraName);
        readFile(fileName);
        Log.e("lines",AllLines.size()+"");
        adapter = new VersesAdapter(AllLines);
        layoutManager = new LinearLayoutManager(activity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void readFile(String fileName) {
        AllLines = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(fileName), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                if (mLine.trim().equals(""))return;
                AllLines.add(mLine);


            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }
}
