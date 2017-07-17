package com.example.tmd.task_mvvm.ListTaskActivity;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.tmd.task_mvvm.R;
import com.example.tmd.task_mvvm.Task.data.TaskDataSource;
import com.example.tmd.task_mvvm.ViewModel.TaskViewModel;
import com.example.tmd.task_mvvm.databinding.ItemTaskBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 09/07/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<TaskViewModel> mList = new ArrayList<>();

    public void updateData(List<TaskViewModel> list) {
        Log.d("MY_TAG", "notifyDataSetChanged: ");
        if (list == null) return;
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void updateData(TaskViewModel taskViewModel) {
        Log.d("MY_TAG", "updateData: ");
        int index;
        if (mList.contains(taskViewModel)) {
            index = mList.indexOf(taskViewModel);
            mList.set(index, taskViewModel);
        } else {
            mList.add(taskViewModel);
            index = mList.size() - 1;
        }
        notifyItemChanged(index);
    }

    public void deleteTask(TaskViewModel taskViewModel) {
        int index = mList.indexOf(taskViewModel);
        if (index >= 0) {
            mList.remove(index);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTaskBinding itemTaskBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_task, parent, false);
        return new ViewHolder(itemTaskBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskViewModel taskViewModel = mList.get(position);
        holder.bindData(taskViewModel);
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

        public void bindData(TaskViewModel taskViewModel) {
            mItemTaskBinding.setViewModel(taskViewModel);
        }
    }
}
