
package com.lantar.testtask.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Response {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("children")
    @Expose
    private List<Child> children = new LinkedList<>();

    private Map<Integer, Child> childMap = new LinkedHashMap<>();

    public Map<Integer, Child> getChildMap() {
        return childMap;
    }

    public void setChildMap(Map<Integer, Child> childMap) {
        this.childMap = childMap;
    }

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

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public void toMap(){
        for(Child child : children)
            this.childMap.put(child.getId(), child);
    }


}
