package com.example.tmd.task_mvvm.ListTaskActivity;

import com.example.tmd.task_mvvm.ViewModel.ObservableTask;
import com.example.tmd.task_mvvm.Task.data.TaskDataSource;
import com.example.tmd.task_mvvm.Task.data.TaskRepository;
import java.util.List;

/**
 * Created by tmd on 07/07/2017.
 */
public class TaskPresenter implements MainContract.Presenter {

    private MainContract.View mView;// view
    private TaskDataSource mTaskDataSource;// model

    public TaskPresenter(MainContract.View view, TaskRepository taskRepository) {
        mView = view;
        mView.setPresenter(this);
        mTaskDataSource = taskRepository;
    }

    @Override
    public void start() {
        // get task from database
        mTaskDataSource.getAllTask(new TaskDataSource.Callbacks<ObservableTask>() {
            @Override
            public void onSuccessfull(List<ObservableTask> data) {
                mView.onGetAllTaskSuccess(data);
            }

            @Override
            public void onFailed(String msg) {
                mView.onFailed(msg);
            }
        });
    }

    @Override
    public void addTask(final ObservableTask observableTask) {
        mTaskDataSource.addTask(observableTask, new TaskDataSource.Callback<Boolean>() {
            @Override
            public void onSuccessfull(Boolean data) {
                mView.onAddTaskSuccess(observableTask);
            }

            @Override
            public void onFailed(String msg) {
                mView.onFailed(msg);
            }
        });
    }

    @Override
    public void editTask(final ObservableTask observableTask) {
        mTaskDataSource.editTask(observableTask, new TaskDataSource.Callback<Boolean>() {
            @Override
            public void onSuccessfull(Boolean data) {
                mView.onEditTaskSuccess(observableTask);
            }

            @Override
            public void onFailed(String msg) {
                mView.onFailed(msg);
            }
        });
    }

    @Override
    public void deleteTask(final ObservableTask observableTask) {
        mTaskDataSource.deleteTask(observableTask, new TaskDataSource.Callback<Boolean>() {
            @Override
            public void onSuccessfull(Boolean data) {
                mView.onDeleteTaskSuccess(observableTask);
            }

            @Override
            public void onFailed(String msg) {
                mView.onFailed(msg);
            }
        });
    }
}
