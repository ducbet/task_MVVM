package com.example.tmd.task_mvvm.Task.data.local;

import android.content.Context;
import com.example.tmd.task_mvvm.ViewModel.ObservableTask;
import com.example.tmd.task_mvvm.Task.data.TaskDataSource;

/**
 * Created by tmd on 07/07/2017.
 */

public class TaskLocalDataSource implements TaskDataSource {

    private Context mContext;
    private _CRUDHelper mCRUDHelper;

    public TaskLocalDataSource(Context context) {
        mContext = context;
        mCRUDHelper = new _CRUDHelper(context);
    }

    @Override
    public void getAllTask(Callbacks<ObservableTask> callback) {
        callback.onSuccessfull(mCRUDHelper.getAllTask());
    }

    @Override
    public void addTask(ObservableTask observableTask, TaskDataSource.Callback<Boolean> callback) {
        if (mCRUDHelper.insertTask(observableTask)) {
            callback.onSuccessfull(Boolean.TRUE);
        } else {
            callback.onFailed("ADD TASK FAILED");
        }
    }

    @Override
    public void editTask(ObservableTask observableTask, Callback<Boolean> callback) {
        if (mCRUDHelper.editTask(observableTask)) {
            callback.onSuccessfull(Boolean.TRUE);
        } else {
            callback.onFailed("EDIT TASK FAILED");
        }
    }

    @Override
    public void deleteTask(ObservableTask observableTask, Callback<Boolean> callback) {
        if (mCRUDHelper.deleteTask(observableTask)) {
            callback.onSuccessfull(Boolean.TRUE);
        } else {
            callback.onFailed("EDIT TASK FAILED");
        }
    }
}
