package com.sarthak01.sarthak.thepyblogger;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostSeriesActivity extends AppCompatActivity {

    ArrayList<String> postserieslist=new ArrayList<String>();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView PleaseWait;
    private Button retry;
    LinearLayout bottomLinearlayout;
    Animation fade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_series);



        init();
        progressBar.setVisibility(View.VISIBLE);
        PleaseWait=(TextView)findViewById(R.id.pleasewait);
        PleaseWait.setVisibility(View.VISIBLE);
        retry=(Button)findViewById(R.id.retry);
        bottomLinearlayout=(LinearLayout)findViewById(R.id.bottonlinearlayout);



        fade= AnimationUtils.loadAnimation(this,R.anim.fade);
        bottomLinearlayout.setAnimation(fade);


        recyclerView=findViewById(R.id.postseriesrecyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        calling();
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retry.setVisibility(View.GONE);
                PleaseWait.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                calling();
            }
        });
    }




    private void calling() {

        final List<PostSeriesModel> postSeriesModelList=new ArrayList<>();


        AdapterSeries adapterSeries=new AdapterSeries(postSeriesModelList);
        recyclerView.setAdapter(adapterSeries);
        adapterSeries.notifyDataSetChanged();




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        Api api = retrofit.create(Api.class);
        Call<List<PostSeries>> call=api.getPostSeries();
        call.enqueue(new Callback<List<PostSeries>>() {
            @Override
            public void onResponse(Call<List<PostSeries>> call, Response<List<PostSeries>> response) {

                List<PostSeries> postSeries=response.body();
                for(PostSeries p:postSeries)
                {
                    postserieslist.add(p.getPost_series());  //list
                    postserieslist.add(p.getPost_summary());
                    postserieslist.add(p.getPost_category());

                    postSeriesModelList.add(new PostSeriesModel(p.getPost_series(),p.getPost_summary(),null));         //cardview list
                    AdapterSeries adapterSeries=new AdapterSeries(postSeriesModelList);
                    recyclerView.setAdapter(adapterSeries);
                    adapterSeries.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
                PleaseWait.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<PostSeries>> call, Throwable t) {
                PleaseWait.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(PostSeriesActivity.this,"Network Failure Please Check Your Internet Connection ...", Toast.LENGTH_LONG).show();
                retry.setVisibility(View.VISIBLE);
            }
        });
    }

    private void init() {
        this.progressBar=findViewById(R.id.progressbar);
    }
}
