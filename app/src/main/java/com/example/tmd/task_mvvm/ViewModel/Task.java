package com.example.tmd.task_mvvm.ViewModel;

import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;
import com.example.tmd.task_mvvm.Task.data.TaskDataSource;

/**
 * Created by tmd on 07/07/2017.
 */
public class Task  {

    private int mId;
    private ObservableBoolean mIsFinished = new ObservableBoolean();
    private ObservableField<String> mTitle = new ObservableField<>();

    public Task() {
    }

    public Task(int id, boolean isFinished, String title) {
        mId = id;
        mIsFinished.set(isFinished);
        mTitle.set(title);
    }

    public Task(String title) {
        mTitle.set(title);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public ObservableField<String> getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle.set(title);
    }

    public ObservableBoolean isFinished() {
        return mIsFinished;
    }

    public void setFinished(boolean finished) {
        mIsFinished.set(finished);
    }


}
