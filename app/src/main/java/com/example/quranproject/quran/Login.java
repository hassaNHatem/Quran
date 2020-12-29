package com.example.quranproject.quran;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quranproject.Base.BaseActivity;
import com.example.quranproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends BaseActivity {
    EditText email, password;
    TextView navigateToResigter;
    Button loginBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        navigateToResigter = findViewById(R.id.navToRegister);
        loginBtn = findViewById(R.id.loginBtn);
        mAuth = FirebaseAuth.getInstance();


        navigateToResigter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressBar();
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.e("login", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Login.this, "sucess ",
                                            Toast.LENGTH_SHORT).show();
                                    DataHolder.userName=email.getText().toString();
                                    if(DataHolder.userName.equals(DataHolder.admin)){
                                        Log.e("e","d5l");
                                        DataHolder.isAdmin=true;}
                                    Intent intent=new Intent(Login.this,HomeActivity.class);
                                    startActivity(intent);
//                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.e("failed", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                    // ...
                                }

                                // ...
                                hideProgressBar();

                            }
                        });
            }
        });


    }
    public void isAdmin(){


    }
}