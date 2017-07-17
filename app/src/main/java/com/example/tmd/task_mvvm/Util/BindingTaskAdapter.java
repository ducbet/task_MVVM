package com.example.tmd.task_mvvm.Util;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.tmd.task_mvvm.ListTaskActivity.TaskAdapter;
import com.example.tmd.task_mvvm.ViewModel.ObservableTask;

/**
 * Created by tmd on 13/07/2017.
 */

public class BindingTaskAdapter {

    @BindingAdapter("layoutManager")
    public static RecyclerView.LayoutManager setLayoutManager(RecyclerView recyclerView, String orientation) {
        if (orientation.equals("vertical")) {
            return new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL,
                    false);
        } else {
            return new LinearLayoutManager(recyclerView.getContext(),
                    LinearLayoutManager.HORIZONTAL, false);
        }
    }

    @BindingAdapter("listTasks")
    public static void setListTasks(RecyclerView recyclerView, ObservableList<ObservableTask> mList) {
        TaskAdapter adapter =
                (TaskAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            recyclerView.setAdapter(new TaskAdapter());
        }
        adapter.updateData(mList);
        adapter.notifyDataSetChanged();
    }
}
