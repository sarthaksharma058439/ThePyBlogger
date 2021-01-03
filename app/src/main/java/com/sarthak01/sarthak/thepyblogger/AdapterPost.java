package com.sarthak01.sarthak.thepyblogger;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.Viewholder> {

    private Context context;

    private List<PostModel> postModelList;

    public AdapterPost(List<PostModel> postModelList) {
        this.postModelList = postModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_items,viewGroup,false);
        context=viewGroup.getContext();
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        final String posttitle=postModelList.get(i).getPost_title();
        final String postcontent=postModelList.get(i).getPost_content();
        final String postpublished=postModelList.get(i).getPost_published();
        String postim=postModelList.get(i).getIm();

        viewholder.setData(posttitle,postcontent,postpublished,postim);

        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,PostStuffActivity.class);
                intent.putExtra("post_title",posttitle);
                intent.putExtra("post_content",postcontent);
                intent.putExtra("post_published",postpublished);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private TextView post_title_textview;
        private TextView post_content_textview;
        private TextView post_published_textview;
        private TextView post_im_textview;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            post_title_textview=itemView.findViewById(R.id.post_title_id);
            post_content_textview=itemView.findViewById(R.id.post_content_id);
            post_published_textview=itemView.findViewById(R.id.post_published_id);
            post_im_textview=itemView.findViewById(R.id.post_im);

        }
        private void setData(String PostTitle, String PostContent, String PostPublished, String PostIm)
        {
            post_title_textview.setText(PostTitle);
            post_content_textview.setText(PostContent);


            ISO8601DateFormat df = new ISO8601DateFormat();
            try {
                Date d = df.parse(PostPublished);
                post_published_textview.setText("Published "+d);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            post_im_textview.setText(PostIm);
        }
    }
}
