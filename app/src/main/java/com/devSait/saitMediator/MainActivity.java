package com.devSait.saitMediator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button msignupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msignupbtn = findViewById(R.id.signupbtn);
        msignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_page();
            }
        });
    }
    public void register_page(){
        Intent intent = new Intent(this, register_page.class);
        startActivity(intent);

    }
}