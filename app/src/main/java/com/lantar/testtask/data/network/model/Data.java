
package com.lantar.testtask.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Data {

    @SerializedName("response")
    @Expose
    private List<Response> response = null;

    private Map<Integer, Response> map = new LinkedHashMap<>();

    public Map<Integer, Response> listToMap() {
        for (Response response : response) {
            response.toMap();
            map.put(response.getId(), response);

        }
        return map;
    }

    public Map<Integer, Response> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Response> map) {
        this.map = map;
    }

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

}
