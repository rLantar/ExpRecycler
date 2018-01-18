package com.lantar.testtask.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lantar.testtask.Db;
import com.lantar.testtask.R;
import com.lantar.testtask.data.network.model.Child;
import com.lantar.testtask.data.network.model.Response;
import com.lantar.testtask.databinding.ChildCardBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LantarPc on 1/18/2018.
 */

public class CheckRecyclerView extends RecyclerView.Adapter<CheckRecyclerView.ViewHolder> {

    private List<Child> childList = new ArrayList<>();

    int responseId = 0;


    public CheckRecyclerView(@NonNull Response response) {
        this.childList = (response.getChildren());
        this.responseId = response.getId();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ChildCardBinding binding = DataBindingUtil.inflate(inflater, R.layout.child_card, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Child child = Db.getInstance().getData().getMap().get(responseId).getChildren().get(position);
        holder.bind(child);

        holder.mBinding.setClick(new OnCheckedChanged() {
            @Override
            public void onCheckedChanged(Child child, boolean status) {
                Db.getInstance().setChecked(responseId, child.getId());

            }
        });


    }


    @Override
    public int getItemCount() {
        return childList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private ChildCardBinding mBinding;

        public ViewHolder(ChildCardBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull Child child) {
            mBinding.setChild(child);
            mBinding.executePendingBindings();
        }
    }

    public interface OnCheckedChanged {
        void onCheckedChanged(Child child, boolean status);
    }

}