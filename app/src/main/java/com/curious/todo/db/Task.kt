package com.curious.todo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    var title: String,
    var description: String,
    var createdDate: Long,
    var type: String,
    var isCompleted: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}