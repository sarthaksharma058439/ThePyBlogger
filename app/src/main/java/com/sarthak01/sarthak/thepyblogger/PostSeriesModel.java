package com.sarthak01.sarthak.thepyblogger;

public class PostSeriesModel {

    private String PostSeries;
    private String PostSummary;
    private String PostCategory;

    public PostSeriesModel(String postSeries, String postSummary, String postCategory) {
        PostSeries = postSeries;
        PostSummary = postSummary;
        PostCategory = postCategory;
    }

    public String getPostSeries() {
        return PostSeries;
    }

    public String getPostSummary() {
        return PostSummary;
    }

    public String getPostCategory() {
        return PostCategory;
    }
}
