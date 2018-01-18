package com.lantar.testtask.data.network;

import android.util.Log;

import com.lantar.testtask.MainActivity;
import com.lantar.testtask.data.network.model.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LantarPc on 1/18/2018.
 */

public class NetworkService {

    private OnResponse onResponse = null;

    private String url;

    private Api api;

    public NetworkService(MainActivity onResponse, String url) {
        this.onResponse = onResponse;
        this.url = url;
        build();
    }

    private void build() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public void getData() {
        Call<Data> call = api.getData();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                onResponse.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("server error ", t.toString());

            }
        });
    }


}
