package com.app.checkchat.models;

import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;
import java.io.Serializable;

/**
 * Created by MyPC on 7/27/2016.
 */

public abstract class BaseEntity<T> implements Serializable {

    @SerializedName("id")
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @SerializedName("updated_at")
    private DateTime updatedAt;

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @SerializedName("created_at")
    private DateTime createdAt;

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) return false;
        if (otherObject == this) return true;
        if (!(otherObject instanceof BaseEntity)) return false;

        BaseEntity other = (BaseEntity) otherObject;
        return this.getId() == other.getId();
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = (hash * 7) + this.id.hashCode();

        return hash;
    }
}
