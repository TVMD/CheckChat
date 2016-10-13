package com.app.checkchat.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tient_000 on 10/13/2016.
 */

public class Demo {

    @SerializedName("title")
    String title;
    @SerializedName("link")
    String link;

    int a;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return(title);
    }
}
