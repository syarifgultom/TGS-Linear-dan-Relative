package com.example.tgssqlite.model;

public class Data {
    private String id, judul, description;

    public Data() {
    }

    public Data(String id, String judul, String description) {
        this.id             = id;
        this.judul          = judul;
        this.description    = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
