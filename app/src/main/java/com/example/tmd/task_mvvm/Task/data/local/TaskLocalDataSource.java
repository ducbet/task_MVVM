package com.example.tmd.task_mvvm.Task.data.local;

import android.content.Context;
import com.example.tmd.task_mvvm.ViewModel.Task;
import com.example.tmd.task_mvvm.Task.data.TaskDataSource;

/**
 * Created by tmd on 07/07/2017.
 */

public class TaskLocalDataSource implements TaskDataSource {

    private _CRUDHelper mCRUDHelper;

    public TaskLocalDataSource(Context context) {
        mCRUDHelper = new _CRUDHelper(context);
    }

    @Override
    public void getAllTask(Callbacks<Task> callback) {
        callback.onSuccessfull(mCRUDHelper.getAllTask());
    }

    @Override
    public void addTask(Task task, TaskDataSource.Callback<Boolean> callback) {
        if (mCRUDHelper.insertTask(task)) {
            callback.onSuccessfull(Boolean.TRUE);
        } else {
            callback.onFailed("ADD TASK FAILED");
        }
    }

    @Override
    public void editTask(Task task, Callback<Boolean> callback) {
        if (mCRUDHelper.editTask(task)) {
            callback.onSuccessfull(Boolean.TRUE);
        } else {
            callback.onFailed("EDIT TASK FAILED");
        }
    }

    @Override
    public void deleteTask(Task task, Callback<Boolean> callback) {
        if (mCRUDHelper.deleteTask(task)) {
            callback.onSuccessfull(Boolean.TRUE);
        } else {
            callback.onFailed("EDIT TASK FAILED");
        }
    }
}
