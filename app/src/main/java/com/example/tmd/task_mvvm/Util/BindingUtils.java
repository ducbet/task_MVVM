package com.example.tmd.task_mvvm.Util;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.Observable;

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
