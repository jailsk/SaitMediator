package com.devSait.saitMediator;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 3550;
    //variables
    Animation topanim,bottomanim;
    ImageView logo;
    TextView text;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        Pair[] pairs=new Pair[2];
        pairs[0] = new Pair<View,String>(logo,"logo_image");
        pairs[1] = new Pair<View,String>(text,"text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
        startActivity(intent, options.toBundle());
        finish();

    },SPLASH_SCREEN);
    }
}