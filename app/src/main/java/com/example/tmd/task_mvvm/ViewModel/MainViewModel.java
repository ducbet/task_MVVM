package com.example.tmd.task_mvvm.ViewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.tmd.task_mvvm.ListTaskActivity.TaskAdapter;
import com.example.tmd.task_mvvm.Task.data.TaskDataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 17/07/2017.
 */

public class MainViewModel {
    private static final String TAG = "MY_TAG";
    private TaskDataSource mTaskDataSource;
    private Context mContext;
    private TaskAdapter mAdapter;
    TaskDataSource.Callback<TaskViewModel> mCallback;

    public MainViewModel(Context context, TaskDataSource taskDataSource) {
        mContext = context;
        mTaskDataSource = taskDataSource;
        createCallback();
        mAdapter = new TaskAdapter();
        start();
    }

    private void createCallback() {
        mCallback = new TaskDataSource.Callback<TaskViewModel>() {
            @Override
            public void onSuccessfull(TaskViewModel data) {
                deleteTask(data);
            }

            @Override
            public void onFailed(String msg) {

            }
        };
    }

    public void start() {
        // get task from database
        mTaskDataSource.getAllTask(new TaskDataSource.Callbacks<Task>() {
            @Override
            public void onSuccessfull(List<Task> data) {
                onGetAllTaskSuccess(data);
            }

            @Override
            public void onFailed(String msg) {
                onShowMsgFailed(msg);
            }
        });
    }

    public void addTask(final Task task) {
        mTaskDataSource.addTask(task, new TaskDataSource.Callback<Boolean>() {
            @Override
            public void onSuccessfull(Boolean data) {
                onAddTaskSuccess(task);
            }

            @Override
            public void onFailed(String msg) {
                onShowMsgFailed(msg);
            }
        });
    }

    public void deleteTask(final TaskViewModel taskViewModel) {
        mTaskDataSource.deleteTask(taskViewModel.getTask(), new TaskDataSource.Callback<Boolean>() {
            @Override
            public void onSuccessfull(Boolean data) {
                onDeleteTaskSuccess(taskViewModel);
            }

            @Override
            public void onFailed(String msg) {
                onShowMsgFailed(msg);
            }
        });
    }

    public void onShowMsgFailed(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    public void onAddTaskSuccess(Task task) {
        mAdapter.updateData(new TaskViewModel(mContext, task, mTaskDataSource, mCallback));
    }

    public void onGetAllTaskSuccess(List<Task> listTasks) {
        List<TaskViewModel> listTaskViewModel = new ArrayList<>();
        for (int i = 0; i < listTasks.size(); i++) {
            listTaskViewModel.add(
                    new TaskViewModel(mContext, listTasks.get(i), mTaskDataSource, mCallback));
        }
        mAdapter.updateData(listTaskViewModel);
    }

    public void onDeleteTaskSuccess(TaskViewModel taskViewModel) {
        mAdapter.deleteTask(taskViewModel);
    }

    public TaskAdapter getAdapter() {
        return mAdapter;
    }
}
