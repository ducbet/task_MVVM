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
import com.example.tmd.task_mvvm.R;
import com.example.tmd.task_mvvm.Task.data.TaskRepository;
import com.example.tmd.task_mvvm.Task.data.local.TaskLocalDataSource;
import com.example.tmd.task_mvvm.Task.data.remote.TaskRemoteDataSource;
import com.example.tmd.task_mvvm.ViewModel.MainViewModel;
import com.example.tmd.task_mvvm.ViewModel.Task;
import com.example.tmd.task_mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MY_TAG";
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainViewModel = new MainViewModel(this,
                new TaskRepository(new TaskLocalDataSource(this), new TaskRemoteDataSource(this)));
        mainBinding.setViewModel(mMainViewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    public void onShowAddTaskInputDialog() {
        final EditText editText = new EditText(this);
        editText.setHint("Title");
        new AlertDialog.Builder(this).setTitle("New task")
                .setView(editText)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mMainViewModel.addTask(new Task(editText.getText().toString()));
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}
