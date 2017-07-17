package com.example.tmd.task_mvvm.ListTaskActivity;

import com.example.tmd.task_mvvm.ViewModel.ObservableTask;
import com.example.tmd.task_mvvm.Util.BasePresenter;
import com.example.tmd.task_mvvm.Util.BaseView;
import java.util.List;

/**
 * Created by tmd on 07/07/2017.
 */
public interface MainContract {
    interface View extends BaseView {
        void onShowAddTaskInputDialog();
        void onShowEditTaskInputDialog(ObservableTask observableTask);
        void onChangeCheckBox(ObservableTask observableTask);
        void onShowDeleteTaskConfirmDialog(ObservableTask observableTask);
        void onAddTaskSuccess(ObservableTask observableTask);
        void onFailed(String msg);
        void onGetAllTaskSuccess(List<ObservableTask> listObservableTasks);
        void onEditTaskSuccess(ObservableTask observableTask);
        void onDeleteTaskSuccess(ObservableTask observableTask);
    }

    interface Presenter extends BasePresenter {
        void addTask(ObservableTask observableTask);
        void editTask(ObservableTask observableTask);
        void deleteTask(ObservableTask observableTask);
    }
}
