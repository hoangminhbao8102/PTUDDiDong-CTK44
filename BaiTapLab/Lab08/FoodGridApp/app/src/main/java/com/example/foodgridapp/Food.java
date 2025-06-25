package com.example.foodgridapp;

public class Food {
    private final String name;
    private final int imageResId;

    public Food(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
