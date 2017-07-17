package com.example.tmd.task_mvvm.Task.data;

import com.example.tmd.task_mvvm.ViewModel.Task;
import java.util.List;

/**
 * Created by tmd on 07/07/2017.
 */

public interface TaskDataSource {

    void getAllTask(Callbacks<Task> callback);

    void addTask(Task task, Callback<Boolean> callback);

    void editTask(Task task, Callback<Boolean> callback);

    void deleteTask(Task task, Callback<Boolean> callback);

    interface Callback<T> {
        void onSuccessfull(T data);

        void onFailed(String msg);
    }

    interface Callbacks<T> {
        void onSuccessfull(List<T> data);

        void onFailed(String msg);
    }
}
