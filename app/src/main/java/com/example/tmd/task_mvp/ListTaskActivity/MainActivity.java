package com.example.tmd.task_mvp.ListTaskActivity;

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
import com.example.tmd.task_mvp.R;
import com.example.tmd.task_mvp.Task.Model.Task;
import com.example.tmd.task_mvp.Task.data.TaskRepository;
import com.example.tmd.task_mvp.Task.data.local.TaskLocalDataSource;
import com.example.tmd.task_mvp.Task.data.remote.TaskRemoteDataSource;
import com.example.tmd.task_mvp.databinding.ActivityMainBinding;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private TaskAdapter mAdapter = new TaskAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TaskPresenter(this,
                new TaskRepository(new TaskLocalDataSource(this), new TaskRemoteDataSource(this)));
        ActivityMainBinding mainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setActivity(this);
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
                        Task task = new Task(editText.getText().toString());
                        mPresenter.addTask(task);
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
    public void onShowEditTaskInputDialog(final Task task) {
        final EditText editText = new EditText(this);
        editText.setHint("Title");
        new AlertDialog.Builder(this).setTitle("New task")
                .setView(editText)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        task.setTitle(editText.getText().toString());
                        mPresenter.editTask(task);
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
    public void onChangeCheckBox(Task task) {
        task.setFinished(!task.isFinished());
        mPresenter.editTask(task);
    }

    @Override
    public void onShowDeleteTaskConfirmDialog(final Task task) {
        new AlertDialog.Builder(this).setTitle("New task")
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteTask(task);
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
    public void onAddTaskSuccess(Task task) {
        mAdapter.updateData(task);
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetAllTaskSuccess(List<Task> listTasks) {
        mAdapter.updateData(listTasks);
    }

    @Override
    public void onEditTaskSuccess(Task task) {
        mAdapter.updateData(task);
    }

    @Override
    public void onDeleteTaskSuccess(Task task) {
        mAdapter.deleteTask(task);
    }

    public TaskAdapter getAdapter() {
        return mAdapter;
    }
}
