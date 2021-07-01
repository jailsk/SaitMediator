package com.devSait.saitMediator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;

public class Dashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ImageView profile_pic;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseAuth fAuth= FirebaseAuth.getInstance();




    }
        public void logout(View view){
            FirebaseAuth.getInstance().signOut();//logout
            startActivity(new Intent(getApplicationContext(), login_page.class));
            finish();
        }

}