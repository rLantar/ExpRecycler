
package com.lantar.testtask.data.network.model;

import android.databinding.ObservableBoolean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Observable;

public class Child extends Observable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    public final ObservableBoolean observableStatus = new ObservableBoolean();


    private boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return observableStatus.get();
    }

    public void setStatus(boolean status) {
        observableStatus.set(status);
        this.status = status;
    }
}
