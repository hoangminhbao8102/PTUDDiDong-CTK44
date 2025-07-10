package com.example.musicapplab12;

public class SongEntity {
    private final String name;
    private final String path;
    private final String album;
    public SongEntity(String name, String path, String album) {
        this.name = name;
        this.path = path;
        this.album = album;
    }
    public String getName() {
        return name;
    }
    public String getPath() {
        return path;
    }
    public String getAlbum() {
        return album;
    }
}
