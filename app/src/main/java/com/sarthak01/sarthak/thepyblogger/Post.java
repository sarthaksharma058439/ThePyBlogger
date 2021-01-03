package com.sarthak01.sarthak.thepyblogger;

class Post {
    private String id;
    private String post_title;
    private String post_content;
    private String post_published;
    private String Im;

    public Post(String id, String post_title, String post_content, String post_published, String im) {
        this.id = id;
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_published = post_published;
        Im = im;
    }

    public String getId() {
        return id;
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
        return Im;
    }
}
