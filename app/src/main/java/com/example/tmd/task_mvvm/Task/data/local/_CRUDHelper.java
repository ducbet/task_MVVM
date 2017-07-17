package com.example.tmd.task_mvvm.Task.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.tmd.task_mvvm.ViewModel.ObservableTask;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 09/07/2017.
 */

public class _CRUDHelper extends DatabaseHelper {
    public _CRUDHelper(Context context) {
        super(context);
    }

    public List<ObservableTask> getAllTask() {
        List<ObservableTask> mList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor =
                database.query(TaskContractDatabase.TaskDatabaseEntry.TABLE_NAME, new String[] {
                        TaskContractDatabase.TaskDatabaseEntry._ID,
                        TaskContractDatabase.TaskDatabaseEntry.COLUMN_TITLE,
                        TaskContractDatabase.TaskDatabaseEntry.COLUMN_FINISHED
                }, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(
                        cursor.getColumnIndex(TaskContractDatabase.TaskDatabaseEntry._ID));
                String title = cursor.getString(
                        cursor.getColumnIndex(TaskContractDatabase.TaskDatabaseEntry.COLUMN_TITLE));
                boolean isFinished = cursor.getInt(cursor.getColumnIndex(
                        TaskContractDatabase.TaskDatabaseEntry.COLUMN_FINISHED)) != 0;
                mList.add(new ObservableTask(id, isFinished, title));
            }
        }
        cursor.close();
        database.close();
        return mList;
    }

    public boolean insertTask(ObservableTask observableTask) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContractDatabase.TaskDatabaseEntry.COLUMN_TITLE, observableTask.getTitle().get());
        values.put(TaskContractDatabase.TaskDatabaseEntry.COLUMN_FINISHED, observableTask.isFinished().get());
        long result =
                database.insert(TaskContractDatabase.TaskDatabaseEntry.TABLE_NAME, null, values);
        if (result >= 0) {
            observableTask.setId((int) result);
        }
        database.close();
        return result >= 1;// id bat dau tu 1
    }

    public boolean editTask(ObservableTask observableTask) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContractDatabase.TaskDatabaseEntry.COLUMN_TITLE, observableTask.getTitle().get());
        values.put(TaskContractDatabase.TaskDatabaseEntry.COLUMN_FINISHED, observableTask.isFinished().get());
        int result = database.update(TaskContractDatabase.TaskDatabaseEntry.TABLE_NAME, values,
                TaskContractDatabase.TaskDatabaseEntry._ID + " = " + observableTask.getId(), null);
        return result > 0; // result la so row bi thay doi
    }

    public boolean deleteTask(ObservableTask observableTask) {
        SQLiteDatabase database = getWritableDatabase();
        int result = database.delete(TaskContractDatabase.TaskDatabaseEntry.TABLE_NAME,
                TaskContractDatabase.TaskDatabaseEntry._ID + " = " + observableTask.getId(), null);
        return result > 0; // result la so row bi delete
    }
}
