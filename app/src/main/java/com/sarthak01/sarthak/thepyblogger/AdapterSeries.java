package com.sarthak01.sarthak.thepyblogger;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterSeries extends RecyclerView.Adapter<AdapterSeries.Viewholder> {

    private List<PostSeriesModel> postSeriesModelList;

    private Context context;
    public AdapterSeries(List<PostSeriesModel> postSeriesModelList) {
        this.postSeriesModelList = postSeriesModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items,viewGroup,false);
        context=viewGroup.getContext();
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        final String series=postSeriesModelList.get(i).getPostSeries();
        String summary=postSeriesModelList.get(i).getPostSummary();
        String category=postSeriesModelList.get(i).getPostCategory();
        viewholder.SetData(series,summary,category);

        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PostActivity.class);
                intent.putExtra("post_series",series);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postSeriesModelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private TextView postseriesTextView;
        private TextView postsummaryTextView;
        private TextView postcategoryTextView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            postseriesTextView=itemView.findViewById(R.id.postseries);
            postsummaryTextView=itemView.findViewById(R.id.postsummary);
            postcategoryTextView=itemView.findViewById(R.id.postcategory);
        }
        private void SetData(String Series,String Summary,String Category)
        {
            postseriesTextView.setText(Series);
            postsummaryTextView.setText(Summary);
            postcategoryTextView.setText(Category);

        }
    }
}
