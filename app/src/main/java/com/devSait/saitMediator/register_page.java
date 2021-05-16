package com.devSait.saitMediator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class register_page extends AppCompatActivity {
    EditText mrollno,memail,mpassword1,mpassword2;
    Button mregisterbtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        mrollno     = findViewById(R.id.rollno);
        memail      = findViewById(R.id.email);
        mpassword1  =  findViewById(R.id.password1);
        mpassword2  =   findViewById(R.id.password2);
        mregisterbtn =  findViewById(R.id.registerbtn);

        fAuth = FirebaseAuth.getInstance();
        ProgressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mregisterbtn.setOnClickListener(new View.OnClickListener()){
            @Override
            public void onClick(View v){
                String email = memail.getText().toString().trim();
                String password1 = mpassword1.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email required");
                    return;
                }
                if(TextUtils.isEmpty(password1)){
                    memail.setError("password required");
                    return;
            }
                if (password1.length()<6){
                    mpassword1.setError("password more than 6 ch");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"user created.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Register.this,"error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                                                                                                }
                                                                                            }
                )
        }
    }
}