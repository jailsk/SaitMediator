package com.devSait.saitMediator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 3550;
    //variables
    Animation topanim,bottomanim;
    ImageView logo;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

    //Animation
    topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
    bottomanim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

    logo=findViewById(R.id.imageView);
    text=findViewById(R.id.textView2);

    logo.setAnimation(topanim);
    text.setAnimation(bottomanim);

    new Handler().postDelayed(() -> {
        Intent intent=new Intent(MainActivity.this,login_page.class);
        startActivity(intent);
        finish();
    },SPLASH_SCREEN);
    }
}