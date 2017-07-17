package com.example.tmd.task_mvvm.Task.data;

import com.example.tmd.task_mvvm.ViewModel.ObservableTask;
import java.util.List;

/**
 * Created by tmd on 07/07/2017.
 */

public interface TaskDataSource {

    void getAllTask(Callbacks<ObservableTask> callback);

    void addTask(ObservableTask observableTask, Callback<Boolean> callback);

    void editTask(ObservableTask observableTask, Callback<Boolean> callback);

    void deleteTask(ObservableTask observableTask, Callback<Boolean> callback);

    interface Callback<T> {
        void onSuccessfull(T data);

        void onFailed(String msg);
    }

    interface Callbacks<T> {
        void onSuccessfull(List<T> data);

        void onFailed(String msg);
    }
}
