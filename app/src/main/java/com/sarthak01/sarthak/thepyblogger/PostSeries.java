package com.sarthak01.sarthak.thepyblogger;

public class PostSeries {

    private String id;
    private String post_series;
    private String post_summary;
    private String post_category;

    public PostSeries(String id, String post_series, String post_summary, String post_category) {
        this.id = id;
        this.post_series = post_series;
        this.post_summary = post_summary;
        this.post_category = post_category;
    }

    public String getId() {
        return id;
    }

    public String getPost_series() {
        return post_series;
    }

    public String getPost_summary() {
        return post_summary;
    }

    public String getPost_category() {
        return post_category;
    }
}
