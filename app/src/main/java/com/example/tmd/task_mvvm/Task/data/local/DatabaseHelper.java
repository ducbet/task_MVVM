package com.example.tmd.task_mvvm.Task.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tmd on 09/07/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "TaskDB";
    public static final int DB_VERSION = 1;
    private static Context mContext;

    private static final String COMMAND_CREATE_TASK_TABLE = "CREATE TABLE "
            + TaskContractDatabase.TaskDatabaseEntry.TABLE_NAME
            + " ( "
            + TaskContractDatabase.TaskDatabaseEntry._ID
            + " INTEGER PRIMARY KEY NOT NULL, "
            + TaskContractDatabase.TaskDatabaseEntry.COLUMN_TITLE
            + " TEXT DEFAULT \"\", "
            + TaskContractDatabase.TaskDatabaseEntry.COLUMN_FINISHED
            + " INTEGER NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMMAND_CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
