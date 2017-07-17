package com.example.tmd.task_mvvm.Task.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.tmd.task_mvvm.ViewModel.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 09/07/2017.
 */

public class _CRUDHelper extends DatabaseHelper {
    public _CRUDHelper(Context context) {
        super(context);
    }

    public List<Task> getAllTask() {
        List<Task> mList = new ArrayList<>();
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
                mList.add(new Task(id, isFinished, title));
            }
        }
        cursor.close();
        database.close();
        return mList;
    }

    public boolean insertTask(Task task) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContractDatabase.TaskDatabaseEntry.COLUMN_TITLE, task.getTitle().get());
        values.put(TaskContractDatabase.TaskDatabaseEntry.COLUMN_FINISHED, task.isFinished().get());
        long result =
                database.insert(TaskContractDatabase.TaskDatabaseEntry.TABLE_NAME, null, values);
        if (result >= 0) {
            task.setId((int) result);
        }
        database.close();
        return result >= 1;// id bat dau tu 1
    }

    public boolean editTask(Task task) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContractDatabase.TaskDatabaseEntry.COLUMN_TITLE, task.getTitle().get());
        values.put(TaskContractDatabase.TaskDatabaseEntry.COLUMN_FINISHED, task.isFinished().get());
        int result = database.update(TaskContractDatabase.TaskDatabaseEntry.TABLE_NAME, values,
                TaskContractDatabase.TaskDatabaseEntry._ID + " = " + task.getId(), null);
        return result > 0; // result la so row bi thay doi
    }

    public boolean deleteTask(Task task) {
        SQLiteDatabase database = getWritableDatabase();
        int result = database.delete(TaskContractDatabase.TaskDatabaseEntry.TABLE_NAME,
                TaskContractDatabase.TaskDatabaseEntry._ID + " = " + task.getId(), null);
        return result > 0; // result la so row bi delete
    }
}
