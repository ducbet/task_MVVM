package com.example.tmd.task_mvvm.Task.data.remote;

import android.content.Context;
import com.example.tmd.task_mvvm.ViewModel.ObservableTask;
import com.example.tmd.task_mvvm.Task.data.TaskDataSource;

/**
 * Created by tmd on 07/07/2017.
 */

public class TaskRemoteDataSource implements TaskDataSource {

    private Context mContext;

    public TaskRemoteDataSource(Context context) {
        mContext = context;
    }

    @Override
    public void getAllTask(Callbacks<ObservableTask> callback) {

    }

    @Override
    public void addTask(ObservableTask observableTask, Callback<Boolean> callback) {

    }

    @Override
    public void editTask(ObservableTask observableTask, Callback<Boolean> callback) {

    }

    @Override
    public void deleteTask(ObservableTask observableTask, Callback<Boolean> callback) {

    }
}
