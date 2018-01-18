package com.lantar.testtask.data.network;

import com.lantar.testtask.data.network.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by LantarPc on 1/18/2018.
 */

public interface Api {
    @GET("list")
    Call<Data> getData();
}
