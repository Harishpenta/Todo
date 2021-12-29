package com.curious.todo.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getNoteDAO(): TaskDAO
}