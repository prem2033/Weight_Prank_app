package com.prem.weightprank;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity    {
    private ImageView scanimage;
    private  TextView weighttext;
    private  int repeat;
    private TranslateAnimation translateAnimation;
    private  boolean flag;
    private View bar;
    LinearLayout linearLayout;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanimage =findViewById(R.id.scanimage);
         bar = findViewById(R.id.bar);
         flag=false;
         weighttext=findViewById(R.id.weighttext);
         weighttext.setText(R.string.inital_msg);
        scanimage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN)
                    moveImageAsScanOnOtherImage();
                if(event.getAction()==MotionEvent.ACTION_UP)
                    translateAnimation.cancel();
                return  true;
            }
        });
    }
public void moveImageAsScanOnOtherImage(){
    int height=scanimage.getHeight();
    translateAnimation=new TranslateAnimation(0f, 0f,0f,height);
    translateAnimation.setDuration(500);
    translateAnimation.setRepeatCount(5);
    translateAnimation.setInterpolator(new LinearInterpolator());
    translateAnimation.setRepeatMode(Animation.REVERSE);
    bar.startAnimation(translateAnimation);
    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
           // Toast.makeText(MainActivity.this, "started", Toast.LENGTH_SHORT).show();
            scanimage.setBackgroundResource(R.color.onclickbackground);
            weighttext.setText("Scanning...");
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(repeat==5) {
                Random random=new Random();
                int weight=34+random.nextInt(70);
               // Toast.makeText(MainActivity.this, "end" + repeat, Toast.LENGTH_SHORT).show();
                weighttext.setText("weight is "+weight);

            }
            else {
                weighttext.setText(R.string.inital_msg);
              //  Toast.makeText(MainActivity.this, "cancel" + repeat, Toast.LENGTH_SHORT).show();
            }
            scanimage.setBackgroundResource(R.color.initialbackgroundcolor);
            repeat=0;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            repeat++;
            //Toast.makeText(MainActivity.this, "repeat"+repeat, Toast.LENGTH_SHORT).show();
        }
    });

}
}
