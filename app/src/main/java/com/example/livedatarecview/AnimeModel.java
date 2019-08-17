package com.example.livedatarecview;

public class AnimeModel {
    private String imageURL;
    private String charName;
    private String charTitle;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnimeModel() {
    }

    public AnimeModel(String imageURL, String charName, String charTitle, int id) {
        this.imageURL = imageURL;
        this.charName = charName;
        this.charTitle = charTitle;
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getCharTitle() {
        return charTitle;
    }

    public void setCharTitle(String charTitle) {
        this.charTitle = charTitle;
    }
}
