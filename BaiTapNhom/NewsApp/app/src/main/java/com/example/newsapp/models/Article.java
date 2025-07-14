package com.example.newsapp.models;

public class Article {
    private int id;
    private final String title;
    private final String content;
    private int views;

    public Article(int id, String title, String content, int views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.views = views;
    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.views = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getViews() {
        return views;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
