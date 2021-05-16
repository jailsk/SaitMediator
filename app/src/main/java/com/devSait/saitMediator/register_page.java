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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class register_page extends AppCompatActivity {
  /*  FirebaseAuth fAuth;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    private static final String TAG = register_page.class.getSimpleName();
    private TextInputEditText mrollno;
    private TextInputEditText memail_id;
    private TextInputEditText mPassword;
    private Button mSignup=(Button)findViewById(R.id.registerbtn);
*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
      /*  mAuth = FirebaseAuth.getInstance();

        mrollno = (TextInputEditText) findViewById(R.id.rollno);
        memail_id = (TextInputEditText) findViewById(R.id.email_id);
        mPassword = (TextInputEditText) findViewById(R.id.password);


        mSignup.setOnClickListener(view -> {
            String rollno = Objects.requireNonNull(mrollno.getText()).toString();
            String email = Objects.requireNonNull(memail_id.getText()).toString();
            String password = Objects.requireNonNull(mPassword.getText()).toString();

            registerUser(rollno, email, password);

        });
    }
    private void registerUser(String rollno, String email, String password) {

        mAuth.signInWithEmailAndPassword(rollno,password)
                .addOnCompleteListener(this, task -> {
                    if(task.isSuccessful()){
                        Intent mainIntent = new Intent(register_page.this,Dashboard.class);
                        startActivity(mainIntent);
                        finish();
                    }else{
                        Toast.makeText(register_page.this,"You got some error.",Toast.LENGTH_SHORT).show();
                    }
                });*/
    }
}

       /* fAuth = FirebaseAuth.getInstance();
        //ProgressBar = findViewById(R.id.Progressbar);

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mregisterbtn.setOnClickListener(v -> {
            String email = memail.getText().toString().trim();
            String password1 = mpassword1.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                memail.setError("Email required");
                return;
            }
            if (TextUtils.isEmpty(password1)) {
                memail.setError("password required");
                return;
            }
            if (password1.length() < 6) {
                mpassword1.setError("password more than 6 ch");
                return;
            }
            progressBar.setVisibility(View.VISIBLE);

            fAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(register_page.this, "user created.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(register_page.this, "error !" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
      */


