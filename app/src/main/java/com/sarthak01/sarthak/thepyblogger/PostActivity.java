package com.sarthak01.sarthak.thepyblogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar2;
    private TextView PleaseWait2;
    private Button retry2;
    LinearLayout bottomLinearlayout2;
    Animation fade2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        PleaseWait2=(TextView)findViewById(R.id.pleasewait2);
        retry2=(Button)findViewById(R.id.retry2);

        bottomLinearlayout2=(LinearLayout)findViewById(R.id.bottonlinearlayout2);



        fade2= AnimationUtils.loadAnimation(this,R.anim.fade);
        bottomLinearlayout2.setAnimation(fade2);

        recyclerView=findViewById(R.id.postrecyclerview);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        init();
        progressBar2.setVisibility(View.VISIBLE);
        PleaseWait2.setVisibility(View.VISIBLE);
        calling();
        retry2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retry2.setVisibility(View.GONE);
                PleaseWait2.setVisibility(View.VISIBLE);
                progressBar2.setVisibility(View.VISIBLE);
                calling();
            }
        });

    }

    private void init() {
        this.progressBar2=findViewById(R.id.progressbar2);
    }

    private void calling() {
        final List<PostModel> postModelList =new ArrayList<>();


        Intent intent = getIntent();
        final String post_series=intent.getStringExtra("post_series");


        AdapterPost adapterPost=new AdapterPost(postModelList);
        recyclerView.setAdapter(adapterPost);
        adapterPost.notifyDataSetChanged();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Post>> call=api.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts=response.body();



                for(Post p:posts)
                {
                    if (post_series.equals(p.getIm()))///wowowwwwwwwwwwww!!!!!! i did it!!
                    {

                        postModelList.add(new PostModel(p.getPost_title(),p.getPost_content(),p.getPost_published(),null));
                        AdapterPost adapterPost=new AdapterPost(postModelList);
                        recyclerView.setAdapter(adapterPost);
                        adapterPost.notifyDataSetChanged();
                    }
                }
                progressBar2.setVisibility(View.GONE);
                PleaseWait2.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                PleaseWait2.setVisibility(View.GONE);
                progressBar2.setVisibility(View.GONE);
                Toast.makeText(PostActivity.this,"Network Failure Please Check Your Internet Connection ...", Toast.LENGTH_LONG).show();
                retry2.setVisibility(View.VISIBLE);
            }
        });
    }
}
