package com.example.tmd.task_mvvm.Task.data.local;

import android.provider.BaseColumns;

/**
 * Created by tmd on 09/07/2017.
 */

public class TaskContractDatabase {
    public class TaskDatabaseEntry implements BaseColumns{
        public static final String TABLE_NAME = "Task";
        public static final String COLUMN_TITLE = "Title";
        public static final String COLUMN_FINISHED = "Finished";
    }
}
