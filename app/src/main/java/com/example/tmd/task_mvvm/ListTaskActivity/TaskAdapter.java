package com.example.tmd.task_mvvm.ListTaskActivity;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.tmd.task_mvvm.R;
import com.example.tmd.task_mvvm.ViewModel.ObservableTask;
import com.example.tmd.task_mvvm.databinding.ItemTaskBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 09/07/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<ObservableTask> mList = new ArrayList<>();
    private MainContract.View mView;

    public TaskAdapter() {
    }

    public void updateData(List<ObservableTask> list) {
        Log.d("MY_TAG", "notifyDataSetChanged: ");
        if (list == null) return;
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void updateData(ObservableTask observableTask) {
        Log.d("MY_TAG", "updateData: ");
        int index;
        if (mList.contains(observableTask)) {
            index = mList.indexOf(observableTask);
            mList.set(index, observableTask);
        } else {
            mList.add(observableTask);
            index = mList.size() - 1;
        }
        notifyItemChanged(index);
    }

    public void deleteTask(ObservableTask observableTask) {
        int index = mList.indexOf(observableTask);
        if (index >= 0) {
            mList.remove(index);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = (MainContract.View) parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTaskBinding itemTaskBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_task, parent, false);
        return new ViewHolder(itemTaskBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ObservableTask observableTask = mList.get(position);
        holder.bindData(observableTask);
    }

    @Override
    public int getItemCount() {
        if (mList != null) return mList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemTaskBinding mItemTaskBinding;

        public ViewHolder(ItemTaskBinding itemTaskBinding) {
            super(itemTaskBinding.getRoot());
            mItemTaskBinding = itemTaskBinding;
        }

        public void bindData(ObservableTask observableTask) {
            mItemTaskBinding.setViewModel(observableTask);
        }
    }
}
