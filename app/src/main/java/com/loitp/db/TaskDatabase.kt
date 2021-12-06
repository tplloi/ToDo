package com.loitp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.loitp.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        var instance: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase? {
            if (instance == null) {
                synchronized(TaskDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        TaskDatabase::class.java.simpleName
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return instance
        }
    }
}
