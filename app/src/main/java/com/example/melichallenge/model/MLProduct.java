package com.example.melichallenge.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan on 14/09/2016.
 */
public class MLProduct {
    @SerializedName("id")
    private String ID;

    private String title;

    private Double price;

    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
