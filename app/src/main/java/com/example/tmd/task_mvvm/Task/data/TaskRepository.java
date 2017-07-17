package com.example.tmd.task_mvvm.Task.data;

import com.example.tmd.task_mvvm.ViewModel.ObservableTask;
import com.example.tmd.task_mvvm.Task.data.local.TaskLocalDataSource;
import com.example.tmd.task_mvvm.Task.data.remote.TaskRemoteDataSource;

/**
 * Created by tmd on 07/07/2017.
 */

public class TaskRepository implements TaskDataSource {
    private TaskLocalDataSource mTaskLocalDataSource;
    private TaskRemoteDataSource mTaskRemotelDataSource;

    public TaskRepository(TaskLocalDataSource taskLocalDataSource,
            TaskRemoteDataSource taskRemotelDataSource) {
        mTaskLocalDataSource = taskLocalDataSource;
        mTaskRemotelDataSource = taskRemotelDataSource;
    }

    @Override
    public void getAllTask(Callbacks<ObservableTask> callback) {
        mTaskLocalDataSource.getAllTask(callback);
    }

    @Override
    public void addTask(ObservableTask observableTask, Callback<Boolean> callback) {
        mTaskLocalDataSource.addTask(observableTask, callback);
    }

    @Override
    public void editTask(ObservableTask observableTask, Callback<Boolean> callback) {
        mTaskLocalDataSource.editTask(observableTask, callback);
    }

    @Override
    public void deleteTask(ObservableTask observableTask, Callback<Boolean> callback) {
        mTaskLocalDataSource.deleteTask(observableTask, callback);
    }
}
