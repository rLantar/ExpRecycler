package com.lantar.testtask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.lantar.testtask.data.network.NetworkService;
import com.lantar.testtask.data.network.OnResponse;
import com.lantar.testtask.data.network.model.Data;
import com.lantar.testtask.ui.adapters.ExpandableRecyclerAdapter;

public class MainActivity extends AppCompatActivity implements OnResponse {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.ex_rv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (Db.getInstance().getData().getMap().size() > 0)
            showRecyclerView();
        else {
            NetworkService networkService = new NetworkService(this, getResources().getString(R.string.server_url));
            networkService.getData();
        }

    }

    private void showRecyclerView() {
        progressBar.setVisibility(View.GONE);
        ExpandableRecyclerAdapter expandableRecyclerAdapter = new ExpandableRecyclerAdapter(Db.getInstance().getData().getMap().values(), this);
        recyclerView.setAdapter(expandableRecyclerAdapter);

    }

    @Override
    public void onResponse(Data data) {
        Db.getInstance().createMap(data);
        showRecyclerView();
    }
}
