package com.lantar.testtask.ui.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lantar.testtask.Db;
import com.lantar.testtask.R;
import com.lantar.testtask.data.network.model.Response;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by LantarPc on 1/18/2018.
 */

public class ExpandableRecyclerAdapter extends RecyclerView.Adapter<ExpandableRecyclerAdapter.ViewHolder> {

    private List<Response> responses = new LinkedList<>();
    private Context mContext;
    private int mExpandedPosition = -1;


    public ExpandableRecyclerAdapter(Collection<Response> collection, Context mContext) {
        this.responses.addAll(collection);
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.parent_card, parent, false);

        ViewHolder holder = new ViewHolder(v);

        holder.itemView.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tv_title.setText((responses.get(position).getName()));
        CheckRecyclerView checkRecyclerView =
                new CheckRecyclerView(Db.getInstance().getData().getMap().get(responses.get(position).getId()));
        holder.rv_ch.setAdapter(checkRecyclerView);

        final boolean isExpanded = position == mExpandedPosition;
        holder.rv_ch.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.iv_arrow.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
        holder.iv_arrow_ex.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;
                TransitionManager.beginDelayedTransition(holder.rv_ch);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rv_ch;
        TextView tv_title;
        ImageView iv_arrow;
        ImageView iv_arrow_ex;


        public ViewHolder(View v) {
            super(v);

            rv_ch = v.findViewById(R.id.rv_ch);
            tv_title = v.findViewById(R.id.tv_parent);
            iv_arrow = v.findViewById(R.id.iv_arrow);
            iv_arrow_ex = v.findViewById(R.id.iv_arrow_ex);
            rv_ch.setHasFixedSize(true);
            rv_ch.setLayoutManager(new LinearLayoutManager(mContext));
        }
    }
}
