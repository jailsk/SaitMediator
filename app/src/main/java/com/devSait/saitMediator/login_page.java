package com.devSait.saitMediator;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class login_page extends AppCompatActivity {
    private static final String TAG = "login_page";
    ImageView image;
    TextView heading, subtitle;
    TextInputLayout rollno, password;
    Button login, signup, forgot;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);

        image = findViewById(R.id.logo_image);
        heading = findViewById(R.id.login_text);
        subtitle = findViewById(R.id.sub_text);
        rollno = findViewById(R.id.rollno);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginbtn);
        signup = findViewById(R.id.registerbtn2);
        forgot = findViewById(R.id.forget_pwsd);
        progressBar = findViewById(R.id.progressBar3);

        progressBar.setVisibility(View.GONE);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser() != null && fAuth.getCurrentUser().isEmailVerified()) {
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            finish();
        }

        signup.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            Intent registration = new Intent(getApplicationContext(), register_page.class);
            Pair[] pairs = new Pair[7];
            pairs[0] = new Pair<View, String>(image, "logo_image");
            pairs[1] = new Pair<View, String>(heading, "text");
            pairs[2] = new Pair<View, String>(subtitle, "text_des");
            pairs[3] = new Pair<View, String>(rollno, "roll_no");
            pairs[4] = new Pair<View, String>(password, "psword");
            pairs[5] = new Pair<View, String>(login, "login_btn");
            pairs[6] = new Pair<View, String>(signup, "signup_btn");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login_page.this, pairs);
            startActivity(registration, options.toBundle());
        });

        login.setOnClickListener(v -> {
            String rollnum = Objects.requireNonNull(rollno.getEditText()).getText().toString().trim().toUpperCase();
            String pswd = Objects.requireNonNull(password.getEditText()).getText().toString().trim();
            if (TextUtils.isEmpty(rollnum)) {
                rollno.setError("Roll no is Required.");
                return;
            }

            if (TextUtils.isEmpty(pswd)) {
                password.setError("Password is Required.");
                return;
            }

            if (pswd.length() < 6) {
                password.setError("Password Must be >= 6 Characters");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            //Retrieving email
            CollectionReference yourCollRef = fstore.collection("users");
            Query query = yourCollRef.whereEqualTo("rollno", rollnum);
            query.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        if (document.exists()) {
                            String email = document.getString("email");
                            // authenticate the user
                            fAuth.signInWithEmailAndPassword(email, pswd).addOnCompleteListener(t -> {
                                if (t.isSuccessful()) {
                                    if(fAuth.getCurrentUser().isEmailVerified()) {
                                        Toast.makeText(login_page.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                    }else{
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(login_page.this, "Please verify you email.", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(login_page.this, "Error ! " + t.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }

                            });
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                } if (task.getResult().size() == 0) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(login_page.this, "User not registered.", Toast.LENGTH_SHORT).show();
                }
            });


        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(login_page.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login_page.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });


    }
}