package com.example.livedatarecview;

public class AnimeModel {
    private String imageURL;
    private String charName;
    private String charTitle;

    public AnimeModel() {
    }

    public AnimeModel(String imageURL, String charName, String charTitle) {
        this.imageURL = imageURL;
        this.charName = charName;
        this.charTitle = charTitle;
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
