package com.lantar.testtask;

import com.lantar.testtask.data.network.model.Data;

/**
 * Created by LantarPc on 1/18/2018.
 */

public class Db {
    private static Db instance;
    private Data data = new Data();
    private int lastResponseCheck = -1;
    private int lastChildCheck;

    public static synchronized Db getInstance() {
        if (instance == null) {
            instance = new Db();
        }

        return instance;
    }


    public void createMap(Data data) {
        data.listToMap();
        this.data.getMap().putAll(data.getMap());
    }

    public Data getData() {
        return data;
    }

    public void setChecked(int responseid, int childId) {
        if (lastResponseCheck != 0 && lastChildCheck != 0)
            getData().getMap().get(lastResponseCheck).getChildMap().get(lastChildCheck).setStatus(false);
        getData().getMap().get(responseid).getChildMap().get(childId).setStatus(true);
        lastResponseCheck = responseid;
        lastChildCheck = childId;
    }


}