package com.example.quranproject.quran;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quranproject.Base.BaseActivity;
import com.example.quranproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAzkar extends BaseActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText title,content;
    Button addBtn;
    Map<String, Object> azkar = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_azkar);
        title = findViewById(R.id.titleEditText);
        content = findViewById(R.id.contentEditText);
        addBtn = findViewById(R.id.addButton);



        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                azkar.put("title", title.getText().toString());
                azkar.put("content", content.getText().toString());
                db.collection("Azkar")
                        .add(azkar)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.e("sucesses", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Intent returnIntent = new Intent();
                                setResult(Activity.RESULT_OK,returnIntent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("failed", "Error adding document", e);
                            }
                        });
            }
        });





    }
}