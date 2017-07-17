package com.example.tmd.task_mvvm.ViewModel;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tmd.task_mvvm.Task.data.TaskDataSource;

/**
 * Created by tmd on 17/07/2017.
 */

public class TaskViewModel {
    private static final String TAG = "MY_TAG";

    private Context mContext;
    private TaskDataSource mTaskDataSource;
    private Task mTask;
    private TaskDataSource.Callback<TaskViewModel> mCallback;

    public TaskViewModel(Context context, Task task, TaskDataSource taskDataSource,
            TaskDataSource.Callback<TaskViewModel> callback) {
        mContext = context;
        mTask = task;
        mTaskDataSource = taskDataSource;
        mCallback = callback;
    }

    public Task getTask() {
        return mTask;
    }

    public void setTask(Task task) {
        mTask = task;
    }

    public void onShowEditTaskInputDialog() {
        final EditText editText = new EditText(mContext);
        editText.setHint("Title");
        new AlertDialog.Builder(mContext).setTitle("New observableTask")
                .setView(editText)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTask.setTitle(editText.getText().toString());
                        editTask(mTask);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    public void onChangeCheckBox() {
        mTask.setFinished(!mTask.isFinished().get());
        editTask(mTask);
    }

    public void editTask(final Task task) {
        mTaskDataSource.editTask(task, new TaskDataSource.Callback<Boolean>() {
            @Override
            public void onSuccessfull(Boolean data) {

            }

            @Override
            public void onFailed(String msg) {
                onShowMsgFailed(msg);
            }
        });
    }

    public void onShowDeleteTaskConfirmDialog() {
        new AlertDialog.Builder(mContext).setTitle("New observableTask")
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallback.onSuccessfull(TaskViewModel.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    public void onShowMsgFailed(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}
