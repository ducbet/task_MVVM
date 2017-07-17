package com.example.tmd.task_mvp.ListTaskActivity;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.tmd.task_mvp.R;
import com.example.tmd.task_mvp.Task.Model.Task;
import com.example.tmd.task_mvp.databinding.ItemTaskBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 09/07/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> mList = new ArrayList<>();
    private MainContract.View mView;

    public TaskAdapter() {
    }

    public void updateData(List<Task> list) {
        if (list == null) return;
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void updateData(Task task) {
        int index;
        if (mList.contains(task)) {
            index = mList.indexOf(task);
            mList.set(index, task);
        } else {
            mList.add(task);
            index = mList.size() - 1;
        }
        notifyItemChanged(index);
    }

    public void deleteTask(Task task) {
        int index = mList.indexOf(task);
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
        Task task = mList.get(position);
        holder.bindData(task);
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
            mItemTaskBinding.setActivity((MainActivity) itemTaskBinding.getRoot().getContext());
        }

        public void bindData(Task task) {
            mItemTaskBinding.setTask(task);
        }
    }
}
