package com.sarthak01.sarthak.thepyblogger;

public class PostModel {

    private String post_title;
    private String post_content;
    private String post_published;
    private String im;

    public PostModel(String post_title, String post_content, String post_published, String im) {
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_published = post_published;
        this.im = im;
    }

    public String getPost_title() {
        return post_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public String getPost_published() {
        return post_published;
    }

    public String getIm() {
        return im;
    }
}
