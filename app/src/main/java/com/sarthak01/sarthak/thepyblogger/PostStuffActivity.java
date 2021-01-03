package com.sarthak01.sarthak.thepyblogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.ParseException;
import java.util.Date;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;

public class PostStuffActivity extends AppCompatActivity {

    private TextView titletv1,datetv2,contenttv3,f333;
    Animation fade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_stuff);

        titletv1=(TextView)findViewById(R.id.post_heading);
        datetv2=(TextView)findViewById(R.id.post_published_heading);
        contenttv3=(TextView)findViewById(R.id.post_content_heading);

        f333=(TextView)findViewById(R.id.f333);
        fade= AnimationUtils.loadAnimation(this,R.anim.fade);
        f333.setAnimation(fade);

        Intent intent=getIntent();
        String post_title=intent.getStringExtra("post_title");
        String post_cotent=intent.getStringExtra("post_content");
        String post_published =intent.getStringExtra("post_published");

        titletv1.setText(post_title);


        ISO8601DateFormat df = new ISO8601DateFormat();
        try {
            Date d = df.parse(post_published);
            datetv2.setText("Published: "+d.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }



        Document doc = Jsoup.parse(post_cotent);
        contenttv3.setText(doc.body().text());
    }
}
