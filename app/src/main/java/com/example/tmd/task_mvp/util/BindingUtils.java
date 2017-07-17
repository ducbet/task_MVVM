package com.example.tmd.task_mvp.util;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.tmd.task_mvp.ListTaskActivity.MainContract;
import com.example.tmd.task_mvp.ListTaskActivity.TaskAdapter;
import com.example.tmd.task_mvp.Task.Model.Task;

/**
 * Created by tmd on 13/07/2017.
 */

public class BindingUtils {
    private static final String TAG = "MY_TAG";

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
            LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        Log.d(TAG, "setAdapter: ");
        recyclerView.setAdapter(adapter);
    }
}
