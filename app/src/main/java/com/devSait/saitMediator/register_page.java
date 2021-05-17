package com.devSait.saitMediator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class register_page extends AppCompatActivity {
    public static final String TAG = "TAG";
    TextInputLayout mrollno,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        mrollno   = findViewById(R.id.rollno);
        mEmail      = findViewById(R.id.email);
        mPassword   = findViewById(R.id.password);
        mPhone      = findViewById(R.id.phone_no);
        mRegisterBtn= findViewById(R.id.registerbtn);
        mLoginBtn   = findViewById(R.id.loginbtn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Dashboard.class));
            finish();
        }

        progressBar.setVisibility(View.GONE);
        mRegisterBtn.setOnClickListener(v -> {
            final String email = Objects.requireNonNull(mEmail.getEditText()).getText().toString().trim();
            String password = Objects.requireNonNull(mPassword.getEditText()).getText().toString().trim();
            final String rollnum = Objects.requireNonNull(mrollno.getEditText()).getText().toString();
            final String phone    = Objects.requireNonNull(mPhone.getEditText()).getText().toString();

            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email is Required.");
                return;
            }

            if(TextUtils.isEmpty(password)){
                mPassword.setError("Password is Required.");
                return;
            }

            if(password.length() < 6){
                mPassword.setError("Password Must be >= 6 Characters");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            // register the user in firebase

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        // send verification link

                        FirebaseUser fuser = fAuth.getCurrentUser();
                        fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(register_page.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                            }
                        });

                        Toast.makeText(register_page.this, "User Created.", Toast.LENGTH_SHORT).show();
                        userID = rollnum;
                        DocumentReference documentReference = fStore.collection("users").document(userID);
                        Map<String,Object> user = new HashMap<>();
                        user.put("rollno",rollnum);
                        user.put("email",email);
                        user.put("Phone no",phone);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: " + e.toString());
                            }
                        });
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));

                    }else {
                        Toast.makeText(register_page.this, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });
        });



        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login_page.class));
            }
        });

    }
}
