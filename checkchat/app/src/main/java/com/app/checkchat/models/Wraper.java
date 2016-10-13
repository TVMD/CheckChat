package com.app.checkchat.models;

/**
 * Created by tient_000 on 10/13/2016.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tient_000 on 10/13/2016.
 */

public class Wraper {
    @SerializedName("items")
    List<Demo> items;
    @SerializedName("has_more")
    boolean has_more;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<Demo> getItems() {
        return items;
    }

    public void setItems(List<Demo> items) {
        this.items = items;
    }
}
