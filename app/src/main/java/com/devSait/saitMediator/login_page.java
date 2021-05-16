package com.devSait.saitMediator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.util.Pair;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class login_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);


        Button register=(Button)findViewById(R.id.registerbtn2);
        register.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            Intent registration=new Intent(getApplicationContext(),register_page.class);
            startActivity(registration);
        });

    }
}