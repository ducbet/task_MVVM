package com.example.tmd.task_mvvm.ListTaskActivity;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tmd.task_mvvm.R;
import com.example.tmd.task_mvvm.ViewModel.ObservableTask;
import com.example.tmd.task_mvvm.Task.data.TaskRepository;
import com.example.tmd.task_mvvm.Task.data.local.TaskLocalDataSource;
import com.example.tmd.task_mvvm.Task.data.remote.TaskRemoteDataSource;
import com.example.tmd.task_mvvm.databinding.ActivityMainBinding;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private TaskAdapter mAdapter = new TaskAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setActivity(this);
        mPresenter = new TaskPresenter(this,
                new TaskRepository(new TaskLocalDataSource(this), new TaskRemoteDataSource(this)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                onShowAddTaskInputDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(TaskPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onShowAddTaskInputDialog() {
        final EditText editText = new EditText(this);
        editText.setHint("Title");
        new AlertDialog.Builder(this).setTitle("New task")
                .setView(editText)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ObservableTask observableTask =
                                new ObservableTask(editText.getText().toString());
                        mPresenter.addTask(observableTask);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    @Override
    public void onShowEditTaskInputDialog(final ObservableTask observableTask) {
        final EditText editText = new EditText(this);
        editText.setHint("Title");
        new AlertDialog.Builder(this).setTitle("New observableTask")
                .setView(editText)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        observableTask.setTitle(editText.getText().toString());
                        mPresenter.editTask(observableTask);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    @Override
    public void onChangeCheckBox(ObservableTask observableTask) {
        observableTask.setFinished(!observableTask.isFinished().get());
        mPresenter.editTask(observableTask);
    }

    @Override
    public void onShowDeleteTaskConfirmDialog(final ObservableTask observableTask) {
        new AlertDialog.Builder(this).setTitle("New observableTask")
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteTask(observableTask);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    @Override
    public void onAddTaskSuccess(ObservableTask observableTask) {
        mAdapter.updateData(observableTask);
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetAllTaskSuccess(List<ObservableTask> listObservableTasks) {
        mAdapter.updateData(listObservableTasks);
    }

    @Override
    public void onEditTaskSuccess(ObservableTask observableTask) {
        mAdapter.updateData(observableTask);
    }

    @Override
    public void onDeleteTaskSuccess(ObservableTask observableTask) {
        mAdapter.deleteTask(observableTask);
    }

    public TaskAdapter getAdapter() {
        return mAdapter;
    }
}
