package com.sarthak01.sarthak.thepyblogger;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    LinearLayout l1,l2;
    Animation uptodown,downtoup,fade;
    TextView harness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        harness=(TextView)findViewById(R.id.harness);


        if(Build.VERSION.SDK_INT >=19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        l1=(LinearLayout)findViewById(R.id.l1);
        l2=(LinearLayout)findViewById(R.id.l2);

        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        l1.setAnimation(uptodown);

        downtoup=AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l2.setAnimation(downtoup);

        fade=AnimationUtils.loadAnimation(this,R.anim.fade);
        harness.setAnimation(fade);

        final Intent intent=new Intent(MainActivity.this,PostSeriesActivity.class);
        Thread thread =new Thread()
        {
            @Override
            public void run() {
                try
                {
                    sleep(5000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(intent);
                    finish();
                }
            }
        };
                thread.start();
    }
}
