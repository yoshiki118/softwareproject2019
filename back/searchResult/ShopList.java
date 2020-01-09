package com.example.myapplication;

import android.graphics.Bitmap;

public class ShopList {
    private int id;
    private String name;
    private String opentime;
    private String pr_short;
    private Bitmap image;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getOpentime() {
        return this.opentime;
    }

    public void setPrshort(String pr_short) {
        this.pr_short = pr_short;
    }

    public String getPrshort() {
        return this.pr_short;
    }

    public void setImage(Bitmap image) {
      this.image = image;
    }

    public Bitmap getImage() {
      return this.image;
    }
}

