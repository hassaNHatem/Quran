package com.example.quranproject.quran;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quranproject.Base.BaseFragment;
import com.example.quranproject.R;
import com.example.quranproject.quran.API.Model.AzkarItem;
import com.example.quranproject.quran.Adapters.azkarAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AzkarFragment extends BaseFragment {
    RecyclerView azkraRecyclerView;
    ArrayList<AzkarItem> list=new ArrayList<AzkarItem>();
    com.example.quranproject.quran.Adapters.azkarAdapter azkarAdapter;
    FirebaseFirestore db  =FirebaseFirestore.getInstance();
    FloatingActionButton fab;
    int LAUNCH_ADD_AZKAR_ACTIVITY = 1;

    public AzkarFragment() {
        // Required empty public constructor
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_azkar, container, false);
        azkraRecyclerView = view.findViewById(R.id.azkraRecyclerView);
        fab=view.findViewById(R.id.fab);
        hideFab();
        if(DataHolder.isAdmin){
            showFab();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Intent intent =new Intent(activity,AddAzkar.class);
                    startActivityForResult(intent, LAUNCH_ADD_AZKAR_ACTIVITY);
                }
            });
        }
          fetchData();
        azkarAdapter = new azkarAdapter(list);
        azkarAdapter.setOnItemClickListner(new azkarAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(AzkarItem azkarItem, int position) {
            Intent intent=new Intent(activity,AzkarContent.class);
            intent.putExtra("item",azkarItem);
            startActivity(intent);
            }
        });

        azkraRecyclerView.setAdapter(azkarAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        azkraRecyclerView.setLayoutManager(layoutManager);

        showProgressBar();
        return view;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_ADD_AZKAR_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                fetchData();
                azkarAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
    public void showFab(){
        fab.show();
    }
    public void hideFab(){
        fab.hide();
    }
    public void fetchData(){
        db.collection("Azkar")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.e("success", document.getId() + " => " + document.getData());
                                String title = document.getData().values().toArray()[1].toString();
                                String Descreption=document.getData().values().toArray()[0].toString();
                                Log.e("e","title is "+ title);
                                list.clear();
                                list.add(new AzkarItem(document.getId(),title,Descreption));
                                azkarAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.e("fail", "Error getting documents.", task.getException());
                        }
                        hideProgressBar();
                    }
                });

    }
}